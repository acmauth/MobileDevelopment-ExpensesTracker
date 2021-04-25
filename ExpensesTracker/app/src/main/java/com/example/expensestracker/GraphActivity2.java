package com.example.expensestracker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class GraphActivity2 extends AppCompatActivity {


    private Button insertButton2;
    private TextView textViewInsertData;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph2);
        ScreenController.remove_ui_header(this);
        insertButton2 = findViewById(R.id.insertButton2);
        textViewInsertData = findViewById(R.id.textViewInsertData);
        iv = findViewById(R.id.iv);

        if (!Python.isStarted())
        {
            Python.start(new AndroidPlatform(this));
        }

        final Python py = Python.getInstance();

        insertButton2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String xData = "1 2 3 4 5";
                String yData = "1.1 2.3 4.5 3.4 5.5";

                PyObject pyo = py.getModule("PyGraph");
                PyObject obj = pyo.callAttr("main", xData, yData);

                String imageString = obj.toString();
                byte [] imageBytes = android.util.Base64.decode(imageString, Base64.DEFAULT);

                Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                iv.setImageBitmap(bmp);

            }
        });

    }

}
