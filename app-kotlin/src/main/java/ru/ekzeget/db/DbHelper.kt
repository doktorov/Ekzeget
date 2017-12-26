package ru.ekzeget.db

class DbHelper {
    companion object {
        var DATABASE_PATH = ""
        const val DATABASE_NAME = "ekzeget.db"
        const val PACK_DATABASE_NAME = "ekzeget.zip"
        val BIBLE_TABLE = "bible"

        private val DATABASE_VERSION = 1
    }
}