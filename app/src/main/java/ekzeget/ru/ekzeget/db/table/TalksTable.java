package ekzeget.ru.ekzeget.db.table;

public class TalksTable {
    public static final String TABLE_NAME = "talks";

    public static final String ID = "_id";
    public static final String KN = "kn";
    public static final String T_NAME = "t_name";
    public static final String ST_NO = "st_no";
    public static final String COMMENTS = "comments";
    public static final String USER = "user";
    public static final String USER_ID = "user_edit";
    public static final String ISSLED = "issled";

    public static final String WHERE_KN = KN + "=?";
    public static final String WHERE_T_NAME = T_NAME + "=?";
    public static final String WHERE_ST_NO = ST_NO + "=?";
}
