package com.example.ark.myapplication;

/**
 * Created by CWDudar on 12/5/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.AdapterView.OnItemClickListener;
import static com.example.ark.myapplication.R.id.liftsList;


import static com.example.ark.myapplication.R.id.lodgesList;

/**
 * Created by CWDudar on 12/2/2016.
 */


public class LodgesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lodges);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> lodges = extras.getStringArrayList("lodges");
        final String region =  extras.getString("region");
        final int hill_id = extras.getInt("Hill_ID");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.lodges_list, lodges);

        ListView listView = (ListView) findViewById(lodgesList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(LodgesActivity.this,SingleLodgeActivity.class);
                String lodgeName = ((TextView)view).getText().toString();
                intent.putExtra("lodge",lodgeName);
                intent.putExtra("region", region);
                intent.putExtra("Hill_ID",hill_id);
                startActivity(intent);
            }
        });

    }
}
