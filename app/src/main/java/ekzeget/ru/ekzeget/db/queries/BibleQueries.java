package ekzeget.ru.ekzeget.db.queries;

import android.database.Cursor;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ekzeget.ru.ekzeget.App;
import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.db.table.BibleTable;
import ekzeget.ru.ekzeget.model.BibleModel;
import ekzeget.ru.ekzeget.model.BibleTranslate;

public class BibleQueries {
    public static List<BibleModel> getListContents(String book_kn) {
        List<BibleModel> bibleModelList = new ArrayList<>();

        String sql = "SELECT " + BibleTable.KN + ", count(kn) cnt FROM " + BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN_LIKE + " group by " + BibleTable.KN;
        Cursor cursor = App.getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(book_kn + "%")});

        final int KN_INDEX = cursor.getColumnIndex(BibleTable.KN);
        final int CNT_INDEX = cursor.getColumnIndex(BibleTable.CNT);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BibleModel bibleModel = new BibleModel();
            bibleModel.chapter = cursor.getString(KN_INDEX);
            bibleModel.parts = cursor.getInt(CNT_INDEX);
            bibleModelList.add(bibleModel);

            cursor.moveToNext();
        }

        cursor.close();

        return bibleModelList;
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

    public static List<BibleModel> getChapterContent(String book_kn) {
        List<BibleModel> bibleModelList = new ArrayList<>();

        String sql = "SELECT " + BibleTable.ST_NO + ", " + BibleTable.ST_TEXT + " FROM " +
                BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN + " ORDER BY " + BibleTable.ST_NO;
        Cursor cursor = App.getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(book_kn)});

        final int ST_NO_INDEX = cursor.getColumnIndex(BibleTable.ST_NO);
        final int ST_TEXT_INDEX = cursor.getColumnIndex(BibleTable.ST_TEXT);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BibleModel bibleModel = new BibleModel();
            bibleModel.stNo = cursor.getInt(ST_NO_INDEX);
            bibleModel.stText = cursor.getString(ST_TEXT_INDEX);
            bibleModelList.add(bibleModel);

            cursor.moveToNext();
        }

        cursor.close();

        return bibleModelList;
    }

    public static List<BibleTranslate> getTranslates(String book_kn, int st_no) {
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

//    (?:\{{3})(\d?\s?[а-яА-Я]{2,})\.\s(\d{1,3})\:(\d{1,3})(?:\}{3}) один стих
//    (?:\{{3})(\d?\s?[а-яА-Я]{2,})\.\s(\d{1,3})\:(\d{1,3}[^}]*)(?:\}{3}) группа стихов
//    (?:\{{3})(\d?\s?[а-яА-Я]{2,})\.\s(\d{1,3})(?:\}{3}) глава
    public static List<String> getListParallels(String book_kn) {
        List<String> parallels = new ArrayList<>();

        String sql = "SELECT " + BibleTable.PARALLEL +
                " FROM " + BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN;
        Cursor cursor = App.getReadableDatabase().rawQuery(sql, new String[]{book_kn});

        final int PARALLEL_INDEX = cursor.getColumnIndex(BibleTable.PARALLEL);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String parallel = cursor.getString(PARALLEL_INDEX);
            if (!TextUtils.isEmpty(parallel)) {
                //parallel = "{{{" + parallel + "}}}";

//                Pattern patternPoem = Pattern.compile("(?:\\{{3})(\\d?\\s?[а-яА-Я]{2,})\\.\\s(\\d{1,3})\\:(\\d{1,3})(?:\\}{3})");
//                Matcher matcherPoem = patternPoem.matcher(parallel);
//                if (matcherPoem.find()) {
//                    String s = "";
//                }
//
//                Pattern patternGroup = Pattern.compile("(?:\\{{3})(\\d?\\s?[а-яА-Я]{2,})\\.\\s(\\d{1,3})\\:(\\d{1,3}[^}]*)(?:\\}{3})");
//                Matcher matcherGroup = patternGroup.matcher(parallel);
//                if (matcherGroup.find()) {
//                    String s = "";
//                }

                Pattern patternParallel = Pattern.compile("(\\d?\\s?[а-яА-Я]{2,}\\.\\s\\d{1,3}\\:\\d{1,3})\\s?");
                Matcher matcherParallel = patternParallel.matcher(parallel);
                if (matcherParallel.find()) {
                    parallels.add(matcherParallel.group(0));
                }

                //parallels.add(parallel);
            }

            cursor.moveToNext();
        }

        cursor.close();

        return parallels;
    }

    public static List<String> getListParallelsPoem(String book_kn, String st_no) {
        List<String> parallels = new ArrayList<>();

        String sql = "SELECT " + BibleTable.PARALLEL +
                " FROM " + BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN + " AND " + BibleTable.WHERE_ST_NO;
        Cursor cursor = App.getReadableDatabase().rawQuery(sql, new String[]{book_kn, st_no});

        final int PARALLEL_INDEX = cursor.getColumnIndex(BibleTable.PARALLEL);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String parallel = cursor.getString(PARALLEL_INDEX);
            if (!TextUtils.isEmpty(parallel)) {
                Pattern patternParallel = Pattern.compile("(\\d?\\s?[а-яА-Я]{2,}\\.\\s\\d{1,3}\\:\\d{1,3})\\s?");
                Matcher matcherParallel = patternParallel.matcher(parallel);
                if (matcherParallel.find()) {
                    parallels.add(matcherParallel.group(0));
                }
            }

            cursor.moveToNext();
        }

        cursor.close();

        return parallels;
    }

    public static List<BibleTranslate> getTranslatesList() {
        List<BibleTranslate> bibleTranslates = new ArrayList<>();

        BibleTranslate st_text = new BibleTranslate();
        st_text.translate = App.getAppResources().getString(R.string.bible_translate_st_text);
        st_text.text = "st_text";
        bibleTranslates.add(st_text);

        BibleTranslate new_text = new BibleTranslate();
        new_text.translate = App.getAppResources().getString(R.string.bible_translate_new_text);
        new_text.text = "new_text";
        bibleTranslates.add(new_text);

        BibleTranslate kassian = new BibleTranslate();
        kassian.translate = App.getAppResources().getString(R.string.bible_translate_kassian);
        kassian.text = "kassian";
        bibleTranslates.add(kassian);

        BibleTranslate podstr = new BibleTranslate();
        podstr.translate = App.getAppResources().getString(R.string.bible_translate_podstr);
        podstr.text = "podstr";
        bibleTranslates.add(podstr);

        BibleTranslate csya = new BibleTranslate();
        csya.translate = App.getAppResources().getString(R.string.bible_translate_csya);
        csya.text = "csya";
        bibleTranslates.add(csya);

        BibleTranslate averincev = new BibleTranslate();
        averincev.translate = App.getAppResources().getString(R.string.bible_translate_averincev);
        averincev.text = "averincev";
        bibleTranslates.add(averincev);

        BibleTranslate ungerov = new BibleTranslate();
        ungerov.translate = App.getAppResources().getString(R.string.bible_translate_ungerov);
        ungerov.text = "ungerov";
        bibleTranslates.add(ungerov);

        BibleTranslate grek = new BibleTranslate();
        grek.translate = App.getAppResources().getString(R.string.bible_translate_grek);
        grek.text = "grek";
        bibleTranslates.add(grek);

        BibleTranslate csya_old = new BibleTranslate();
        csya_old.translate = App.getAppResources().getString(R.string.bible_translate_csya_old);
        csya_old.text = "csya_old";
        bibleTranslates.add(csya_old);

        BibleTranslate sovr_rbo = new BibleTranslate();
        sovr_rbo.translate = App.getAppResources().getString(R.string.bible_translate_sovr_rbo);
        sovr_rbo.text = "sovr_rbo";
        bibleTranslates.add(sovr_rbo);

        BibleTranslate latin = new BibleTranslate();
        latin.translate = App.getAppResources().getString(R.string.bible_translate_latin);
        latin.text = "latin";
        bibleTranslates.add(latin);

        BibleTranslate ukr = new BibleTranslate();
        ukr.translate = App.getAppResources().getString(R.string.bible_translate_ukr);
        ukr.text = "ukr";
        bibleTranslates.add(ukr);

        BibleTranslate nkjv = new BibleTranslate();
        nkjv.translate = App.getAppResources().getString(R.string.bible_translate_nkjv);
        nkjv.text = "nkjv";
        bibleTranslates.add(nkjv);

        return bibleTranslates;
    }

    public static List<String> getTranslatesGlava(String book_kn, String translate_field) {
        List<String> translates = new ArrayList<>();

        String sql = "SELECT " + BibleTable.ST_NO + ", " + translate_field +
                " FROM " + BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN;
        Cursor cursor = App.getReadableDatabase().rawQuery(sql, new String[]{book_kn});

        final int ST_NO_INDEX = cursor.getColumnIndex(BibleTable.ST_NO);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String translate = cursor.getString(1);
            if (!TextUtils.isEmpty(translate)) {
                translates.add(cursor.getString(ST_NO_INDEX) + ". " + translate);
            }

            cursor.moveToNext();
        }

        cursor.close();

        return translates;
    }

    public static Map<Integer, List<BibleModel>> getListContextTextSorted(String book_kn, int book_parts) {
        Map<Integer, List<BibleModel>> bibleList = new HashMap<>();

        for (int i = 1; i < book_parts + 1; i++) {
            String sql = "SELECT " + BibleTable.ST_NO + ", " + BibleTable.ST_TEXT + " FROM " +
                    BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN + " order by " + BibleTable.ST_NO;
            Cursor cursor = App.getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(book_kn + i)});

            final int ST_NO_INDEX = cursor.getColumnIndex(BibleTable.ST_NO);
            final int ST_TEXT_INDEX = cursor.getColumnIndex(BibleTable.ST_TEXT);

            List<BibleModel> contextList = new ArrayList<>();

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                BibleModel bibleModel = new BibleModel();
                bibleModel.stNo = cursor.getInt(ST_NO_INDEX);
                bibleModel.stText = cursor.getString(ST_TEXT_INDEX);
                contextList.add(bibleModel);

                cursor.moveToNext();
            }

            cursor.close();

            bibleList.put(i, contextList);
        }

        return new TreeMap<>(bibleList);
    }

    public static List<Integer> getListContextStNoSorted(String book_kn, int book_parts) {
        List<Integer> bibleList = new ArrayList<>();

        for (int i = 1; i < book_parts + 1; i++) {
            String sql = "SELECT " + BibleTable.ST_NO + " FROM " +
                    BibleTable.TABLE_NAME + " WHERE " + BibleTable.WHERE_KN + " order by " + BibleTable.ST_NO;
            Cursor cursor = App.getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(book_kn + i)});

            final int ST_NO_INDEX = cursor.getColumnIndex(BibleTable.ST_NO);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                bibleList.add(cursor.getInt(ST_NO_INDEX));

                cursor.moveToNext();
            }

            cursor.close();
        }

        return bibleList;
    }
}
