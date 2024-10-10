package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarContra extends AppCompatActivity {

    //referencia a la base de datos
    FirebaseAuth mAuth ;

    //El correo
    EditText emailRecuperar;
    Button restaurarButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contra);

        mAuth = FirebaseAuth.getInstance();
        emailRecuperar = findViewById(R.id.emailRecuperar);
        restaurarButton = findViewById(R.id.restaurarButton);
        restaurarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailRecuperar.getText().toString().trim();

                if (email.isEmpty()) {
                    emailRecuperar.setError("Email is required");
                    emailRecuperar.requestFocus();
                    return;
                }

                // Enviar el correo de restablecimiento de contraseña
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Correo enviado exitosamente
                                    Toast.makeText(RecuperarContra.this, "\n" +
                                            "Recovery email sent", Toast.LENGTH_LONG).show();
                                } else {
                                    // Error al enviar el correo
                                    Toast.makeText(RecuperarContra.this, "Error sending email", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }});
    }

}