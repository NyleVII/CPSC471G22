package com.example.ark.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.ark.myapplication.R.id.hillsList;
import static com.example.ark.myapplication.R.styleable.View;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HillsActivity extends AppCompatActivity {
    int id;
    ArrayList<Integer> ids = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hills);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> hills = extras.getStringArrayList("hills");
        final ArrayList<Integer> hill_ids = extras.getIntegerArrayList("Hill_IDs");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.hills_list, hills);

        ListView listView = (ListView) findViewById(hillsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HillsActivity.this,SingleHillActivity.class);
                String hillName = ((TextView)view).getText().toString();
                intent.putExtra("hillName",hillName);
                intent.putExtra("Hill_ID",hill_ids.get(i));
                startActivity(intent);
            }
        });


    }


}
