package com.neonatal.app.src;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.neonatal.app.src.adapters.MilestoneAdapter;
import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.Event;
import com.neonatal.app.src.entity.JournalEntry;
import com.neonatal.app.src.entity.Milestone;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateJournalActivity extends DrawerActivity {
    NeonatalApp app;
    AppDatabase db;

    private ImageView mImageView;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;

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

        ArrayList<Milestone> milestones = new ArrayList(db.milestoneDAO().getAll());

        MilestoneAdapter adapter = new MilestoneAdapter(
                this, android.R.layout.simple_spinner_item, milestones);

        Spinner spinner_milestone = (Spinner) findViewById(R.id.spinner_milestone);
        spinner_milestone.setAdapter(adapter);

        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.setClickable(true);

        mImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (cameraIntent.resolveActivity(getPackageManager()) != null) {

                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                    }

                    if (photoFile != null) {
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            }
        });

    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
                mImageView.setImageBitmap(mImageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public void pickDate(View view) {
        new DatePickerDialog(CreateJournalActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        EditText editText = (EditText) findViewById(R.id.editText_journalDate);
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(myCalendar.getTime()));
    }

    public void save(View view) {
        EditText editText_bodyText = (EditText) findViewById(R.id.editText_bodyText);
        EditText editText_journalDate = (EditText) findViewById(R.id.editText_journalDate);
        Spinner spinner_milestone = (Spinner) findViewById(R.id.spinner_milestone);

        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setImagePath("/internal/storage/Pictures");
        journalEntry.setBodyText(editText_bodyText.getText().toString());
        journalEntry.setMilestoneId(((Milestone) spinner_milestone.getSelectedItem()).getId());
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
