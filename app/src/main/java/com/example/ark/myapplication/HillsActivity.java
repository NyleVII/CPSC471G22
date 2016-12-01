package com.example.ark.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HillsActivity extends AppCompatActivity {
    String[] testArray = {"FUCK","ANDROID","STUDIO","KILL",
            "IT","WITH","FIRE","!!!!!!","hill 1","hill 2","hill 3","hill 4","hill 5","hill 6","hill 7",
            "hill 8","hill 9","hill 10","hill 11","hill 12","hill 13","hill 14","hill 15","hill 16","hill 17",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hills);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.hills_list, testArray);

        ListView listView = (ListView) findViewById(R.id.hillsList);
        listView.setAdapter(adapter);


    }
}
