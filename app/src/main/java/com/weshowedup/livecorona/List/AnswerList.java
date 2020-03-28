package com.weshowedup.livecorona.List;

public class AnswerList {
    private String question;
    private String answer;

    public AnswerList(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return question + " " + answer;
    }
}
