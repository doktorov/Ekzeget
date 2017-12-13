package ekzeget.ru.ekzeget.db.model;

import java.util.List;

import ekzeget.ru.ekzeget.db.table.Bible;
import io.reactivex.Flowable;

public interface BibleDataSource {
    Flowable<Bible> getBible();

    Flowable<List<ekzeget.ru.ekzeget.db.table.Bible>> getList();

    void insertOrUpdateBible(Bible bible);

    void deleteAllBibles();
}
