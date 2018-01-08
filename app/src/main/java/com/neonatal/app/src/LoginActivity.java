package com.neonatal.app.src;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.Event;
import com.neonatal.app.src.entity.JournalEntry;
import com.neonatal.app.src.entity.Milestone;
import com.neonatal.app.src.entity.Person;
import com.neonatal.app.src.entity.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    NeonatalApp app;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        app = ((NeonatalApp) getApplicationContext());
        db = AppDatabase.getAppDatabase(getApplicationContext());

        populateWithTestData(db);

        //EditText editText = (EditText) findViewById(R.id.txt_Username);
        //editText.setText(getDatabasePath("neonatal-database").getAbsolutePath());
    }

    public void Register(View v){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private static User getUserByUsername(AppDatabase db, String username) {
        return db.userDAO().getUserByUserName(username);
    }

    //POPULATE WITH TEST DATA
    //==============================================================================================
    private static User addUser(final AppDatabase db, User user) {
        db.userDAO().insertAll(user);
        return user;
    }

    private static Person addPerson(final AppDatabase db, Person person) {
        db.personDAO().insertAll(person);
        return person;
    }

    private static List<Milestone> addMilestones(final AppDatabase db, List<Milestone> milestones) {
        db.milestoneDAO().insertAll(milestones.toArray(new Milestone[milestones.size()]));
        return milestones;
    }

    private static Event addEvent(final AppDatabase db, Event event){
        db.eventDAO().insertAll(event);
        return event;
    }

    private static JournalEntry addJournalEntry (final AppDatabase db, JournalEntry journalEntry) {
        db.journalEntryDAO().insertAll(journalEntry);
        return journalEntry;
    }

    private static void populateWithTestData(AppDatabase db) {

        //User
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setPersonId(1);
        addUser(db, user);

        //Person
        Person person = new Person();
        person.setFirstName("Mother");
        person.setLastName("Goose");
        person.setEmail("mgoose@hotmail.com");
        person.setPhone("905-777-7777");
        addPerson(db, person);

        //Event
        Event event = new Event();
        event.setEventDateTime("2017-12-21 13:14:15");
        event.setEventType("journalEntry");
        event.setEventChildId(1);
        addEvent(db, event);

        //Journal Entry
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setBodyText("Today Susan had her first breastfeeding!");
        journalEntry.setMilestoneId(1);
        addJournalEntry(db, journalEntry);

        //Milestone
        String[] milestoneDescriptions = {
                "First Breastfeeding",
                "Back to Birth Weight",
                "Example Milestone",
                "Some other Milestone"
        };

        List<Milestone> milestones = new ArrayList<Milestone>();

        for (String description : milestoneDescriptions)
        {
            Milestone milestone = new Milestone();
            milestone.setDescription(description);
            milestones.add(milestone);
        }
        addMilestones(db, milestones);

    }
    //==============================================================================================

    public void Login(View v){
        TextView textView_username =  (TextView) findViewById(R.id.txt_Username);
        TextView textView_password =  (TextView) findViewById(R.id.txt_password);

        String inputUsername = textView_username.getText().toString();
        String inputPassword = textView_password.getText().toString();

        User user = null;
        if(!inputUsername.equals("")){
            user = getUserByUsername(db, inputUsername);
        }

        if(user!=null)
        {
            if(inputUsername.equals(user.getUsername()) && inputPassword.equals(user.getPassword()))
            {
                app.setCurrentUser(user.getId());
                startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
                this.finish();
            }
            else
            {
                Context context = getApplicationContext();
                CharSequence text = "Wrong username/password";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }


    }
}
