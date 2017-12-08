package dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import entity.Patient;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface PatientDAO {
    @Query("SELECT * FROM Patient")
    List<Patient> getAll();

    @Insert
    void insertAll(List<Patient> patients);

    @Update
    void update(Patient patient);

    @Delete
    void delete(Patient patient);
}
