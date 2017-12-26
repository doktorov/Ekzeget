package ru.ekzeget

import android.app.Application
import android.content.Context
import android.content.res.Resources
import ru.ekzeget.preferences.PDefaultValue
import ru.ekzeget.preferences.Prefs
import ru.ekzeget.utils.AppVersionCode

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        infoPackageName = packageName

        if (Prefs.getApplicationVersionCode(this) < AppVersionCode.getApkVersionCode(this))
            checkApplicationVersionCode()

        //        EkzegetDatabase db = Room.databaseBuilder(getApplicationContext(),
        //                EkzegetDatabase.class, "database-name").build();
    }

    internal fun checkApplicationVersionCode() {
        when (Prefs.getApplicationVersionCode(this)) {
            PDefaultValue.VERSION_CODE -> {
//                val db = DbQuery(this)
//                db.createDatabase()
//                db.close()
//                Prefs.putApplicationVersionCode(this, AppVersionCode.getApkVersionCode(this))
//                checkApplicationVersionCode()
            } else -> {
            }
        }
    }

    companion object {

        var context: Context? = null
            private set

        var infoPackageName: String? = null
            private set

        val appResources: Resources
            get() = context!!.resources

//        val writableDatabase: SQLiteDatabase
//            get() = DbHelper(context).writableDatabase
//
//        val readableDatabase: SQLiteDatabase
//            get() = DbHelper(context).readableDatabase
    }
}