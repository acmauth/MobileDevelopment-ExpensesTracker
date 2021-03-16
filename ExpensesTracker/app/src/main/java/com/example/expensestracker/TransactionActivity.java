package com.example.expensestracker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_activity);

        Bundle b = getIntent().getExtras(); // gets bundle from Intent
        TextView textView = findViewById(R.id.title_of_top_bar); // gets TextView (top bar title)

        if(b != null){
            String categoryName = b.getString("category"); // gets category name
            textView.setText(categoryName); // sets title for top bar
        }


    }

}