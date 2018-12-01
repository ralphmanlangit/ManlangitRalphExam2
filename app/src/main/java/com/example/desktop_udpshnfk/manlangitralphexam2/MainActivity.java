package com.example.desktop_udpshnfk.manlangitralphexam2;

import android.database.Cursor;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText fname, lname, exam1, exam2;
    TextView ave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fname = findViewById(R.id.etFirstName);
        lname = findViewById(R.id.etLastName);
        exam1 = findViewById(R.id.etExam1);
        exam2 = findViewById(R.id.etExam2);
        ave = findViewById(R.id.tvAverage);
    }
    //save to SD card
    public void displayAverage(View v) {
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "External.txt");
        String first = fname.getText().toString();
        String last = lname.getText().toString();
        String score1 = exam1.getText().toString();
        String score2 = exam2.getText().toString();
        int num1 = Integer.parseInt(score1);
        int num2 = Integer.parseInt(score2);
        int calculate = (num1+num2)/2;
        int res = calculate;
        ave.setText(""+res);
        String result = ave.getText().toString();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(first.getBytes());
            fos.write(last.getBytes());
            fos.write(result.getBytes());
            Toast.makeText(this, "Data saved in SD card!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error writing in SD card...", Toast.LENGTH_LONG).show();
        }

        FileInputStream fis = null;
        int c;
        StringBuffer buffer = new StringBuffer();
        try {
            fis = new FileInputStream(file);
            while ((c = fis.read()) != -1) {
                buffer.append((char) c);
            }
            ave.setText(result);
        } catch (Exception e) {
            Toast.makeText(this, "Error reading...", Toast.LENGTH_LONG).show();
        }
    }
}

