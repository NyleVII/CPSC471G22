package com.example.ark.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SingleRentalActivity extends AppCompatActivity {
    String rentalName, db, rentalType, rentalPrice;
    int hill_id;
    Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_rental);

        conn = MainActivity.getConn();
        db = MainActivity.getDatabase();
        TextView rentalNameText = (TextView) findViewById(R.id.rentalName);
        TextView rentalTypeText = (TextView) findViewById(R.id.rentalType);
        TextView rentalPriceText = (TextView) findViewById(R.id.rentalPrice);

        Bundle extras = getIntent().getExtras();
        rentalName = extras.getString("rentalName");
        hill_id = extras.getInt("Hill_ID");


        try {
            String query = "SELECT Name,Type,Price FROM " + db + ".dbo.Rental WHERE Name='" + rentalName + "' AND Hill_ID='" + hill_id +"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                rentalType = rs.getString("Type");
                rentalPrice = rs.getString("Price");
                rentalNameText.setText(rentalName);
                rentalTypeText.setText(rentalType);
                rentalPriceText.setText(rentalPrice);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
