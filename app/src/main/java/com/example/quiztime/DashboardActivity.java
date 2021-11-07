package com.example.quiztime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.quiztime.MainActivity.quizModals;

public class DashboardActivity extends AppCompatActivity {
    private TextView questions, textbtnA, textbtnB, textbtnC, textbtnD;
    CardView cardOptA, cardOptB, cardOptC, cardOptD;
    List<QuizModal> allquestionlist;
    QuizModal quizModal;
    int correct = 0, wrong = 0, index = 0;
    TextView next;

    CountDownTimer countDownTimer;
    int timervalue = 20;
    LinearProgressIndicator linearProgressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        idmethod();

        next.setClickable(false);
//        next.setVisibility(View.INVISIBLE);
        allquestionlist = quizModals;
        Collections.shuffle(allquestionlist);//to get random questions in the quiz
        quizModal = quizModals.get(index);
        setDatatoViews();
        resetcolour();
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timervalue = timervalue - 1;
                linearProgressIndicator.setProgress(timervalue);
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(DashboardActivity.this);
//                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);
                dialog.show();

                dialog.findViewById(R.id.try_again).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }.start();
    }

    private void idmethod() {
        linearProgressIndicator = findViewById(R.id.quest_timer);
        questions = findViewById(R.id.quest);
        textbtnA = findViewById(R.id.btnA);
        textbtnB = findViewById(R.id.btnB);
        textbtnC = findViewById(R.id.btnC);
        textbtnD = findViewById(R.id.btnD);

        cardOptA = findViewById(R.id.cardoA);
        cardOptB = findViewById(R.id.cardoB);
        cardOptC = findViewById(R.id.cardoC);
        cardOptD = findViewById(R.id.cardoD);

        next = findViewById(R.id.nextbtn);
    }


    private void setDatatoViews() {
        questions.setText(quizModal.getQuestion());
        textbtnA.setText(quizModal.getOption1());
        textbtnB.setText(quizModal.getOption2());
        textbtnC.setText(quizModal.getOption3());
        textbtnD.setText(quizModal.getOption4());
    }


    public void correctans(CardView cardView)
    {
//        next.setVisibility(View.VISIBLE);
        cardView.setCardBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correct++;
                index++;
                quizModal=quizModals.get(index);//to get the position of the list in model class
                setDatatoViews();
                enablebutton();
            }
        });
    }

    public void wrongans(CardView cardOptA)
    {
//        next.setVisibility(View.VISIBLE);
        cardOptA.setCardBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong++;
                if(index<quizModals.size()-1)
                {
                    index++;
                    quizModal=quizModals.get(index);
                    setDatatoViews();
                    resetcolour();
                    enablebutton();
                }
                else
                {
                    GameWon();
                }

            }
        });
    }

    private void GameWon() {
        Intent intent = new Intent(DashboardActivity.this,FinalResult.class);
        intent.putExtra("correct",correct);
        intent.putExtra("wrong",wrong);
        startActivity(intent);
    }

    public void enablebutton()
    {
        cardOptA.setClickable(true);
        cardOptB.setClickable(true);
        cardOptC.setClickable(true);
        cardOptD.setClickable(true);
    }
    public void dissablebutton()
    {
        cardOptA.setClickable(false);
        cardOptB.setClickable(false);
        cardOptC.setClickable(false);
        cardOptD.setClickable(false);
    }

    public void resetcolour()
    {
        cardOptA.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOptB.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOptC.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOptD.setCardBackgroundColor(getResources().getColor(R.color.white));
    }


    public void optAclick(View view) {
        dissablebutton();
        next.setClickable(true);
//        next.setVisibility(View.VISIBLE);
        if(quizModal.getOption1().equals(quizModal.getAnswer()))
        {
//            cardOptA.setCardBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
            if(index < quizModals.size()-1)
            {
                correctans(cardOptA);
                resetcolour();//some error

            }
            else
            {
                GameWon();
            }
        }
        else
            wrongans(cardOptA);

    }

    public void optBclick(View view) {
        dissablebutton();
        next.setClickable(true);
//        next.setVisibility(View.VISIBLE);
        if(quizModal.getOption2().equals(quizModal.getAnswer()))
        {
//            cardOptB.setCardBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
            if(index<quizModals.size()-1)
            {
                correctans(cardOptB);
                resetcolour();//some error

            }
            else
            {
                GameWon();
            }
        }
        else
            wrongans(cardOptB);

    }

    public void optCclick(View view) {
        dissablebutton();
        next.setClickable(true);
//        next.setVisibility(View.VISIBLE);
        if(quizModal.getOption3().equals(quizModal.getAnswer()))
        {
//            cardOptC.setCardBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
            if(index<quizModals.size()-1)
            {
                correctans(cardOptC);
                resetcolour();//some error

            }
            else
            {
                GameWon();
            }
        }
        else
            wrongans(cardOptC);

    }

    public void optDclick(View view) {
        dissablebutton();
        next.setClickable(true);
//        next.setVisibility(View.VISIBLE);
        if(quizModal.getOption4().equals(quizModal.getAnswer()))
        {
//            cardOptD.setCardBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
            if(index<quizModals.size()-1)
            {
                correctans(cardOptD);
                resetcolour();//some error

            }
            else
            {
                GameWon();
            }
        }
        else
            wrongans(cardOptD);

    }
}