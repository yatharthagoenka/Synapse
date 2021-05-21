package com.example.AppTest.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.AppTest.entities.Note;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NoteDao_Impl implements NoteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Note> __insertionAdapterOfNote;

  private final EntityDeletionOrUpdateAdapter<Note> __deletionAdapterOfNote;

  public NoteDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNote = new EntityInsertionAdapter<Note>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Notes` (`id`,`title`,`datetime`,`subtitle`,`notetext`,`color`,`image`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Note value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDatetime() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDatetime());
        }
        if (value.getSubtitle() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSubtitle());
        }
        if (value.getNotetext() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getNotetext());
        }
        if (value.getColor() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getColor());
        }
        if (value.getImagepath() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getImagepath());
        }
      }
    };
    this.__deletionAdapterOfNote = new EntityDeletionOrUpdateAdapter<Note>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Notes` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Note value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void insertNote(final Note note) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNote.insert(note);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteNote(final Note note) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfNote.handle(note);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Note> getAllNotes() {
    final String _sql = "SELECT * FROM notes ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfDatetime = CursorUtil.getColumnIndexOrThrow(_cursor, "datetime");
      final int _cursorIndexOfSubtitle = CursorUtil.getColumnIndexOrThrow(_cursor, "subtitle");
      final int _cursorIndexOfNotetext = CursorUtil.getColumnIndexOrThrow(_cursor, "notetext");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfImagepath = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
      final List<Note> _result = new ArrayList<Note>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Note _item;
        _item = new Note();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _item.setTitle(_tmpTitle);
        final String _tmpDatetime;
        _tmpDatetime = _cursor.getString(_cursorIndexOfDatetime);
        _item.setDatetime(_tmpDatetime);
        final String _tmpSubtitle;
        _tmpSubtitle = _cursor.getString(_cursorIndexOfSubtitle);
        _item.setSubtitle(_tmpSubtitle);
        final String _tmpNotetext;
        _tmpNotetext = _cursor.getString(_cursorIndexOfNotetext);
        _item.setNotetext(_tmpNotetext);
        final String _tmpColor;
        _tmpColor = _cursor.getString(_cursorIndexOfColor);
        _item.setColor(_tmpColor);
        final String _tmpImagepath;
        _tmpImagepath = _cursor.getString(_cursorIndexOfImagepath);
        _item.setImagepath(_tmpImagepath);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
