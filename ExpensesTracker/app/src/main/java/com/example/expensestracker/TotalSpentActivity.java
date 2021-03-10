package com.example.expensestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TotalSpentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_spent_activity);

        View bottom_menu=findViewById(R.id.bottom_menu_bar);

        ImageButton categoriesButton=bottom_menu.findViewById(R.id.categories_button);
        ImageButton moneySpentButton=bottom_menu.findViewById(R.id.money_spent_button);
        ImageButton graphButton=bottom_menu.findViewById(R.id.graph_button);

        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CategoriesActivity.class);
                startActivity(intent);
            }
        });

        moneySpentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //doNothing

            }
        });

        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO add graph activity
                //Intent intent=new Intent(getApplicationContext(),GraphActivity.class);
                //startActivity(intent);
            }
        });
    }
}