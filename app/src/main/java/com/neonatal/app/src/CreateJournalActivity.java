package com.neonatal.app.src;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.Event;
import com.neonatal.app.src.entity.JournalEntry;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateJournalActivity extends DrawerActivity {
    NeonatalApp app;
    AppDatabase db;

    private ImageView mImageView;
    private Bitmap mImageBitmap;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateDrawer();

        app = ((NeonatalApp) getApplicationContext());
        db = AppDatabase.getAppDatabase(getApplicationContext());

        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_create_journal);
        View inflated = stub.inflate();


    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    public void imgCameraAccess(View view) {

    }

    public void pickDate(View view) {
        new DatePickerDialog(CreateJournalActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        EditText editText = (EditText)findViewById(R.id.editText_journalDate) ;
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(myCalendar.getTime()));
    }

    public void save(View view) {
        EditText editText_bodyText = (EditText) findViewById(R.id.editText_bodyText);
        EditText editText_journalDate = (EditText) findViewById(R.id.editText_journalDate);

        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setImagePath("");
        journalEntry.setBodyText(editText_bodyText.getText().toString());
        journalEntry.setMilestoneId(0);
        int journalEntryId = (int) db.journalEntryDAO().insertAll(journalEntry)[0];

        Event event = new Event();
        event.setEventDateTime(editText_journalDate.getText().toString());
        event.setPersonId(db.patientDAO().getById(app.getCurrentPatient()).getPersonId());
        event.setEventType("JournalEntry");
        event.setEventChildId(journalEntryId);
        db.eventDAO().insertAll(event);

        this.finish();
    }
}
