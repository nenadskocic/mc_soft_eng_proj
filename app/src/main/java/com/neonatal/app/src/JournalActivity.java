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

        this.events = (ArrayList) db.eventDAO().getPatientsEvents(app.getCurrentPatient());
        ArrayList<Integer>  journalEvents = (ArrayList) db.eventDAO().getPatientsJournalEvents(db.patientDAO().getById(app.getCurrentPatient()).getPersonId(), "JournalEntry");

        this.journals = (ArrayList) db.journalEntryDAO().getUsersJournals(journalEvents);

        ListView journal_entries = (ListView) findViewById(R.id.lv_journals);

        JournalsAdapter journalsAdapter = new JournalsAdapter(this, this.journals);

        journal_entries.setAdapter(journalsAdapter);

        journal_entries.setOnItemClickListener(this);

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
                ArrayList<Integer>  journalEvents = (ArrayList) db.eventDAO().getPatientsJournalEvents(app.getCurrentPatient(), "JournalEntry");

                journals = (ArrayList) db.journalEntryDAO().getUsersJournals(journalEvents);

                ListView journal_entries = (ListView) findViewById(R.id.lv_journals);

                JournalsAdapter journalsAdapter = new JournalsAdapter(getBaseContext(), journals);

                journal_entries.setAdapter(journalsAdapter);


            }
        });
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

        this.journals = (ArrayList) db.journalEntryDAO().getUsersJournalByMilestoneId(app.getCurrentPatient(), milestone);

        ListView journal_entries = (ListView) findViewById(R.id.lv_journals);

        JournalsAdapter journalsAdapter = new JournalsAdapter(this, this.journals);

        journal_entries.setAdapter(journalsAdapter);

        journal_entries.setOnItemClickListener(this);
    }




    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_journal, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_journal, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
