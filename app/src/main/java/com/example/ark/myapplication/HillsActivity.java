package com.example.ark.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HillsActivity extends AppCompatActivity {
    String[] testArray = {"FUCK","ANDROID","STUDIO","KILL",
            "IT","WITH","FIRE","!!!!!!"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hills);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.hills_list, testArray);

        ListView listView = (ListView) findViewById(R.id.hillsList);
        listView.setAdapter(adapter);


    }
}
