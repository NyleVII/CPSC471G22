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
   // String[] testArray = {"FUCK","ANDROID","STUDIO","KILL",
     //       "IT","WITH","FIRE","!!!!!!","hill 1","hill 2","hill 3","hill 4","hill 5","hill 6","hill 7",
      //      "hill 8","hill 9","hill 10","hill 11","hill 12","hill 13","hill 14","hill 15","hill 16","hill 17",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hills);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> hills = extras.getStringArrayList("hills");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.hills_list, hills);

        ListView listView = (ListView) findViewById(hillsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HillsActivity.this,SingleHillActivity.class);
                String hillName = ((TextView)view).getText().toString();
                intent.putExtra("hillName",hillName);
                startActivity(intent);
            }
        });


    }


}
