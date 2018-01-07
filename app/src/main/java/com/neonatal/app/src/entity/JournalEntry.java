package com.neonatal.app.src.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Date;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "JournalEntry"
        //foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")}
        )

/*


 */
public class JournalEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="imagePath")
    private String imagePath;

    @ColumnInfo(name="bodyText")
    private String bodyText;

    @ColumnInfo(name="milestoneId")
    private int milestoneId;

    @ColumnInfo(name="date")
    private String date;

    @ColumnInfo(name="userId")
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDate() { return date; }

    public void  setDate(String date) { this.date = date; }

    public int getUserId() { return  userId; }

    public void setUserId(int userId) { this.userId = id; }
}