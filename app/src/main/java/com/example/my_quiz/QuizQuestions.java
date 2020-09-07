package com.example.my_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizQuestions extends AppCompatActivity implements View.OnClickListener {

    private Button flaseButton;
    private Button trueButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private TextView questionText;
    private Button mathsSection;

    private int currentQuestionIndex = 0;


    private Question[] questions = new Question[]{
            new Question(R.string.question_science_1,false),
            new Question(R.string.question_science_2, false),
            new Question(R.string.question_science_3, true),
            new Question(R.string.question_science_4,false),
            new Question(R.string.question_science_5,true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questions);

        flaseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.previous_button);
        questionText = findViewById(R.id.question_text_view);
        mathsSection = findViewById(R.id.maths_section);


        flaseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        mathsSection.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.false_button:
                if(questions[currentQuestionIndex].isAnswerTrue() == false)
                {
                    Toast.makeText(QuizQuestions.this, R.string.correct_answer, Toast.LENGTH_SHORT).show();
                    currentQuestionIndex = (currentQuestionIndex + 1) % questions.length ;
                    UpdateQuestion();
                }
                else
                {
                    Toast.makeText(QuizQuestions.this, R.string.wrong_answer, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.true_button:
                if(questions[currentQuestionIndex].isAnswerTrue() == true)
                {
                    Toast.makeText(QuizQuestions.this, R.string.correct_answer, Toast.LENGTH_SHORT).show();
                    currentQuestionIndex = (currentQuestionIndex + 1) % questions.length ;
                    UpdateQuestion();
                }
                else
                {
                    Toast.makeText(QuizQuestions.this, R.string.wrong_answer, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.next_button:
                currentQuestionIndex = (currentQuestionIndex + 1) % questions.length ;
                Log.d("Current", "onClick: "+ currentQuestionIndex);
                UpdateQuestion();
                break;
            case R.id.previous_button:
                if(currentQuestionIndex > 0){
                    currentQuestionIndex = (currentQuestionIndex - 1) % questions.length;
                    Log.d("Current", "Question_Index: "+ currentQuestionIndex);
                }
                UpdateQuestion();
                break;
            case R.id.maths_section:
                Intent intent = new Intent(QuizQuestions.this, MainActivity.class);//Intent is to go from this activity to MathsQuestions Activity
                startActivity(intent);//Starts the second activity

        }
    }

    private void UpdateQuestion()
    {
        questionText.setText(questions[currentQuestionIndex].getAnswerResId());
    }
}