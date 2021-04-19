package com.example.expensestracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBAmounts extends AppCompatActivity {

    EditText priceBox;
    EditText catBox;
    private final String[] categories = {"Food"};
    private ArrayList<String> categoriesArraylist = new ArrayList<>();
    LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_amounts);
        //Get references to view objects
        priceBox = findViewById(R.id.price);
        catBox = findViewById(R.id.category);


      /*  findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DBAmounts.this, DBActivity.class);
                //   i.putExtra("price",Integer.parseInt(priceBox.getText().toString()));
                startActivity(i);
            }
        }); */
    }

    //OnClick method for ADD button
    public void newProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        String price = priceBox.getText().toString();
        String category = catBox.getText().toString();

        if (!priceBox.equals("")) {
            Product found = dbHandler.findProduct(catBox.getText().toString());
            if (found == null) {
                Product product = new Product(Integer.parseInt(price), category);
                dbHandler.addProduct(product);
                priceBox.setText("");
                catBox.setText("");

            }
        }
    }

    public void getCat(View view) {

        Log.d("get", "cat");

        ll = findViewById(R.id.buttons_layout);
        LayoutInflater inflater = this.getLayoutInflater();

        String categoryName = catBox.getText().toString();
        categoriesArraylist.add(categoryName);

        // access category_button.xml
        View categoriesView = inflater.inflate(R.layout.category_button, ll, false);
        View view1 = getLayoutInflater().inflate(R.layout.category_button, ll, false);

        Button btn = view.findViewById(R.id.category_button); // gets category button
        btn.setText(catBox.getText().toString()); // sets category name to button

        // adds on click lister to each button
        btn.setOnClickListener(v -> {
            // provides which class to be invoked (in this case, TransactionActivity to start)
            Intent intent = new Intent(v.getContext(), TransactionActivity.class);

            Bundle b = new Bundle(); // used to pass data to between activities
            b.putString("category", ((Button) v).getText().toString()); // stores category name

            intent.putExtras(b); // adds bundle

            startActivity(intent); // starts the new activity specified above
        });
        ll.addView(view1); // adds button to Linear Layout


    }
}




