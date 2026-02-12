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
        private const val DATABASE_NAME = "gym.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_PLAN = "PlanEntrenamiento"
        private const val COL_PLAN_ID = "id"
        private const val COL_PLAN_NAME = "name"
        private const val COL_PLAN_DAYS = "days"
        private const val COL_PLAN_WEEKS = "semanas"
        private const val COL_PLAN_ACTIVE = "activo"
        private const val TABLE_EXERCISE = "Ejercicio"
        private const val COL_EX_ID = "id"
        private const val COL_EX_PLAN_ID = "plan_id"
        private const val COL_EX_DAY = "dia"
        private const val COL_EX_NAME = "nombre"
        private const val COL_EX_WEIGHT = "peso"
        private const val COL_EX_REPS = "repes"

        private const val SQL_CREATE_PLAN = """
            CREATE TABLE $TABLE_PLAN (
                $COL_PLAN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_PLAN_NAME TEXT NOT NULL,
                $COL_PLAN_DAYS INTEGER NOT NULL,
                $COL_PLAN_WEEKS INTEGER NOT NULL,
                $COL_PLAN_ACTIVE INTEGER NOT NULL
            )
        """

        private const val SQL_CREATE_EXERCISE = """
            CREATE TABLE $TABLE_EXERCISE (
                $COL_EX_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_EX_PLAN_ID INTEGER NOT NULL,
                $COL_EX_DAY INTEGER NOT NULL,
                $COL_EX_NAME TEXT NOT NULL,
                $COL_EX_WEIGHT REAL,
                $COL_EX_REPS INTEGER,
                FOREIGN KEY($COL_EX_PLAN_ID) 
                    REFERENCES $TABLE_PLAN($COL_PLAN_ID) 
                    ON DELETE CASCADE
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_PLAN)
        db.execSQL(SQL_CREATE_EXERCISE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EXERCISE")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PLAN")
        onCreate(db)
    }

    fun insertPlan(plan: PlanEntrenamiento): Long {
        val db = writableDatabase

        val values = ContentValues().apply {
            put(COL_PLAN_NAME, plan.nombre.trim())
            put(COL_PLAN_DAYS, plan.dias)
            put(COL_PLAN_WEEKS, plan.semanas)
            put(COL_PLAN_ACTIVE, if (plan.activo) 1 else 0)
        }

        val id = db.insert(TABLE_PLAN, null, values)
        db.close()
        return id
    }

    fun getAllPlans(): List<PlanEntrenamiento> {
        val db = readableDatabase
        val list = mutableListOf<PlanEntrenamiento>()

        val cursor = db.query(
            TABLE_PLAN, arrayOf(
                COL_PLAN_ID, COL_PLAN_NAME, COL_PLAN_DAYS, COL_PLAN_WEEKS, COL_PLAN_ACTIVE
            ), null, null, null, null, "$COL_PLAN_ID DESC"
        )

        cursor.use { c ->
            while (c.moveToNext()) {
                list.add(
                    PlanEntrenamiento(
                        id = c.getLong(c.getColumnIndexOrThrow(COL_PLAN_ID)),
                        nombre = c.getString(c.getColumnIndexOrThrow(COL_PLAN_NAME)),
                        dias = c.getInt(c.getColumnIndexOrThrow(COL_PLAN_DAYS)),
                        semanas = c.getInt(c.getColumnIndexOrThrow(COL_PLAN_WEEKS)),
                        activo = c.getInt(c.getColumnIndexOrThrow(COL_PLAN_ACTIVE)) == 1
                    )
                )
            }
        }

        db.close()
        return list
    }

    fun updatePlan(planId: Long, isActive: Boolean): Boolean {
        val db = writableDatabase

        val values = ContentValues().apply {
            if (isActive) {
                put(COL_PLAN_ACTIVE, 1)
            } else {
                put(COL_PLAN_ACTIVE, 0)
            }
        }
        val rowsAffected = db.update(
            TABLE_PLAN, values, "$COL_PLAN_ID = ?", arrayOf(planId.toString())
        )

        db.close()
        if (rowsAffected > 0) {
            return true
        } else {
            return false
        }
    }

    fun deletePlan(planId: Long): Boolean {
        val db = writableDatabase
        val rowsDeleted = db.delete(
            TABLE_PLAN, "$COL_PLAN_ID = ?", arrayOf(planId.toString())
        )

        db.close()
        return rowsDeleted > 0
    }

    fun insertExercise(exercise: EjercicioRegistrado): Long {
        val db = writableDatabase

        val values = ContentValues().apply {
            put(COL_EX_PLAN_ID, exercise.planId)
            put(COL_EX_DAY, exercise.dia)
            put(COL_EX_NAME, exercise.nombre)
            put(COL_EX_WEIGHT, exercise.peso)
            put(COL_EX_REPS, exercise.repes)
        }

        val id = db.insert(TABLE_EXERCISE, null, values)
        db.close()
        return id
    }

    fun getExercises(planId: Long, day: Int): List<EjercicioRegistrado> {
        val db = readableDatabase
        val list = mutableListOf<EjercicioRegistrado>()

        val cursor = db.query(
            TABLE_EXERCISE,
            arrayOf(
                COL_EX_ID, COL_EX_PLAN_ID, COL_EX_DAY, COL_EX_NAME, COL_EX_WEIGHT, COL_EX_REPS
            ),
            "$COL_EX_PLAN_ID = ? AND $COL_EX_DAY = ?",
            arrayOf(planId.toString(), day.toString()),
            null,
            null,
            "$COL_EX_ID ASC"
        )

        cursor.use { c ->
            while (c.moveToNext()) {
                list.add(
                    EjercicioRegistrado(
                        id = c.getLong(c.getColumnIndexOrThrow(COL_EX_ID)),
                        planId = c.getLong(c.getColumnIndexOrThrow(COL_EX_PLAN_ID)),
                        dia = c.getInt(c.getColumnIndexOrThrow(COL_EX_DAY)),
                        nombre = c.getString(c.getColumnIndexOrThrow(COL_EX_NAME)),
                        peso = c.getDouble(c.getColumnIndexOrThrow(COL_EX_WEIGHT)),
                        repes = c.getInt(c.getColumnIndexOrThrow(COL_EX_REPS))
                    )
                )
            }
        }

        db.close()
        return list
    }
}
