package com.example.AppTest.adapters;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AppTest.R;
import com.example.AppTest.entities.Note;
import com.example.AppTest.listeners.NotesListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import java.util.logging.LogRecord;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    Timer timer;
    List<Note> notes;
    List<Note> notesSource;
    private NotesListener notesListener;

    public NotesAdapter(List<Note> notes, NotesListener notesListener) {
        this.notes = notes;
        this.notesListener = notesListener;
        notesSource = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.note_container, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.setNote(notes.get(position));
        holder.noteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesListener.onNoteClicked(notes.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView ntitle, nsubtitle, ndt;
        LinearLayout noteLayout;
        RoundedImageView roundedImageView;

        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            ntitle = itemView.findViewById(R.id.noteh1);
            nsubtitle = itemView.findViewById(R.id.noteh2);
            ndt = itemView.findViewById(R.id.noteh3);
            noteLayout = itemView.findViewById(R.id.layoutNote);
            roundedImageView = itemView.findViewById(R.id.noteImageCont);
        }

        void setNote(Note note) {
            ntitle.setText(note.getTitle());
            if (note.getSubtitle().trim().isEmpty()) {
                nsubtitle.setVisibility(View.GONE);
            } else {
                nsubtitle.setText(note.getSubtitle());
            }
            ndt.setText(note.getDatetime());

            GradientDrawable gradientDrawable = (GradientDrawable) noteLayout.getBackground();
            if (note.getColor() != null) {
                gradientDrawable.setColor(Color.parseColor(note.getColor()));
            } else {
                gradientDrawable.setColor(Color.parseColor("#3d3d3d"));
            }

            if (note.getImagepath() != null) {
                roundedImageView.setImageBitmap(BitmapFactory.decodeFile(note.getImagepath()));
                roundedImageView.setVisibility(View.VISIBLE);
            } else {
                roundedImageView.setVisibility(View.GONE);
            }
        }
    }

    public void searchNotes(final String sKey) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (sKey.trim().isEmpty()) {
                    notes = notesSource;
                } else {
                    ArrayList<Note> tmp = new ArrayList<>();
                    for (Note note : notesSource) {
                        if (note.getTitle().toLowerCase().contains(sKey.toLowerCase())
                                || note.getSubtitle().toLowerCase().contains(sKey.toLowerCase())
                                || note.getNotetext().toLowerCase().contains(sKey.toLowerCase())) {
                            tmp.add(note);
                        }
                    }
                    notes=tmp;
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        }, 500);
    }

    public void cancelTimer(){
        if(timer!=null){
            timer.cancel();
        }
    }
}
