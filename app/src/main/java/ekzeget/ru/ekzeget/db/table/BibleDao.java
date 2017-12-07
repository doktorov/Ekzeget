package ekzeget.ru.ekzeget.db.table;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import io.reactivex.Flowable;

@Dao
public interface BibleDao {
    @Query("SELECT * FROM Bible LIMIT 1")
    Flowable<Bible> getBible();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBible(Bible bible);

    @Query("DELETE FROM Bible")
    void deleteAllBibles();
}
