package com.softwareuiteam.a000355473.uidesign.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "GeneralEventParticipant")
public class GeneralEventParticipant {
    @PrimaryKey(autoGenerate = true)
    private int generalEventParticipantId;

    @ColumnInfo(name="generalEventId")
    private int generalEventId;

    @ColumnInfo(name="personId")
    private int personId;

    public int getGeneralEventParticipantId() {
        return generalEventParticipantId;
    }

    public void setGeneralEventParticipantId(int generalEventParticipantId) {
        this.generalEventParticipantId = generalEventParticipantId;
    }

    public int getGeneralEventId() {
        return generalEventId;
    }

    public void setGeneralEventId(int generalEventId) {
        this.generalEventId = generalEventId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}