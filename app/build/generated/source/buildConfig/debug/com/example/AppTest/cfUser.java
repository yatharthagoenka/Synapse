package com.example.AppTest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class cfUser extends Fragment {
    TextView rate,rank,lrat,uname,org,reg,mxrate,labelconti,conti;
    ImageView dp;
    EditText handle;
    String tmp;
    boolean f=true;
    FloatingActionButton search;

    public class ImgDownload extends AsyncTask<String,Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... urls) {
            try{
                URL url=new URL(urls[0]);
                HttpURLConnection httpURLConnection1=(HttpURLConnection)url.openConnection();
                httpURLConnection1.connect();
                InputStream in=httpURLConnection1.getInputStream();
                Bitmap myimg= BitmapFactory.decodeStream(in);
                return myimg;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }


    public class Download extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... urls) {
            String result="";
            URL url;
            HttpURLConnection httpURLConnection;
            try {
                url=new URL(urls[0]);
                httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream in=httpURLConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                int data=reader.read();
                while(data!=-1){
                    char curr=(char)data;
                    result+=curr;
                    data=reader.read();
                }
                return result;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                JSONObject obj=new JSONObject(s);
                String uinfo=obj.getString("result");
                Log.i("User Info",uinfo);
                JSONArray arr=new JSONArray(uinfo);
                Log.i("test", String.valueOf(arr.length()));
                for(int i=0;i<arr.length();i++){
                    JSONObject part=arr.getJSONObject(i);
                    uname.setText(part.getString("handle"));
                    conti.setText(part.getString("contribution"));
                    rank.setText(part.getString("rank"));
                    rate.setText(part.getString("rating"));
                    try{
                        reg.setText(part.getString("city")+", "+part.getString("country"));
                        org.setText(part.getString("organization"));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    tmp=part.getString("titlePhoto");
                    mxrate.setText("(max rating: "+part.getString("maxRating")+")");
                }
                switch(rank.getText().toString()){
                    case "newbie":
                        uname.setTextColor(Color.parseColor("#bdbdbd"));
                        rank.setTextColor(Color.parseColor("#bdbdbd"));
                        break;
                    case "pupil":
                        uname.setTextColor(Color.parseColor("#88cc22"));
                        rank.setTextColor(Color.parseColor("#88cc22"));
                        break;
                    case "apprentice":
                        uname.setTextColor(Color.parseColor("#008000"));
                        rank.setTextColor(Color.parseColor("#008000"));
                        break;
                    case "specialist":
                        uname.setTextColor(Color.parseColor("#03a89e"));
                        rank.setTextColor(Color.parseColor("#03a89e"));
                        break;
                    case "expert":
                        uname.setTextColor(Color.parseColor("#0000ff"));
                        rank.setTextColor(Color.parseColor("#0000ff"));
                        break;
                    case "candidate master":
                        uname.setTextColor(Color.parseColor("#aa00aa"));
                        rank.setTextColor(Color.parseColor("#aa00aa"));
                        break;
                    case "master":
                        uname.setTextColor(Color.parseColor("#ff0000"));
                        rank.setTextColor(Color.parseColor("#ff0000"));
                        break;
                    case "international master":
                        uname.setTextColor(Color.parseColor("#ff0000"));
                        rank.setTextColor(Color.parseColor("#ff0000"));
                        break;
                    case "grandmaster":
                        uname.setTextColor(Color.parseColor("#ff0000"));
                        rank.setTextColor(Color.parseColor("#ff0000"));
                        break;
                    case "international grandmaster":
                        uname.setTextColor(Color.parseColor("#ff0000"));
                        rank.setTextColor(Color.parseColor("#ff0000"));
                        break;
                    case "legendary grandmaster":
                        uname.setTextColor(Color.parseColor("#ff0000"));
                        rank.setTextColor(Color.parseColor("#ff0000"));
                        break;
                }
                ImgDownload task2=new ImgDownload();
                Bitmap userimg;
                try {
                    userimg=task2.execute(tmp).get();
                    dp.setImageBitmap(userimg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handle=(EditText)getView().findViewById(R.id.handle);
        uname=(TextView)getView().findViewById(R.id.username);
        rate=(TextView)getView().findViewById(R.id.rating);
        mxrate=(TextView)getView().findViewById(R.id.mxrate);
        rank=(TextView)getView().findViewById(R.id.rank);
        reg=(TextView)getView().findViewById(R.id.reg);
        labelconti=getView().findViewById(R.id.labelconti);
        conti=(TextView)getView().findViewById(R.id.conti);
        org=(TextView)getView().findViewById(R.id.org);
        lrat=(TextView)getView().findViewById(R.id.labelrate);
        dp=(ImageView)getView().findViewById(R.id.imageView);
        search=getView().findViewById(R.id.cfb);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Download task=new Download();
                task.execute(("https://codeforces.com/api/user.info?handles="+handle.getText().toString()));
                if(f){
                    search.animate().translationYBy(-80).setDuration(1000);
                    handle.animate().alpha(0).setDuration(1000);
                    f=false;
                    lrat.animate().alpha(1).setDuration(2000);
                    dp.animate().alpha(1).setDuration(2000);
                    rank.animate().alpha(1).setDuration(2000);
                    reg.animate().alpha(1).setDuration(2000);
                    uname.animate().alpha(1).setDuration(2000);
                    org.animate().alpha(1).setDuration(2000);
                    rate.animate().alpha(1).setDuration(2000);
                    labelconti.animate().alpha(1).setDuration(2000);
                    conti.animate().alpha(1).setDuration(2000);
                    mxrate.animate().alpha(1).setDuration(2000);
                    uname.setText("");
                    org.setText("");
                    conti.setText("");
                    rank.setText("");
                    rate.setText("");
                    reg.setText("");
                    tmp="";
                    mxrate.setText("");
                }
                else{
                    search.animate().translationYBy(80).setDuration(1000);
                    handle.animate().alpha(1).setDuration(1000);
                    f=true;
                    lrat.animate().alpha(0).setDuration(1000);
                    dp.animate().alpha(0).setDuration(1000);
                    rank.animate().alpha(0).setDuration(1000);
                    uname.animate().alpha(0).setDuration(1000);
                    mxrate.animate().alpha(0).setDuration(1000);
                    reg.animate().alpha(0).setDuration(1000);
                    labelconti.animate().alpha(0).setDuration(1000);
                    conti.animate().alpha(0).setDuration(1000);
                    org.animate().alpha(0).setDuration(1000);
                    rate.animate().alpha(0).setDuration(1000);
                }

            }
        });
    }
}
