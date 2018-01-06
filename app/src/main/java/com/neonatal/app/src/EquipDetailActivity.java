package com.neonatal.app.src;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

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
        //set drawer list
        super.onCreateDrawer();

        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_equip_details);
        View inflated = stub.inflate();
        //set drawer list

        equipImage = findViewById(R.id.imageViewEquip);
        mToolbar = findViewById(R.id.toolbarEquip);
        TextView textView = findViewById(R.id.textViewEquip);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            mToolbar.setTitle(bundle.getString("EquipName"));

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Cardiopulmonary Monitor")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_cardiopulmonary_monitor));

                textView.setText(R.string.equip_cm_summary);
            }

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Central Venous Catheter")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_central_venous_catheter));

                textView.setText(R.string.equip_cvc_summary);
            }

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Continuous Positive Airway Pressure")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_continuous_pos_airway_pressure));

                textView.setText(R.string.equip_cpap_summary);
            }

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Incubator")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_incubator));

                textView.setText(R.string.equip_i_summary);
            }

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Nasogastric Tube")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_nasogastric_tube));

                textView.setText(R.string.equip_nt_summary);
            }

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Peripheral Intravenous Catheter")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_peripheral_intrav_catheter));

                textView.setText(R.string.equip_pic_summary);
            }

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Phototherapy")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_phototherapy));

                textView.setText(R.string.equip_p_summary);
            }

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Pulse Oximeter")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_pulse_oximeter));

                textView.setText(R.string.equip_po_summary);
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Umbilical Catheter")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_umbilical_catheter));

                textView.setText(R.string.equip_uc_summary);
            }
        }
    }

    public void onBackPressed(View view) {
        super.onBackPressed();
        startActivity(new Intent(EquipDetailActivity.this, EquipmentActivity.class));
    }
}
