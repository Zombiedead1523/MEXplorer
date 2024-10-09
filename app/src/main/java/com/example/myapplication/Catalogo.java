package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Catalogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        FloatingActionButton fabProfile = findViewById(R.id.fabProfile);
        fabProfile.setOnClickListener(view -> {
            // Abrir la actividad de perfil
            Intent intent = new Intent(Catalogo.this, Perfil.class);
            startActivity(intent);
        });
        Spinner spinnerFilter = findViewById(R.id.spinnerFilter);
        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedFilter = parentView.getItemAtPosition(position).toString();
                // Aplicar el filtro correspondiente
                Toast.makeText(Catalogo.this, "Seleccionado: " + selectedFilter, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Cuando no se selecciona nada
            }
        });

    }
    public void abrirGto(View v) {
        Intent i = new Intent(this, Info.class);
        startActivity(i);
    }
    public void abrirCancun(View v) {
        Intent i = new Intent(this, Info2.class);
        startActivity(i);
    }
    public void abrirChiapas(View v) {
        Intent i = new Intent(this, Info3.class);
        startActivity(i);
    }
    public void abrirCdmx(View v) {
        Intent i = new Intent(this, Info4.class);
        startActivity(i);
    }

}
