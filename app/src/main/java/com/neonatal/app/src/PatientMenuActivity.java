package com.neonatal.app.src;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.neonatal.app.src.classes.PatientPerson;
import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.Patient;
import com.neonatal.app.src.entity.Person;

import java.util.ArrayList;

public class PatientMenuActivity extends AppCompatActivity {
    NeonatalApp app;
    AppDatabase db;
    PatientPerson patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_menu);

        app = ((NeonatalApp) getApplicationContext());
        db = AppDatabase.getAppDatabase(getApplicationContext());

        refreshPatientData();
    }

    private void refreshPatientData() {
        patient = getPatientPerson();

        String patientFullName = patient.getFirstName() + " " + patient.getLastName();

        TextView textView_titleText = (TextView) findViewById(R.id.textView_titleText);
        textView_titleText.setText(patientFullName);
    }

    private PatientPerson getPatientPerson() {
        Patient patient = db.patientDAO().getById(app.getCurrentPatient());
        Person person = db.personDAO().getById(patient.getPersonId());
        return new PatientPerson(patient, person);
    }

    public void ViewHistory(View v){
        Intent childIntent = new Intent(PatientMenuActivity.this, PatientHistoryActivity.class);
        startActivity(childIntent);
    }

    public void ViewJournal(View v){
        startActivity(new Intent(PatientMenuActivity.this, JournalActivity.class));
    }

    public void ViewAppointments(View view) {
        //startActivity(new Intent(PatientMenuActivity.this, AppointmentActivity.class));
    }
}
