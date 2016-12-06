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

public class PurchasablesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchasables);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> purchNames = extras.getStringArrayList("purchasableNames");
        final int hill_id = extras.getInt("Hill_ID");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.purchasables_list, purchNames);

        ListView listView = (ListView) findViewById(R.id.purchasablesList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(PurchasablesActivity.this,SinglePurchasableActivity.class);
                String purchName = ((TextView)view).getText().toString();
                intent.putExtra("purchasableName",purchName);
                intent.putExtra("Hill_ID",hill_id);
                startActivity(intent);
            }
        });
    }
}
