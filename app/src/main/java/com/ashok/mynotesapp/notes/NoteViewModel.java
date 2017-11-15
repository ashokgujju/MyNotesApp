package com.ashok.mynotesapp.notes;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableField;

import com.ashok.mynotesapp.data.Note;

/**
 * Created by ashok on 13/11/17.
 */

public class NoteViewModel extends BaseObservable {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> body = new ObservableField<>();
    public final ObservableField<String> date = new ObservableField<>();
    private final ObservableField<Note> noteObservable = new ObservableField<>();
    public String noteId;

    public NoteViewModel() {
        noteObservable.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                Note note = noteObservable.get();
                if (note != null) {
                    title.set(note.getTitle());
                    body.set(note.getBody() == null ? null : note.getBody().trim());
                    date.set(note.getDate());
                    noteId = note.getId();
                }
            }
        });
    }

    public void setNote(Note note) {
        noteObservable.set(note);
        notifyChange();
    }
}