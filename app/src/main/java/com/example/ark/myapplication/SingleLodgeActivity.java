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
 * Created by CWDudar on 12/5/2016.
 */


public class SingleLodgeActivity extends AppCompatActivity {

    String regionName, lodgeName, db, availableSeating, foodMenu;
    int hill_id;
    Connection conn;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_lodge);

        conn = MainActivity.getConn();
        db = MainActivity.getDatabase();

        Bundle extras = getIntent().getExtras();
        hill_id = extras.getInt("Hill_ID");
        regionName = extras.getString("region");
        lodgeName = extras.getString("lodge");


        System.out.println(lodgeName);
        System.out.println(regionName);
        System.out.println(hill_id);
        TextView lodgeTag = (TextView) findViewById(R.id.lodge);
        TextView availableSeatingText = (TextView) findViewById(R.id.availableSeating);
        //TextView foodMenuText = (TextView) findViewById(R.id.foodMenu);



        try {
            String query = "SELECT AvailableSeating FROM " + db + ".dbo.Lodges WHERE Hill_id = " + hill_id + " AND RegionName = '" + regionName + "' AND Name = '" + lodgeName + "'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                availableSeating = String.valueOf(rs.getInt("AvailableSeating"));
               // foodMenu = rs.getString("FoodMenu");

                lodgeTag.setText(lodgeName);
                availableSeatingText.setText(availableSeating);
                //foodMenuText.setText(foodMenu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}