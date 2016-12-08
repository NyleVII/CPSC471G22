package com.example.ark.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewsActivity extends AppCompatActivity {

    int hill_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> starRatings = extras.getStringArrayList("starRatings");
        final ArrayList<Integer> reviewIds = extras.getIntegerArrayList("reviewIds");
        hill_id = extras.getInt("Hill_ID");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.reviews_list, starRatings);

        ListView listView = (ListView) findViewById(R.id.reviewsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ReviewsActivity.this,SingleReviewActivity.class);
                intent.putExtra("reviewId",reviewIds.get(i));
                intent.putExtra("Hill_ID",hill_id);
                startActivity(intent);
            }
        });
    }

    public void openInsertReview(View view){

        Intent intent = new Intent(this,InsertReviewActivity.class);
        intent.putExtra("Hill_ID", hill_id);
        startActivity(intent);
    }

}
