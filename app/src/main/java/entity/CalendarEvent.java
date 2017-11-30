package entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "CalendarEvent")
public class CalendarEvent {
    @PrimaryKey(autoGenerate = true)
    private int calendarEventId;
}