package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

public class Info extends AppCompatActivity {

    private ImageView backgroundImage;
    private TextView textTitle, know, description, cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Inicializar vistas
        backgroundImage = findViewById(R.id.backgroundImage);
        textTitle = findViewById(R.id.textTitle);
        know = findViewById(R.id.know);
        description = findViewById(R.id.description);
        cost = findViewById(R.id.cost);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        String ubicacion = intent.getStringExtra("ubicacion");
        String descripcionText = intent.getStringExtra("descripcion");
        String review = intent.getStringExtra("review");
        String presupuesto = intent.getStringExtra("presupuesto");
        int imagen = intent.getIntExtra("imagen", R.drawable.rosa);  // Recurso de imagen

        // Asignar los datos a las vistas
        textTitle.setText(titulo);
        know.setText(review);
        description.setText(descripcionText);
        cost.setText("Cost: " + presupuesto);
        backgroundImage.setImageResource(imagen);  // Establecer la imagen
    }
}
