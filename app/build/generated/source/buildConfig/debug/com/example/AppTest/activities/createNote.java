package com.example.AppTest.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.AppTest.R;
import com.example.AppTest.database.NotesDatabase;
import com.example.AppTest.entities.Note;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class createNote extends AppCompatActivity {
    public ImageView back;
    EditText title,subtitle,text;
    TextView datetime;
    String noteColor,imgPath;
    View subtInd;
    ImageView imgNote;
    AlertDialog delDialogvar;
    public static final int REQUEST_CODE_STORAGE_PERMISSION=1;
    public static final int REQUEST_CODE_SELECT_IMAGE=2;

    private Note createdNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        back=(ImageView)findViewById(R.id.backb);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(createNote.this, notesMain.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_right, R.anim.right_left);
            }
        });

        noteColor="#3d3d3d";
        imgPath="";
        subtInd=findViewById(R.id.indicator);
        title=findViewById(R.id.title);
        subtitle=findViewById(R.id.subtitle);
        text=findViewById(R.id.notetext);
        imgNote=findViewById(R.id.imgNote);
        datetime=findViewById(R.id.dandt);
        datetime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                .format(new Date())
        );

        ImageView save=(ImageView)findViewById(R.id.saveb);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        if(getIntent().getBooleanExtra("viewUpdateNote",false)){
            createdNote=(Note)getIntent().getSerializableExtra("note");
            viewUpdate();
        }

        initMenu();
        setSubtColor();
    }

    private void viewUpdate(){
        title.setText(createdNote.getTitle());
        subtitle.setText(createdNote.getSubtitle());
        datetime.setText(createdNote.getDatetime());
        text.setText(createdNote.getNotetext());

        if(createdNote.getImagepath()!=null && !createdNote.getImagepath().trim().isEmpty()){
            imgNote.setImageBitmap(BitmapFactory.decodeFile(createdNote.getImagepath()));
            imgNote.setVisibility(View.VISIBLE);
            imgPath=createdNote.getImagepath();
        }
    }

    private void saveNote(){
        if(title.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Title can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }else if(subtitle.getText().toString().trim().isEmpty() && text.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Note can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        final Note note = new Note();
        note.setTitle(title.getText().toString());
        note.setSubtitle(subtitle.getText().toString());
        note.setNotetext(text.getText().toString());
        note.setDatetime(datetime.getText().toString());
        note.setColor(noteColor);
        note.setImagepath(imgPath);

        if(createdNote!=null){
            note.setId(createdNote.getId());  //setting ID of new note to existing note's ID in case of edit since we have set conflict strategy to REPLACE. So the new one would replace the old one if we are editing a note.
        }

        @SuppressLint("StaticFieldLeak")
        class SaveNoteTask extends AsyncTask<Void,Void,Void>{ //to save the note to the database.
            @Override
            protected Void doInBackground(Void... voids) {
                NotesDatabase.getDatabase(getApplicationContext()).noteDao().insertNote(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent=new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        }
        new SaveNoteTask().execute();
    }

    private void initMenu(){
        final LinearLayout notesBsheet=findViewById(R.id.notesBsheet);
        final BottomSheetBehavior bottomSheetBehavior=BottomSheetBehavior.from(notesBsheet);
        notesBsheet.findViewById(R.id.toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottomSheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else{
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        final ImageView img1=notesBsheet.findViewById(R.id.imgc1);
        final ImageView img2=notesBsheet.findViewById(R.id.imgc2);
        final ImageView img3=notesBsheet.findViewById(R.id.imgc3);
        final ImageView img4=notesBsheet.findViewById(R.id.imgc4);
        final ImageView img5=notesBsheet.findViewById(R.id.imgc5);
        //on click listener for every color
        notesBsheet.findViewById(R.id.vc1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteColor="#3d3d3d";
                img1.setImageResource(R.drawable.ic_baseline_check_24);
                img2.setImageResource(0);
                img3.setImageResource(0);
                img4.setImageResource(0);
                img5.setImageResource(0);
                setSubtColor();
            }
        });
        notesBsheet.findViewById(R.id.vc2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteColor="#ffd83d";
                img2.setImageResource(R.drawable.ic_baseline_check_24);
                img1.setImageResource(0);
                img3.setImageResource(0);
                img4.setImageResource(0);
                img5.setImageResource(0);
                setSubtColor();
            }
        });
        notesBsheet.findViewById(R.id.vc3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteColor="#bd4040";
                img3.setImageResource(R.drawable.ic_baseline_check_24);
                img2.setImageResource(0);
                img1.setImageResource(0);
                img4.setImageResource(0);
                img5.setImageResource(0);
                setSubtColor();
            }
        });
        notesBsheet.findViewById(R.id.vc4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteColor="#596aa8";
                img4.setImageResource(R.drawable.ic_baseline_check_24);
                img2.setImageResource(0);
                img3.setImageResource(0);
                img1.setImageResource(0);
                img5.setImageResource(0);
                setSubtColor();
            }
        });
        notesBsheet.findViewById(R.id.vc5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteColor="#f0f0f0";
                img5.setImageResource(R.drawable.ic_baseline_check_24);
                img2.setImageResource(0);
                img3.setImageResource(0);
                img4.setImageResource(0);
                img1.setImageResource(0);
                setSubtColor();
            }
        });

        if(createdNote!=null && createdNote.getColor()!=null && !createdNote.getColor().trim().isEmpty()){
            switch (createdNote.getColor()){
                case "#ffd83d":
                    notesBsheet.findViewById(R.id.vc2).performClick();
                case "#bd4040":
                    notesBsheet.findViewById(R.id.vc3).performClick();
                case "#596aa8":
                    notesBsheet.findViewById(R.id.vc4).performClick();
                case "#f0f0f0":
                    notesBsheet.findViewById(R.id.vc5).performClick();
            }
        }

        notesBsheet.findViewById(R.id.addImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(createNote.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_STORAGE_PERMISSION);
                }else{
                    selectImage();
                }
            }
        });

        if(createdNote!=null){
            notesBsheet.findViewById(R.id.deleteNote).setVisibility(View.VISIBLE);
            notesBsheet.findViewById(R.id.deleteNote).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    showDelDialog();
                }
            });
        }
    }

    private void showDelDialog(){
        if(delDialogvar==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(createNote.this);
            View view= LayoutInflater.from(this).inflate(R.layout.note_del,(ViewGroup)findViewById(R.id.delDialog));
            builder.setView(view);
            delDialogvar=builder.create();
            if(delDialogvar.getWindow()!=null){
                delDialogvar.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            view.findViewById(R.id.delbutton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    @SuppressLint("StaticFieldLeak")
                    class DeleteNoteTask extends AsyncTask<Void,Void,Void>{
                        @Override
                        protected Void doInBackground(Void... voids) {
                            NotesDatabase.getDatabase(getApplicationContext()).noteDao().deleteNote(createdNote);
                            return null;
                        }
                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            Intent intent=new Intent();
                            intent.putExtra("isNoteDeleted",true);  //name is for referrecing in other activity
                            setResult(RESULT_OK,intent);
                            finish();

                        }
                    }
                    new DeleteNoteTask().execute();
                }
            });
            view.findViewById(R.id.cancelbutton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delDialogvar.dismiss();
                }
            });
        }
        delDialogvar.show();
    }

    private void setSubtColor(){
        GradientDrawable gradientDrawable=(GradientDrawable)subtInd.getBackground();
        gradientDrawable.setColor(Color.parseColor(noteColor));
    }

    private void selectImage(){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,REQUEST_CODE_SELECT_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE_STORAGE_PERMISSION && grantResults.length>0){
            selectImage();
        }else{
            Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_SELECT_IMAGE && resultCode==RESULT_OK){
            Uri selectedImgUri=data.getData();
            if(selectedImgUri!=null){
                try {
                    InputStream inputStream=getContentResolver().openInputStream(selectedImgUri);
                    Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                    imgNote.setImageBitmap(bitmap);
                    imgNote.setVisibility(View.VISIBLE);

                    imgPath=getPathUri(selectedImgUri);  //get img path in device to store in DB
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private String getPathUri(Uri contentUri){
        String filePath;
        Cursor cursor=getContentResolver().query(contentUri,null,null,null,null);
        if(cursor==null){
            filePath=contentUri.getPath();
        }else{
            cursor.moveToFirst();
            int index=cursor.getColumnIndex("_data");
            filePath=cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }
}