package ekzeget.ru.ekzeget.db.table;

import android.provider.BaseColumns;

public class BibleTable implements BaseColumns {
    public static final String TABLE_NAME = "bible";

    public static final String ST_NO = "st_no";
    public static final String KN = "kn";
    public static final String ST_TEXT = "st_text";

    public static final String WHERE_KN = KN + "=?";
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