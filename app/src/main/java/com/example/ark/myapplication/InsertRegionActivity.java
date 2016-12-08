package com.example.ark.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertRegionActivity extends AppCompatActivity {

    String db;
    Connection conn;
    int hill_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_region);

        conn = MainActivity.getConn();
        db = MainActivity.getDatabase();

        Bundle extras = getIntent().getExtras();
        hill_id = extras.getInt("Hill_ID");

    }

    public void insertRegion(View view){
        String weather = "";
        String region_name = "";

        TextView regionNameText = (TextView) findViewById(R.id.regionName);
        TextView weatherText = (TextView) findViewById(R.id.weather);

        region_name = regionNameText.getText().toString();
        weather = weatherText.getText().toString();

        try {
            String query = "INSERT INTO " + db + ".dbo.Regions VALUES (" + hill_id + "," + "'" + region_name + "'" + "," + "'" + weather + "'" + ")";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
