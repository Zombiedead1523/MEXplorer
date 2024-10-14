package com.example.myapplication;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Perfil extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private ImageView fotoPerfil;
    private ImageButton editButton;
    private Uri imageUri;

    // Firebase instances
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private FirebaseStorage mStorage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        fotoPerfil = findViewById(R.id.FotoPerfil);
        editButton = findViewById(R.id.edit_button);

        // Initialize Firebase instances
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mStorage = FirebaseStorage.getInstance();
        storageReference = mStorage.getReference();

        // Cargar la foto de perfil desde Firebase Storage al iniciar
        cargarFotoPerfil();

        // Configurar el botón de editar para abrir la galería
        editButton.setOnClickListener(v -> openGallery());
    }

    // Método para abrir la galería
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                fotoPerfil.setImageBitmap(bitmap); // Actualizar la imagen de perfil localmente
                subirImagenFirebase(); // Subir la imagen a Firebase
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para cargar la foto de perfil desde Firebase Storage
    // Método para cargar la foto de perfil desde Firebase Storage
    private void cargarFotoPerfil() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            // Obtener la URL de la imagen desde Firestore
            mFirestore.collection("Users").document(userId).get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    String imageUrl = documentSnapshot.getString("profileImageUrl");
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        // Cargar la imagen desde Firebase Storage usando Glide
                        Glide.with(this)
                                .load(imageUrl) // URL de la imagen en Firebase Storage
                                .placeholder(R.drawable.perfil) // Imagen de carga por defecto
                                .into(fotoPerfil); // Actualizar ImageView con la imagen descargada
                    }
                }
            }).addOnFailureListener(e -> Toast.makeText(Perfil.this, "", Toast.LENGTH_SHORT).show());
        }
    }

    // Método para subir la imagen seleccionada a Firebase Storage
    private void subirImagenFirebase() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null && imageUri != null) {
            String userId = user.getUid();
            StorageReference filePath = storageReference.child("profile_images").child(userId + ".jpg");

            filePath.putFile(imageUri).addOnSuccessListener(taskSnapshot -> filePath.getDownloadUrl().addOnSuccessListener(uri -> {
                String downloadUrl = uri.toString();

                // Guardar la URL de la imagen en Firestore
                mFirestore.collection("Users").document(userId).update("profileImageUrl", downloadUrl)
                        .addOnSuccessListener(aVoid -> Toast.makeText(Perfil.this, "Profile image updated successfully", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(Perfil.this, "Error saving profile image URL", Toast.LENGTH_SHORT).show());

            })).addOnFailureListener(e -> Toast.makeText(Perfil.this, "Error uploading image", Toast.LENGTH_SHORT).show());
        }
    }

    public void abrirMain(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}