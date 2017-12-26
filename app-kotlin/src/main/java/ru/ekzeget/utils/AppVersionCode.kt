package ru.ekzeget.utils

import android.content.Context
import android.content.pm.PackageManager
import ru.ekzeget.preferences.PDefaultValue

object AppVersionCode {
    fun getApkVersionCode(context: Context): Int {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionCode
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        return PDefaultValue.VERSION_CODE
    }
}