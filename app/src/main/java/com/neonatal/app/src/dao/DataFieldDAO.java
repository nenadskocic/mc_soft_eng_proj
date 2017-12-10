package com.softwareuiteam.a000355473.uidesign.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.softwareuiteam.a000355473.uidesign.entity.DataField;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface DataFieldDAO {
    @Query("SELECT * FROM DataField")
    List<DataField> getAll();

    @Insert
    void insertAll(List<DataField> dataFields);

    @Update
    void update(DataField dataField);

    @Delete
    void delete(DataField dataField);
}
