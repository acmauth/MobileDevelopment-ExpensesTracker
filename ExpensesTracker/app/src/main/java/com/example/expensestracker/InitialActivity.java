package com.example.expensestracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This class is the backend of the initial_activity.xml. It is used for the user to input his budget
 * goals and other personal info. It is called during the 1st initial start of the app and afterwards
 * it can be changed through the (???)
 */
//TODO have options in other menus to access this activity if user needs to change the budget goals
public class InitialActivity extends AppCompatActivity {

    private Button submitButton;
    private String name;
    private double budget;
    private String periodSelection;
    private boolean options = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_activity);

        ScreenController.remove_ui_header(this);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (allFieldsFilled()){
                    openCategoriesActivity();
                }

            }
        });
    }

    public boolean setName(){
        TextView nameTextView = findViewById(R.id.nameTextView);
        name = nameTextView.getText().toString();
        return true;
    }

    public boolean setBudget(){

        String budget;
        TextView budgetTextView = findViewById(R.id.budgetTextView);
        budget = budgetTextView.getText().toString();

        try {
            this.budget = Double.parseDouble(budget);
        }
        catch (NumberFormatException nfe){
            budgetTextView.setHint("*Required Field");
            budgetTextView.setHintTextColor(Color.RED);
            budgetTextView.setText("");
            return false;
        }

        return true;

    }

    public boolean setPeriodSelection(){

        RadioButton monthRadioButton = findViewById(R.id.monthRadioButton);
        RadioButton weekRadioButton = findViewById(R.id.weekRadioButton);

        if (monthRadioButton.isChecked()){
            periodSelection = "month";
        }
        else if (weekRadioButton.isChecked()){
            periodSelection = "week";
        }
        else {
            TextView textView = findViewById(R.id.warnChoices);
            textView.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }

    /**
     * This method is used to check whether the user input is valid
     */
    public boolean allFieldsFilled(){
        if (!setName()){
            return false;
        }
        if (!setBudget()){
            return false;
        }
        if (!setPeriodSelection()){
            return false;
        }
        return true;
    }

    /**
     * This method starts a new activity
     */
    public void openCategoriesActivity(){
        Intent intent = new Intent(this, TransactionCategoriesActivity.class);
        startActivity(intent);
    }


}