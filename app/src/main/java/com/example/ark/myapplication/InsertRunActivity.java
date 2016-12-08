package com.example.ark.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertRunActivity extends AppCompatActivity {

    String db, regionName, liftName;
    Connection conn;
    int hill_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_run);

        conn = MainActivity.getConn();
        db = MainActivity.getDatabase();

        Bundle extras = getIntent().getExtras();
        hill_id = extras.getInt("Hill_ID");
        regionName = extras.getString("region");
        liftName = extras.getString("lift");
    }

    public void insertRun(View view){
        String name = "";
        String difficulty = "";
        String snowCondition = "";

        TextView nameText = (TextView) findViewById(R.id.name);
        TextView difficultyText = (TextView) findViewById(R.id.difficulty);
        TextView snowConditionText = (TextView) findViewById(R.id.snowCondition);

        name = nameText.getText().toString();
        difficulty = difficultyText.getText().toString();
        snowCondition = snowConditionText.getText().toString();


        try {
            String query = "INSERT INTO " + db + ".dbo.Runs" +
                    " VALUES ('" + name + "'," + hill_id + ",'" + liftName +"','" + regionName + "','" +
                    difficulty + "','" + snowCondition + "')";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
