package com.softwareuiteam.a000355473.uidesign.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.softwareuiteam.a000355473.uidesign.entity.CalendarNote;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface CalendarNoteDAO {
    @Query("SELECT * FROM CalendarNote")
    List<CalendarNote> getAll();

    @Insert
    void insertAll(List<CalendarNote> calendarNotes);

    @Update
    void update(CalendarNote calendarNote);

    @Delete
    void delete(CalendarNote calendarNote);
}
