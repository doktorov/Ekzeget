package ekzeget.ru.ekzeget.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DbHelper extends SQLiteOpenHelper {
    private static String DATABASE_PATH = "";
    private static final String DATABASE_NAME = "ekzeget.db";
    public static final String BIBLE_TABLE = "bible";

    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase mDataBase;
    private final Context mContext;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DATABASE_PATH = context.getDatabasePath(DATABASE_NAME).getPath();
        this.mContext = context;
    }

    public void createDataBase() throws IOException {
        if (!checkDataBase()) {
            this.getReadableDatabase();

            copyDataBase();

            this.close();
        }
    }

    private boolean checkDataBase() {
        File DbFile = new File(DATABASE_PATH + DATABASE_NAME);
        return DbFile.exists();
    }

    boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DATABASE_PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        SQLiteDatabase.releaseMemory();
        super.close();
    }

    private void copyDataBase() throws IOException {
        InputStream mInput =  mContext.getAssets().open(DATABASE_NAME);
        String outfileName = DATABASE_PATH;
        OutputStream mOutput = new FileOutputStream(outfileName);
        byte[] buffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(buffer))>0) {
            mOutput.write(buffer, 0, mLength);
        }
        mOutput.flush();
        mInput.close();
        mOutput.close();
    }


//    public DbHelper(Context context, boolean loadFromAssets) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        if (loadFromAssets) {
//            copyDataBaseFromAssets(context);
//        }
//    }

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
        if (!db_file.exists()) {
            try {
                InputStream inputStream = context.getAssets().open(DATABASE_NAME);
                writeInputStream(inputStream, db_file);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
