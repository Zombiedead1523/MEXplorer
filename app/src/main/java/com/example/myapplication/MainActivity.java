package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore mFirestore;
    private TextInputEditText email;
    private TextInputEditText contraseña;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (TextInputEditText) findViewById(R.id.email);
        contraseña = (TextInputEditText) findViewById(R.id.editTextPassword);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public void abrirCatalogo(View v) {
        Intent i = new Intent(this, Intereses.class);
        startActivity(i);
    }

    public void abrirRegister(View v) {
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

    public void recuperarContra(View v) {
        Intent i = new Intent(this, RecuperarContra.class);
        startActivity(i);
    }

    public void Verificar(View v) {
        String correo= email.getText().toString();
        String password = contraseña.getText().toString();

        if (!correo.isEmpty()) {
            if (!password.isEmpty()) {
                mAuth.signInWithEmailAndPassword(correo, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Inicio de sesión exitoso, obtener el usuario actual
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        // El inicio de sesión fue exitoso, redirigir al menú
                                        // Obtener el rol del usuario desde Firestore
                                        mFirestore.collection("Users").document(correo)
                                                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                        if (documentSnapshot.exists()) {
                                                            Intent i = new Intent(MainActivity.this, Catalogo.class);
                                                            startActivity(i);
                                                        } else {
                                                            // El usuario no tiene datos adicionales en Firestore
                                                            Toast.makeText(MainActivity.this, "El email no se encuentra registrado", Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });
                                    }
                                } else {
                                    // Error en el inicio de sesión
                                    Toast.makeText(MainActivity.this, "Email o contraseña incorrectos", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(MainActivity.this, "Ingrese contraseña", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Ingrese email", Toast.LENGTH_LONG).show();
        }
    }


}