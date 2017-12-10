package com.neonatal.app.src.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "JournalEntry")
public class JournalEntry {
    @PrimaryKey(autoGenerate = true)
    private int journalEntryId;

    @ColumnInfo(name="imagePath")
    private String imagePath;

    @ColumnInfo(name="bodyText")
    private String bodyText;

    @ColumnInfo(name="milestoneId")
    private int milestoneId;

    public int getJournalEntryId() {
        return journalEntryId;
    }

    public void setJournalEntryId(int journalEntryId) {
        this.journalEntryId = journalEntryId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public int getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(int milestoneId) {
        this.milestoneId = milestoneId;
    }
}