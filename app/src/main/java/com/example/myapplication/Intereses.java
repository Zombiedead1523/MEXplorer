package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Intereses extends AppCompatActivity {

    private List<CardView> selectedCards = new ArrayList<>();
    private List<String> selectedInterests = new ArrayList<>();
    private FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intereses);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        CardView gastronomia = findViewById(R.id.gastronomia);
        CardView places = findViewById(R.id.places);
        CardView crafts = findViewById(R.id.crafts);
        CardView culture = findViewById(R.id.culture);
        CardView adventure = findViewById(R.id.adventure);
        CardView nigth = findViewById(R.id.nigth);
        CardView shopin = findViewById(R.id.shopin);
        CardView beach = findViewById(R.id.beach);

        setCardClickListener(gastronomia);
        setCardClickListener(places);
        setCardClickListener(crafts);
        setCardClickListener(culture);
        setCardClickListener(adventure);
        setCardClickListener(nigth);
        setCardClickListener(beach);
        setCardClickListener(shopin);


        // Cuando el usuario presione "Continuar"
        Button save;
        save = findViewById(R.id.save);
        save.setOnClickListener(v -> {
            abrirCatalogo();
        });

    }

    private void setCardClickListener(CardView cardView) {
        cardView.setOnClickListener(v -> {
            if (selectedCards.contains(cardView)) {
                selectedCards.remove(cardView);
                cardView.setCardBackgroundColor(getResources().getColor(android.R.color.white)); // Deseleccionado
            } else {
                selectedCards.add(cardView);
                cardView.setCardBackgroundColor(getResources().getColor(R.color.fucsia));// Seleccionado

                String interes = String.valueOf(cardView.getId());
                selectedInterests.add(interes);

            }
        });
    }

    private void abrirCatalogo() {
        if (selectedInterests.size() >= 3) {
            guardarInteresesEnFirestore();  // Guardar intereses en Firestore
        } else {
            Toast.makeText(this, "Debes seleccionar al menos 3 intereses para continuar", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarInteresesEnFirestore() {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // Crear un mapa para almacenar los intereses seleccionados
            Map<String, Object> interesesMap = new HashMap<>();
            interesesMap.put("intereses", selectedInterests);
            interesesMap.put("interesesSeleccionados", true); // Campo adicional para verificar si ya seleccionó intereses

            // Guardar los intereses en Firestore, dentro del documento del usuario
            mFirestore.collection("Users").document(user.getEmail())
                    .update(interesesMap)
                    .addOnSuccessListener(aVoid -> {
                        // Si se guardó correctamente, abrir la ventana de Catálogo
                        Intent intent = new Intent(Intereses.this, Catalogo.class);
                        startActivity(intent);
                    })
                    .addOnFailureListener(e -> {
                        // Si ocurrió un error, mostrar mensaje
                        Toast.makeText(Intereses.this, "Error al guardar intereses", Toast.LENGTH_SHORT).show();
                    });
        }
    }


}