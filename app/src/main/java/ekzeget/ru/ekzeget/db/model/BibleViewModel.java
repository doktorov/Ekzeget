package ekzeget.ru.ekzeget.db.model;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import ekzeget.ru.ekzeget.model.Bible;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class BibleViewModel extends ViewModel {

    private final BibleDataSource mDataSource;

    private ekzeget.ru.ekzeget.db.table.Bible mBible;
    private List<ekzeget.ru.ekzeget.db.table.Bible> mList;

    BibleViewModel(BibleDataSource dataSource) {
        mDataSource = dataSource;
    }

    public Flowable<String> getBibleName() {
        return mDataSource.getBible()
                .map(user -> {
                    mBible = user;
                    return user.getStText();
                });

    }

    public Flowable<List<ekzeget.ru.ekzeget.db.table.Bible>> getList() {
        return mDataSource.getList().map(bibles -> bibles);
    }

//    public Completable updateUserName(final String userName) {
//        return Completable.fromAction(() -> {
////            mBible = mBible == null
////                    ? new ekzeget.ru.ekzeget.db.table.Bible(userName)
////                    : new ekzeget.ru.ekzeget.db.table.Bible(mBible.getId(), userName);
////
////            mDataSource.insertOrUpdateBible(mBible);
//        });
//    }
}
