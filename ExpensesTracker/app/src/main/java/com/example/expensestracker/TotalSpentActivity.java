package com.example.expensestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TotalSpentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_spent_activity);

        TextView title = findViewById(R.id.title_of_top_bar); // gets TextView (top bar title)
        title.setText(R.string.money_spent_each_category_title); // sets title for top bar
    }
}