package com.ashok.mynotesapp.notes;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ashok.mynotesapp.R;
import com.ashok.mynotesapp.addeditnote.AddEditNoteActivity;
import com.ashok.mynotesapp.data.Note;
import com.ashok.mynotesapp.databinding.ActivityNotesBinding;

import java.util.List;

public class NotesActivity extends AppCompatActivity
        implements NotesNavigator, NoteItemNavigator, NotesViewModel.DataListener {

    private ActivityNotesBinding binding;
    private NotesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notes);

        setUpActionBar();
        setupRecyclerView(binding.notesList);

        viewModel = new NotesViewModel(this);
        viewModel.setDataListener(this);
        viewModel.setNavigator(this);

        binding.setViewModel(viewModel);
        viewModel.loadNotes();
    }

    private void setUpActionBar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(null);
    }

    private void setupRecyclerView(RecyclerView notesList) {
        notesList.setAdapter(new NotesAdapter(this));
        notesList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && binding.fab.getVisibility() == View.VISIBLE) {
                    binding.fab.hide();
                } else if (dy < 0 && binding.fab.getVisibility() != View.VISIBLE) {
                    binding.fab.show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onActivityDestroy();
    }

    @Override
    public void onNotesChanged(List<Note> notes) {
        NotesAdapter adapter = (NotesAdapter) binding.notesList.getAdapter();
        adapter.setNotes(notes);
    }

    @Override
    public void addNewNote() {
        Intent intent = new Intent(this, AddEditNoteActivity.class);
        startActivity(intent);
    }

    @Override
    public void openNote(String noteId) {
        Intent intent = new Intent(this, AddEditNoteActivity.class);
        intent.putExtra(AddEditNoteActivity.NOTE_ID, noteId);
        startActivity(intent);
    }
}