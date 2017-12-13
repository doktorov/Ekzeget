package ekzeget.ru.ekzeget.db.model;

import java.util.List;

import ekzeget.ru.ekzeget.db.table.Bible;
import ekzeget.ru.ekzeget.db.table.BibleDao;
import io.reactivex.Flowable;

public class LocalBibleDataSource implements BibleDataSource {
    private final BibleDao mBibleDao;

    LocalBibleDataSource(BibleDao userDao) {
        mBibleDao = userDao;
    }

    @Override
    public Flowable<Bible> getBible() {
        return mBibleDao.getBible();
    }

    @Override
    public Flowable<List<Bible>> getList() {
        return mBibleDao.getList();
    }

    @Override
    public void insertOrUpdateBible(Bible bible) {
        mBibleDao.insertBible(bible);
    }

    @Override
    public void deleteAllBibles() {
        mBibleDao.deleteAllBibles();
    }
}
