package com.ashok.mynotesapp.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ashok.mynotesapp.data.Note;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by ashok on 13/11/17.
 */

@Dao
public interface NoteDao {
    @Query("SELECT * FROM Notes")
    Flowable<List<Note>> getNotes();

    @Query("SELECT * FROM Notes WHERE noteId = :noteId")
    Single<Note> getNoteById(String noteId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Update
    int updateNote(Note note);

    @Query("DELETE FROM Notes WHERE noteId = :noteId")
    int deleteNoteById(String noteId);
}
