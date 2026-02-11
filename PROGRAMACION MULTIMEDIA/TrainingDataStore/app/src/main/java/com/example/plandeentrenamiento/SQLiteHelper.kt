package com.example.plandeentrenamiento

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.plandeentrenamiento.data.EjercicioRegistrado
import com.example.plandeentrenamiento.data.PlanEntrenamiento

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "gimnasio.db"
        private const val DATABASE_VERSION = 4

        // Tabla PlanEntrenamiento
        private const val TABLE_PLAN = "PlanEntrenamiento"
        private const val COL_PLAN_ID = "id"
        private const val COL_PLAN_NOMBRE = "nombre"
        private const val COL_PLAN_DIAS = "dias"
        private const val COL_PLAN_SEMANAS = "semanas"
        private const val COL_PLAN_ACTIVO = "activo"

        // Tabla Ejercicio (única tabla de ejercicios)
        private const val TABLE_EJERCICIO = "Ejercicio"
        private const val COL_EJ_ID = "id"
        private const val COL_EJ_PLAN_ID = "plan_id"
        private const val COL_EJ_DIA = "dia"
        private const val COL_EJ_NOMBRE = "nombre"
        private const val COL_EJ_PESO = "peso"
        private const val COL_EJ_REPES = "repes"

        private const val SQL_CREATE_PLAN = """
            CREATE TABLE $TABLE_PLAN (
                $COL_PLAN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_PLAN_NOMBRE TEXT NOT NULL,
                $COL_PLAN_DIAS INTEGER NOT NULL,
                $COL_PLAN_SEMANAS INTEGER NOT NULL,
                $COL_PLAN_ACTIVO INTEGER NOT NULL
            )
        """

        private const val SQL_CREATE_EJERCICIO = """
            CREATE TABLE $TABLE_EJERCICIO (
                $COL_EJ_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_EJ_PLAN_ID INTEGER NOT NULL,
                $COL_EJ_DIA INTEGER NOT NULL,
                $COL_EJ_NOMBRE TEXT NOT NULL,
                $COL_EJ_PESO REAL,
                $COL_EJ_REPES INTEGER,
                FOREIGN KEY($COL_EJ_PLAN_ID) 
                    REFERENCES $TABLE_PLAN($COL_PLAN_ID) 
                    ON DELETE CASCADE
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_PLAN)
        db.execSQL(SQL_CREATE_EJERCICIO)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EJERCICIO")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PLAN")
        onCreate(db)
    }

    // ==================== MÉTODOS PARA PLANES ====================

    fun insertPlan(plan: PlanEntrenamiento): Long {
        val db = writableDatabase

        val values = ContentValues().apply {
            put(COL_PLAN_NOMBRE, plan.nombre.trim())
            put(COL_PLAN_DIAS, plan.dias)
            put(COL_PLAN_SEMANAS, plan.semanas)
            put(COL_PLAN_ACTIVO, if (plan.activo) 1 else 0)
        }

        val id = db.insert(TABLE_PLAN, null, values)
        db.close()
        return id
    }

    fun getAllPlanes(): List<PlanEntrenamiento> {
        val db = readableDatabase
        val list = mutableListOf<PlanEntrenamiento>()

        val cursor = db.query(
            TABLE_PLAN,
            arrayOf(
                COL_PLAN_ID,
                COL_PLAN_NOMBRE,
                COL_PLAN_DIAS,
                COL_PLAN_SEMANAS,
                COL_PLAN_ACTIVO
            ),
            null, null, null, null,
            "$COL_PLAN_ID DESC"
        )

        cursor.use { c ->
            while (c.moveToNext()) {
                list.add(
                    PlanEntrenamiento(
                        id = c.getLong(c.getColumnIndexOrThrow(COL_PLAN_ID)),
                        nombre = c.getString(c.getColumnIndexOrThrow(COL_PLAN_NOMBRE)),
                        dias = c.getInt(c.getColumnIndexOrThrow(COL_PLAN_DIAS)),
                        semanas = c.getInt(c.getColumnIndexOrThrow(COL_PLAN_SEMANAS)),
                        activo = c.getInt(c.getColumnIndexOrThrow(COL_PLAN_ACTIVO)) == 1
                    )
                )
            }
        }

        db.close()
        return list
    }

    // NUEVO MÉTODO: Actualizar estado activo/inactivo de un plan
    fun updatePlanActivo(planId: Long, activo: Boolean): Boolean {
        val db = writableDatabase

        val values = ContentValues().apply {
            put(COL_PLAN_ACTIVO, if (activo) 1 else 0)
        }

        val rowsAffected = db.update(
            TABLE_PLAN,
            values,
            "$COL_PLAN_ID = ?",
            arrayOf(planId.toString())
        )

        db.close()
        return rowsAffected > 0
    }

    // NUEVO MÉTODO: Eliminar un plan
    fun deletePlan(planId: Long): Boolean {
        val db = writableDatabase

        // Al tener ON DELETE CASCADE, los ejercicios se eliminan automáticamente
        val rowsDeleted = db.delete(
            TABLE_PLAN,
            "$COL_PLAN_ID = ?",
            arrayOf(planId.toString())
        )

        db.close()
        return rowsDeleted > 0
    }

    // ==================== MÉTODOS PARA EJERCICIOS ====================

    fun insertEjercicio(ejercicio: EjercicioRegistrado): Long {
        val db = writableDatabase

        val values = ContentValues().apply {
            put(COL_EJ_PLAN_ID, ejercicio.planId)
            put(COL_EJ_DIA, ejercicio.dia)
            put(COL_EJ_NOMBRE, ejercicio.nombre)
            put(COL_EJ_PESO, ejercicio.peso)
            put(COL_EJ_REPES, ejercicio.repes)
        }

        val id = db.insert(TABLE_EJERCICIO, null, values)
        db.close()
        return id
    }

    fun getEjerciciosByPlanAndDay(planId: Long, dia: Int): List<EjercicioRegistrado> {
        val db = readableDatabase
        val list = mutableListOf<EjercicioRegistrado>()

        val cursor = db.query(
            TABLE_EJERCICIO,
            arrayOf(
                COL_EJ_ID,
                COL_EJ_PLAN_ID,
                COL_EJ_DIA,
                COL_EJ_NOMBRE,
                COL_EJ_PESO,
                COL_EJ_REPES
            ),
            "$COL_EJ_PLAN_ID = ? AND $COL_EJ_DIA = ?",
            arrayOf(planId.toString(), dia.toString()),
            null, null,
            "$COL_EJ_ID ASC"
        )

        cursor.use { c ->
            while (c.moveToNext()) {
                list.add(
                    EjercicioRegistrado(
                        id = c.getLong(c.getColumnIndexOrThrow(COL_EJ_ID)),
                        planId = c.getLong(c.getColumnIndexOrThrow(COL_EJ_PLAN_ID)),
                        dia = c.getInt(c.getColumnIndexOrThrow(COL_EJ_DIA)),
                        nombre = c.getString(c.getColumnIndexOrThrow(COL_EJ_NOMBRE)),
                        peso = c.getDouble(c.getColumnIndexOrThrow(COL_EJ_PESO)),
                        repes = c.getInt(c.getColumnIndexOrThrow(COL_EJ_REPES))
                    )
                )
            }
        }

        db.close()
        return list
    }

    fun getEjerciciosByPlan(planId: Long): List<EjercicioRegistrado> {
        val db = readableDatabase
        val list = mutableListOf<EjercicioRegistrado>()

        val cursor = db.query(
            TABLE_EJERCICIO,
            arrayOf(
                COL_EJ_ID,
                COL_EJ_PLAN_ID,
                COL_EJ_DIA,
                COL_EJ_NOMBRE,
                COL_EJ_PESO,
                COL_EJ_REPES
            ),
            "$COL_EJ_PLAN_ID = ?",
            arrayOf(planId.toString()),
            null, null,
            "$COL_EJ_DIA ASC, $COL_EJ_ID ASC"
        )

        cursor.use { c ->
            while (c.moveToNext()) {
                list.add(
                    EjercicioRegistrado(
                        id = c.getLong(c.getColumnIndexOrThrow(COL_EJ_ID)),
                        planId = c.getLong(c.getColumnIndexOrThrow(COL_EJ_PLAN_ID)),
                        dia = c.getInt(c.getColumnIndexOrThrow(COL_EJ_DIA)),
                        nombre = c.getString(c.getColumnIndexOrThrow(COL_EJ_NOMBRE)),
                        peso = c.getDouble(c.getColumnIndexOrThrow(COL_EJ_PESO)),
                        repes = c.getInt(c.getColumnIndexOrThrow(COL_EJ_REPES))
                    )
                )
            }
        }

        db.close()
        return list
    }
}
