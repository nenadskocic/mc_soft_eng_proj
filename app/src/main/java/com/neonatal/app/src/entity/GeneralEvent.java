package com.neonatal.app.src.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "GeneralEvent")
public class GeneralEvent {
    @PrimaryKey(autoGenerate = true)
    private int generalEventId;

    @ColumnInfo(name="location")
    private String location;

    @ColumnInfo(name="description")
    private String description;

    public int getGeneralEventId() {
        return generalEventId;
    }

    public void setGeneralEventId(int generalEventId) {
        this.generalEventId = generalEventId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}