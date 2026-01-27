package com.example.plandeentrenamiento

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.plandeentrenamiento.Person

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "people.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_PEOPLE = "people"
        private const val COL_ID = "id"
        private const val COL_NAME = "name"
        private const val SQL_CREATE = """
            CREATE TABLE $TABLE_PEOPLE (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NAME TEXT NOT NULL
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PEOPLE")
        onCreate(db)
    }

    fun insert(name: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_NAME, name.trim())
        }
        val id = db.insert(TABLE_PEOPLE, null, values)
        db.close()
        return id
    }

    fun update(id: Long, newName: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_NAME, newName.trim())
        }
        val rows = db.update(
            TABLE_PEOPLE,
            values,
            "$COL_ID = ?",
            arrayOf(id.toString())
        )
        db.close()
        return rows
    }

    fun delete(id: Long): Int {
        val db = writableDatabase
        val rows = db.delete(
            TABLE_PEOPLE,
            "$COL_ID = ?",
            arrayOf(id.toString())
        )
        db.close()
        return rows
    }

    fun getAll(): List<Person> {
        val db = readableDatabase
        val people = mutableListOf<Person>()

        val cursor = db.query(
            TABLE_PEOPLE,
            arrayOf(COL_ID, COL_NAME),
            null,
            null,
            null,
            null,
            "$COL_ID DESC"
        )

        cursor.use { c ->
            val idIndex = c.getColumnIndexOrThrow(COL_ID)
            val nameIndex = c.getColumnIndexOrThrow(COL_NAME)

            while (c.moveToNext()) {
                people.add(
                    Person(
                        id = c.getLong(idIndex),
                        name = c.getString(nameIndex)
                    )
                )
            }
        }

        db.close()
        return people
    }
}