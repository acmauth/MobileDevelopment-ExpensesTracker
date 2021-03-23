package com.example.expensestracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InitialActivity extends AppCompatActivity {

    private Button submitButton;
    private String name;
    private double budget;
    private String periodSelection;


        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.initial_activity);

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


        public void openCategoriesActivity(){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


}
