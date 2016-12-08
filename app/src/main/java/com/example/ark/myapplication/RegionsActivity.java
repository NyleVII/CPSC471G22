package com.example.ark.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.ark.myapplication.R.id.hillsList;
import static com.example.ark.myapplication.R.id.regionsList;

public class RegionsActivity extends AppCompatActivity {
    int hill_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regions);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> regions = extras.getStringArrayList("regions");
        hill_id = extras.getInt("Hill_ID");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.regions_list, regions);

        ListView listView = (ListView) findViewById(regionsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(RegionsActivity.this,SingleRegionActivity.class);
                String regionName = ((TextView)view).getText().toString();
                intent.putExtra("Region",regionName);
                intent.putExtra("Hill_ID",hill_id);
                startActivity(intent);
            }
        });

    }

    public void openInsertRegion(View view){

        Intent intent = new Intent(this,InsertRegionActivity.class);
        intent.putExtra("Hill_ID", hill_id);
        startActivity(intent);
    }
}
