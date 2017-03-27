package ekzeget.ru.ekzeget.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.util.ArrayList;

public class DbQuery {
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public DbQuery(Context context) {
        dbHelper = new DbHelper(context);
    }

    public DbQuery createDatabase() throws SQLException {
        return this;
    }

    public DbQuery open() throws SQLException {
        try {
            dbHelper.openDataBase();
            dbHelper.close();
            db = dbHelper.getReadableDatabase();
        } catch (SQLException ignored) {
        }
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<String> getCategories() {
        String sql = "SELECT * FROM bible ";
        Cursor cursor = db.rawQuery(sql, null);
        try {
            ArrayList<String> categoriesName = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    categoriesName.add(cursor.getString(2));
                } while (cursor.moveToNext());
            }
            return categoriesName;
        } catch (Exception ignored) {
        } finally {
            cursor.close();
        }
        return null;
    }
}
