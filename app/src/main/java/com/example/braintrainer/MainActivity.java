package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    int locationCorrect;
    TextView resultTextView;
    int score=0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    ConstraintLayout gameLayout;
    Button playAgainButton;

    ArrayList<Integer> answers = new ArrayList<Integer>();

    public void playAgain(View view){

        score= 0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public void chooseAnswer (View view){

        if(Integer.toString(locationCorrect).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;

        }else{
            resultTextView.setText("Wrong:(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        newQuestion();

    }



    public void newQuestion(){
        Random rand = new Random();
        locationCorrect = rand.nextInt(4);

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        answers.clear();



        for(int i = 0; i < 4; i++){

            if(i==locationCorrect){
                answers.add(a+b);
            }else{
                int wrongAnswer=rand.nextInt(41);
                while(wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }

                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = (TextView) findViewById(R.id.sumTextView);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        resultTextView = (TextView) findViewById(R.id.result);
        timerTextView=(TextView) findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);

        gameLayout = findViewById(R.id.gameLayout);

        startButton = (Button) findViewById(R.id.goButton);
        startButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }


}
