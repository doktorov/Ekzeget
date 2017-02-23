package ekzeget.ru.ekzeget.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ekzeget.ru.ekzeget.db.table.BooksTable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ekzeget.db";
    public static final int DATABASE_VERSION = 1;

    public static final String NULL_FIELD = "empty";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BooksTable.CREATE_TABLE);
        db.execSQL(BooksTable.DEFAULT_VALUES.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BooksTable.DROP_TABLE);

        onCreate(db);
    }
}
