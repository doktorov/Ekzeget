package ekzeget.ru.ekzeget.db.table;

import android.provider.BaseColumns;

public class BibleTable implements BaseColumns {
    public static final String TABLE_NAME = "bible";

    private static final String IDENTIFIER = "identifier";
    private static final String ZAV = "zav";
    public static final String KN = "kn";
    public static final String TITLE = "title";
    private static final String SOKR = "sokr";
    public static final String MENU = "menu";
    public static final String PARTS = "parts";

    public static final String WHERE_ZAV = ZAV + "=?";
}