package com.softwareuiteam.a000355473.uidesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

public class EventsActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_test);

        super.onCreateDrawer();
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_test);
        View inflated = stub.inflate();

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");


        TextView text = (TextView) findViewById(R.id.textView3);
        text.setText(date);


    }
}
