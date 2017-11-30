package entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "Person")
public class Person {
    @PrimaryKey(autoGenerate = true)
    private int personId;

    @ColumnInfo(name = "firstName")
    private String firstName;

    public int getPersonId()
    {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
}
