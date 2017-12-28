package ekzeget.ru.ekzeget.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipInputStream;

import ekzeget.ru.ekzeget.App;
import ekzeget.ru.ekzeget.db.DbHelper;
import ekzeget.ru.ekzeget.mvp.views.SplashView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class SplashPresenter extends MvpPresenter<SplashView> {
    private io.reactivex.Observable<String> getObservable() {
        return io.reactivex.Observable.just("Cricket", "Football");
    }

    public void checkDataBaseBeforeStart() {
        getViewState().showProgress();

        if (!checkDataBase()) {
            getObservable()
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getObserver());
        } else {
            getViewState().hideProgress();

            getViewState().delayShowMain();
        }
    }

    private boolean checkDataBase() {
        File db_path = App.getContext().getDatabasePath(DbHelper.DATABASE_NAME);
        return db_path.exists();
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                onUnzipZip();
            }

            @Override
            public void onNext(String value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                getViewState().showMain();
            }
        };
    }

    private String onUnzipZip() {
        try {
            InputStream is = App.getContext().getAssets().open(DbHelper.PACK_DATABASE_NAME);
            File db_path = App.getContext().getDatabasePath(DbHelper.DATABASE_NAME);
            if (!db_path.exists())
                db_path.getParentFile().mkdirs();
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
