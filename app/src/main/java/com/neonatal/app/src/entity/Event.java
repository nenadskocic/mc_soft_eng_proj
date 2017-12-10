package com.softwareuiteam.a000355473.uidesign.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "Event")
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int eventId;

    @ColumnInfo(name="eventDateTime")
    private String eventDateTime;

    @ColumnInfo(name="eventType")
    private String eventType;

    @ColumnInfo(name="eventChildId")
    private int eventChildId;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getEventChildId() {
        return eventChildId;
    }

    public void setEventChildId(int eventChildId) {
        this.eventChildId = eventChildId;
    }
}