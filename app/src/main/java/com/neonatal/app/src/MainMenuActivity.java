package com.neonatal.app.src;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.neonatal.app.src.classes.PatientPerson;
import com.neonatal.app.src.entity.Patient;
import com.neonatal.app.src.entity.Person;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    ArrayList<PatientPerson> arrayPatients = new ArrayList<PatientPerson>();
    ArrayAdapter<PatientPerson> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        Patient patient = new Patient();
        patient.setUserId(1);

        Person person = new Person();
        person.setFirstName("Susan");
        person.setLastName("Example");

        arrayPatients.add(new PatientPerson(patient, person));

        adapter = new ArrayAdapter<PatientPerson>(this, android.R.layout.simple_list_item_1, arrayPatients);
        ListView listViewPatients = (ListView) findViewById(R.id.listViewPatients);
        listViewPatients.setAdapter(adapter);
        listViewPatients.setOnItemClickListener(this);
    }

    public void NicuVideo(View v){
        //startActivity(new Intent(MainMenuActivity.this, NicuVideoActivity.class));
        //this.finish();
    }

    public void NewPatient(View v){
        startActivity(new Intent(MainMenuActivity.this, PatientProfileActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (! (position > arrayPatients.size()))
        {
            Intent displayIntent = new Intent(this, PatientMenuActivity.class);
            displayIntent.putExtra("patient_id", position);
            displayIntent.putExtra("patients", arrayPatients);
            startActivity(displayIntent);
        }
    }
}
