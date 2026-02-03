package com.example.plandeentrenamiento

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.plandeentrenamiento.Ejercicio
import com.example.plandeentrenamiento.PlanEntrenamiento

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "gimnasio.db"
        private const val DATABASE_VERSION = 1

        // --- Plan ---
        private const val TABLE_PLAN = "PlanEntrenamiento"
        private const val COL_PLAN_ID = "id"
        private const val COL_PLAN_NOMBRE = "nombre"
        private const val COL_PLAN_DIAS = "dias"
        private const val COL_PLAN_ACTIVO = "activo"

        // --- Ejercicio ---
        private const val TABLE_EJERCICIO = "Ejercicio"
        private const val COL_EJ_ID = "id"
        private const val COL_EJ_PLAN_ID = "plan_id"
        private const val COL_EJ_NOMBRE = "nombre"
        private const val COL_EJ_FECHA = "fecha"
        private const val COL_EJ_PESO = "peso"
        private const val COL_EJ_REPES = "repes"
        private const val COL_EJ_TIPO = "tipo"

        private const val SQL_CREATE_PLAN = """
            CREATE TABLE $TABLE_PLAN (
                $COL_PLAN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_PLAN_NOMBRE TEXT NOT NULL,
                $COL_PLAN_DIAS INTEGER NOT NULL,
                $COL_PLAN_ACTIVO INTEGER NOT NULL
            )
        """

        private const val SQL_CREATE_EJERCICIO = """
            CREATE TABLE $TABLE_EJERCICIO (
                $COL_EJ_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_EJ_PLAN_ID INTEGER NOT NULL,
                $COL_EJ_NOMBRE TEXT NOT NULL,
                $COL_EJ_FECHA TEXT NOT NULL,
                $COL_EJ_PESO REAL,
                $COL_EJ_REPES INTEGER,
                $COL_EJ_TIPO TEXT,
                FOREIGN KEY($COL_EJ_PLAN_ID) REFERENCES $TABLE_PLAN($COL_PLAN_ID) ON DELETE CASCADE
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

    // ------------------ CRUD Plan ------------------
    fun insertPlan(plan: PlanEntrenamiento): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_PLAN_NOMBRE, plan.nombre.trim())
            put(COL_PLAN_DIAS, plan.dias)
            put("activo", if (plan.activo) 1 else 0)
        }
        val id = db.insert(TABLE_PLAN, null, values)
        db.close()
        return id
    }


    fun getAllPlanes(): List<PlanEntrenamiento> {
        val db = readableDatabase
        val list = mutableListOf<PlanEntrenamiento>()
        val cursor = db.query(TABLE_PLAN,
            arrayOf(COL_PLAN_ID, COL_PLAN_NOMBRE, COL_PLAN_DIAS),
            null, null, null, null, "$COL_PLAN_ID DESC"
        )

        cursor.use { c ->
            while (c.moveToNext()) {
                list.add(
                    PlanEntrenamiento(
                        id = c.getLong(c.getColumnIndexOrThrow(COL_PLAN_ID)),
                        nombre = c.getString(c.getColumnIndexOrThrow(COL_PLAN_NOMBRE)),
                        dias = c.getInt(c.getColumnIndexOrThrow(COL_PLAN_DIAS)),
                        activo = c.getInt(c.getColumnIndexOrThrow("activo")) == 1
                    )
                )
            }
        }
        db.close()
        return list
    }

    // ------------------ CRUD Ejercicio ------------------
    fun insertEjercicio(ejercicio: Ejercicio): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_EJ_PLAN_ID, ejercicio.planId)
            put(COL_EJ_NOMBRE, ejercicio.nombre.trim())
            put(COL_EJ_FECHA, ejercicio.fecha)
            put(COL_EJ_PESO, ejercicio.peso)
            put(COL_EJ_REPES, ejercicio.repes)
            put(COL_EJ_TIPO, ejercicio.tipo)
        }
        val id = db.insert(TABLE_EJERCICIO, null, values)
        db.close()
        return id
    }

    fun getEjerciciosByPlan(planId: Long): List<Ejercicio> {
        val db = readableDatabase
        val list = mutableListOf<Ejercicio>()
        val cursor = db.query(TABLE_EJERCICIO,
            arrayOf(COL_EJ_ID, COL_EJ_PLAN_ID, COL_EJ_NOMBRE, COL_EJ_FECHA, COL_EJ_PESO, COL_EJ_REPES, COL_EJ_TIPO),
            "$COL_EJ_PLAN_ID = ?", arrayOf(planId.toString()), null, null, "$COL_EJ_ID ASC"
        )

        cursor.use { c ->
            while (c.moveToNext()) {
                list.add(
                    Ejercicio(
                        id = c.getLong(c.getColumnIndexOrThrow(COL_EJ_ID)),
                        planId = c.getLong(c.getColumnIndexOrThrow(COL_EJ_PLAN_ID)),
                        nombre = c.getString(c.getColumnIndexOrThrow(COL_EJ_NOMBRE)),
                        fecha = c.getString(c.getColumnIndexOrThrow(COL_EJ_FECHA)),
                        peso = c.getDouble(c.getColumnIndexOrThrow(COL_EJ_PESO)),
                        repes = c.getInt(c.getColumnIndexOrThrow(COL_EJ_REPES)),
                        tipo = c.getString(c.getColumnIndexOrThrow(COL_EJ_TIPO))
                    )
                )
            }
        }
        db.close()
        return list
    }
}