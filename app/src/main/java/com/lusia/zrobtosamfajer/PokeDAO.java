package com.lusia.zrobtosamfajer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PokeDAO {

    @Insert
    public void wstawpokemona(Pokemon pokemon);

    @Insert
    public void wstawlistepokemonow(Pokemon  ... pokemon);

    @Delete
    public  void usunpokemonazbazy(Pokemon pokemon);
    @Update
    public  void aktualizujpokemona(Pokemon pokemon);
    @Query("SELECT * FROM POKEMON")
    public List<Pokemon> zwrocpokemonyzbazy();
    @Query("SELECT nazwa FROM POKEMON where generacja=:generacjaparametr")
    public List<String> zwrocpokemonyzbazygeneracja(int generacjaparametr);

}
