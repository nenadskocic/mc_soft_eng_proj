package entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "DataEntry")
public class DataEntry {
    @PrimaryKey(autoGenerate = true)
    private int dataEntryId;
}