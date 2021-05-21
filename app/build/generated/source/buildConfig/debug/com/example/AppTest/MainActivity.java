package com.example.AppTest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.AppTest.R;

public class MainActivity extends AppCompatActivity {
    public static int loadtime=2500;
    public View l1,l2,l3,l4,l5,l6,l7;
    public TextView h1,h2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l1=findViewById(R.id.l1);
        l2=findViewById(R.id.l2);
        l3=findViewById(R.id.li3);
        l4=findViewById(R.id.l4);
        l5=findViewById(R.id.l5);
        l6=findViewById(R.id.l6);
        l7=findViewById(R.id.l7);
        h1=findViewById(R.id.h1);
        h2=findViewById(R.id.h2);

        l1.setTranslationX(-400);
        l2.setTranslationX(-400);
        l3.setTranslationX(-400);
        l4.setTranslationX(-400);
        l5.setTranslationX(-400);
        l6.setTranslationX(-400);
        l7.setTranslationX(-400);
        h1.setScaleX(0);h1.setScaleY(0);
        h2.setScaleX(0);h2.setScaleY(0);

        l1.animate().setDuration(1000).translationXBy(400);
        l2.animate().setDuration(1000).translationXBy(400);
        l3.animate().setDuration(1000).translationXBy(400);
        l4.animate().setDuration(1000).translationXBy(400);
        l5.animate().setDuration(1000).translationXBy(400);
        l6.animate().setDuration(1000).translationXBy(400);
        l7.animate().setDuration(1000).translationXBy(400);
        h1.animate().setDuration(500).scaleX((float) 1.35).scaleY((float) 1.35);
        h2.animate().setDuration(500).scaleX((float) 0.85).scaleY((float) 0.85);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,dashboard.class);
                startActivity(i);
                finish();
            }
        },loadtime);

    }
}