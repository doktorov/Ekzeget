package ekzeget.ru.ekzeget.db.queries;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ekzeget.ru.ekzeget.App;
import ekzeget.ru.ekzeget.db.table.TalksTable;
import ekzeget.ru.ekzeget.model.Talks;

public class TalksQueries {
    public static List<Talks> getListTalksPoem(String book_kn, String st_no) {
        List<Talks> talksList = new ArrayList<>();

        String sql = "SELECT " + TalksTable.T_NAME + ", " + TalksTable.COMMENTS +
                " FROM " + TalksTable.TABLE_NAME + " WHERE " + TalksTable.WHERE_KN  + " AND " +
                TalksTable.WHERE_ST_NO                 + " order by " + TalksTable.ID ;
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
}
