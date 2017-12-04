package ekzeget.ru.ekzeget.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ekzeget.ru.ekzeget.db.table.Bible;
import ekzeget.ru.ekzeget.db.table.BibleDao;

@Database(entities = {Bible.class}, version = 1, exportSchema = false)
public abstract class EkzegetDatabase extends RoomDatabase {
    private static volatile EkzegetDatabase INSTANCE;

    public abstract BibleDao bibleDao();

    public static EkzegetDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (EkzegetDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EkzegetDatabase.class, "ekzeget.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
