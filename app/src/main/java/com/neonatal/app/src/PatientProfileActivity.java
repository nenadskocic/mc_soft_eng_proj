package com.neonatal.app.src;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;

public class PatientProfileActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateDrawer();
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_patient_profile);
        View inflated = stub.inflate();

        Spinner spinnerWeeks = (Spinner)findViewById(R.id.spinnerWeeks);
        spinnerWeeks.setVisibility(View.GONE);
    }

    public void updateselected(View view) {
        RadioButton radioButton = findViewById(R.id.radioButton3);
        if(radioButton.isChecked()){
            radioButton.setChecked(false);
        }
    }

    public void addselected(View view) {

        RadioButton radioButton = findViewById(R.id.radioButton4);
        if(radioButton.isChecked()){
            radioButton.setChecked(false);
        }
    }

    public void newPatient(View view) {

    }

    public void hidePreMatureWeeks(View view) {
        CheckBox prechk = (CheckBox) findViewById(R.id.PreMature);
        Spinner spinnerWeeks = (Spinner)findViewById(R.id.spinnerWeeks);

        if(!prechk.isChecked()){
            spinnerWeeks.setVisibility(View.GONE);
        }else{
            spinnerWeeks.setVisibility(View.VISIBLE);
        }

    }
}
