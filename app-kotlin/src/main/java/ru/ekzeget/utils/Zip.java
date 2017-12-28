package ru.ekzeget.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.zip.ZipInputStream;

import io.reactivex.Observable;
import ru.ekzeget.App;
import ru.ekzeget.db.DbHelper;

public class Zip {
    public Observable<String> onUnzipZip() {
        try {
            InputStream is = Objects.requireNonNull(App.Companion.getContext()).getAssets().open(DbHelper.PACK_DATABASE_NAME);
            File db_path = App.Companion.getContext().getDatabasePath(DbHelper.DATABASE_NAME);
            if (db_path != null && !db_path.exists()) {
                db_path.getParentFile().mkdirs();
            }
            if (db_path != null) {
                OutputStream os = new FileOutputStream(db_path);

                ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is));
                while (zis.getNextEntry() != null) {
                    byte[] buffer = new byte[1024];
                    int count;
                    while ((count = zis.read(buffer)) > -1) {
                        os.write(buffer, 0, count);
                    }
                    os.close();
                    zis.closeEntry();
                }
                zis.close();
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
