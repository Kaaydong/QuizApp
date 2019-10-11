package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private String firstName;
    private String lastName;
    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();

        // https://stackoverflow.com/questions/15912825/how-to-read-file-from-res-raw-by-name#new-answer?newreg=ff2c26d632b245888d8a8c5b3de3dd06
        InputStream jsonInputStream = getResources().openRawResource(R.raw.questions);
        String sjson = readTextFile(jsonInputStream);

        test.setText(sjson);
    }

    private void wireWidgets()
    {
        test = findViewById(R.id.textView_main_test);
    }

    // https://stackoverflow.com/questions/15912825/how-to-read-file-from-res-raw-by-name#new-answer?newreg=ff2c26d632b245888d8a8c5b3de3dd06
    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
//    public void createGson()
//    {
//        Gson gson = new Gson();
//// read your json file into an array of questions
//        Question[] questions =  gson.fromJson(jsonString, Question[].class);
//// convert your array to a list using the Arrays utility class
//        List<Question> questionList = Arrays.asList(questions);
//// verify that it read everything properly
//        Log.d(TAG, "onCreate: " + questionList.toString());
//    }
}
