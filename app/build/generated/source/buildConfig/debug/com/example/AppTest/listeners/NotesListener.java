package com.example.AppTest.listeners;

import com.example.AppTest.entities.Note;

public interface NotesListener {
    void onNoteClicked(Note note, int position);
}
