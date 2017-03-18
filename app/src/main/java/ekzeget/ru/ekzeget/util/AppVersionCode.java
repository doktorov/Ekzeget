package ekzeget.ru.ekzeget.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import ekzeget.ru.ekzeget.preferences.PDefaultValue;

public class AppVersionCode {
    public static int getApkVersionCode(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return PDefaultValue.VERSION_CODE;
    }
}
