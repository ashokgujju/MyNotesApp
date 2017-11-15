package com.ashok.mynotesapp.notes;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.ashok.mynotesapp.R;
import com.ashok.mynotesapp.data.LocalNotesDataSource;
import com.ashok.mynotesapp.data.Note;
import com.ashok.mynotesapp.data.NotesDatabase;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by ashok on 13/11/17.
 */

public class NotesViewModel extends BaseObservable {
    public final ObservableBoolean notesAvailable = new ObservableBoolean();
    public final ObservableField<String> noNotesInfoMsg = new ObservableField<>();

    private DataListener dataListener;
    private NotesNavigator navigator;
    private Disposable disposable;

    private Context context;

    public NotesViewModel(Context context) {
        this.context = context;
    }

    public void loadNotes() {
        NotesDatabase database = NotesDatabase.getInstance(context);
        LocalNotesDataSource dataSource = new LocalNotesDataSource(database.noteDao());
        disposable = dataSource
                .loadNotes()
                .subscribe(notes -> {
                            if (notes.size() > 0) {
                                dataListener.onNotesChanged(notes);
                                notesAvailable.set(true);
                            } else {
                                notesAvailable.set(false);
                                noNotesInfoMsg.set(context.getString(R.string.no_notes));
                            }
                        },
                        throwable -> {
                            notesAvailable.set(false);
                            noNotesInfoMsg.set(throwable.getMessage());
                            throwable.printStackTrace();
                        });
    }

    public void newNote() {
        navigator.addNewNote();
    }

    public void setNavigator(NotesNavigator navigator) {
        this.navigator = navigator;
    }

    public void setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    public void onActivityDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public interface DataListener {
        void onNotesChanged(List<Note> notes);
    }
}