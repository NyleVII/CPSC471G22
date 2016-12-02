package com.example.ark.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SingleHillActivity extends AppCompatActivity {
    String hillName, db, snowPack;
    Connection conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_hill);
        conn = MainActivity.getConn();
        db = MainActivity.getDatabase();
        TextView hillNameText = (TextView) findViewById(R.id.hillName);
        TextView snowPackText = (TextView) findViewById(R.id.snowPack);

        Bundle extras = getIntent().getExtras();
        hillName = extras.getString("hillName");
        try {
            String query = "SELECT name, SnowPack FROM " + db + ".dbo.SkiHill WHERE name='" + hillName + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                snowPack = rs.getString("SnowPack");
                hillNameText.setText(hillName);
                snowPackText.setText(snowPack);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
