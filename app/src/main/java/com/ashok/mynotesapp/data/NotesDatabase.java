package com.ashok.mynotesapp.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by ashok on 13/11/17.
 */
@Database(entities = {Note.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    private static final Object sLock = new Object();
    private static NotesDatabase INSTANCE;

    public static NotesDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        NotesDatabase.class, "Notes.db")
                        .build();
            }
            return INSTANCE;
        }
    }

    public abstract NoteDao noteDao();
}
