package ekzeget.ru.ekzeget.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ekzeget.ru.ekzeget.App;

public class DbProvider extends ContentProvider {

    private static final boolean COPY_FROM_ASSETS = true;
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    @Override
    public boolean onCreate() {
        //this.dbHelper = new DbHelper(getContext(), COPY_FROM_ASSETS);
        if (this.db == null) {
            return false;
        }
        return COPY_FROM_ASSETS;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        this.db = this.dbHelper.getReadableDatabase();
        Cursor c;

        c = this.db.query(DbHelper.BIBLE_TABLE, null, null, null, null, null, null);
        c.setNotificationUri(App.getContext().getContentResolver(), uri);

        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
