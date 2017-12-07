package ekzeget.ru.ekzeget.db.model;

import android.content.Context;

import ekzeget.ru.ekzeget.db.EkzegetDatabase;

public class Injection {
    public static BibleDataSource provideBibleDataSource(Context context) {
        EkzegetDatabase database = EkzegetDatabase.getInstance(context);
        return new LocalBibleDataSource(database.bibleDao());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        BibleDataSource dataSource = provideBibleDataSource(context);
        return new ViewModelFactory(dataSource);
    }
}
