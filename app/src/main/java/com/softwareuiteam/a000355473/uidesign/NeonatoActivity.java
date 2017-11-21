package com.softwareuiteam.a000355473.uidesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NeonatoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neonato);
    }

    public void Register(View v){
        startActivity(new Intent(NeonatoActivity.this, RegisterActivity.class));
        this.finish();
    }

    public void Login(View v){
        //database validation in here
        startActivity(new Intent(NeonatoActivity.this, PatientProfileActivity.class));
        this.finish();
    }
}
