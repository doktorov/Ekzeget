package ekzeget.ru.ekzeget.db.table;

import android.provider.BaseColumns;

public class BibleTable implements BaseColumns {
    public static final String TABLE_NAME = "bible";

    public static final String CNT = "cnt";
    public static final String ST_NO = "st_no";
    public static final String KN = "kn";
    public static final String ST_TEXT = "st_text";
    public static final String PARALLEL = "parallel";
    public static final String NEW_TEXT = "new_text";
    public static final String KASSIAN = "kassian";
    public static final String PODSTR = "podstr";
    public static final String CSYA = "csya";
    public static final String AVERINCEV = "averincev";
    public static final String UNGEROV = "ungerov";
    public static final String GREK = "grek";
    public static final String CSYA_OLD = "csya_old";
    public static final String SOVR_RBO = "sovr_rbo";
    public static final String LATIN = "latin";
    public static final String UKR = "ukr";
    public static final String NKJV = "nkjv";

    public static final String WHERE_KN = KN + "=?";
    public static final String WHERE_ST_NO = ST_NO + "=?";
    public static final String WHERE_KN_LIKE = KN + " like ?";



    /*CREATE TABLE `bible` ( `st_no` INTEGER,
    `kn` TEXT,
    `st_text` TEXT,
    `parallel` TEXT,
    `new_text` TEXT,
    `kassian` TEXT,
    `podstr` TEXT,
    `csya` TEXT,
    `averincev` TEXT,
    `ungerov` TEXT,
    `grek` TEXT,
    `csya_old` TEXT,
    `sovr_rbo` TEXT,
    `latin` TEXT,
    `ukr` TEXT,
    `nkjv` TEXT )*/
}