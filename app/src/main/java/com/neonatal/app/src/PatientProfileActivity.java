package com.neonatal.app.src;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.RadioButton;

public class PatientProfileActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateDrawer();
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_patient_profile);
        View inflated = stub.inflate();
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
}
