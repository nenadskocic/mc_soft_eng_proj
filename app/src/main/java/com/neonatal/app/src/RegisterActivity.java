package com.neonatal.app.src;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.Person;
import com.neonatal.app.src.entity.User;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    Dictionary<String, EditText> inputs = new Hashtable<>();
    Person person = null;
    AppDatabase db = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputs.put("username",(EditText) findViewById(R.id.txtRemail));
        inputs.put("password",(EditText) findViewById(R.id.txtRpassword));
        inputs.put("fname",(EditText) findViewById(R.id.txtRfname));
        inputs.put("lname",(EditText) findViewById(R.id.txtRlname));
        inputs.put("phone",(EditText) findViewById(R.id.txtrPhone));

        person = new Person();

        db = AppDatabase.getAppDatabase(getApplicationContext());
    }

    public void Register(View v){

        User user = new User();
        //Person person = new Person();

        //String key = "";
        Enumeration<String> keyEnumeration = inputs.keys();

        for(Enumeration e = inputs.keys(); e.hasMoreElements();){
            String key = e.nextElement().toString();
            registerUserRecords(key, person, user);
        }


        long theperson  = addPerson(db, person);
        user.setPersonId((int)theperson);
        addUser(db,user);

        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        this.finish();
    }


    private static User addUser(final AppDatabase db, User user) {
        db.userDAO().insertAll(user);
        return user;
    }

    private static long addPerson(final AppDatabase db, Person person) {
        return  db.personDAO().insertAll(person)[0];
    }

    private void registerUserRecords(String key, Person person, User user){

        switch (key){
            case "password":
                if(inputs.get(key).getText().toString().length() < 6){
                    return;
                }
                user.setPassword(inputs.get(key).getText().toString());
                break;
            case "phone":
                if(inputs.get(key).getText().toString().
                        replaceAll("[\\s\\-()]", "").length() > 0) {

                    if (inputs.get(key).getText().toString().
                            replaceAll("[\\s\\-()]", "").length() < 10) {
                        return;
                    } else {
                        try {
                            long phone = Long.parseLong(inputs.get("phone").getText().toString().
                                    replaceAll("[\\s\\-()]", ""));
                        } catch (Exception ex) {
                            return;
                        }
                    }
                }
                person.setPhone(inputs.get(key).getText().toString());
                break;
            default:
                if(inputs.get(key).getText().toString().trim().length() == 0){
                    return;
                }
                switch (key) {
                    case "username":
                        user.setUsername(inputs.get(key).getText().toString());
                        person.setEmail(inputs.get(key).getText().toString());
                        break;
                    case "fname":
                        person.setFirstName(inputs.get(key).getText().toString());
                        break;
                    case "lname":
                        person.setLastName(inputs.get(key).getText().toString());
                        break;
                }
                break;
        }

    }
}
