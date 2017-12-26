package ru.ekzeget.preferences

import android.content.Context
import android.content.SharedPreferences
import ru.ekzeget.App

object Prefs {
    private operator fun get(context: Context): SharedPreferences {
        return context.getSharedPreferences(App.infoPackageName, Context.MODE_PRIVATE)
    }

    internal fun getPref(context: Context, pref: String, def: String): String? {
        val prefs = Prefs[context]
        val `val` = prefs.getString(pref, def)

        return if (`val` == null || `val` == "" || `val` == "null")
            def
        else
            `val`
    }

    internal fun putPref(context: Context, pref: String, `val`: String) {
        val prefs = Prefs[context]
        val editor = prefs.edit()

        editor.putString(pref, `val`)
        editor.apply()
    }

    private fun getIntPref(context: Context, pref: String, def: Int): Int {
        val prefs = Prefs[context]
        return prefs.getInt(pref, def)
    }

    private fun putIntPref(context: Context, pref: String, `val`: Int) {
        val prefs = Prefs[context]
        val editor = prefs.edit()

        editor.putInt(pref, `val`)
        editor.apply()
    }

    //================================================================================================
    private fun encryptInteger(i: Int): Int {
        /*todo*/
        return i
    }

    private fun decryptInteger(i: Int): Int {
        /*todo*/
        return i
    }

    //++++++++++++++++++++++ ENCRYPTED PREFERENCES START (1)++++++++++++++++++++++++++++++++++++++++

    //________________________________________________________________________________________ STAGE
    fun getStage(context: Context): Int {
        val encryptedValue = Prefs.getIntPref(context, PKey.STAGE, encryptInteger(PDefaultValue.STAGE))
        return decryptInteger(encryptedValue)
    }

    fun putStage(context: Context, stage: Int) {
        Prefs.putIntPref(context, PKey.STAGE, encryptInteger(stage))
    }
    //------------------------- ENCRYPTED PREFERENCES END (1) --------------------------------------


    fun getApplicationVersionCode(context: Context): Int {
        return Prefs.getIntPref(context, PKey.VERSION_CODE, PDefaultValue.VERSION_CODE)
    }

    fun putApplicationVersionCode(context: Context, version_code: Int) {
        Prefs.putIntPref(context, PKey.VERSION_CODE, version_code)
    }

}
