package entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "CalendarNote")
public class CalendarNote {
    @PrimaryKey(autoGenerate = true)
    private int calendarNoteId;

    @ColumnInfo(name="bodyText")
    private String bodyText;

    public int getCalendarNoteId() {
        return calendarNoteId;
    }

    public void setCalendarNoteId(int calendarNoteId) {
        this.calendarNoteId = calendarNoteId;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }
}