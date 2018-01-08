package com.neonatal.app.src.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.neonatal.app.src.entity.Event;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface EventDAO {
    @Query("SELECT * FROM Event")
    List<Event> getAll();

    @Query("SELECT * FROM Event WHERE personId = :personId ORDER BY eventDateTime DESC")
    List<Event> getPatientsEvents(int personId);

    @Query("SELECT eventChildId FROM Event WHERE personId = :personId AND eventType LIKE :eventType ORDER BY eventDateTime DESC")
    List<Integer> getPatientsJournalEvents(int personId, String eventType);

    @Query("SELECT * FROM Event WHERE eventChildId = :childId AND eventType  LIKE :eventType ")
    List<Event> getEventByChildId(int childId, String eventType);

    @Insert
    void insertAll(Event... events);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);
}
