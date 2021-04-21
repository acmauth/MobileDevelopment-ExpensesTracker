package com.example.expensestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class GraphActivity extends AppCompatActivity {

    Button insertButton;
    EditText inputTextY;
    GraphView graphView;

    DatabaseHandler dbh;
    SQLiteDatabase sqLiteDatabase;

    LineGraphSeries<DataPoint> dataseries = new LineGraphSeries<>();

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        ScreenController.remove_ui_header(this);

        insertButton = (Button) findViewById(R.id.insertButton);
        inputTextY = (EditText) findViewById(R.id.inputTextY);
        graphView = (GraphView) findViewById(R.id.graph);

        dbh = new DatabaseHandler(this);
        sqLiteDatabase = dbh.getWritableDatabase();
        
        graphView.addSeries(dataseries);
        graphView.getGridLabelRenderer().setNumHorizontalLabels(3);
        //graphView.getGridLabelRenderer().setLabelVerticalWidth(150);
        //Xromata
        //dataseries.setColor(Color.argb(226, 91,34)); Xrwma grammon
        //dataseries.setThickness(6); poso lepti tha einai i grammi

//        TODO FIX THE COLORS

         dataseries.setDrawBackground(true);
//        graphView.setBackgroundColor(Color.argb(50, 50, 0, 200));
//        dataseries.setBackgroundColor(Color.argb(60, 95, 226, 156));
        dataseries.setDrawDataPoints(true);


        //dataseries.setDataPointsRadius(8); poso megales na einai i telies


        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return sdf.format(new Date((long) value));
                } else {
                    return super.formatLabel(value, isValueX)+"€";
                }
            }
        });

        //dataseries.resetData(getDataPoint());
        insertData();
    }

    public void insertData() {
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Dexomaste Dedomena kai ta bazi sto  db
                //to x einai imerominia
                long xValue = new Date().getTime();
                // y = Text
                int yValue = Integer.parseInt(String.valueOf(inputTextY.getText()));

                dbh.insertToData(xValue, yValue);

                //Perni ta dedomena kai ta bazi sto grafima

                dataseries.resetData(getDataPoint());

                graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX) {
                            return sdf.format(new Date((long) value));
                        } else {
                            return super.formatLabel(value, isValueX)+"€";
                        }
                    }
                });

            }
        });
    }

    private DataPoint[] getDataPoint() {
        String[] column = {"xValue", "yValue"};
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query("Table1", column, null, null, null, null, null);

        DataPoint[] dataPoints = new DataPoint[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            dataPoints[i] = new DataPoint(cursor.getLong(0), cursor.getInt(1));
        }
        Log.i("arithmos", Arrays.toString(dataPoints));
        return dataPoints;
    }

    @SuppressLint("NonConstantResourceId")
    public void menuChange (View v){
        Intent intent = ScreenController.get_intent_from_menu(v);
        startActivity(intent);
    }
}