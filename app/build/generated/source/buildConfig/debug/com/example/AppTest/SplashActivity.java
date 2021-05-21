package com.example.AppTest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.AppTest.activities.notesMain;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;

import me.ibrahimsn.particle.ParticleView;

public class SplashActivity extends AppCompatActivity implements MailDialog.DialogShare {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    ImageView mic;
    EditText nameVal;
    boolean pass=true;
    String s;
    public ParticleView particleView;
    ArrayList<String> logs=new ArrayList<String>();
    TextView test2;
    String tmp;
    int color1=0;
    View sheetView;
    TextToSpeech t1;
    FloatingActionButton b1,cf,cw;
    private BottomSheetDialog bsheet;
    public void click(View view){
        Log.i("Info","Button Clicked");
//        Toast.makeText(this, "Hey "+ nameVal.getText().toString(), Toast.LENGTH_SHORT).show();
    }
    public void change(View view){
//        Toast.makeText(this,logs.get(0), Toast.LENGTH_SHORT).show();
        EditText tmp=(EditText)findViewById(R.id.nameEditText);
        tmp.setHint("Enter Command");
        pass=false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_main);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        particleView=findViewById(R.id.particleView);
        particleView.resume();

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_dash){
                    Intent intent=new Intent(getApplicationContext(),dashboard.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.nav_notes){
                    Intent intent=new Intent(getApplicationContext(), notesMain.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.nav_home){
                    Intent intent=new Intent(getApplicationContext(),SplashActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.nav_gallery){
                    Intent intent=new Intent(getApplicationContext(),cowinActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.nav_slideshow){
                    Intent intent=new Intent(getApplicationContext(),cfActivity.class);
                    startActivity(intent);
                }
                DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        logs.add("Sys: Enter Password.");
        Set<String> a=new HashSet<>();
        a.add("male");
        Voice voiceobj = new Voice("en-us-x-sfg#male_2-local",new Locale("en","US"),400,200,true,a);
        t1.setVoice(voiceobj);
        t1.setPitch((float) 0.8);
        t1.setSpeechRate((float) 0.95);
        b1=findViewById(R.id.fab2);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                t1.speak("Interaction Logs",TextToSpeech.QUEUE_FLUSH,null);
                bsheet=new BottomSheetDialog(SplashActivity.this,R.style.bottomSheetTheme);
                sheetView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bsheet_layout,(ViewGroup)findViewById(R.id.b_sheet));
//                sheetView.findViewById(R.id.)
                test2=(TextView)sheetView.findViewById(R.id.textView);
                for(int i=0;i<logs.size();i++){
                    test2.setText(test2.getText()+"\n\n"+logs.get(i));
                }
                bsheet.setContentView(sheetView);
                bsheet.show();
//                bsheet sheet= new bsheet();
//                sheet.show(getSupportFragmentManager(),sheet.getTag());
            }

        });
        cf=findViewById(R.id.cfb);
        cf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, cfActivity.class);
                startActivity(intent);
            }
        });
        mic=(ImageView)findViewById(R.id.fab3);
        nameVal=(EditText)findViewById(R.id.nameEditText);
//        nameVal.setVisibility(View.INVISIBLE);
        mic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");
                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                }
                catch (Exception e) {
                    Toast.makeText(SplashActivity.this, " " + e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                nameVal.setText(Objects.requireNonNull(result).get(0));
                s=nameVal.getText().toString();
//                tmp+=s;
                logs.add(("User: "+s));
//                if(s.equals("hello world") && pass){
//                    pass=false;
//                    nameVal.setText("");
//                    nameVal.setHint("Enter Command");
////                    Toast.makeText(this, "SANE Activated", Toast.LENGTH_SHORT).show();
//                    logs.add("Sys: SANE Activated. Enter Command.");
//                    t1.speak("Sane Activated. Enter Command",TextToSpeech.QUEUE_FLUSH,null);
//                    nameVal.setVisibility(View.VISIBLE);
//                    Animation animation1 = new AlphaAnimation(0.0f, 1.0f);
//                    animation1.setDuration(400);
//                    nameVal.startAnimation(animation1);
//                }
//                else if(pass){
//                    nameVal.setText("");
//                    nameVal.setHint("Enter Password");
//                    logs.add("Sys: Incorrect Password, Try Again.");
////                    Toast.makeText(this, "Incorrect Password, Try Again.", Toast.LENGTH_SHORT).show();
//                    t1.speak("Incorrect Password. Try again.",TextToSpeech.QUEUE_FLUSH,null);
//                }
                nameVal.setText("");
                if(s.contains("time")) {
                    Date currentTime = Calendar.getInstance().getTime();
                    DateFormat date = new SimpleDateFormat("HH:mm a");
                    date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
                    logs.add("Sys: "+date.format(currentTime).toString());
                    t1.speak(date.format(currentTime).toString(), TextToSpeech.QUEUE_FLUSH, null);
                    Toast.makeText(this, date.format(currentTime).toString(), Toast.LENGTH_SHORT).show();
                }
                if(s.contains("mail")){
                    mailDialog();
                    t1.speak("Sure. Draft your Mail.",TextToSpeech.QUEUE_FLUSH,null);
                }
                if(s.contains("user")){
//                    cfDialog();
//                    t1.speak("Sure. Draft your Mail.",TextToSpeech.QUEUE_FLUSH,null);
                    Intent intent = new Intent(SplashActivity.this, cfActivity.class);
                    startActivity(intent);
                }
                if(s.contains("logs") || s.contains("log") || s.contains("history")){
                    logs.add("Sys: Expanding Command Logs");
                    t1.speak("Expanding Command Logs", TextToSpeech.QUEUE_FLUSH, null);
                    bsheet=new BottomSheetDialog(SplashActivity.this,R.style.bottomSheetTheme);
                    sheetView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bsheet_layout,(ViewGroup)findViewById(R.id.b_sheet));
                    test2=(TextView)sheetView.findViewById(R.id.textView);
                    for(int i=0;i<logs.size();i++){
                        test2.setText(test2.getText()+"\n\n"+logs.get(i));
                    }
                    bsheet.setContentView(sheetView);
                    bsheet.show();
                }
            }
        }
    }

    public void mailDialog(){
        MailDialog mailDialog=new MailDialog();
        mailDialog.show(getSupportFragmentManager(),"Mail Dialog");
    }


    @Override
    public void sharetext(String id) {
        Toast.makeText(this, "Sending mail to "+id, Toast.LENGTH_SHORT).show();
    }
}
