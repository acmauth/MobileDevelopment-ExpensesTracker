package com.example.expensestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db);
        Bundle b = getIntent().getExtras(); // gets bundle from Intent
        TextView textView = findViewById(R.id.title_of_top_bar); // gets TextView (top bar title)

        if(b != null){
            String categoryName = b.getString("category"); // gets category name
            textView.setText(categoryName); // sets title for top bar
        }
    }
}