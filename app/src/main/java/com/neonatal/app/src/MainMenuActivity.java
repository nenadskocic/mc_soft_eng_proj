package com.neonatal.app.src;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
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
