package com.example.expensestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TransactionCategoriesActivity extends AppCompatActivity implements View.OnClickListener{

    // Categories name
    private final String[] categories = {"Food", "Drinks", "Shopping", "Entertainment", "Investments"};
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_categories_activity);

        TextView textView = findViewById(R.id.title_of_top_bar); // gets TextView (top bar title)
        textView.setText(R.string.transaction_categories_title); // sets title for top bar

        ll = findViewById(R.id.buttons_layout); // gets layout (Linear layout)

        /*
        * For each category, creates dynamically a button based on category_button.xml
        * Uses Inflater to find Category Button in a different xml file (Desired Button isn't declared
        * in transaction_categories_activity which is used in setContentView)
        */
        for (String category: categories) {
            // access category_button.xml
            View view = getLayoutInflater().inflate(R.layout.category_button,ll,false);

            Button btn = view.findViewById(R.id.category_button); // gets category button
            btn.setText(category); // sets category name to button
            btn.setOnClickListener(this); // adds on click lister to each button
            ll.addView(view); // adds button to Linear Layout
        }

    }

    @Override
    public void onClick(View v) {
        // provides which class to be invoked (in this case, TransactionActivity to start)
        Intent intent = new Intent(this, TransactionActivity.class);

        Bundle b = new Bundle(); // used to pass data to between activities
        b.putString("category", ((Button) v).getText().toString()); // stores category name

        intent.putExtras(b); // adds bundle

        startActivity(intent); // starts the new activity specified above
    }
}