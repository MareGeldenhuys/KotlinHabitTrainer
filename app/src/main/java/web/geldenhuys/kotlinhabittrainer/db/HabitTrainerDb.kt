package web.geldenhuys.kotlinhabittrainer.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class HabitTrainerDb(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val SQL_CREATE_QUERIES = "CREATE TABLE ${HabitEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "${HabitEntry.TITLE_COL} TEXT, " +
            "${HabitEntry.DESCR_COL} TEXT, " +
            "${HabitEntry.IMAGE_COL} BLOB " +
            ")"

    private val SQL_DELETE_QUERIES = "DROP TABLE IF EXISTS ${HabitEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_QUERIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(SQL_DELETE_QUERIES)
        onCreate(db)
    }
}