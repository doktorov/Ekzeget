package ekzeget.ru.ekzeget.db.queries;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ekzeget.ru.ekzeget.App;
import ekzeget.ru.ekzeget.db.table.CommonTable;
import ekzeget.ru.ekzeget.model.Common;

public class CommonQueries {
    public static List<Common> getListContents(String book_kn) {
        List<Common> commonList = new ArrayList<>();

        String sql = "SELECT " + CommonTable.NAME + ", " + CommonTable.TEXT +
                " FROM " + CommonTable.TABLE_NAME + " WHERE " + CommonTable.WHERE_KN + " order by " + CommonTable.ID ;
        Cursor cursor= App.getReadableDatabase().rawQuery(sql, new String[] { String.valueOf(book_kn) });

        final int NAME_INDEX = cursor.getColumnIndex(CommonTable.NAME);
        final int TEXT_INDEX = cursor.getColumnIndex(CommonTable.TEXT);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Common common = new Common();
            common.name = cursor.getString(NAME_INDEX);
            common.text = cursor.getString(TEXT_INDEX);
            commonList.add(common);

            cursor.moveToNext();
        }

        cursor.close();

        return commonList;
    }
}
