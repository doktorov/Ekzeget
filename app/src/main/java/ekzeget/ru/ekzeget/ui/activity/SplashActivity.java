package ekzeget.ru.ekzeget.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import ekzeget.ru.ekzeget.R;
import ekzeget.ru.ekzeget.db.DbHelper;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (!checkDataBase()) {
            final Observable<String> operationObservable = Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    subscriber.onNext(onUnzipZip());
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

            operationObservable.subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(String value) {
                    showMain();
                }
            });
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showMain();
                }
            }, 5 * 1000);
        }
    }

    private void showMain() {
        Intent intent = new Intent(this, MainCheesesActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean checkDataBase() {
        File DbFile = new File(DbHelper.DATABASE_PATH + DbHelper.DATABASE_NAME);
        return DbFile.exists();
    }

    public String onUnzipZip() {
        try {
            InputStream is = getAssets().open(DbHelper.PACK_DATABASE_NAME);
            File db_path = getDatabasePath(DbHelper.DATABASE_NAME);
            if (!db_path.exists())
                db_path.getParentFile().mkdirs();
            OutputStream os = new FileOutputStream(db_path);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
