package com.example.ark.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import android.view.View;
import android.widget.TextView;
import android.content.Intent;






public class SingleRegionActivity extends AppCompatActivity {
    String regionName, db, weather;
    int hill_id;
    Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_region);

        conn = MainActivity.getConn();
        db = MainActivity.getDatabase();
        TextView weatherText = (TextView) findViewById(R.id.weather);

        Bundle extras = getIntent().getExtras();
        regionName = extras.getString("Region");
        hill_id = extras.getInt("Hill_ID");


        TextView regionTag = (TextView) findViewById(R.id.region);
        regionTag.setText(regionName);

        try {
            String query = "SELECT Weather FROM " + db + ".dbo.Regions WHERE Hill_id = " + hill_id + " AND Name = '" + regionName + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                weather = rs.getString("Weather");
                weatherText.setText(weather);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openLifts(View view){
        ArrayList<String> lifts = new ArrayList<String>(); //hills array for sending to the hills activity

        try {
            //Inserting into the database with db = database name
            //dbo.names is the table name
            //standard format for accessing the sql server provided through Tamer is the following
            String query = "SELECT Name FROM " + db + ".dbo.Lifts WHERE Hill_ID = " + hill_id + " AND RegionName = '" + regionName + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                String Name = rs.getString("Name");

                lifts.add(Name);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        Intent intent = new Intent(this, LiftsActivity.class);
        intent.putExtra("lifts", lifts);
        intent.putExtra("region", regionName);
        intent.putExtra("Hill_ID",hill_id);
        startActivity(intent);
    }

}

