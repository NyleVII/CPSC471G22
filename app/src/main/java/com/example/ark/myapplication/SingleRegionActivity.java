package com.example.ark.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SingleRegionActivity extends AppCompatActivity {
    String regionName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_region);

        Bundle extras = getIntent().getExtras();
        regionName = extras.getString("Region");

        TextView regionTag = (TextView) findViewById(R.id.region);
        regionTag.setText(regionName);
    }
}
