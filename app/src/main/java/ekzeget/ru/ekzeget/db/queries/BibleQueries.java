package ekzeget.ru.ekzeget.db.queries;

import android.database.Cursor;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ekzeget.ru.ekzeget.App;
import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.db.table.BibleTable;
import ekzeget.ru.ekzeget.model.Bible;
import ekzeget.ru.ekzeget.model.BibleTranslate;

public class BibleQueries {
    public static List<Bible> getListContents(String book_kn) {
        List<Bible> bibleList = new ArrayList<>();

        String sql = "SELECT " + BibleTable.KN + ", count(kn) cnt FROM " + BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN_LIKE + " group by " + BibleTable.KN;
        Cursor cursor = App.getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(book_kn + "%")});

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
        Cursor cursor = App.getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(book_kn)});

        final int ST_NO_INDEX = cursor.getColumnIndex(BibleTable.ST_NO);
        final int ST_TEXT_INDEX = cursor.getColumnIndex(BibleTable.ST_TEXT);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Bible bible = new Bible();
            bible.stNo = cursor.getInt(ST_NO_INDEX);
            bible.stText = cursor.getString(ST_TEXT_INDEX);
            bibleList.add(bible);

            cursor.moveToNext();
        }

        cursor.close();

        return bibleList;
    }

    public static List<BibleTranslate> getTranslates(String book_kn, int st_no) {
        //select * from bible where kn='mf1' and st_no=1


        List<BibleTranslate> bibleTranslates = new ArrayList<>();

        String sql = "SELECT " + BibleTable.KN + ", " + BibleTable.ST_TEXT + ", " +
                BibleTable.NEW_TEXT + ", " + BibleTable.KASSIAN + ", " +
                BibleTable.PODSTR + ", " + BibleTable.CSYA + ", " +
                BibleTable.AVERINCEV + ", " + BibleTable.UNGEROV + ", " +
                BibleTable.GREK + ", " + BibleTable.CSYA_OLD + ", " +
                BibleTable.SOVR_RBO + ", " + BibleTable.LATIN + ", " +
                BibleTable.UKR + ", " + BibleTable.NKJV +
                " FROM " + BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN + " AND " + BibleTable.WHERE_ST_NO;
        Cursor cursor = App.getReadableDatabase().rawQuery(sql, new String[]{book_kn, String.valueOf(st_no)});

        final int ST_TEXT_INDEX = cursor.getColumnIndex(BibleTable.ST_TEXT);
        final int NEW_TEXT_INDEX = cursor.getColumnIndex(BibleTable.NEW_TEXT);
        final int KASSIAN_INDEX = cursor.getColumnIndex(BibleTable.KASSIAN);
        final int PODSTR_INDEX = cursor.getColumnIndex(BibleTable.PODSTR);
        final int CSYA_INDEX = cursor.getColumnIndex(BibleTable.CSYA);
        final int AVERINCEV_INDEX = cursor.getColumnIndex(BibleTable.AVERINCEV);
        final int UNGEROV_INDEX = cursor.getColumnIndex(BibleTable.UNGEROV);
        final int GREK_INDEX = cursor.getColumnIndex(BibleTable.GREK);
        final int CSYA_OLD_INDEX = cursor.getColumnIndex(BibleTable.CSYA_OLD);
        final int SOVR_RBO_INDEX = cursor.getColumnIndex(BibleTable.SOVR_RBO);
        final int LATIN_INDEX = cursor.getColumnIndex(BibleTable.LATIN);
        final int UKR_INDEX = cursor.getColumnIndex(BibleTable.UKR);
        final int NKJV_INDEX = cursor.getColumnIndex(BibleTable.NKJV);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (!TextUtils.isEmpty(cursor.getString(ST_TEXT_INDEX))) {
                BibleTranslate translate = new BibleTranslate();
                translate.translate = App.getAppResources().getString(R.string.bible_translate_st_text);
                translate.text = cursor.getString(ST_TEXT_INDEX);
                bibleTranslates.add(translate);
            }

            if (!TextUtils.isEmpty(cursor.getString(NEW_TEXT_INDEX))) {
                BibleTranslate translate = new BibleTranslate();
                translate.translate = App.getAppResources().getString(R.string.bible_translate_new_text);
                translate.text = cursor.getString(NEW_TEXT_INDEX);
                bibleTranslates.add(translate);
            }

            if (!TextUtils.isEmpty(cursor.getString(KASSIAN_INDEX))) {
                BibleTranslate translate = new BibleTranslate();
                translate.translate = App.getAppResources().getString(R.string.bible_translate_kassian);
                translate.text = cursor.getString(KASSIAN_INDEX);
                bibleTranslates.add(translate);
            }

            if (!TextUtils.isEmpty(cursor.getString(PODSTR_INDEX))) {
                BibleTranslate translate = new BibleTranslate();
                translate.translate = App.getAppResources().getString(R.string.bible_translate_podstr);
                translate.text = cursor.getString(PODSTR_INDEX);
                bibleTranslates.add(translate);
            }

            if (!TextUtils.isEmpty(cursor.getString(CSYA_INDEX))) {
                BibleTranslate translate = new BibleTranslate();
                translate.translate = App.getAppResources().getString(R.string.bible_translate_csya);
                translate.text = cursor.getString(CSYA_INDEX);
                bibleTranslates.add(translate);
            }

            if (!TextUtils.isEmpty(cursor.getString(AVERINCEV_INDEX))) {
                BibleTranslate translate = new BibleTranslate();
                translate.translate = App.getAppResources().getString(R.string.bible_translate_averincev);
                translate.text = cursor.getString(AVERINCEV_INDEX);
                bibleTranslates.add(translate);
            }

            if (!TextUtils.isEmpty(cursor.getString(UNGEROV_INDEX))) {
                BibleTranslate translate = new BibleTranslate();
                translate.translate = App.getAppResources().getString(R.string.bible_translate_ungerov);
                translate.text = cursor.getString(UNGEROV_INDEX);
                bibleTranslates.add(translate);
            }

            if (!TextUtils.isEmpty(cursor.getString(GREK_INDEX))) {
                BibleTranslate translate = new BibleTranslate();
                translate.translate = App.getAppResources().getString(R.string.bible_translate_grek);
                translate.text = cursor.getString(GREK_INDEX);
                bibleTranslates.add(translate);
            }

            if (!TextUtils.isEmpty(cursor.getString(CSYA_OLD_INDEX))) {
                BibleTranslate translate = new BibleTranslate();
                translate.translate = App.getAppResources().getString(R.string.bible_translate_csya_old);
                translate.text = cursor.getString(CSYA_OLD_INDEX);
                bibleTranslates.add(translate);
            }

            if (!TextUtils.isEmpty(cursor.getString(SOVR_RBO_INDEX))) {
                BibleTranslate translate = new BibleTranslate();
                translate.translate = App.getAppResources().getString(R.string.bible_translate_sovr_rbo);
                translate.text = cursor.getString(SOVR_RBO_INDEX);
                bibleTranslates.add(translate);
            }

            if (!TextUtils.isEmpty(cursor.getString(LATIN_INDEX))) {
                BibleTranslate translate = new BibleTranslate();
                translate.translate = App.getAppResources().getString(R.string.bible_translate_latin);
                translate.text = cursor.getString(LATIN_INDEX);
                bibleTranslates.add(translate);
            }

            if (!TextUtils.isEmpty(cursor.getString(UKR_INDEX))) {
                BibleTranslate translate = new BibleTranslate();
                translate.translate = App.getAppResources().getString(R.string.bible_translate_ukr);
                translate.text = cursor.getString(UKR_INDEX);
                bibleTranslates.add(translate);
            }

            if (!TextUtils.isEmpty(cursor.getString(NKJV_INDEX))) {
                BibleTranslate translate = new BibleTranslate();
                translate.translate = App.getAppResources().getString(R.string.bible_translate_nkjv);
                translate.text = cursor.getString(NKJV_INDEX);
                bibleTranslates.add(translate);
            }

            cursor.moveToNext();
        }

        cursor.close();

        return bibleTranslates;
    }

    public static List<String> getListParallels(String book_kn) {
        List<String> parallels = new ArrayList<>();

        String sql = "SELECT " + BibleTable.PARALLEL +
                " FROM " + BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN;
        Cursor cursor = App.getReadableDatabase().rawQuery(sql, new String[]{book_kn});

        final int PARALLEL_INDEX = cursor.getColumnIndex(BibleTable.PARALLEL);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (!TextUtils.isEmpty(cursor.getString(PARALLEL_INDEX))) {
                parallels.add(cursor.getString(PARALLEL_INDEX));
            }

            cursor.moveToNext();
        }

        cursor.close();

        return parallels;
    }
}
