package com.neonatal.app.src;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.DataField;
import com.neonatal.app.src.entity.Person;
import com.neonatal.app.src.entity.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DataEntryActivity extends DrawerActivity {

    private ImageView mImageView;
    private Bitmap mImageBitmap;
    Calendar myCalendar = Calendar.getInstance();
    AppDatabase db = null;

    EditText text_treatment = null;
    EditText text_Type = null;
    EditText text_Reason = null;
    EditText editTextact = null;
    EditText editTextDate = null;

    long[] idField = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_journal);
        super.onCreateDrawer();
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_data_entry);
        View inflated = stub.inflate();
        //////////////////////////////database////////////////////////////////////////
        db = AppDatabase.getAppDatabase(getApplicationContext());
        /////////////////////////////elements////////////////////////////////////////
        CheckBox chbxTreatment = (CheckBox)findViewById(R.id.checkBox);
        chbxTreatment.setChecked(false);
        text_treatment = (EditText)findViewById(R.id.editTextTreatment);
        text_Type = (EditText)findViewById(R.id.editTextType);
        text_Reason = (EditText)findViewById(R.id.editTextReason);

        text_treatment.setVisibility(View.GONE);
        text_Type.setVisibility(View.GONE);
        text_Reason.setVisibility(View.GONE);

        editTextact = (EditText)findViewById(R.id.editTextActivity) ;
        editTextDate = (EditText)findViewById(R.id.editTextJurnalDate) ;

        editTextact.requestFocus();

        String[] dataFielddescription = new String[]{"height", "weight"};
        List<DataField> dataFields_list = queryDataField(db);
        int index = 0;
        if(dataFields_list.size() > 0){
            for(DataField df : dataFields_list){
                if(index < dataFielddescription.length){
                    String discription  = dataFielddescription[index];
                    if(!discription.equals(df.getDescription())){
                        df.setDescription(discription);
                        insertDataField(db,df);
                    }
                }
                index++;
            }
        }else{
            for(DataField df : dataFields_list){
                if(index < dataFielddescription.length){
                    String discription  = dataFielddescription[index];
                    df.setDescription(discription);
                    insertDataField(db,df);
                }
                index++;
            }
        }

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
        new DatePickerDialog(DataEntryActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        EditText editText = (EditText)findViewById(R.id.editTextJurnalDate) ;
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(myCalendar.getTime()));
    }

    public void saveData(View view) {


    }

    public void upDateData(View view) {
    }

    public void discardData(View view) {
    }

    private List<DataField> queryDataField(final  AppDatabase db){
        return db.dataFieldDAO().getAll();
    }

    private long[] insertDataField(final AppDatabase db, DataField df){
        return db.dataFieldDAO().insertAll(df);
    }

   /* private static User addUser(final AppDatabase db, User user) {
        db.userDAO().insertAll(user);
        return user;
    }

    private static long addPerson(final AppDatabase db, Person person) {
        return  db.personDAO().insertAll(person)[0];
    }*/

    //@Override
    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }*/
}
