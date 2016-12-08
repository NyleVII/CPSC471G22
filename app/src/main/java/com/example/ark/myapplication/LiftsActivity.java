package com.example.ark.myapplication;

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

/**
 * Created by CWDudar on 12/2/2016.
 */


    public class LiftsActivity extends AppCompatActivity {
        String region;
        int hill_id;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lifts);

            Bundle extras = getIntent().getExtras();
            ArrayList<String> lifts = extras.getStringArrayList("lifts");
            region =  extras.getString("region");
            hill_id = extras.getInt("Hill_ID");

            ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.lifts_list, lifts);

            ListView listView = (ListView) findViewById(liftsList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(LiftsActivity.this,SingleLiftActivity.class);
                    String liftName = ((TextView)view).getText().toString();
                    intent.putExtra("lift",liftName);
                    intent.putExtra("region", region);
                    intent.putExtra("Hill_ID",hill_id);
                    startActivity(intent);
                }
            });

        }
        public void openInsertLift(View view){

            Intent intent = new Intent(this,InsertLiftActivity.class);
            intent.putExtra("Hill_ID", hill_id);
            intent.putExtra("region", region);
            startActivity(intent);

        }


    }


