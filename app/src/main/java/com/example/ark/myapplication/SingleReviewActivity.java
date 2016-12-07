package com.example.ark.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SingleReviewActivity extends AppCompatActivity {

    String starRating, db, reviewText;
    int hill_id, reviewId;
    Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_review);

        conn = MainActivity.getConn();
        db = MainActivity.getDatabase();
        TextView rating = (TextView) findViewById(R.id.rating);
        TextView review = (TextView) findViewById(R.id.review);


        Bundle extras = getIntent().getExtras();
        reviewId = extras.getInt("reviewId");
        hill_id = extras.getInt("Hill_ID");


        try {
            String query = "SELECT StarRating, TextReview FROM " + db + ".dbo.Reviews WHERE Hill_ID=" + hill_id + " AND ReviewID=" + reviewId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                starRating = rs.getString("StarRating");
                reviewText = rs.getString("TextReview");
                rating.setText(starRating);
                review.setText(reviewText);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
