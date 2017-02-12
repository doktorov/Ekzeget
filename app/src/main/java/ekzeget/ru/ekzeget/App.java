package ekzeget.ru.ekzeget;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

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
}
