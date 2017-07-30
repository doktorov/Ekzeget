package ekzeget.ru.ekzeget.db.queries;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ekzeget.ru.ekzeget.App;
import ekzeget.ru.ekzeget.db.table.BibleTable;
import ekzeget.ru.ekzeget.db.table.TalksTable;
import ekzeget.ru.ekzeget.model.Talks;

public class TalksQueries {
    public static List<Talks> getListTalksPoem(String book_kn, String st_no) {
        List<Talks> talksList = new ArrayList<>();

        String sql = "SELECT " + TalksTable.T_NAME + ", " + TalksTable.COMMENTS +
                " FROM " + TalksTable.TABLE_NAME + " WHERE " + TalksTable.WHERE_KN  +
                " AND " + TalksTable.WHERE_ST_NO + " ORDER BY " + TalksTable.ID ;
        Cursor cursor= App.getReadableDatabase().rawQuery(sql, new String[] { book_kn, st_no });

        final int T_NAME_INDEX = cursor.getColumnIndex(TalksTable.T_NAME);
        final int COMMENTS_INDEX = cursor.getColumnIndex(TalksTable.COMMENTS);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Talks talks = new Talks();
            talks.tName = cursor.getString(T_NAME_INDEX);
            talks.comments = cursor.getString(COMMENTS_INDEX);
            talksList.add(talks);

            cursor.moveToNext();
        }

        cursor.close();

        return talksList;
    }

    public static List<Talks> getListTalksGroupByName(String book_kn) {
        List<Talks> talksList = new ArrayList<>();

        String sql = "SELECT " + TalksTable.ST_NO + ", " + TalksTable.T_NAME +
                " FROM " + TalksTable.TABLE_NAME + " WHERE " + TalksTable.WHERE_KN  +
                " GROUP BY " + TalksTable.T_NAME + " ORDER BY " + TalksTable.T_NAME;
        Cursor cursor= App.getReadableDatabase().rawQuery(sql, new String[] { book_kn });

        final int ST_NO_INDEX = cursor.getColumnIndex(TalksTable.ST_NO);
        final int T_NAME_INDEX = cursor.getColumnIndex(TalksTable.T_NAME);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Talks talks = new Talks();
            talks.stNo = cursor.getInt(ST_NO_INDEX);
            talks.tName = cursor.getString(T_NAME_INDEX);
            talksList.add(talks);

            cursor.moveToNext();
        }

        cursor.close();

        return talksList;
    }

    public static List<Talks> getListTalksText(String book_kn, String t_name) {
        List<Talks> talksList = new ArrayList<>();

        String sql = "SELECT T.ST_NO AS " + TalksTable.ST_NO +
                ", T.COMMENTS AS " + TalksTable.COMMENTS +
                ", B.ST_TEXT " + BibleTable.ST_TEXT +
                " FROM " + TalksTable.TABLE_NAME + " T " +
                " LEFT JOIN " + BibleTable.TABLE_NAME + " B ON T.KN = B.KN AND B.ST_NO = T.ST_NO " +
                " WHERE T." + TalksTable.WHERE_KN + " AND T." + TalksTable.WHERE_T_NAME +
                " ORDER BY CAST(T.ST_NO AS INT)";

        Cursor cursor= App.getReadableDatabase().rawQuery(sql, new String[] { book_kn, t_name });

        final int ST_NO_INDEX = cursor.getColumnIndex(TalksTable.ST_NO);
        final int COMMENTS_INDEX = cursor.getColumnIndex(TalksTable.COMMENTS);
        final int ST_TEXT_INDEX = cursor.getColumnIndex(BibleTable.ST_TEXT);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Talks talks = new Talks();
            talks.stNo = cursor.getInt(ST_NO_INDEX);
            talks.comments = cursor.getString(COMMENTS_INDEX);
            talks.stText = cursor.getString(ST_TEXT_INDEX);
            talksList.add(talks);

            cursor.moveToNext();
        }

        cursor.close();

        return talksList;
    }
}
