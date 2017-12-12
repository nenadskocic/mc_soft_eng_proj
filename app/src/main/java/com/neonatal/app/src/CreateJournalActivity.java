package com.neonatal.app.src;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateJournalActivity extends DrawerActivity {

    private ImageView mImageView;
    private Bitmap mImageBitmap;

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

    public void imgCamaraAccess(View view) {

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
