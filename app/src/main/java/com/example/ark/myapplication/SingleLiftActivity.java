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

    String regionName, liftName, db, liftType, liftCapacity;
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
                liftType = rs.getString("Type");
                liftCapacity = rs.getString("Capacity");
                runTypeText.setText(liftType);
                runCapacityText.setText(liftCapacity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void openRuns(View view){
        ArrayList<String> runs = new ArrayList<String>(); //hills array for sending to the hills activity

        try {
            //Inserting into the database with db = database name
            //dbo.names is the table name
            //standard format for accessing the sql server provided through Tamer is the following
            String query = "SELECT Name FROM " + db + ".dbo.Runs WHERE Hill_ID = " + hill_id + " AND RegionName = '" + regionName + "' AND LiftName = '" + liftName + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                String Name = rs.getString("Name");

                runs.add(Name);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        Intent intent = new Intent(this, RunsActivity.class);
        intent.putExtra("runs", runs);
        intent.putExtra("lift", liftName);
        intent.putExtra("region", regionName);
        intent.putExtra("Hill_ID",hill_id);
        startActivity(intent);
    }

    public void deleteLift(View view) {


        try {
            String query = "DELETE FROM " + db + ".dbo.Lifts WHERE Hill_ID=" + hill_id + "AND Name='" + liftName +
                    "' AND RegionName='" + regionName + "'";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}



