package com.example.quizapp;

public class Question {

    private String question;
    private String answer;
    private String afterStatement;

    public Question(String question, String answer, String afterStatement)
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

    public String returnAnswer()
    {
        return answer;
    }

    //{"question":"" ,"answer":"","afterStatement": ""}
}
