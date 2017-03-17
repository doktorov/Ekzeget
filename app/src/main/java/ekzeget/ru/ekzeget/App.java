package ekzeget.ru.ekzeget;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import ekzeget.ru.ekzeget.db.DbHelper;

public class App extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
    }

    public static Resources getAppResources() {
        return sContext.getResources();
    }

    public static Context getContext() {
        return sContext;
    }

//    public static SQLiteDatabase getWritableDatabase() {
//        return new DbHelper(sContext).getWritableDatabase();
//    }
//
//    public static SQLiteDatabase getReadableDatabase() {
//        return new DbHelper(sContext).getReadableDatabase();
//    }
}
