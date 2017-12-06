package dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import entity.User;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface UserDAO {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Insert
    void insertAll(List<User> users);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
