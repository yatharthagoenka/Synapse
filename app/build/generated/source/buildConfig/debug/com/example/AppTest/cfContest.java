 package com.example.AppTest;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

 public class cfContest extends Fragment {
    TextView tmp;
    FloatingActionButton cfb2;
//    ArrayList<String> coname,coid,stime;
     public String[][] data;
    ListView names;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contest_layout,container,false);
    }

    public class Downloadlist extends AsyncTask<String,Void,String>{

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
                        if(result.length()>=9){
                            if(result.substring(result.length()-9,result.length()-1).equals("FINISHED")) {
                                result+="}]}";
                                break;
                            }
                        }
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
//                Log.i("User Info",uinfo);
//                Log.i("test2", String.valueOf(arr.length()));
                JSONArray arr=new JSONArray(uinfo);
                data=new String[arr.length()-1][arr.length()-1];
//                HashMap<String,String> contests=new HashMap<>();
                for(int i=0;i<arr.length()-1;i++){
                    JSONObject part=arr.getJSONObject(i);
//                    coname.add(part.getString("name"));
//                    coid.add(part.getString("id"));
//                    contests.put(part.getString("id"),part.getString("name"));
                    int days=(abs(Integer.parseInt( part.getString("relativeTimeSeconds"))))/86400;
                    int hours=((abs(Integer.parseInt( part.getString("relativeTimeSeconds"))))-(86400*days))/3600;
                    data[i]= new String[]{part.getString("id"), part.getString("name"),days+" Days "+hours+" Hours"};
                }
//                Log.i("test data",data[3][2]);
                List<HashMap<String, String>> listItems = new ArrayList<>();
                SimpleAdapter adapter = new SimpleAdapter(getActivity(), listItems, R.layout.contestlist_items,new String[]{"First Line", "Second Line","Third Line"},new int[]{R.id.cid, R.id.cname,R.id.stime});
//                Iterator it = contests.entrySet().iterator();
//                while (it.hasNext()){
//                    HashMap<String, String> resultsMap = new HashMap<>();
//                    Map.Entry pair = (Map.Entry)it.next();
//                    resultsMap.put("First Line", pair.getKey().toString());
//                    resultsMap.put("Second Line", pair.getValue().toString());
//                    listItems.add(resultsMap);
//                }
                HashMap<String,String> item;
                for(int i=0;i<data.length;i++){
                    item = new HashMap<String,String>();
                    item.put( "First Line", data[i][0]);
                    item.put( "Second Line", data[i][1]);
                    item.put( "Third Line", data[i][2]);
                    listItems.add( item );
                }
                names.setAdapter(adapter);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tmp=(TextView)getView().findViewById(R.id.textView10);
        tmp.setText("");
        names=getView().findViewById(R.id.myList);
//        coname=new ArrayList<String>();coid=new ArrayList<String>();
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,coname);
//        names.setAdapter(adapter);
        Downloadlist task=new Downloadlist();
        cfb2=(FloatingActionButton)getView().findViewById(R.id.cfb2);
        cfb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.execute("https://codeforces.com/api/contest.list?gym=false");
            }
        });

    }
}

