package com.neonatal.app.src;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

/**
 * Created by Nenad on 2017-12-12.
 */

public class EquipDetailActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView equipImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_details);

        equipImage = findViewById(R.id.imageView);
        mToolbar = findViewById(R.id.toolbar2);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            mToolbar.setTitle(bundle.getString("EquipName"));

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Incubator")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_incubator));
            }
        }
    }
}
