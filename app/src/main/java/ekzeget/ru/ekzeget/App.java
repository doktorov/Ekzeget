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
[07.08.2017 18:26:46] Сергей Жидков: осталось в принципе только продумать:
1. как осуществлять переход между стихами когда читаешь стих? Хочется свайпом влево или вправо
2. Как осуществлять аналогичный переход в самом толковании (толкование на следующий или предыдущий стих)?
3. Все-таки хочется видеть текст самого стиха, когда читаешь толкование.
4. По умолчанию андроид не воспринимает html теги и аски коды?

[07.08.2017 21:01:20] Dmitriy Doktorov: 1. Если читаешь стих там где табы, то никак
2. Можно перенести как было сделано в прошлый раз
3. Хорошо
4. Нет

[07.08.2017 22:03:17 | Изменены 22:03:17] Сергей Жидков: 1. переход по стихам - жалко. Надо что-то придумать. Это из-за таб нельзя?
[07.08.2017 22:03:36] Сергей Жидков: 2. Да, видимо так придется сделать
[07.08.2017 22:03:46] Сергей Жидков: 4. Надо обучить его )))

[07.08.2017 22:08:52] Dmitriy Doktorov: 1. переход по стихам - жалко. Надо что-то придумать. Это из-за таб нельзя?Я подумаю
[07.08.2017 22:09:00] Dmitriy Doktorov: 4. Надо обучить его )))попробую
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
