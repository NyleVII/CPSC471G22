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

import static java.sql.Types.NULL;

public class InsertHillActivity extends AppCompatActivity {
    String db;
    Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_hill);

        conn = MainActivity.getConn();
        db = MainActivity.getDatabase();
    }

    public void insertHill(View view){
        String hillName, snowPack;
        int hill_id = 0;

        TextView hillNameText = (TextView) findViewById(R.id.hillName);
        TextView snowPackText = (TextView) findViewById(R.id.snowPack);

        hillName = hillNameText.getText().toString();
        snowPack = snowPackText.getText().toString();

        try {
            String query = "SELECT MAX(Hill_ID) as maxHillID FROM " + db + ".dbo.SkiHill";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                hill_id = rs.getInt("maxHillID") + 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            String query = "INSERT INTO " + db + ".dbo.SkiHill VALUES (" + hill_id + "," + "'" + hillName + "'" + "," + "'" + snowPack + "'" + ")";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
