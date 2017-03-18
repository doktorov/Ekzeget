package ekzeget.ru.ekzeget.db.table;

import android.provider.BaseColumns;

public class BooksTable implements BaseColumns {
    public static final String TABLE_NAME = "books";

    private static final String IDENTIFIER = "identifier";
    private static final String ZAV = "zav";
    public static final String KN = "kn";
    public static final String TITLE = "title";
    private static final String SOKR = "sokr";
    public static final String MENU = "menu";
    public static final String PARTS = "parts";

    public static final String WHERE_ZAV = ZAV + "=?";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String CREATE_TABLE
            = "CREATE TABLE " + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY,"
            + IDENTIFIER + " INTEGER,"
            + ZAV + " TEXT,"
            + KN + " TEXT,"
            + TITLE + " TEXT,"
            + SOKR + " TEXT,"
            + MENU + " TEXT,"
            + PARTS + " INTEGER"
            + ");";
}