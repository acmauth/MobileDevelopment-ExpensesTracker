package com.example.expensestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TotalSpentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_spent_activity);

        TextView title = findViewById(R.id.title_of_top_bar); // gets TextView (top bar title)
        title.setText(R.string.money_spent_each_category_title); // sets title for top bar

        ScreenController.remove_ui_header(this);

    }

    @SuppressLint("NonConstantResourceId")
    public void menuChange (View v){
        Intent intent = ScreenController.get_intent_from_menu(v);
        startActivity(intent);
    }
}