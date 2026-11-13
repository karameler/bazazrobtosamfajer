package com.lusia.zrobtosamfajer;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pokemon.class}, version = 1)
public abstract class PokeDataBase extends RoomDatabase {
    public abstract PokeDAO zwrocpokemonyDao();
}