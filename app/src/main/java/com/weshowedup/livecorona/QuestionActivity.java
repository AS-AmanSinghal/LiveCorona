package com.weshowedup.livecorona;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.weshowedup.livecorona.List.AnswerList;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    public static String[] question = new String[]{
            "Dyspnoea", "Cough", "Chest Tightness", "Sputum", "Rhinorrhea", "Weakness", "Vomating", "Dizziness",
            "Diarrhoea", "Headache"
    };
    public static String[] question_hindi = new String[]{
            "दमा(दम फूलना)", "खांसी", "सीने में जकड़न", "कफ", "नाक से स्राव होना", "कमजोरी",
            "उल्टी", "चक्कर आना", "दस्त", "सिरदर्द"
    };
    ProgressBar progressBar;
    TextView ques_english, ques_hindi;
    Button yes, no;
    List<AnswerList> answerList = new ArrayList<>();
    private int questionNumber = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ques_english = findViewById(R.id.question_english);
        ques_hindi = findViewById(R.id.question_hindi);
        yes = findViewById(R.id.question_yes);
        no = findViewById(R.id.question_no);
        progressBar = findViewById(R.id.question_progress);
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        updateQuestion();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnswerList answer = new AnswerList(question[questionNumber], "Yes");
                answerList.add(answer);
                questionNumber++;
                if (questionNumber == question.length) {
                    yes.setEnabled(false);
                    yes.setBackgroundResource(R.drawable.disablebuttonbackground);
                    no.setEnabled(false);
                    no.setBackgroundResource(R.drawable.disablebuttonbackground);
                    System.out.println(answerList);
                    startActivity(new Intent(QuestionActivity.this, ResultActivity.class));
                    finish();
                } else {
                    updateQuestion();
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnswerList answer = new AnswerList(question[questionNumber], "No");
                answerList.add(answer);
                questionNumber++;
                if (questionNumber == question.length) {
                    no.setEnabled(false);
                    no.setBackgroundResource(R.drawable.disablebuttonbackground);
                    yes.setEnabled(false);
                    yes.setBackgroundResource(R.drawable.disablebuttonbackground);
                    System.out.println(answerList);
                    startActivity(new Intent(QuestionActivity.this, ResultActivity.class));
                    finish();
                } else {
                    updateQuestion();
                }
            }
        });
    }

    private void updateQuestion() {

        ques_english.setText(question[questionNumber]);
        ques_hindi.setText(question_hindi[questionNumber]);
    }
}
