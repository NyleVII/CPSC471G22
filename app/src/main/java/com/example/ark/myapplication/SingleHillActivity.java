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
import java.util.ArrayList;

public class SingleHillActivity extends AppCompatActivity {
    String hillName, db, snowPack;
    int hill_id;
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
        hill_id = extras.getInt("Hill_ID");


        try {
            String query = "SELECT name, SnowPack FROM " + db + ".dbo.SkiHill WHERE name='" + hillName + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                snowPack = rs.getString("SnowPack");
                hillNameText.setText(hillName);
                snowPackText.setText(snowPack + "cm");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void openRegions(View view){
        ArrayList<String> regions = new ArrayList<String>(); //hills array for sending to the hills activity

        try {
            //Inserting into the database with db = database name
            //dbo.names is the table name
            //standard format for accessing the sql server provided through Tamer is the following
            String query = "SELECT name FROM " + db + ".dbo.Regions WHERE Hill_ID=" + hill_id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                String Name = rs.getString("name");

                regions.add(Name);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        Intent intent = new Intent(this, RegionsActivity.class);
        intent.putExtra("regions", regions);
        intent.putExtra("Hill_ID",hill_id);
        startActivity(intent);
    }

    public void openRentals(View view){
        ArrayList<String> rentalNames = new ArrayList<String>(); //hills array for sending to the hills activity

        try {
            //Inserting into the database with db = database name
            //dbo.names is the table name
            //standard format for accessing the sql server provided through Tamer is the following
            String query = "SELECT Name,Type,Price FROM " + db + ".dbo.Rental WHERE Hill_ID=" + hill_id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                String rentalName = rs.getString("Name");

                rentalNames.add(rentalName);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        Intent intent = new Intent(this, RentalsActivity.class);
        intent.putExtra("rentalNames", rentalNames);
        intent.putExtra("Hill_ID",hill_id);
        startActivity(intent);
    }

    public void openPurchasables(View view){
        ArrayList<String> purchNames = new ArrayList<String>(); //hills array for sending to the hills activity

        try {
            //Inserting into the database with db = database name
            //dbo.names is the table name
            //standard format for accessing the sql server provided through Tamer is the following
            String query = "SELECT Name FROM " + db + ".dbo.Purchasable WHERE Hill_ID=" + hill_id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                String purchName = rs.getString("Name");

                purchNames.add(purchName);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        Intent intent = new Intent(this, PurchasablesActivity.class);
        intent.putExtra("purchasableNames", purchNames);
        intent.putExtra("Hill_ID",hill_id);
        startActivity(intent);
    }

    public void openReviews(View view){
        ArrayList<String> starRatings = new ArrayList<String>(); //hills array for sending to the hills activity
        ArrayList<Integer> reviewIds = new ArrayList<Integer>(); //hills array for sending to the hills activity

        try {
            //Inserting into the database with db = database name
            //dbo.names is the table name
            //standard format for accessing the sql server provided through Tamer is the following
            String query = "SELECT ReviewID, StarRating FROM " + db + ".dbo.Reviews WHERE Hill_ID=" + hill_id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                String starRating = rs.getString("StarRating");
                Integer reviewId = rs.getInt("ReviewID");

                reviewIds.add(reviewId);
                starRatings.add(starRating);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        Intent intent = new Intent(this, ReviewsActivity.class);
        intent.putExtra("reviewIds", reviewIds);
        intent.putExtra("starRatings", starRatings);
        intent.putExtra("Hill_ID",hill_id);
        startActivity(intent);
    }

    public void deleteHill(View view) {

        try {
            String query = "DELETE FROM " + db + ".dbo.SkiHill WHERE Hill_ID=" + hill_id;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
