package com.ashok.mynotesapp.data;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ashok on 13/11/17.
 */

public class LocalNotesDataSource implements NotesDataSource {

    private final NoteDao noteDao;

    public LocalNotesDataSource(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public Flowable<List<Note>> loadNotes() {
        return noteDao.getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<Note> getNoteById(String noteId) {
        return noteDao.getNoteById(noteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable insertNote(Note note) {
        return Completable.fromAction(() -> noteDao.insertNote(note))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable updateNote(Note note) {
        return Completable.fromAction(() -> noteDao.updateNote(note))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable deleteNoteById(String noteId) {
        return Completable.fromAction(() -> noteDao.deleteNoteById(noteId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}