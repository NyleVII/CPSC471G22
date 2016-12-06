package com.example.ark.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SinglePurchasableActivity extends AppCompatActivity {
    String purchName, db, purchType, purchPrice;
    int hill_id;
    Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_purchasable);

        conn = MainActivity.getConn();
        db = MainActivity.getDatabase();
        TextView purchNameText = (TextView) findViewById(R.id.purchasableName);
        TextView purchTypeText = (TextView) findViewById(R.id.purchasableType);
        TextView purchPriceText = (TextView) findViewById(R.id.purchasablePrice);

        Bundle extras = getIntent().getExtras();
        purchName = extras.getString("purchasableName");
        hill_id = extras.getInt("Hill_ID");


        try {
            String query = "SELECT Name,Type,Price FROM " + db + ".dbo.Rental WHERE Name='" + purchName + "' AND Hill_ID=" + hill_id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                purchType = rs.getString("Type");
                purchPrice = rs.getString("Price");
                purchNameText.setText(purchName);
                purchTypeText.setText(purchType);
                purchPriceText.setText(purchPrice);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
