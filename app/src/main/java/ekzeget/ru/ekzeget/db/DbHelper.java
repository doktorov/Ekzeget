package ekzeget.ru.ekzeget.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import ekzeget.ru.ekzeget.db.table.BooksTable;
import ekzeget.ru.ekzeget.db.table.VersesTable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ekzeget.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context, boolean loadFromAssets) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        if (loadFromAssets) {
            copyDataBaseFromAssets(context);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(BooksTable.CREATE_TABLE);
        //db.execSQL(BooksTable.DEFAULT_VALUES.toString());
        //db.execSQL(VersesTable.CREATE_TABLE);
        //db.execSQL(VersesTable.DEFAULT_VALUES.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(BooksTable.DROP_TABLE);
        //db.execSQL(VersesTable.DROP_TABLE);

        //onCreate(db);
    }

    private void copyDataBaseFromAssets(Context context) {
        File db_file = new File(context.getApplicationInfo().dataDir + "/databases/" + DATABASE_NAME);
        if (db_file.exists()) {
            return;
        }
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            writeInputStream(inputStream, db_file);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeInputStream(InputStream is, File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
        while (true) {
            int length = is.read(buffer);
            if (length > 0) {
                fos.write(buffer, 0, length);
            } else {
                fos.flush();
                fos.close();
                return;
            }
        }
    }
}
