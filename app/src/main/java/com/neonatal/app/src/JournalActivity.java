package com.neonatal.app.src;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.neonatal.app.src.adapters.JournalsAdapter;
import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.Event;
import com.neonatal.app.src.entity.JournalEntry;
import com.neonatal.app.src.entity.Milestone;

import java.util.ArrayList;
import java.util.List;

public class JournalActivity extends DrawerActivity implements ListView.OnItemClickListener, MilestonePicker.MilestoneListener{


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
   // private SectionsPagerAdapter mSectionsPagerAdapter;


    private ArrayList<JournalEntry> journals = null;
    private ArrayList<Event> events = null;
    AppDatabase db = null;
    NeonatalApp app = null;
    private int milestone;
    ArrayList<Integer>  journalEvents = null;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
   // private ViewPager mViewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_journal);
        super.onCreateDrawer();

        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_journal);
        View inflated = stub.inflate();

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Baby Journal");
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        //setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        //mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        //mViewPager = (ViewPager) findViewById(R.id.container);
        //mViewPager.setAdapter(mSectionsPagerAdapter);


        this.app = ((NeonatalApp) getApplicationContext());
        this.db = AppDatabase.getAppDatabase(getApplicationContext());

       fillLists();

        FloatingActionButton fbtn = (FloatingActionButton) findViewById(R.id.search);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MilestonePicker milestonePicker = new MilestonePicker();
                milestonePicker.show(getFragmentManager(), "Title");
            }
        });

        //read the data base and retrieve data from here
        fbtn =  (FloatingActionButton) findViewById(R.id.edit);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JournalActivity.this, CreateJournalActivity.class));




            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        fillLists();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        JournalEntry entry = this.journals.get(position);
        ArrayList<Event> eventResults = (ArrayList<Event>) db.eventDAO().getEventByChildId(entry.getId(), "JournalEntry");
        Event event = eventResults.get(0);
        ArrayList<Milestone> milestones;
        Milestone milestone = null;
        if(entry.getMilestoneId() != 0)
        {
            milestones = (ArrayList<Milestone>) db.milestoneDAO().getById(entry.getMilestoneId());
            milestone = milestones.get(0);
        }

        if(entry != null)
        {
            Intent intent = new Intent(this, DisplayJournalActivity.class);

            //intent.putExtra("image", entry.getImagePath());
            intent.putExtra("body", entry.getBodyText());
            intent.putExtra("date", event.getEventDateTime());
            if(milestone != null)
            {
                intent.putExtra("milestone", milestone.getDescription());
            }
            startActivity(intent);

        }


    }

    @Override
    public void returnMilestone(int milestone) {
        this.milestone = milestone;
        int personId = (db.patientDAO().getById(app.getCurrentPatient()).getPersonId());
        this.journals = (ArrayList) db.journalEntryDAO().getUsersJournalByMilestoneId(this.journalEvents ,milestone);

        ListView journal_entries = (ListView) findViewById(R.id.lv_journals);

        JournalsAdapter journalsAdapter = new JournalsAdapter(this, this.journals);

        journal_entries.setAdapter(journalsAdapter);

        journal_entries.setOnItemClickListener(this);
    }

    public void fillLists()
    {
        this.events = (ArrayList) db.eventDAO().getPatientsEvents(app.getCurrentPatient());
        this.journalEvents = (ArrayList) db.eventDAO().getPatientsJournalEvents(db.patientDAO().getById(app.getCurrentPatient()).getPersonId(), "JournalEntry");

        this.journals = (ArrayList) db.journalEntryDAO().getUsersJournals(journalEvents);

        ListView journal_entries = (ListView) findViewById(R.id.lv_journals);

        JournalsAdapter journalsAdapter = new JournalsAdapter(this, this.journals);

        journal_entries.setAdapter(journalsAdapter);

        journal_entries.setOnItemClickListener(this);
    }



}
