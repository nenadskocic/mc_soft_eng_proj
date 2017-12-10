package com.softwareuiteam.a000355473.uidesign.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.softwareuiteam.a000355473.uidesign.entity.Person;

/**
 * Created by Maurice on 11/30/2017.
 */

@Dao
public interface PersonDAO {
    @Query("SELECT * FROM Person")
    List<Person> getAll();

    @Insert
    void insertAll(List<Person> people);

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);
}
