package com.neonatal.app.src.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.neonatal.app.src.entity.JournalEntry;

import java.util.ArrayList;

/**
 * Created by Owen Bryan on 1/7/2018.
 */

public class JournalsAdapter extends ArrayAdapter<JournalEntry> {

    private ArrayList<JournalEntry> journals;

    public JournalsAdapter (Context context, ArrayList<JournalEntry> journals)
    {
        super (context, 0, journals);
        this.journals = journals;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        JournalEntry journalEntry = journals.get(position);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        String entry = journalEntry.getId() + " " + journalEntry.getDate();

        View view  = inflater.inflate(android.R.layout.simple_list_item_1, null);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(entry);
        return view;

    }
}
