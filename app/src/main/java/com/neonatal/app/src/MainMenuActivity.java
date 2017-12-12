package com.neonatal.app.src;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    ArrayList<String> arrayPatients = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        arrayPatients.add("Susan Example");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayPatients);
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
        this.finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (! (position > arrayPatients.size()))
        {
            Intent displayIntent = new Intent(this, PatientActivity.class);
            displayIntent.putExtra("patient_id", position);
            displayIntent.putExtra("patients", arrayPatients);
            startActivity(displayIntent);
        }
    }
}
