package com.neonatal.app.src.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "DataEntry")
public class DataEntry {
    @PrimaryKey(autoGenerate = true)
    private int dataEntryId;

    @ColumnInfo(name="dataFieldId")
    private int dataFieldId;

    @ColumnInfo(name="dataType")
    private String dataType;

    @ColumnInfo(name="value")
    private String value;

    public int getDataEntryId() {
        return dataEntryId;
    }

    public void setDataEntryId(int dataEntryId) {
        this.dataEntryId = dataEntryId;
    }

    public int getDataFieldId() {
        return dataFieldId;
    }

    public void setDataFieldId(int dataFieldId) {
        this.dataFieldId = dataFieldId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}