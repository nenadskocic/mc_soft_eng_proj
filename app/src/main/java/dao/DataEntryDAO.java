package dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import entity.DataEntry;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface DataEntryDAO {
    @Query("SELECT * FROM DataEntry")
    List<DataEntry> getAll();

    @Insert
    void insertAll(List<DataEntry> dataEntries);

    @Update
    void update(DataEntry dataEntry);

    @Delete
    void delete(DataEntry dataEntry);
}
