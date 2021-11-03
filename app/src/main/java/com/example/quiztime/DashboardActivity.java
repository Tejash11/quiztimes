package com.example.quiztime;

import androidx.appcompat.app.AppCompatActivity;

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
    private TextView questions, quiz;
    private Button optbtn1, optbtn2, optbtn3, optbtn4;
    List<QuizModal> allquestionlist;
    QuizModal quizModal;
    int correct=0,wrong=0,index=0;
    int currentscore =0, questAttempted =1, currentpos;

    TextView next;
    Random random;

    CountDownTimer countDownTimer;
    int timervalue = 20;

    LinearProgressIndicator linearProgressIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        questions = findViewById(R.id.quest);
        quiz = findViewById(R.id.quiz_arena);
        optbtn1 = findViewById(R.id.b1);
        optbtn2 = findViewById(R.id.b2);
        optbtn3 = findViewById(R.id.b3);
        optbtn4 = findViewById(R.id.b4);
        next = findViewById(R.id.nextbtn);

        next.setClickable(false);
        allquestionlist=quizModals;
        Collections.shuffle(allquestionlist);//to get random questions in the quiz
        quizModal=quizModals.get(index);
//        quizModals = new ArrayList<>();
        random =  new Random();
//        addingquestion(quizModals);
        currentpos = random.nextInt(quizModals.size());
        setDatatoViews(currentpos);
//        resetcolour();

        optbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModals.get(currentpos).getAnswer().trim().toLowerCase().equals(optbtn1.getText().toString().trim().toLowerCase())){
                    currentscore++;
                }
                questAttempted++;
                currentpos = random.nextInt(quizModals.size());
                setDatatoViews(currentpos);
            }
        });

        optbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModals.get(currentpos).getAnswer().trim().toLowerCase().equals(optbtn2.getText().toString().trim().toLowerCase())){
                    currentscore++;
                }
                questAttempted++;
                currentpos = random.nextInt(quizModals.size());
                setDatatoViews(currentpos);

            }
        });

        optbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModals.get(currentpos).getAnswer().trim().toLowerCase().equals(optbtn3.getText().toString().trim().toLowerCase())){
                    currentscore++;
                }
                questAttempted++;
                currentpos = random.nextInt(quizModals.size());
                setDatatoViews(currentpos);

            }
        });

        optbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModals.get(currentpos).getAnswer().trim().toLowerCase().equals(optbtn4.getText().toString().trim().toLowerCase())){
                    currentscore++;
                }
                questAttempted++;
                currentpos = random.nextInt(quizModals.size());
                setDatatoViews(currentpos);

            }
        });


        linearProgressIndicator = findViewById(R.id.quest_timer);
        countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timervalue = timervalue-1;
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
                        Intent  intent = new Intent(DashboardActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }.start();
    }

    /*showing score of the quiz*/
    private void scoresheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(DashboardActivity.this);
        View bottomSheet = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_final_result, (RelativeLayout) findViewById(R.id.score));
        Button replay = bottomSheet.findViewById(R.id.replay);
        TextView scoretv = bottomSheet.findViewById(R.id.scores);
        scoretv.setText("Your Score is :" + currentscore + "/10");
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentpos = random.nextInt(quizModals.size());
                setDatatoViews(currentpos);
                questAttempted = 1;
                currentscore = 0;
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setCancelable(false);//to avoid cancelling on clicking on another part of the screen
        bottomSheetDialog.setContentView(bottomSheet);
        bottomSheetDialog.show();
    }

    private void setDatatoViews(int currentpos) {
        questions.setText(quizModals.get(currentpos).getQuestion());
        optbtn1.setText(quizModals.get(currentpos).getOption1());
        optbtn2.setText(quizModals.get(currentpos).getOption2());
        optbtn3.setText(quizModals.get(currentpos).getOption3());
        optbtn4.setText(quizModals.get(currentpos).getOption4());
    }

    public void correctans(Button button)
    {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correct++;
                index++;
                quizModal=quizModals.get(index);
                setDatatoViews(currentpos);
                enablebutton();
            }
        });
    }

    public void wrongans(Button button)//maybe some error here
    {
        button.setBackgroundColor(getResources().getColor(R.color.design_default_color_error));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong++;
                if(index<quizModals.size()-1)
                {
                    index++;
                    quizModal=quizModals.get(index);
                    setDatatoViews(currentpos);
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
        optbtn1.setClickable(true);
        optbtn2.setClickable(true);
        optbtn3.setClickable(true);
        optbtn4.setClickable(true);
    }
    public void dissablebutton()
    {
        optbtn1.setClickable(false);
        optbtn2.setClickable(false);
        optbtn3.setClickable(false);
        optbtn4.setClickable(false);
    }

    public void resetcolour()
    {
        optbtn1.setBackgroundColor(getResources().getColor(R.color.white));
        optbtn2.setBackgroundColor(getResources().getColor(R.color.white));
        optbtn3.setBackgroundColor(getResources().getColor(R.color.white));
        optbtn4.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void b1(View view){
        next.setClickable(true);
        dissablebutton();
        if(quizModal.getOption1().equals(quizModal.getAnswer()))
        {
            optbtn1.setBackgroundColor(getResources().getColor(R.color.teal_200));
            if(index<quizModals.size()-1)
            {
                correctans(optbtn1);
//                resetcolour();
            }
            else
            {
                GameWon();
            }
        }
        else
            wrongans(optbtn1);
    }

    public void b2(View view){
        next.setClickable(true);
        dissablebutton();
        if(quizModal.getOption2().equals(quizModal.getAnswer()))
        {
            optbtn2.setBackgroundColor(getResources().getColor(R.color.teal_200));
            if(index<quizModals.size()-1)
            {
                correctans(optbtn2);
//                resetcolour();
            }
            else
            {
                GameWon();
            }
        }
        else
            wrongans(optbtn2);
    }

    public void b3(View view){
        next.setClickable(true);
        dissablebutton();
        if(quizModal.getOption3().equals(quizModal.getAnswer()))
        {
            optbtn3.setBackgroundColor(getResources().getColor(R.color.teal_200));
            if(index<quizModals.size()-1)
            {
                correctans(optbtn3);
//                resetcolour();
            }
            else
            {
                GameWon();
            }
        }
        else
            wrongans(optbtn3);
    }

    public void b4(View view){
        next.setClickable(true);
        dissablebutton();
        if(quizModal.getOption4().equals(quizModal.getAnswer()))
        {
            optbtn4.setBackgroundColor(getResources().getColor(R.color.teal_200));
            if(index<quizModals.size()-1)
            {

//                resetcolour();
                correctans(optbtn4);
            }
            else
            {
                GameWon();
            }
        }
        else
            wrongans(optbtn4);
    }
}