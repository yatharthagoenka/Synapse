package com.example.AppTest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.AppTest.activities.notesMain;

public class dashboard extends AppCompatActivity {
    CardView c1,c2,c3,c4;
    ImageView i1,i2,i3,i4;
    TextView handles;
    boolean p1=true,p2=true,p3=true,p4=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        handles=findViewById(R.id.handles);
        c1=findViewById(R.id.card1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard.this, notesMain.class);
                startActivity(i);
            }
        });
        c2=findViewById(R.id.card2);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard.this,SplashActivity.class);
                startActivity(i);
            }
        });
        c3=findViewById(R.id.card3);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard.this,cowinActivity.class);
                startActivity(i);
            }
        });
        c4=findViewById(R.id.card4);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard.this,cfActivity.class);
                startActivity(i);
            }
        });
        i1=findViewById(R.id.i1);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p1 && p2 && p3 && p4){
                    handles.setText("https://github.com/yatharthagoenka/");
                    handles.animate().alpha((float) 0.5).translationXBy(-140);
                    i1.animate().scaleXBy((float) 0.7).scaleYBy((float) 0.7);
                    i1.animate().translationYBy(40);
                    i2.animate().translationYBy(40);
                    i3.animate().translationYBy(40);
                    i4.animate().translationYBy(40);
                    p1=false;
                }
                else if(!p1){
                    handles.animate().alpha(0).translationXBy(140);
                    i1.animate().scaleXBy((float) -0.7).scaleYBy((float)- 0.7);
                    i1.animate().translationYBy(-40);
                    i2.animate().translationYBy(-40);
                    i3.animate().translationYBy(-40);
                    i4.animate().translationYBy(-40);
                    p1=true;
                }
            }
        });
        i2=findViewById(R.id.i2);
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p1 && p2 && p3 && p4){
                    handles.setText("yatharthagoenka");
                    handles.animate().alpha((float) 0.5).translationXBy(24);
                    i2.animate().scaleXBy((float) 0.7).scaleYBy((float) 0.7);
                    i1.animate().translationYBy(40);
                    i2.animate().translationYBy(40);
                    i3.animate().translationYBy(40);
                    i4.animate().translationYBy(40);
                    p2=false;
                }
                else if(!p2){
                    handles.animate().alpha(0).translationXBy(-24);
                    i2.animate().scaleXBy((float) -0.7).scaleYBy((float)- 0.7);
                    i1.animate().translationYBy(-40);
                    i2.animate().translationYBy(-40);
                    i3.animate().translationYBy(-40);
                    i4.animate().translationYBy(-40);
                    p2=true;
                }
            }
        });
        i3=findViewById(R.id.i3);
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p1 && p2 && p3 && p4){
                    handles.setText("Username: Yathartha Goenka");
                    handles.animate().alpha((float) 0.5).translationXBy(-80);
                    i3.animate().scaleXBy((float) 0.7).scaleYBy((float) 0.7);
                    i1.animate().translationYBy(40);
                    i2.animate().translationYBy(40);
                    i3.animate().translationYBy(40);
                    i4.animate().translationYBy(40);
                    p3=false;
                }
                else if(!p3){
                    handles.animate().alpha(0).translationXBy(80);
                    i3.animate().scaleXBy((float) -0.7).scaleYBy((float)- 0.7);
                    i1.animate().translationYBy(-40);
                    i2.animate().translationYBy(-40);
                    i3.animate().translationYBy(-40);
                    i4.animate().translationYBy(-40);
                    p3=true;
                }
            }
        });
        i4=findViewById(R.id.i4);
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p1 && p2 && p3 && p4){
                    handles.setText("goenkayathartha2002@gmail.com");
                    handles.animate().alpha((float) 0.5).translationXBy(-120);
                    i4.animate().scaleXBy((float) 0.7).scaleYBy((float) 0.7);
                    i1.animate().translationYBy(40);
                    i2.animate().translationYBy(40);
                    i3.animate().translationYBy(40);
                    i4.animate().translationYBy(40);
                    p4=false;
                }
                else if(!p4){
                    handles.animate().alpha(0).translationXBy(120);
                    i4.animate().scaleXBy((float) -0.7).scaleYBy((float)- 0.7);
                    i1.animate().translationYBy(-40);
                    i2.animate().translationYBy(-40);
                    i3.animate().translationYBy(-40);
                    i4.animate().translationYBy(-40);
                    p4=true;
                }
            }
        });
    }
}