package com.example.expensestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TotalSpentActivity extends AppCompatActivity {

    //TODO make fields for each category
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
        Intent intent;
        switch (v.getId()) {
            case R.id.categories_button:
                intent = new Intent(v.getContext(), TransactionCategoriesActivity.class);
                startActivity(intent);
                break;

            case R.id.money_spent_button:
                intent = new Intent(v.getContext(), TotalSpentActivity.class);
                startActivity(intent);
                break;

            case R.id.graph_button:
                //
                break;
        }

    }
}