package com.example.expensestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_activity);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        Bundle b = getIntent().getExtras(); // gets bundle from Intent
        TextView textView = findViewById(R.id.title_of_top_bar); // gets TextView (top bar title)

        if(b != null){
            String categoryName = b.getString("category"); // gets category name
            textView.setText(categoryName); // sets title for top bar
        }

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