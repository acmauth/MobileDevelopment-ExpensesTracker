package com.example.expensestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_main);
=======
        // TODO Check in DB if user already registered before moving to initial,
        // otherwise move to transaction categories Activity
        Intent intent = new Intent(this, InitialActivity.class);
        startActivity(intent);
>>>>>>> main
    }
}