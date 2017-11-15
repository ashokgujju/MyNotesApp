package com.ashok.mynotesapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

/**
 * Created by ashok on 13/11/17.
 */

@Entity(tableName = "notes")
public final class Note {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "noteId")
    private final String mId;

    @Nullable
    @ColumnInfo(name = "title")
    private final String title;

    @Nullable
    @ColumnInfo(name = "body")
    private final String body;

    @ColumnInfo(name = "date")
    private final String date;

    // use to create note
    @Ignore
    public Note(@Nullable String title, @Nullable String body, String date) {
        this(UUID.randomUUID().toString(), title, body, date);
    }

    // use to edit note
    public Note(@NonNull String mId, @Nullable String title, @Nullable String body, String date) {
        this.mId = mId;
        this.title = title;
        this.body = body;
        this.date = date;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }
}
