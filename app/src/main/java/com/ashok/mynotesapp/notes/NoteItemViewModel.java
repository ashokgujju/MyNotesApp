package com.ashok.mynotesapp.notes;

/**
 * Created by ashok on 13/11/17.
 */

public class NoteItemViewModel extends NoteViewModel {
    private NoteItemNavigator navigator;

    public NoteItemViewModel() {}

    public void setNavigator(NoteItemNavigator navigator) {
        this.navigator = navigator;
    }

    public void openNote(String noteId) {
        navigator.openNote(noteId);
    }
}
