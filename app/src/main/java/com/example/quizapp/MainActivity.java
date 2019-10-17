package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private String firstName;
//    private String lastName;
    private String jsonFileText;
    private TextView textDisplayQuestion, textScore;
    private Button buttonTrue, buttonFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();

        // https://stackoverflow.com/questions/15912825/how-to-read-file-from-res-raw-by-name#new-answer?newreg=ff2c26d632b245888d8a8c5b3de3dd06
        InputStream jsonInputStream = getResources().openRawResource(R.raw.questions);
        jsonFileText = readTextFile(jsonInputStream);

        textDisplayQuestion.setText(jsonFileText);
    }

    private void wireWidgets()
    {
        textDisplayQuestion = findViewById(R.id.textView_main_display);
        textScore = findViewById(R.id.textView_main_score);
        buttonFalse = findViewById(R.id.button_main_false);
        buttonTrue = findViewById(R.id.button_main_true);
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
    public void createGson()
    {
        Gson gson = new Gson();
// read your json file into an array of questions
        Question[] questions =  gson.fromJson(jsonFileText, Question[].class);
// convert your array to a list using the Arrays utility class
        List<Question> questionList = Arrays.asList(questions);
// verify that it read everything properly
        Log.d("O no", "onCreate: " + questionList.toString());
    }


}
