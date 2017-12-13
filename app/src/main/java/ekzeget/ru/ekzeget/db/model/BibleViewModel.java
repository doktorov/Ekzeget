package ekzeget.ru.ekzeget.db.model;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.Flowable;

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
////                    ? new ekzeget.ru.ekzeget.db.table.BibleModel(userName)
////                    : new ekzeget.ru.ekzeget.db.table.BibleModel(mBible.getId(), userName);
////
////            mDataSource.insertOrUpdateBible(mBible);
//        });
//    }
}
