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


public class SingleRunActivity extends AppCompatActivity {

    String regionName, liftName, runName, db, difficulty, snowCondition;
    int hill_id;
    Connection conn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_run);

        conn = MainActivity.getConn();
        db = MainActivity.getDatabase();

        Bundle extras = getIntent().getExtras();
        hill_id = extras.getInt("Hill_ID");
        regionName = extras.getString("region");
        liftName = extras.getString("lift");
        runName = extras.getString("run");

        TextView runTag = (TextView) findViewById(R.id.run);
        TextView difficultyText = (TextView) findViewById(R.id.difficulty);
        TextView snowConditionText = (TextView) findViewById(R.id.snowCondition);

        runTag.setText(runName);

        try {
            String query = "SELECT Difficulty, SnowCondition FROM " + db + ".dbo.Runs WHERE Hill_id = " + hill_id + " AND RegionName = '" + regionName
                    + "' AND LiftName = '" + liftName + "' AND Name = '" + runName + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                difficulty = rs.getString("Difficulty");
                snowCondition = rs.getString("SnowCondition");

                difficultyText.setText(difficulty);
                snowConditionText.setText(snowCondition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRun(View view) {


        try {
            String query = "DELETE FROM " + db + ".dbo.Runs WHERE Hill_ID=" + hill_id + "AND Name='" + runName +
                    "' AND RegionName='" + regionName + "' AND LiftName='" + liftName + "'";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
