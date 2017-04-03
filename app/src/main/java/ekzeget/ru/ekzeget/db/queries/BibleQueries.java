package ekzeget.ru.ekzeget.db.queries;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ekzeget.ru.ekzeget.App;
import ekzeget.ru.ekzeget.db.table.BibleTable;
import ekzeget.ru.ekzeget.model.Bible;

public class BibleQueries {
    public static List<Bible> getListContents(String book_kn) {
        List<Bible> bibleList = new ArrayList<>();

        String sql = "SELECT " + BibleTable.KN + ", count(kn) cnt FROM " + BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN_LIKE + " group by " + BibleTable.KN ;
        Cursor cursor= App.getReadableDatabase().rawQuery(sql, new String[] { String.valueOf(book_kn + "%") });

        final int KN_INDEX = cursor.getColumnIndex(BibleTable.KN);
        final int CNT_INDEX = cursor.getColumnIndex(BibleTable.CNT);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int chapter = Integer.parseInt(cursor.getString(KN_INDEX).replace(book_kn, ""));

            Bible bible = new Bible();
            bible.chapter = String.valueOf(chapter);
            bible.parts = cursor.getInt(CNT_INDEX);
            bibleList.add(bible);

            cursor.moveToNext();
        }

        cursor.close();

        return bibleList;
    }

    public static Map<Integer, Integer> getListContents2(String book_kn) {
        Map<Integer, Integer> bibleList = new HashMap<>();

        String sql = "SELECT " + BibleTable.KN + ", count(kn) cnt FROM " + BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN_LIKE + " group by " + BibleTable.KN ;
        Cursor cursor= App.getReadableDatabase().rawQuery(sql, new String[] { String.valueOf(book_kn + "%") });

        final int KN_INDEX = cursor.getColumnIndex(BibleTable.KN);
        final int CNT_INDEX = cursor.getColumnIndex(BibleTable.CNT);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int chapter = Integer.parseInt(cursor.getString(KN_INDEX).replace(book_kn, ""));

            bibleList.put(chapter, cursor.getInt(CNT_INDEX));

            cursor.moveToNext();
        }

        cursor.close();

        return bibleList;
    }
}
