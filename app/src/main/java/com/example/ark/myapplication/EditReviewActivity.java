package com.example.ark.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EditReviewActivity extends AppCompatActivity {

    String db, currentReview, currentRating;
    Connection conn;
    int review_id;
    TextView starRatingText, reviewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_review);

        starRatingText = (TextView) findViewById(R.id.starRating);
        reviewText = (TextView) findViewById(R.id.reviewText);

        Bundle extras = getIntent().getExtras();
        review_id = extras.getInt("ReviewID");
        currentReview = extras.getString("currentReview");
        currentRating = extras.getString("currentRating");
        conn = MainActivity.getConn();
        db = MainActivity.getDatabase();

        starRatingText.setText(currentRating);
        reviewText.setText(currentReview);
    }

    public void editReview(View view){

        String newReview;
        int newRating;

        newRating =Integer.parseInt(starRatingText.getText().toString());
        newReview = reviewText.getText().toString();


        try {
            String query = "UPDATE " + db + ".dbo.Reviews SET StarRating=" + newRating + ", TextReview="+"'"+ newReview + "'"+" WHERE ReviewID=" + review_id;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
