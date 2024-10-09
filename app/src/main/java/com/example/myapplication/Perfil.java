package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Perfil extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private ImageView fotoPerfil;
    private ImageButton editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        fotoPerfil = findViewById(R.id.FotoPerfil);
        editButton = findViewById(R.id.edit_button);

        // Configurar el botón de editar
        editButton.setOnClickListener(v -> openGallery());
    }

    // Método para abrir la galería
    private void openGallery() {
        Intent intent;
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                fotoPerfil.setImageBitmap(bitmap); // Actualizar la imagen de perfil
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void abrirMain(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}