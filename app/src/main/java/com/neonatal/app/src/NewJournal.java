package com.neonatal.app.src;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class NewJournal extends AppCompatActivity {

    boolean display_milestones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_journal);
        this.display_milestones = false;
    }

    public void display_milestones(View view) {
        Spinner milestone_spinner = (Spinner) findViewById(R.id.spn_milestone);

        if(!this.display_milestones)
        {
            this.display_milestones = true;
            milestone_spinner.setVisibility(View.VISIBLE);
        }
        else
        {
            this.display_milestones = false;
            milestone_spinner.setVisibility(View.INVISIBLE);
        }

    }
}
