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

public class RentalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentals);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> rentalNames = extras.getStringArrayList("rentalNames");
        final int hill_id = extras.getInt("Hill_ID");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.rentals_list, rentalNames);

        ListView listView = (ListView) findViewById(R.id.rentalsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(RentalsActivity.this,SingleRentalActivity.class);
                String rentalName = ((TextView)view).getText().toString();
                intent.putExtra("rentalName",rentalName);
                intent.putExtra("Hill_ID",hill_id);
                startActivity(intent);
            }
        });


    }
}
