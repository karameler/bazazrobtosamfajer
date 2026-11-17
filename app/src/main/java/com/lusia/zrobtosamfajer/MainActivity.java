package com.lusia.zrobtosamfajer;

import static android.app.PendingIntent.getActivity;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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
    EditText editText2;
    EditText editText3;
    CheckBox czyforma;
    Pokemon kliknientypokemon;
    PokeDataBase pokeDataBase;
    ListView listView;
    ArrayAdapter<Pokemon> arrayAdapter;
    List<Pokemon> pokemony;
    AlertDialog alertDialog;

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
        editText2 = findViewById(R.id.editTextNumber);
        editText3 = findViewById(R.id.editTextText2);
        czyforma = findViewById(R.id.checkBox);

        buttondadaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pokemon pokemon = new Pokemon(czyforma.isChecked(),editText3.getText().toString(),Integer.parseInt(editText2.getText().toString()),editText.getText().toString());

                arrayAdapter.notifyDataSetChanged();
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

                Pokemon pokemondousuniencia = pokemony.get(i);
                pokeDataBase.zwrocpokemonyDao().usunpokemonazbazy(pokemondousuniencia);
                pokemony.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                kliknientypokemon = pokemony.get(i);
                editText.setText(kliknientypokemon.getNazwa());
                editText2.setText(String.valueOf(kliknientypokemon.getGeneracja()));
                editText3.setText(kliknientypokemon.getTyp());
                czyforma.setChecked(kliknientypokemon.isFormaczynie());
                buttonaktual.setVisibility(VISIBLE);
            }
        });
        buttonaktual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kliknientypokemon.setNazwa(editText.getText().toString());
                kliknientypokemon.setGeneracja(Integer.parseInt(editText2.getText().toString()));
                kliknientypokemon.setTyp(editText3.getText().toString());
                kliknientypokemon.setFormaczynie(czyforma.isChecked());
                pokeDataBase.zwrocpokemonyDao().aktualizujpokemona(kliknientypokemon);
                arrayAdapter.notifyDataSetChanged();
                buttonaktual.setVisibility(GONE);
            }
        });

    }
}