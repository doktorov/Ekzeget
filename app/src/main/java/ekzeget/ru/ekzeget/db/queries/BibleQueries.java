package ekzeget.ru.ekzeget.db.queries;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ekzeget.ru.ekzeget.App;
import ekzeget.ru.ekzeget.db.table.BibleTable;

public class BibleQueries {
    public static List<String> getListContents(String book_kn) {
        List<String> books = new ArrayList<>();

        String sql = "SELECT " + BibleTable.KN + ", count(kn) cnt FROM " + BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN_LIKE + " group by " + BibleTable.KN ;
        Cursor cursor= App.getReadableDatabase().rawQuery(sql, new String[] { String.valueOf(book_kn + "%") });

        final int KN_INDEX = cursor.getColumnIndex(BibleTable.KN);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            books.add(cursor.getString(KN_INDEX));

            cursor.moveToNext();
        }

        cursor.close();

        return books;
    }
}
