package web.geldenhuys.kotlinhabittrainer.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.BaseColumns._ID
import android.util.Log
import web.geldenhuys.kotlinhabittrainer.Habit
import web.geldenhuys.kotlinhabittrainer.db.HabitEntry.DESCR_COL
import web.geldenhuys.kotlinhabittrainer.db.HabitEntry.IMAGE_COL
import web.geldenhuys.kotlinhabittrainer.db.HabitEntry.TABLE_NAME
import web.geldenhuys.kotlinhabittrainer.db.HabitEntry.TITLE_COL
import java.io.ByteArrayOutputStream

class HabitDbTable(context: Context) {

    private val TAG = HabitDbTable::class.simpleName
    private val habitTrainerDb = HabitTrainerDb(context)

    fun store(habit: Habit): Long {
        val db = habitTrainerDb.writableDatabase

        val values = ContentValues()
        with(values) {
            put(TITLE_COL, habit.title)
            put(DESCR_COL, habit.description)
            put(IMAGE_COL, toByteArray(habit.image))
        }

        return db.transaction {
            Log.d(TAG, "store: new habit $habit")
            insert(TABLE_NAME, null, values)
        }
    }

    fun readAllHabits() : List<Habit> {
        val columns = arrayOf(_ID, TITLE_COL, DESCR_COL, IMAGE_COL)
        val order = "${_ID} ASC"
        val db = habitTrainerDb.readableDatabase

        val cursor = db.doQuery(TABLE_NAME, columns, orderBy = order)

        val habits = mutableListOf<Habit>()
        while(cursor.moveToNext()) {
            val title = cursor.getString(TITLE_COL)
            val descr = cursor.getString(DESCR_COL)
            val bitmap = cursor.getBitmap(IMAGE_COL)
            habits.add(Habit(title,descr,bitmap))
        }
        cursor.close()
        return habits
    }

    private fun toByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }
}

private inline fun Cursor.getString(columnName: String) = getString(getColumnIndex(columnName))

private inline fun Cursor.getBitmap(columnName: String): Bitmap {
    val blob = getBlob(getColumnIndex(columnName))
    return BitmapFactory.decodeByteArray(blob, 0, blob.size)
}

private inline fun SQLiteDatabase.doQuery(tableName:String, columns: Array<String>,
                                          selection: String? = null, selectionArgs: Array<String>? = null,
                                          groupBy: String? = null, having: String? = null,
                                          orderBy: String? = null) = query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy)


private inline fun <T> SQLiteDatabase.transaction(function: SQLiteDatabase.() -> T): T {
    beginTransaction()
    return try {
        val returnValue = function()
        setTransactionSuccessful()
        returnValue
    } finally {
        endTransaction()
        close()
    }

}