package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.HashMap;

public class QuestionsActivity extends AppCompatActivity
{
    TextView tv,anscheck;
    ImageView imgview;
    private static int mindis=0;
    private float x1,y1,x2,y2;
    Button submitbutton, nextbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;
    HashMap<Integer , String> map = new HashMap<>();
    private ProgressBar progressBar;


    String questions[] = {
            " 1 Royalty on the book is less than the printing cost by:",
            "2. Image belongs to which company ?",
            "3. Guess cricketer",
    };
    String answers[] = {"25%","Nike","Virat Kohli"};

    String opt[] = {
            "12.5%","10%","25%","none of these",
            "Nike","Puma","Reebok","Fila",
            "Sachin Tendulkar","MS Dhoni","Yuvraj Singh","Virat Kohli",

    };

    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView=(TextView)findViewById(R.id.DispName);
        Intent intent = getIntent();

        submitbutton=(Button)findViewById(R.id.button3);
        nextbutton=(Button)findViewById(R.id.buttonnext);
        nextbutton.setVisibility(View.INVISIBLE);
        tv=(TextView) findViewById(R.id.tvque);
        imgview=(ImageView)findViewById(R.id.imgv);
        progressBar=(ProgressBar)findViewById(R.id.pb);

        map.put(0 , "https://www.indiabix.com/_files/images/data-interpretation/pie-charts/15-2-1-1.png");
        map.put(1 , "https://cdn.britannica.com/50/213250-050-02322AA8/Nike-logo.jpg");
        map.put(2, "https://static.toiimg.com/thumb/msid-96131622,imgsize-49438,width-400,resizemode-4/96131622.jpg");


        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        anscheck=(Button)findViewById(R.id.anscheck);
        anscheck.setVisibility(View.INVISIBLE);
        tv.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        Glide.with(QuestionsActivity.this).asBitmap().load(map.get(flag)).into(imgview);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {


                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
                if(ansText.equals(answers[flag])) {
                    anscheck.setVisibility(View.VISIBLE);
                    anscheck.setBackground((Drawable)getResources().getDrawable(R.drawable.greenn));
                    anscheck.setText("Correct");

                }
                else {
                    anscheck.setVisibility(View.VISIBLE);
                    anscheck.setBackground((Drawable)getResources().getDrawable(R.drawable.red));
                    anscheck.setText("Wrong");
                }

                flag++;


                progressBar.setProgress(flag);
                progressBar.setMax(3);
                submitbutton.setVisibility(View.INVISIBLE);
                nextbutton.setVisibility(View.VISIBLE);

                for (int i = 0; i < radio_g.getChildCount(); i++) {
                    radio_g.getChildAt(i).setEnabled(false);
                }

            }
        });

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitbutton.setVisibility(View.VISIBLE);
                anscheck.setVisibility(View.INVISIBLE);


                Glide.with(QuestionsActivity.this).asBitmap().load(map.get(flag)).into(imgview);
                YoYo.with(Techniques.SlideInRight)
                        .duration(2000)
                        .repeat(0)
                        .playOn(findViewById(R.id.imgv));
                for (int i = 0; i < radio_g.getChildCount(); i++) {
                    radio_g.getChildAt(i).setEnabled(true);
                }
                radio_g.clearCheck();

                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                }
                else
                {

                    Intent in = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(in);
                }
                nextbutton.setVisibility(View.INVISIBLE);
            }
        });
    }


}