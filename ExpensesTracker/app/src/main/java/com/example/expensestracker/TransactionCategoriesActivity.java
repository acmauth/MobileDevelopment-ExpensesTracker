package com.example.expensestracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;

public class TransactionCategoriesActivity extends AppCompatActivity{

    // Categories name
    private final String[] categories = {"Food", "Drinks", "Shopping", "Entertainment", "Investments"};
    private ArrayList<String> categoriesArraylist = new ArrayList<>();
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_categories_activity);

        ScreenController.remove_ui_header(this);

        TextView textView = findViewById(R.id.title_of_top_bar); // gets TextView (top bar title)
        textView.setText(R.string.transaction_categories_title); // sets title for top bar

        setCategoriesButtons();

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
                intent = new Intent(v.getContext(), GraphActivity2.class);
                startActivity(intent);
                break;
        }

    }

    public void setCategoriesButtons(){
        Button infoButton = findViewById(R.id.info_button); // gets Button ( information button)
        infoButton.setText("?"); // sets text for the specific button

        ll = findViewById(R.id.buttons_layout); // gets layout (Linear layout)


        Collections.addAll(categoriesArraylist, categories);

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

            // adds on click lister to each button
            btn.setOnClickListener(v -> {
                // provides which class to be invoked (in this case, TransactionActivity to start)
                Intent intent = new Intent(v.getContext(), TransactionActivity.class);

                Bundle b = new Bundle(); // used to pass data to between activities
                b.putString("category", ((Button) v).getText().toString()); // stores category name

                intent.putExtras(b); // adds bundle

                startActivity(intent); // starts the new activity specified above
            });
            ll.addView(view); // adds button to Linear Layout
        }
    }

    public void addCategory(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_category_dialog, null);
        EditText editText = dialogView.findViewById(R.id.give_category_name_EditText);

        builder.setView(dialogView)
                .setTitle("Create a new category")
                .setPositiveButton("Submit", (dialog, which) -> {

                    String categoryName = editText.getText().toString();
                    categoriesArraylist.add(categoryName);

                    // access category_button.xml
                    View categoriesView = inflater.inflate(R.layout.category_button,ll,false);

                    Button btn = categoriesView.findViewById(R.id.category_button); // gets category button
                    btn.setText(categoryName); // sets category name to button

                    // adds on click lister to each button
                    btn.setOnClickListener(v -> {
                        // provides which class to be invoked (in this case, TransactionActivity to start)
                        Intent intent = new Intent(v.getContext(), TransactionActivity.class);

                        Bundle b = new Bundle(); // used to pass data to between activities
                        b.putString("category", ((Button) v).getText().toString()); // stores category name

                        intent.putExtras(b); // adds bundle

                        startActivity(intent); // starts the new activity specified above
                    });
                    ll.addView(categoriesView); // adds button to Linear Layout
                    Toast.makeText(getApplicationContext(),"Category added",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(s.length() >= 1);
            }
        });
    }


}