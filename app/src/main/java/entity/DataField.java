package entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "DataField")
public class DataField {
    @PrimaryKey(autoGenerate = true)
    private int dataFieldId;

    @ColumnInfo(name="description")
    private String description;

    public int getDataFieldId() {
        return dataFieldId;
    }

    public void setDataFieldId(int dataFieldId) {
        this.dataFieldId = dataFieldId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}