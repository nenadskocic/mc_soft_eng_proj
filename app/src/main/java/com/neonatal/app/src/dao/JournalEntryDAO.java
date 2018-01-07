package com.neonatal.app.src.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.neonatal.app.src.entity.JournalEntry;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface JournalEntryDAO {
    @Query("SELECT * FROM JournalEntry")
    List<JournalEntry> getAll();

    @Query("SELECT * FROM JournalEntry WHERE date LIKE :date AND userId = :userId ORDER BY date DESC")
    List<JournalEntry> getByDateAndId( String date, int userId);

    @Query("SELECT * FROM JournalEntry WHERE userId = :userId ORDER BY date DESC")
    List<JournalEntry> getAllById( int userId);

     @Query("SELECT * FROM JournalEntry WHERE date LIKE :date  ORDER BY date DESC")
    List<JournalEntry> getByDate( String date);

    @Insert
    void insertAll(JournalEntry... journalEntries);

    @Update
    void update(JournalEntry journalEntry);

    @Delete
    void delete(JournalEntry journalEntry);
}
