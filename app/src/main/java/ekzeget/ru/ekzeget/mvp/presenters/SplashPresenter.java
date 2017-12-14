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
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

@InjectViewState
public class SplashPresenter extends MvpPresenter<SplashView> {
    public void checkDataBaseBeforeStart() {
        getViewState().showProgress();

        if (!checkDataBase()) {
//            final Observable<String> operationObservable = Observable.create((Observable.OnSubscribe<String>) subscriber -> {
//                subscriber.onNext(onUnzipZip());
//                subscriber.onCompleted();
//            }).subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread());

            final Observable<String> operationObservable = Observable.create((Observable.OnSubscribe<String>) subscriber -> {
                subscriber.onNext(onUnzipZip());
                subscriber.onCompleted();
            }).subscribeOn(Schedulers.io());

            operationObservable.subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(String value) {
                    getViewState().showMain();
                }
            });
        } else {
            getViewState().hideProgress();

            getViewState().delayShowMain();
        }
    }

    private boolean checkDataBase() {
        File db_path = App.getContext().getDatabasePath(DbHelper.DATABASE_NAME);
        return db_path.exists();
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
