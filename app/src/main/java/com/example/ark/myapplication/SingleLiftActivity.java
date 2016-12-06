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
/**
 * Created by CWDudar on 12/2/2016.
 */

public class SingleLiftActivity extends AppCompatActivity {

    String regionName, liftName, db, runType, runCapacity;
    int hill_id;
    Connection conn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_lift);

        conn = MainActivity.getConn();
        db = MainActivity.getDatabase();

        Bundle extras = getIntent().getExtras();
        hill_id = extras.getInt("Hill_ID");
        regionName = extras.getString("region");
        liftName = extras.getString("lift");


        TextView liftTag = (TextView) findViewById(R.id.lift);
        liftTag.setText(liftName);

        TextView runTypeText = (TextView) findViewById(R.id.type);
        TextView runCapacityText = (TextView) findViewById(R.id.capacity);


        try {
            String query = "SELECT Type, Capacity FROM " + db + ".dbo.Lifts WHERE Hill_ID = "+ hill_id + " AND RegionName = '" + regionName + "' AND Name = '" + liftName + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                runType = rs.getString("Type");
                runCapacity = rs.getString("Capacity");
                runTypeText.setText(runType);
                runCapacityText.setText(runCapacity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

