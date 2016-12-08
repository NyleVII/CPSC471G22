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

import static com.example.ark.myapplication.R.id.runsList;

/**
 * Created by CWDudar on 12/5/2016.
 */

public class RunsActivity extends AppCompatActivity {

    String lift, region;
    int hill_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runs);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> runs = extras.getStringArrayList("runs");
        lift = extras.getString("lift");
        region =  extras.getString("region");
        hill_id = extras.getInt("Hill_ID");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.runs_list, runs);

        ListView listView = (ListView) findViewById(runsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(RunsActivity.this,SingleRunActivity.class);
                String runName = ((TextView)view).getText().toString();
                intent.putExtra("run", runName);
                intent.putExtra("lift",lift);
                intent.putExtra("region", region);
                intent.putExtra("Hill_ID",hill_id);
                startActivity(intent);
            }
        });

    }

    public void openInsertRun(View view){
        Intent intent = new Intent(this,InsertRunActivity.class);
        intent.putExtra("Hill_ID", hill_id);
        intent.putExtra("region", region);
        intent.putExtra("lift", lift);
        startActivity(intent);


    }

}
