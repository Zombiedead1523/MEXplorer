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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Catalogo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PublicacionAdapter adapter;
    private List<Publicacion> listaPublicaciones;
    private static final int FILTER_REQUEST_CODE = 1; // Código para identificar la solicitud de filtro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializa la lista de publicaciones
        listaPublicaciones = new ArrayList<>();
        // Aquí debes agregar tus publicaciones, por ejemplo:
        listaPublicaciones.add(new Publicacion("GET TO KNOW GUANAJUATO",
                "It is located in the Bajío, which is in the north-central region of the country",
                "Its capital is the city of the same name and its most populated city is León de Los Aldama.",
                "Review 1", R.drawable.gto,

                "$500 MX - $1,000 MX", "Solo traveler", "Historical"));


        listaPublicaciones.add(new Publicacion("GETAWAY TO CHIAPAS",
                "officially the Free and Sovereign State of Chiapas, is one of the thirty-one states that, together with Mexico City, make up Mexico.",
                "Its capital and most populous city is Tuxtla Gutiérrez. It is divided into one hundred and twenty-four municipalities",
                "AMAZING!!!!", R.drawable.chiapas,

                "$2,500 MX - $5,000 MX", "Families", "Cultural"));

        listaPublicaciones.add(new Publicacion("XCARET",
                "is located in the heart of the Riviera Maya, approximately 6 kilometers from Playa del Carmen",
                "Xcaret is a majestic park on the seashore, with unique activities in the middle of the forest",
                "AMAZING!!!!", R.drawable.cancun1,

                "$2,500 MX - $5,000 MX", "Couples", "Adventure"));

        listaPublicaciones.add(new Publicacion("Pujol: Mexican haute cuisine tour",
                "Located in the heart of CDMX",
                "It is the undisputed benchmark of Mexican haute cuisine.",
                "So delicious", R.drawable.pujol,

                "$2,500 MX - $5,000 MX", "Families", "Gastronomy"));

        adapter = new PublicacionAdapter(listaPublicaciones);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fabProfile = findViewById(R.id.fabProfile);
        fabProfile.setOnClickListener(view -> {
            // Abrir la actividad de perfil
            Intent intent = new Intent(Catalogo.this, Perfil.class);
            startActivity(intent);
        });
    }

    public void abrirGto(View v) {
        Intent i = new Intent(this, Info.class);
        startActivity(i);
    }

    public void abrirFiltro(View v) {
        Intent i = new Intent(this, Filtro.class);
        startActivityForResult(i, FILTER_REQUEST_CODE); // Cambia a startActivityForResult
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILTER_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String presupuesto = data.getStringExtra("BUDGET");
            String tamañoGrupo = data.getStringExtra("GROUP_SIZE");
            String tipoLugar = data.getStringExtra("TYPE_OF");

            aplicarFiltros(presupuesto, tamañoGrupo, tipoLugar);
        }
    }

    private void aplicarFiltros(String presupuesto, String tamañoGrupo, String tipoLugar) {

        List<Publicacion> listaFiltrada = new ArrayList<>();
        for (Publicacion publicacion : listaPublicaciones) {
            boolean matches = true;
            Toast.makeText(this, "Presupuesto: " +publicacion.getPresupuesto() + ", Tamaño de Grupo: " + publicacion.getTamañoGrupo()+ ", Tipo de Lugar: " + publicacion.getTipoLugar(), Toast.LENGTH_LONG).show();
            // Comprobaciones de filtros
            if (!publicacion.getPresupuesto().trim().equals(presupuesto.trim())) {
               // Toast.makeText(Catalogo.this, "Agarro"+publicacion.getTamañoGrupo() , Toast.LENGTH_SHORT).show();
                matches = false;
            }
            if (!publicacion.getTamañoGrupo().trim().equals(tamañoGrupo.trim()) ) {
                //Toast.makeText(Catalogo.this, "Agarro"+publicacion.getTamañoGrupo() , Toast.LENGTH_SHORT).show();
                matches = false;
            }
            if (!publicacion.getTipoLugar().trim().equals(tipoLugar.trim()) ) {
               // Toast.makeText(Catalogo.this, "Agarro"+publicacion.getTamañoGrupo() , Toast.LENGTH_SHORT).show();
                matches = false;
            }

            if (matches) {
                listaFiltrada.add(publicacion);
            }
        }

        // Actualiza el adaptador con la lista filtrada
        adapter = new PublicacionAdapter(listaFiltrada);
        recyclerView.setAdapter(adapter);
        Toast.makeText(this, "Filtros aplicados", Toast.LENGTH_SHORT).show();
    }





}
