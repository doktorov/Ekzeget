package ekzeget.ru.ekzeget;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import ekzeget.ru.ekzeget.db.DbHelper;
import ekzeget.ru.ekzeget.db.DbQuery;
import ekzeget.ru.ekzeget.preferences.PDefaultValue;
import ekzeget.ru.ekzeget.preferences.Prefs;
import ekzeget.ru.ekzeget.util.AppVersionCode;

/*
- при чтении толкования должно быть вверху видно текст стиха
- не удобно, что для того, чтобы выйти из толкования в другую книгу или стих, надо много кликать "назад"
- между стихами (и толкованиями на стих) надо перемещаться свайпом влево (к пред.стиху) и вправо (след.стих)
 */
public class App extends Application {

    private static Context mContext;

    private static String mPackageName;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        mPackageName = getPackageName();

        if (Prefs.getApplicationVersionCode(this) < AppVersionCode.getApkVersionCode(this))
            checkApplicationVersionCode();
    }

    public static Resources getAppResources() {
        return mContext.getResources();
    }

    public static Context getContext() {
        return mContext;
    }

    public static String getInfoPackageName() {
        return mPackageName;
    }

    public static SQLiteDatabase getWritableDatabase() {
        return new DbHelper(mContext).getWritableDatabase();
    }

    public static SQLiteDatabase getReadableDatabase() {
        return new DbHelper(mContext).getReadableDatabase();
    }

    void checkApplicationVersionCode() {
        switch(Prefs.getApplicationVersionCode(this)) {
            case PDefaultValue.VERSION_CODE:
                DbQuery db = new DbQuery(this);
                db.createDatabase();
                db.close();
                Prefs.putApplicationVersionCode(this, AppVersionCode.getApkVersionCode(this));
                checkApplicationVersionCode();
                break;
            //**********************************************************//
            default:
                break;
        }
    }
}
