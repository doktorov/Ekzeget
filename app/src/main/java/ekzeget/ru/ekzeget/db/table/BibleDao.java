package ekzeget.ru.ekzeget.db.table;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface BibleDao {
    @Query("SELECT * FROM Bible LIMIT 1")
    Flowable<Bible> getBible();

    @Query("SELECT * FROM Bible where kn='mf1'")
    Flowable<List<Bible>> getList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBible(Bible bible);

    @Query("DELETE FROM Bible")
    void deleteAllBibles();
}
