package com.softwareuiteam.a000355473.uidesign;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import com.softwareuiteam.a000355473.uidesign.database.AppDatabase;

public class NeonatoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neonato);

        AppDatabase database = Room.databaseBuilder(
                                getApplicationContext(),
                                AppDatabase.class,
                                "NEONATAL_DB").build();

        //List<User> users = com.softwareuiteam.a000355473.uidesign.database.get().getDB().UserDAO().getAll();
    }

    public void Register(View v){
        startActivity(new Intent(NeonatoActivity.this, RegisterActivity.class));
        this.finish();
    }

    public void Login(View v){
        //com.softwareuiteam.a000355473.uidesign.database validation in here
        startActivity(new Intent(NeonatoActivity.this, PatientProfileActivity.class));
        this.finish();
    }
}
