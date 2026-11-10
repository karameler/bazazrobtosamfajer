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
    private String typ;

    private boolean formaczynie;

    public Pokemon(boolean formaczynie, String typ, int generacja, String nazwa) {
        this.formaczynie = formaczynie;
        this.typ = typ;
        this.generacja = generacja;
        this.nazwa = nazwa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getGeneracja() {
        return generacja;
    }

    public void setGeneracja(int generacja) {
        this.generacja = generacja;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public boolean isFormaczynie() {
        return formaczynie;
    }

    public void setFormaczynie(boolean formaczynie) {
        this.formaczynie = formaczynie;
    }

    public Pokemon() {
    }

    @Override
    public String toString() {
        return nazwa + ", " + generacja + ", " + typ + ", " +  formaczynie;
    }
}
