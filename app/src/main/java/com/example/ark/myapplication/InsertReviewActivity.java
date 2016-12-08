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

public class InsertReviewActivity extends AppCompatActivity {
    String db;
    Connection conn;
    int hill_id, review_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_review);

        Bundle extras = getIntent().getExtras();
        hill_id = extras.getInt("Hill_ID");
        conn = MainActivity.getConn();
        db = MainActivity.getDatabase();
    }

    public void insertReview(View view){
        String starRatingString, review;
        int starRating;

        TextView starRatingText = (TextView) findViewById(R.id.starRating);
        TextView reviewText = (TextView) findViewById(R.id.reviewText);

        starRatingString = starRatingText.getText().toString();
        starRating = Integer.parseInt(starRatingString);
        review = reviewText.getText().toString();

        try {
            String query = "SELECT MAX(ReviewID) as maxReviewID FROM " + db + ".dbo.Reviews";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                review_id = rs.getInt("maxReviewID") + 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            String query = "INSERT INTO " + db + ".dbo.Reviews VALUES (" + review_id + "," +  hill_id + "," + starRating + ",'" + review + "')";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
