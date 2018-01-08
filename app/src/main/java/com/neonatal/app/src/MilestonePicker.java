package com.neonatal.app.src;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.Milestone;

import java.util.ArrayList;

public class MilestonePicker extends DialogFragment {


    public interface MilestoneListener{
        public void returnMilestone(int milestone);
    }

    private MilestoneListener mLisenter;
    private ArrayList<String> milestones;

    public Dialog onCreateDialog (Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AppDatabase db = AppDatabase.getAppDatabase(getContext());

        milestones = (ArrayList<String>) db.milestoneDAO().getAllMilestoneNames();

        mLisenter = (MilestoneListener) getActivity();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, milestones);

        builder
                .setTitle("Search For Milestone")
                .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mLisenter.returnMilestone(which + 1);
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }


}
