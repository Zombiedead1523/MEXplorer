package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Intereses extends AppCompatActivity {

    private List<CardView> selectedCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intereses);
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
    }

    private void setCardClickListener(CardView cardView) {
        cardView.setOnClickListener(v -> {
            if (selectedCards.contains(cardView)) {
                selectedCards.remove(cardView);
                cardView.setCardBackgroundColor(getResources().getColor(android.R.color.white)); // Deseleccionado
            } else {
                selectedCards.add(cardView);
                cardView.setCardBackgroundColor(getResources().getColor(R.color.fucsia)); // Seleccionado
            }

            // Mostrar un mensaje si se seleccionaron menos de 3 opciones
            if (selectedCards.size() < 3) {
                Toast.makeText(Intereses.this, "Selecciona al menos 3 intereses", Toast.LENGTH_SHORT).show();
            }
        });
    }


}