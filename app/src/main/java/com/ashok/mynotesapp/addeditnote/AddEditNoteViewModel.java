package com.ashok.mynotesapp.addeditnote;

import android.content.Context;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.ashok.mynotesapp.data.LocalNotesDataSource;
import com.ashok.mynotesapp.data.Note;
import com.ashok.mynotesapp.data.NotesDatabase;
import com.ashok.mynotesapp.util.Util;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ashok on 13/11/17.
 */

public class AddEditNoteViewModel {
    public final ObservableField<String> body = new ObservableField<>();
    public final ObservableBoolean enableSaveBtn = new ObservableBoolean(false);
    public boolean isNewTask = true;
    private AddEditNoteNavigator navigator;
    private LocalNotesDataSource dataSource;
    private String noteId = null;

    private CompositeDisposable compositeDisposable;

    public AddEditNoteViewModel(Context context) {
        compositeDisposable = new CompositeDisposable();

        NotesDatabase notesDatabase = NotesDatabase.getInstance(context);
        dataSource = new LocalNotesDataSource(notesDatabase.noteDao());

        body.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                enableSaveBtn.set(body.get().length() > 0);
            }
        });
    }

    public void saveNote() {
        String noteStr = body.get().trim();
        String title = noteStr.split("\n")[0]; //first line is title
        String body = noteStr.substring(title.length());

        if (noteId == null) {
            Note note = new Note(title, body, Util.getDate());
            saveNote(note);
        } else {
            Note note = new Note(noteId, title, body, Util.getDate());
            updateNote(note);
        }
    }

    private void saveNote(Note note) {
        compositeDisposable.add(dataSource.insertNote(note)
                .subscribe(() -> navigator.onNoteSaved(), Throwable::printStackTrace));
    }

    private void updateNote(Note note) {
        compositeDisposable.add(dataSource.updateNote(note)
                .subscribe(() -> navigator.onNoteSaved(), Throwable::printStackTrace));
    }

    public void setNavigator(AddEditNoteNavigator navigator) {
        this.navigator = navigator;
    }

    public void setNoteId(String noteId) {
        isNewTask = false;
        this.noteId = noteId;

        compositeDisposable.add(dataSource.getNoteById(noteId)
                .subscribe(note -> body.set(String.format("%s%s", note.getTitle(),
                        note.getBody())), Throwable::printStackTrace));
    }

    public void onActivityDestroy() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}