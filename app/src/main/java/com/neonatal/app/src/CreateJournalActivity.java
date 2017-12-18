package com.neonatal.app.src;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateJournalActivity extends DrawerActivity {

    private ImageView mImageView;
    private Bitmap mImageBitmap;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_journal);
        super.onCreateDrawer();
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_create_journal);
        View inflated = stub.inflate();
        CheckBox chbxTreatment = (CheckBox)findViewById(R.id.checkBox);
        chbxTreatment.setChecked(false);
        EditText text_treatment = (EditText)findViewById(R.id.editTextTreatment);
        EditText text_Type = (EditText)findViewById(R.id.editTextType);
        EditText text_Reason = (EditText)findViewById(R.id.editTextReason);
        text_treatment.setVisibility(View.GONE);
        text_Type.setVisibility(View.GONE);
        text_Reason.setVisibility(View.GONE);
        EditText editTextact = (EditText)findViewById(R.id.editTextActivity) ;
        EditText editTextDate = (EditText)findViewById(R.id.editTextJurnalDate) ;
        editTextact.requestFocus();
    }

    public void showTreatment(View view) {
        CheckBox chbxTreatment = (CheckBox)findViewById(R.id.checkBox);
        EditText text_treatment = (EditText)findViewById(R.id.editTextTreatment);
        EditText text_Type = (EditText)findViewById(R.id.editTextType);
        EditText text_Reason = (EditText)findViewById(R.id.editTextReason);

        if(!chbxTreatment.isChecked()){
            text_treatment.setVisibility(View.GONE);
            text_Type.setVisibility(View.GONE);
            text_Reason.setVisibility(View.GONE);

        }else{
            text_treatment.setVisibility(View.VISIBLE);
            text_Type.setVisibility(View.VISIBLE);
            text_Reason.setVisibility(View.VISIBLE);
        }
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    public void imgCameraAccess(View view) {

    }

    public void datepickerFunc(View view) {
        new DatePickerDialog(CreateJournalActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        EditText editText = (EditText)findViewById(R.id.editTextJurnalDate) ;
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(myCalendar.getTime()));
    }
    //@Override
    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }*/
}
