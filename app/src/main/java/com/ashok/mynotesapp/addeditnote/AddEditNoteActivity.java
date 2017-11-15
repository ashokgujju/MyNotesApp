package com.ashok.mynotesapp.addeditnote;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.ashok.mynotesapp.R;
import com.ashok.mynotesapp.databinding.ActivityAddEditNoteBinding;

public class AddEditNoteActivity extends AppCompatActivity implements AddEditNoteNavigator {

    public static final String NOTE_ID = "noteId";
    private AddEditNoteViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAddEditNoteBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_add_edit_note);
        setupActionBar(binding);

        mViewModel = new AddEditNoteViewModel(this);
        mViewModel.setNavigator(this);
        if (getIntent().getExtras() != null) {
            mViewModel.setNoteId(getIntent().getExtras().getString(NOTE_ID));
        }

        binding.setViewModel(mViewModel);
    }

    private void setupActionBar(ActivityAddEditNoteBinding binding) {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.onActivityDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNoteSaved() {
        Toast.makeText(getApplicationContext(), R.string.note_saved_msg,
                Toast.LENGTH_SHORT).show();
        finish();
    }
}