package com.neonatal.app.src;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {
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
    }

    public void NicuVideo(View v){
        //startActivity(new Intent(MainMenuActivity.this, NicuVideoActivity.class));
        //this.finish();
    }

    public void NewPatient(View v){
        //com.softwareuiteam.a000355473.uidesign.database validation in here
        startActivity(new Intent(MainMenuActivity.this, PatientProfileActivity.class));
        this.finish();
    }
}
