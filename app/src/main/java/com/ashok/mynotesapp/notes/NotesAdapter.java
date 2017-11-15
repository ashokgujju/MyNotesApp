package com.ashok.mynotesapp.notes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ashok.mynotesapp.data.Note;
import com.ashok.mynotesapp.databinding.ItemNotesBinding;

import java.util.List;

/**
 * Created by ashok on 13/11/17.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    private List<Note> notes;
    private NoteItemNavigator navigator;

    public NotesAdapter(NoteItemNavigator navigator) {
        this.navigator = navigator;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemNotesBinding itemNotesBinding = ItemNotesBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(itemNotesBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(notes.get(position), navigator);
    }

    @Override
    public int getItemCount() {
        return notes == null ? 0 : notes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemNotesBinding binding;

        public MyViewHolder(ItemNotesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Note note, NoteItemNavigator navigator) {
            if (binding.getViewModel() == null) {
                NoteItemViewModel viewModel = new NoteItemViewModel();
                viewModel.setNavigator(navigator);
                viewModel.setNote(note);
                binding.setViewModel(viewModel);
            } else {
                binding.getViewModel().setNote(note);
            }
        }
    }
}