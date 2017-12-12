package com.neonatal.app.src;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class PatientMenuActivity extends AppCompatActivity {
    Intent parentIntent;
    ArrayList<?> patients;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_menu);

        parentIntent = getIntent();
        patients = parentIntent.getStringArrayListExtra("patients");
        index = parentIntent.getIntExtra("patient_id",0);
        TextView titleText = (TextView) findViewById(R.id.titleText);

        titleText.setText(patients.get(index).toString());
    }

    public void ViewHistory(View v){
        startActivity(new Intent(PatientMenuActivity.this, PatientHistoryActivity.class));
    }

    public void ViewCalendar(View v){
        startActivity(new Intent(PatientMenuActivity.this, CalendarViewActivity.class));
    }

    public void ViewJournal(View v){
        Intent childIntent = new Intent(PatientMenuActivity.this, PatientHistoryActivity.class);
        childIntent.putExtra("patient_id", index);
        childIntent.putExtra("patients", patients);
        startActivity(childIntent);
    }
}
