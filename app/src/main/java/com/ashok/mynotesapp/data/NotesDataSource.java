package com.ashok.mynotesapp.data;

import com.ashok.mynotesapp.data.Note;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by ashok on 13/11/17.
 */

public interface NotesDataSource {

    Flowable<List<Note>> loadNotes();

    Single<Note> getNoteById(String noteId);

    Completable insertNote(Note note);

    Completable updateNote(Note note);

    Completable deleteNoteById(String noteId);
}
