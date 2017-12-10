package com.softwareuiteam.a000355473.uidesign.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "CalendarEvent")
public class CalendarEvent {
    @PrimaryKey(autoGenerate = true)
    private int calendarEventId;

    @ColumnInfo(name="personId")
    private int personId;

    @ColumnInfo(name="eventId")
    private int eventId;

    public int getCalendarEventId() {
        return calendarEventId;
    }

    public void setCalendarEventId(int calendarEventId) {
        this.calendarEventId = calendarEventId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}