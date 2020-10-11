package web.geldenhuys.kotlinhabittrainer.db

import android.provider.BaseColumns


val DATABASE_NAME = "habittrainer.db"
val DATABASE_VERSION = 10

object HabitEntry : BaseColumns {
    val TABLE_NAME = "habit"
    val TITLE_COL = "title"
    val DESCR_COL = "descr"
    val IMAGE_COL = "image"
}

