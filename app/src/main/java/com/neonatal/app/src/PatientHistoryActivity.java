package com.neonatal.app.src;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PatientHistoryActivity extends AppCompatActivity {
    ArrayList<String> patient_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_history);

        Intent intent = getIntent();
        ArrayList<?> patients = intent.getStringArrayListExtra("patients");
        int index = intent.getIntExtra("patient_id",0);
        TextView txtName = (TextView) findViewById(R.id.txtName);

        String testString = patients.get(index).toString();

        txtName.setText("Patient History: " + patients.get(index).toString());

        this.patient_history = new ArrayList<>();

        this.patient_history.add("Appointment 1 - 2017/10/12");
        this.patient_history.add("Appointment 2 - 2017/10/24");
        this.patient_history.add("Appointment 3 - 2017/11/6");
        this.patient_history.add("Appointment 4 - 2017/11/18");

        ListView lstHistory = (ListView) findViewById(R.id.lstHistory);

        ArrayAdapter historyAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, this.patient_history);
        lstHistory.setAdapter(historyAdapter);
    }
}
