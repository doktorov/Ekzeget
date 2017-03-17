package ekzeget.ru.ekzeget.db.table;

import android.provider.BaseColumns;

public class VersesTable implements BaseColumns {
    public static final String TABLE_NAME = "verses";

    private static final String IDENTIFIER = "identifier";
    private static final String ST_TEXT = "st_text";
    private static final String PARALLEL = "parallel";
    private static final String NEW_TEXT = "new_text";
    private static final String KASSIAN = "kassian";
    private static final String PODSTR = "podstr";
    private static final String CSYA = "csya";
    private static final String AVERINCEV = "averincev";
    private static final String UNGEROV = "ungerov";
    private static final String GREK = "grek";
    private static final String CSYA_OLD = "csya_old";
    private static final String SOVR_RBO = "sovr_rbo";
    private static final String LATIN = "latin";
    private static final String UKR = "ukr";
    private static final String NKJV = "nkjv";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String CREATE_TABLE
            = "CREATE TABLE " + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY,"
            + IDENTIFIER + " INTEGER,"
            + ST_TEXT + " TEXT,"
            + PARALLEL + " TEXT,"
            + NEW_TEXT + " TEXT,"
            + KASSIAN + " TEXT,"
            + PODSTR + " TEXT,"
            + CSYA + " TEXT,"
            + AVERINCEV + " TEXT,"
            + UNGEROV + " TEXT,"
            + GREK + " TEXT,"
            + CSYA_OLD + " TEXT,"
            + SOVR_RBO + " TEXT,"
            + LATIN + " TEXT,"
            + UKR + " TEXT,"
            + NKJV + " TEXT"
            + ");";

    public static final StringBuilder DEFAULT_VALUES = new StringBuilder()
            .append("INSERT INTO " + TABLE_NAME +" (")
            .append("'" + IDENTIFIER + "', '" + ST_TEXT + "', '" + PARALLEL + "',")
            .append("'" + NEW_TEXT + "', '" + KASSIAN + "', '" + PODSTR + "', '" + CSYA + "'")
            .append("'" + AVERINCEV + "', '" + UNGEROV + "', '" + GREK + "', '" + CSYA_OLD + "'")
            .append("'" + SOVR_RBO + "', '" + LATIN + "', '" + UKR + "', '" + NKJV + "'")
            .append(") VALUES");



}
