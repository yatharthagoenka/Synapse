package com.example.AppTest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.AppTest.activities.notesMain;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class cowinActivity extends AppCompatActivity implements CowinDialog.DialogListener{

    public TextView district;
    public String spin,sdate;
    ArrayList<String> cid,cname;
    public ListView list;
    public String[][] data;
    public Downloadlist task;

    public class Downloadlist extends AsyncTask<String,Void,String> {

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
                String uinfo=obj.getString("centers");
                Log.i("User Info",uinfo);
                JSONArray arr=new JSONArray(uinfo);
                Log.i("test1", String.valueOf(arr.length()));
                data=new String[arr.length()-1][arr.length()-1];
                for(int i=0;i<arr.length()-1;i++){
                    JSONObject part=arr.getJSONObject(i);
                    String sess=part.getString("sessions");
                    JSONArray sesarr=new JSONArray(sess);
                    String ctid=part.getString("center_id");
                    String ctname=part.getString("name");
                    String pc=part.getString("fee_type");
                    if(!pc.equals("Free")){pc="Rs "+pc;}
                    String vac="",av="";
                    for(int j=0;j<sesarr.length();j++){
                        JSONObject part2=sesarr.getJSONObject(j);
                        av=part2.getString("available_capacity");
                        vac=part2.getString("vaccine");
                    }
                    data[i]=new String[]{ctid,ctname,vac,"Availability: "+av,pc};
                }

                int c=data.length;
                Log.i("test",String.valueOf(data.length));
                List<HashMap<String, String>> listItems = new ArrayList<>();
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), listItems, R.layout.cowinlist_items,
                        new String[]{"line1", "line2","line3","line4","line5"},
                        new int[]{R.id.centreid, R.id.centrename,R.id.vacc,R.id.avv,R.id.price});
                HashMap<String,String> item;
                for(int i=0;i<c;i++){
                    item = new HashMap<String,String>();
                    item.put( "line1", data[i][0]);
                    item.put( "line2", data[i][1]);
                    item.put( "line3", data[i][2]);
                    item.put( "line4", data[i][3]);
                    item.put( "line5", data[i][4]);
                    listItems.add( item );
                }
                list.setAdapter(adapter);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_cowin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

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
        list=findViewById(R.id.cowinlist);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.,cid);
//        names.setAdapter(adapter);

        task=new Downloadlist();
//        task.execute(("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=201005&date=19-05-2021"));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoDialog();

            }
        });

//        district=findViewById(R.id.textView9);
    }


    public void infoDialog(){
        CowinDialog cowinDialog=new CowinDialog();
        cowinDialog.show(getSupportFragmentManager(),"cowin info dialog");
    }

    @Override
    public void sharetext2(String id,String date) {
        spin=id;
        sdate=date;
        task.execute(("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode="+spin+"&date="+sdate));
    }
}