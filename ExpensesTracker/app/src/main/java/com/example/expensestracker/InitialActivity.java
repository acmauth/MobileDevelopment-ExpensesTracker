package com.example.expensestracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InitialActivity extends AppCompatActivity {

    private Button submitButton;
    private String name;
    private String budget;
    private String periodSelection;
    // create field for initial budget


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.initial_activity);

            submitButton = (Button) findViewById(R.id.submitButton);
            submitButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    if (allFieldsFilled()){
                        setName();
                        setPeriodSelection();
                        // Antonis invoke the method that set the Budget
                        openCategoriesActivity();
                    }
                    else {
                        //popup message at Text Views that is required
                    }

                }
            });
        }



        public void openCategoriesActivity(){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        public void setName(){
            TextView nameTextView = findViewById(R.id.nameTextView);
            name = nameTextView.getText().toString();
        }

        // Antonis
        public void setBudget(){}

        public void setPeriodSelection(){

            Button monthRadioButton = findViewById(R.id.monthRadioButton);
            Button weekRadioButton = findViewById(R.id.weekRadioButton);

            if (monthRadioButton.isSelected()){
                periodSelection = "month";
            }
            else if (weekRadioButton.isSelected()){
                periodSelection = "week";
            }

        }

        public boolean allFieldsFilled(){
            // this needs to be done
            return true;
        }

}
