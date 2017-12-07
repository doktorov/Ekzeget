package ekzeget.ru.ekzeget.db.model;

import ekzeget.ru.ekzeget.db.table.Bible;
import ekzeget.ru.ekzeget.db.table.BibleDao;
import io.reactivex.Flowable;

public class LocalBibleDataSource implements BibleDataSource {
    private final BibleDao mBibleDao;

    public LocalBibleDataSource(BibleDao userDao) {
        mBibleDao = userDao;
    }

    @Override
    public Flowable<Bible> getBible() {
        return mBibleDao.getBible();
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
