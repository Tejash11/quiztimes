package com.example.quiztime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<QuizModal>quizModals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quizModals = new ArrayList<>();
        quizModals.add(new QuizModal("Samsung is a company of ?","South Korea","Brazil","USA","China","South Korea"));
        quizModals.add(new QuizModal("Full form of API ?","Application Programming Implementation","Application Programming Interface","Automatic Programming Implementation","Application Processing Interface","Application Programming Interface"));
        quizModals.add(new QuizModal("Types Of Layouts in Android Studio ?","Relative Layout","Linear Layout","Constraint Layout","All of the above","All of the above"));
        quizModals.add(new QuizModal("Virtual Machine used for Android OS ?","JVM","Dalvik Virtual Machine","Simple Virtual Machine","None of the above","Dalvik Virtual Machine"));
        quizModals.add(new QuizModal("Android is Licensed under ?","OSS","Sourceforge","Apache/MIT","None of the above","Sourceforge"));quizModals.add(new QuizModal("Samsung is a company of ?","South Korea","Brazil","USA","China","South Korea"));
        quizModals.add(new QuizModal("Full form of API ?","Application Programming Implementation","Application Programming Interface","Automatic Programming Implementation","Application Processing Interface","Application Programming Interface"));
        quizModals.add(new QuizModal("Types Of Layouts in Android Studio ?","Relative Layout","Linear Layout","Constraint Layout","All of the above","All of the above"));
        quizModals.add(new QuizModal("Virtual Machine used for Android OS ?","JVM","Dalvik Virtual Machine","Simple Virtual Machine","None of the above","Dalvik Virtual Machine"));
        quizModals.add(new QuizModal("Android is Licensed under ?","OSS","Sourceforge","Apache/MIT","None of the above","Sourceforge"));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);
    }
}