package com.neonatal.app.src.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.neonatal.app.src.dao.*;
import com.neonatal.app.src.entity.*;

/**
 * Created by Maurice on 11/30/2017.
 */

@Database(entities = {
        CalendarEvent.class,
        CalendarNote.class,
        DataEntry.class,
        DataField.class,
        Event.class,
        GeneralEvent.class,
        GeneralEventParticipant.class,
        JournalEntry.class,
        Milestone.class,
        Notification.class,
        Patient.class,
        Person.class,
        User.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CalendarEventDAO calendarEventDAO();
    public abstract CalendarNoteDAO calendarNoteDAO();
    public abstract DataEntryDAO dataEntryDAO();
    public abstract DataFieldDAO dataFieldDAO();
    public abstract EventDAO eventDAO();
    public abstract GeneralEventDAO generalEventDAO();
    public abstract GeneralEventParticipantDAO generalEventParticipantDAO();
    public abstract JournalEntryDAO journalEntryDAO();
    public abstract MilestoneDAO milestoneDAO();
    public abstract NotificationDAO notificationDAO();
    public abstract PatientDAO patientDAO();
    public abstract PersonDAO personDAO();
    public abstract UserDAO userDAO();
}
