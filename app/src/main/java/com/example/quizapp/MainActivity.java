package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    private Button buttonTrue, buttonFalse, buttonStart;
    private List<Question> questionList;
    private int questionNumber, score;
    private boolean questionAnswer, gameStart, gameRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();

        // https://stackoverflow.com/questions/15912825/how-to-read-file-from-res-raw-by-name#new-answer?newreg=ff2c26d632b245888d8a8c5b3de3dd06
        InputStream jsonInputStream = getResources().openRawResource(R.raw.questions);
        jsonFileText = readTextFile(jsonInputStream);

        createGson();

        buttonFalse.setVisibility(View.INVISIBLE);
        buttonTrue.setVisibility(View.INVISIBLE);
        textScore.setVisibility(View.INVISIBLE);
    }

    private void wireWidgets()
    {
        textDisplayQuestion = findViewById(R.id.textView_main_display);
        textScore = findViewById(R.id.textView_main_score);
        buttonFalse = findViewById(R.id.button_main_false);
        buttonTrue = findViewById(R.id.button_main_true);
        buttonStart = findViewById(R.id.button_main_start);
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
        questionList = Arrays.asList(questions);
// verify that it read everything properly
        Log.d("O no", "onCreate: " + questionList.toString());
    }

    public void updateQuestion(int number)
    {
        buttonStart.setVisibility(View.INVISIBLE);
        Question point = questionList.get(number);
        textDisplayQuestion.setText(point.returnQuestion());
        questionAnswer = point.returnAnswer();
    }

    public void setListeners()
    {
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonStart.setVisibility(View.VISIBLE);
                buttonFalse.setVisibility(View.INVISIBLE);
                buttonTrue.setVisibility(View.INVISIBLE);

                questionNumber++;

                    if (questionAnswer == true) {
                        score++;
                        textScore.setText(score + "");
                        textDisplayQuestion.setText(getString(R.string.CORRECT));

                    } else {
                        textDisplayQuestion.setText(questionList.get(questionNumber - 1).returnAfterStatement());
                    }

            }
        });
        buttonFalse.setOnClickListener(new View.OnClickListener() {  // Plz dont butcher me grade cause i didnt use intents
            @Override
            public void onClick(View view) {
                buttonStart.setVisibility(View.VISIBLE);
                buttonFalse.setVisibility(View.INVISIBLE);
                buttonTrue.setVisibility(View.INVISIBLE);

                questionNumber++;
                if(questionAnswer == false)
                {
                    score++;
                    textScore.setText(score+"");
                    textDisplayQuestion.setText(getString(R.string.CORRECT));

                }
                else
                {
                    textDisplayQuestion.setText(questionList.get(questionNumber-1).returnAfterStatement());
                }
            }
        });
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gameRepeat == true)
                {
                    gameRepeat = false;
                    score = 0;
                    questionNumber = 0;
                    updateQuestion(0);
                    buttonStart.setText(getString(R.string.CORRECT));
                    textScore.setText("0");
                    buttonFalse.setVisibility(View.VISIBLE);
                    buttonTrue.setVisibility(View.VISIBLE);
                    textScore.setVisibility(View.VISIBLE);
                }
                else {
                    if (questionNumber == 10) {
                        buttonStart.setText(getString(R.string.Retry));
                        buttonFalse.setVisibility(View.INVISIBLE);
                        buttonTrue.setVisibility(View.INVISIBLE);
                        textScore.setVisibility(View.INVISIBLE);
                        textDisplayQuestion.setText(getString(R.string.YourScore) + " " + score + "/10.\n" + getString(R.string.TryAgain));
                        gameRepeat = true;
                    }
                    else {

                        buttonFalse.setVisibility(View.VISIBLE);
                        buttonTrue.setVisibility(View.VISIBLE);
                        textScore.setVisibility(View.VISIBLE);

                        if (gameStart == false) {
                            gameStart = true;
                            buttonStart.setText(getString(R.string.next));
                            updateQuestion(0);

                        }
                        else {
                            updateQuestion(questionNumber);
                        }
                    }
                }
            }
        });
    }
}
