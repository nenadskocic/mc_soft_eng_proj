package com.neonatal.app.src;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayJournalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_journal);

        Intent intent = getIntent();

        TextView body = (TextView) findViewById(R.id.tv_body);
        TextView date = (TextView) findViewById(R.id.tv_date);
        TextView milestone = (TextView) findViewById(R.id.tv_milestone);

        ImageView image = (ImageView) findViewById(R.id.img_journal_image);

        body.setText(intent.getStringExtra("body"));
        date.setText(intent.getStringExtra("date"));
        if(intent.hasExtra("image"))
        {
            image.setImageBitmap(BitmapFactory.decodeFile(intent.getStringExtra("image")));
        }
        if(intent.hasExtra("milestone"))
        {
            milestone.setVisibility(View.VISIBLE);
            milestone.setText("Milestone: " + intent.getStringExtra("milestone"));
        }

    }
}
