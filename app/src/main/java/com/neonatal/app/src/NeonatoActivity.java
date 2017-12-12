package com.neonatal.app.src;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.neonatal.app.src.database.AppDatabase;

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
        TextView userName =  (TextView) findViewById(R.id.txt_Username);
        TextView Pwd =  (TextView) findViewById(R.id.txt_password);

        String user = userName.getText().toString();
        String password = Pwd.getText().toString();

        if(user == "admin" && password == "admin")
        {
            startActivity(new Intent(NeonatoActivity.this, PatientProfileActivity.class));
        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = "Wrong username/password";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            
        }

        this.finish();
    }
}
