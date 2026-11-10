package com.lusia.zrobtosamfajer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemon")
public class Pokemon {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nazwa;

    @ColumnInfo(name = "generacja")
    private int generacja;
}
