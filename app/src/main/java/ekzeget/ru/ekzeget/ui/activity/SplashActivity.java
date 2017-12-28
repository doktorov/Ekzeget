package ekzeget.ru.ekzeget.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

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
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//public class SplashActivity extends MvpAppCompatActivity implements SplashView {
//    @InjectPresenter
//    SplashPresenter mSplashPresenter;
//
//    @BindView(R.id.progressBarView)
//    View mProgress;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_splash);
//
//        ButterKnife.bind(this);
//
//        mSplashPresenter.checkDataBaseBeforeStart();
//    }
//
//    @Override
//    public void showProgress() {
//        mProgress.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void hideProgress() {
//        mProgress.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void delayShowMain() {
//        new Handler().postDelayed(this::showMain, 1000);
//    }
//
//    @Override
//    public void showMain() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
//}

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (!checkDataBase()) {
            LinearLayout progress = findViewById(R.id.progressBarView);
            progress.setVisibility(View.VISIBLE);

            getObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getObserver());
        } else {
            new Handler().postDelayed(this::showMain, 1000);
        }
    }

    private Observable<String> getObservable() {
        return Observable.create(subscriber -> {
            subscriber.onNext(onUnzipZip());
            subscriber.onComplete();
        });
    }

    private void showMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean checkDataBase() {
        File db_path = getDatabasePath(DbHelper.DATABASE_NAME);
        return db_path.exists();
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                showMain();
            }
        };
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

