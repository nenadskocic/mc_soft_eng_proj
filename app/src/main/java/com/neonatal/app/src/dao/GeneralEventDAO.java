package com.softwareuiteam.a000355473.uidesign.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.softwareuiteam.a000355473.uidesign.entity.GeneralEvent;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface GeneralEventDAO {
    @Query("SELECT * FROM GeneralEvent")
    List<GeneralEvent> getAll();

    @Insert
    void insertAll(List<GeneralEvent> generalEvents);

    @Update
    void update(GeneralEvent generalEvent);

    @Delete
    void delete(GeneralEvent generalEvent);
}
