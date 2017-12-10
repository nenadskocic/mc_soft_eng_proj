package com.neonatal.app.src;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

public class CreateJournalActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_journal);
        super.onCreateDrawer();
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_create_journal);
        View inflated = stub.inflate();
    }
}
