package com.neonatal.app.src;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.DataEntry;
import com.neonatal.app.src.entity.DataField;
import com.neonatal.app.src.entity.Event;
import com.neonatal.app.src.entity.Patient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

public class DataEntryActivity extends DrawerActivity {

    private ImageView mImageView;
    private Bitmap mImageBitmap;
    Calendar myCalendar = Calendar.getInstance();
    AppDatabase db = null;
    NeonatalApp app;

    EditText text_treatment = null;
    EditText text_Type = null;
    EditText text_Reason = null;
    EditText editTextact = null;


    ////////////////////////////////////////////
    //height
    EditText editHeight = null;
    EditText editWeight = null;
    EditText editHead = null;
    EditText editTextDate = null;
    //weight
    ///////////////////////////////////////////

    long[] idField = null;
    String[] dataFielddescription = new String[]{"Height", "Weight", "Head"};
    String[] dataFieldUnit = new String[]{"cm", "lb", "cm"};


    private EditText[] fields = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_journal);
        super.onCreateDrawer();

        app = (NeonatalApp)getApplicationContext();

        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_data_entry);
        View inflated = stub.inflate();
        //////////////////////////////database////////////////////////////////////////
        db = AppDatabase.getAppDatabase(getApplicationContext());
        /////////////////////////////elements////////////////////////////////////////
        CheckBox chbxTreatment = (CheckBox)findViewById(R.id.checkBox);
        chbxTreatment.setChecked(false);
        //comment this line out if you need treatment set
        chbxTreatment.setVisibility(View.GONE);
        text_treatment = (EditText)findViewById(R.id.editTextTreatment);
        text_Type = (EditText)findViewById(R.id.editTextType);
        text_Reason = (EditText)findViewById(R.id.editTextReason);

        text_treatment.setVisibility(View.GONE);
        text_Type.setVisibility(View.GONE);
        text_Reason.setVisibility(View.GONE);

        editTextact = (EditText)findViewById(R.id.editTextHead);
        ///////////////////
        editTextDate = (EditText)findViewById(R.id.editTextJurnalDate);

        editTextact.requestFocus();

        editHead = (EditText)findViewById(R.id.editTextHead);
        editHeight = (EditText)findViewById(R.id.editTextHeight);
        editWeight = (EditText)findViewById(R.id.editTextWeight);


        fields = new EditText[]{
                editHeight,
                editWeight,
                editHead
        };


        List<DataField> dataFields_list = queryDataField(db);
        int index = 0;
        if(dataFields_list.size() > 0){
            for(DataField df : dataFields_list){
                if(index < dataFielddescription.length){
                    String discription  = dataFielddescription[index];
                    if(!discription.equals(df.getDescription())){
                        df.setDescription(discription);
                        df.setDataType(dataFieldUnit[index]);
                        insertDataField(db,df);
                    }
                }
                index++;
            }
        }else{
            for(String discription : dataFielddescription){
                DataField df = new DataField();
                //String discription  = dataFielddescription[index];
                df.setDescription(discription);
                df.setDataType(dataFieldUnit[index]);
                insertDataField(db,df);
                index++;
            }
        }

       /* editTextDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
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
        DatePickerDialog dpd =  new DatePickerDialog(DataEntryActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        dpd.show();
    }

    private void updateLabel() {
        EditText editText = (EditText)findViewById(R.id.editTextJurnalDate) ;
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(myCalendar.getTime()));
    }

    public void saveData(View view) {
        List<DataField> fieldlist = queryDataField(db);

        DataEntry[] dataArray = new DataEntry[fieldlist.size()];



        int index = 0;
        long[] ids = new long[fieldlist.size()];
        for(DataField df : fieldlist){
            dataArray[index] = new DataEntry();
           // if(!fields[index].getText().toString().trim().equals("")){
            for(int i = 0; i < dataFielddescription.length; i++){
                if(df.getDescription().trim().equals(dataFielddescription[i])){
                    dataArray[i].setValue(fields[i].getText().toString());
                    dataArray[i].setDataFieldId(df.getId());
                    ids[index] = db.dataEntryDAO().insertAll(dataArray[i])[0];
                    break;
                }else{
                    continue;
                }
            }
            index++;
        }

        for(int i = 0; i < ids.length; i++){
            Event event = new Event();
            event.setEventType("dataEntry");
            event.setEventDateTime(editTextDate.getText().toString());
            event.setPersonId(app.getCurrentPatient());
            event.setEventChildId((int)ids[i]);
            db.eventDAO().insertAll(event);
        }
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

    public void retriveData(View view) {
        Dictionary<Integer, String> dataDictionary = new Hashtable<>();
        //Patient p = db.patientDAO().getById(app.getCurrentPatient());
        String dateTimestr = editTextDate.getText().toString();
        List<Event> eList = db.eventDAO().getByPersonIdAndTimeAndType(app.getCurrentPatient(), dateTimestr, "dataEntry");


        for(Event e : eList){

            DataEntry dataEntry = db.dataEntryDAO().
                    getById(e.getEventChildId());

            // DataField df = db.dataFieldDAO().getbyDataField(dataEntry.getDataFieldId());
            String aRecord = dataEntry.getValue();// + df.getDataType();
            dataDictionary.put(dataEntry.getDataFieldId(), aRecord);
        }



        int idx = 0;
        if(dataDictionary.size() > 0){
            Enumeration<Integer> keyEnumeration = dataDictionary.keys();
                    /**/
            for(Enumeration e = dataDictionary.elements(); e.hasMoreElements();) {
                if(idx >= dataDictionary.size())
                {
                    break;
                }
                else
                {
                    if(idx <  fields.length){
                        //String test = dataDictionary.;
                        String test = e.nextElement().toString();
                        fields[idx].setText(test);
                        Toast.makeText(this, fields[idx].getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                idx++;
            }
        }
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
