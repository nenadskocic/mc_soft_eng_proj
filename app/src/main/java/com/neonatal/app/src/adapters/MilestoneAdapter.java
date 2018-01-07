package com.neonatal.app.src.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.neonatal.app.src.entity.Milestone;

import java.util.ArrayList;

/**
 * Created by ufe-i134-pc10 on 07/01/2018.
 */

public class MilestoneAdapter extends ArrayAdapter<Milestone>{
    ArrayList<Milestone> data = new ArrayList<>();

    public MilestoneAdapter(Context context, int textViewResourceId, ArrayList<Milestone> data) {
        super(context,textViewResourceId,data);
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Milestone milestone = data.get(position);

        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(android.R.layout.simple_spinner_item, null);

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(milestone.getDescription());

        return view;
    }

    /*@Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(android.R.layout.simple_list_item_1,parent, false);
        }
        Milestone milestone = getItem(position);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        txtTitle.setText(rowItem.getTitle());
        ImageView imageView = (ImageView) convertView.findViewById(R.id.icon);
        imageView.setImageResource(rowItem.getImageId());
        return convertView;
    }*/
}
