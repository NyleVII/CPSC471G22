package com.example.ark.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PurchasablesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchasables);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> purchNames = extras.getStringArrayList("rentalNames");
        final int hill_id = extras.getInt("Hill_ID");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.purchasables_list, purchNames);

        ListView listView = (ListView) findViewById(R.id.purchasablesList);
        listView.setAdapter(adapter);
    }
}
