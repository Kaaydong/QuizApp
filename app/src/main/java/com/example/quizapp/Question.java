package com.example.quizapp;

public class Question {

    private String question;
    private boolean answer;
    private String afterStatement;

    public Question(String question, boolean answer, String afterStatement)
    {
        this.question = question;
        this.answer = answer;
        this.afterStatement = afterStatement;
    }

    public String returnQuestion()
    {
        return question;
    }

    public String returnAfterStatement()
    {
        return afterStatement;
    }

    public boolean returnAnswer()
    {
        return answer;
    }

    //{"question":"" ,"answer":"","afterStatement": ""}
}
