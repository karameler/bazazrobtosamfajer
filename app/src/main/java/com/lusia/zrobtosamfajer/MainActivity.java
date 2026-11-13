package com.lusia.zrobtosamfajer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button buttondadaj;
    Button buttonaktual;
    EditText editText;
    Pokemon kliknientypokemon;
    PokeDataBase pokeDataBase;
    ListView listView;
    ArrayAdapter<Pokemon> arrayAdapter;
    List<Pokemon> pokemony;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pokeDataBase = Room.databaseBuilder(
                getApplicationContext(),PokeDataBase.class,"pokedb"
        ).allowMainThreadQueries().build();
        //pokeDataBase.zwrocpokemonyDao().wstawpokemona(new Pokemon(false, "normalny", 6, "furfrou"));
        buttonaktual = findViewById(R.id.button2);
        buttondadaj = findViewById(R.id.button);
        editText = findViewById(R.id.editTextText);
        buttondadaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pokemon pokemon = new Pokemon(false,"najlepszy" ,9,editText.getText().toString());
                pokeDataBase.zwrocpokemonyDao().wstawpokemona(pokemon);

            }
        });
        listView = findViewById(R.id.listapok);
        pokemony = pokeDataBase.zwrocpokemonyDao().zwrocpokemonyzbazy();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, pokemony);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                return false;
            }
        });
        listView.setOnItemClickListener();
    }
}