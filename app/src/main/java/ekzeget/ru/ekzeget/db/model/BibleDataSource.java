package ekzeget.ru.ekzeget.db.model;

import ekzeget.ru.ekzeget.db.table.Bible;
import io.reactivex.Flowable;

public interface BibleDataSource {
    Flowable<Bible> getBible();

    void insertOrUpdateBible(Bible bible);

    void deleteAllBibles();
}
