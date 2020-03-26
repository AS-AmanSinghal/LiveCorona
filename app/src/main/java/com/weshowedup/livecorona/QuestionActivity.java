package com.weshowedup.livecorona;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {
    TextView ques_english, ques_hindi;
    Button yes, no;
    ProgressBar progressBar;
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

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                no.setEnabled(false);
                no.setBackgroundResource(R.drawable.disablebuttonbackground);
                yes.setEnabled(false);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                yes.setEnabled(false);
                yes.setBackgroundResource(R.drawable.disablebuttonbackground);
                no.setEnabled(false);
            }
        });
    }
}
