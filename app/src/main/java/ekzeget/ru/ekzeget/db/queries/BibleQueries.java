package ekzeget.ru.ekzeget.db.queries;

import android.database.Cursor;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
            Bible bible = new Bible();
            bible.chapter = cursor.getString(KN_INDEX);
            bible.parts = cursor.getInt(CNT_INDEX);
            bibleList.add(bible);

            cursor.moveToNext();
        }

        cursor.close();

        return bibleList;
    }

    public static Map<Integer, Integer> getListContentsSorted(String book_kn, int book_parts) {
        Map<Integer, Integer> bibleList = new HashMap<>();

        for (int i = 1; i < book_parts + 1; i++) {
            String sql = "SELECT " + BibleTable.KN + ", count(kn) cnt FROM " + BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN + " group by " + BibleTable.KN;
            Cursor cursor = App.getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(book_kn + i)});

            final int KN_INDEX = cursor.getColumnIndex(BibleTable.KN);
            final int CNT_INDEX = cursor.getColumnIndex(BibleTable.CNT);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String kn = cursor.getString(KN_INDEX);
                if (!TextUtils.isEmpty(kn)) {
                    int chapter = Integer.parseInt(kn.replace(book_kn, ""));

                    bibleList.put(chapter, cursor.getInt(CNT_INDEX));
                }

                cursor.moveToNext();
            }

            cursor.close();
        }

        return new TreeMap<>(bibleList);
    }

    public static List<Bible> getChapterContent(String book_kn) {
        List<Bible> bibleList = new ArrayList<>();

        String sql = "SELECT " + BibleTable.ST_NO + ", " + BibleTable.ST_TEXT + " FROM " +
                BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN + " ORDER BY " + BibleTable.ST_NO;
        Cursor cursor= App.getReadableDatabase().rawQuery(sql, new String[] { String.valueOf(book_kn) });

        final int ST_NO_INDEX = cursor.getColumnIndex(BibleTable.ST_NO);
        final int ST_TEXT_INDEX = cursor.getColumnIndex(BibleTable.ST_TEXT);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Bible bible = new Bible();
            bible.st_no = cursor.getInt(ST_NO_INDEX);
            bible.st_text = cursor.getString(ST_TEXT_INDEX);
            bibleList.add(bible);

            cursor.moveToNext();
        }

        cursor.close();

        return bibleList;
    }
}
