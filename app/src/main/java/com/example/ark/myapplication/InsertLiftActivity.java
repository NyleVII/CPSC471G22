package com.example.ark.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertLiftActivity extends AppCompatActivity {

    String db, regionName;
    Connection conn;
    int hill_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_lift);

        conn = MainActivity.getConn();
        db = MainActivity.getDatabase();

        Bundle extras = getIntent().getExtras();
        hill_id = extras.getInt("Hill_ID");
        regionName = extras.getString("region");
    }

    public void insertLift(View view){
        String name = "";
        String type = "";
        int capacity;

        TextView nameText = (TextView) findViewById(R.id.name);
        TextView typeText = (TextView) findViewById(R.id.type);
        TextView capacityText = (TextView) findViewById(R.id.capacity);

        name = nameText.getText().toString();
        type = typeText.getText().toString();
        capacity = Integer.parseInt(capacityText.getText().toString());

        try {
            String query = "INSERT INTO " + db + ".dbo.Lifts VALUES ('" + name + "','" + regionName + "'," + hill_id + ",'" + type +"'," + capacity + ")";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
