package com.softwareuiteam.a000355473.uidesign.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.softwareuiteam.a000355473.uidesign.entity.JournalEntry;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface JournalEntryDAO {
    @Query("SELECT * FROM JournalEntry")
    List<JournalEntry> getAll();

    @Insert
    void insertAll(List<JournalEntry> journalEntries);

    @Update
    void update(JournalEntry journalEntry);

    @Delete
    void delete(JournalEntry journalEntry);
}
