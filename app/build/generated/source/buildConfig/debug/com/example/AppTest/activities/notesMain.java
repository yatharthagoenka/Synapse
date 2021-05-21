package com.example.AppTest.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.AppTest.R;
import com.example.AppTest.SplashActivity;
import com.example.AppTest.adapters.NotesAdapter;
import com.example.AppTest.cfActivity;
import com.example.AppTest.cowinActivity;
import com.example.AppTest.dashboard;
import com.example.AppTest.database.NotesDatabase;
import com.example.AppTest.entities.Note;
import com.example.AppTest.listeners.NotesListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class notesMain extends AppCompatActivity implements NotesListener {
    public static final int REQUEST_CODE_ADD_NOTE=1;  //request to add note
    public static final int REQUEST_CODE_UPDATE_NOTE=2;  //request to update note
    public static final int REQUEST_CODE_SHOW_NOTES=3;
    private RecyclerView notesRecyclerView;
    private List<Note> noteDisp;
    private NotesAdapter notesAdapter;

    private int noteClickedPos=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_notes);

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_dash){
                    Intent intent=new Intent(getApplicationContext(), dashboard.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.nav_notes){
                    Intent intent=new Intent(getApplicationContext(),notesMain.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.nav_home){
                    Intent intent=new Intent(getApplicationContext(), SplashActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.nav_gallery){
                    Intent intent=new Intent(getApplicationContext(), cowinActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.nav_slideshow){
                    Intent intent=new Intent(getApplicationContext(), cfActivity.class);
                    startActivity(intent);
                }
                DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        FloatingActionButton fab = findViewById(R.id.newnote);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(getApplicationContext(),createNote.class),
                        REQUEST_CODE_ADD_NOTE
                );
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        EditText search=findViewById(R.id.searchbar);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                notesAdapter.cancelTimer();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(noteDisp.size()!=0){
                    notesAdapter.searchNotes(s.toString());
                }
            }
        });

        notesRecyclerView=findViewById(R.id.notesRecyclerView);
        notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        noteDisp=new ArrayList<Note>();
        notesAdapter=new NotesAdapter(noteDisp,this);
        notesRecyclerView.setAdapter(notesAdapter);
        getNotes(REQUEST_CODE_SHOW_NOTES,false); //passign req code for showing all notes on OnCreate
    }

    @Override
    public void onNoteClicked(Note note, int position) {
        noteClickedPos=position;
        Intent intent=new Intent(getApplicationContext(),createNote.class);
        intent.putExtra("viewUpdateNote",true);
        intent.putExtra("note",note);
        startActivityForResult(intent,REQUEST_CODE_UPDATE_NOTE);
    }

    private void getNotes(final int requestCode,final boolean noteDeleted){
        @SuppressLint("StaticFieldLeak")
        class GetNotesTask extends AsyncTask<Void,Void, List<Note>>{    //to get data from DB. Therefore output is List<note>

            @Override
            protected List<Note> doInBackground(Void... voids) {
                return NotesDatabase.getDatabase(getApplicationContext()).noteDao().getAllNotes();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
                if(requestCode==REQUEST_CODE_SHOW_NOTES){
                    noteDisp.addAll(notes);
                    notesAdapter.notifyDataSetChanged();
                }else if(requestCode==REQUEST_CODE_ADD_NOTE){
                    noteDisp.add(0,notes.get(0));
                    notesAdapter.notifyItemInserted(0);
                    notesRecyclerView.smoothScrollToPosition(0);
                }else if(requestCode==REQUEST_CODE_UPDATE_NOTE){ //removing existing note from certain pos and adding new one to the same pos
                    noteDisp.remove(noteClickedPos);
                    if(noteDeleted){
                        notesAdapter.notifyItemRemoved(noteClickedPos);
                        Toast.makeText(notesMain.this, "Note Deleted", Toast.LENGTH_SHORT).show();
                    }else{
                        noteDisp.add(noteClickedPos,notes.get(noteClickedPos));
                        notesAdapter.notifyItemChanged(noteClickedPos);
                    }
                }
            }
        }
        new GetNotesTask().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_ADD_NOTE && resultCode==RESULT_OK){
            getNotes(REQUEST_CODE_ADD_NOTE,false); //since new note is added, again show all notes requested
        }else if(requestCode==REQUEST_CODE_UPDATE_NOTE && resultCode==RESULT_OK){
            if(data!=null){
                getNotes(REQUEST_CODE_UPDATE_NOTE,data.getBooleanExtra("isNoteDeleted",false));
            }
        }
    }


}