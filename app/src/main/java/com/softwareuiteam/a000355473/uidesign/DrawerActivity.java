package com.softwareuiteam.a000355473.uidesign;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

public class DrawerActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ListView drawerList;
    public String[] layers;
    private ActionBarDrawerToggle drawerToggle;
   // private Map map;

    protected void onCreateDrawer() {
        // R.id.drawer_layout should be in every activity with exactly the same id.

        setContentView(R.layout.activity_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_drawer);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle((Activity) this, drawerLayout, toolbar, 0, 0)
        {
            public void onDrawerClosed(View view)
            {
                getSupportActionBar().setTitle(R.string.app_name);
            }

            public void onDrawerOpened(View drawerView)
            {
                getSupportActionBar().setTitle(R.string.menu);
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        layers = getResources().getStringArray(R.array.layers_array);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        View header = getLayoutInflater().inflate(R.layout.drawer_list_header, null);
        drawerList.addHeaderView(header, null, false);
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, android.R.id.text1,
                layers));
        View footerView = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.drawer_list_footer, null, false);
        drawerList.addFooterView(footerView);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                switch (pos){
                    case 1:
                        startActivity(new Intent(DrawerActivity.this, PatientProfileActivity.class));
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        startActivity(new Intent(DrawerActivity.this, CreateJournalActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(DrawerActivity.this, JournalActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(DrawerActivity.this, CalendarViewActivity.class));
                        break;
                    case 7:
                        break;
                    default:
                        startActivity(new Intent(DrawerActivity.this, NeonatoActivity.class));
                        break;
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
