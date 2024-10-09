package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;
    private TextInputEditText contra;
    private TextInputEditText confirmcontra;
    private TextInputEditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        contra = (TextInputEditText) findViewById(R.id.editTextPassword);
        confirmcontra = (TextInputEditText) findViewById(R.id.editTextConfirmPassword);
        email = (TextInputEditText)findViewById(R.id.email);
    }
    public boolean VerificarContraseña(String contra1, String contra2){
        boolean error=false;
        Pattern patron = Pattern.compile("^[a-zA-Z0-9_]{8,}$");
        if(!(patron.matcher(contra1).find())){
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres [A-Z,a-z,1-9,_]", Toast.LENGTH_LONG).show();
            error=true;
        }else{
            if(!(contra1.equals(contra2))){
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                error=true;
            }
        }
        return error;
    }

    public boolean Direccion(String direccion){
        Pattern validar = Pattern.compile("^[a-zA-Z0-9._%+-]+@(gmail|hotmail)\\.com$");
        boolean DireccionValida=false;

        if(direccion.isEmpty()) {
            email.setError("Ingrese correo electrónico");
            DireccionValida=false;
        }

        if(!(validar.matcher(direccion).find())) {
            email.setError("Correo electrónico inválido");
            DireccionValida=false;
        }else {
            email.setError(null);
            DireccionValida = true;
        }
        return DireccionValida;
    }
    public void mandarCorreo(){
        FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification();
    }

    public void Ejecutar(View v){
        Registrar();
    }

    public void Registrar(){
        String direccion = email.getText().toString();
        String contra1 = contra.getText().toString();
        String contra2 = confirmcontra.getText().toString();

        if(!contra1.isEmpty()||!contra2.isEmpty()) {
            boolean emailValido = Direccion(direccion);
            boolean contrasenaValida = VerificarContraseña(contra1, contra2);
            if (emailValido && !contrasenaValida) {
                if(Direccion(direccion)==true) {
                    crearUsuario(direccion, contra1);
                }
            }
        }else{
            contra.setError("Ingrese contraseña");
            Toast.makeText(this, "Ingrese contraseña", Toast.LENGTH_LONG).show();
        }
    }
    private void crearUsuario(final String direccion, final String contra) {
        // Verificar si el número de control ya existe
        mFirestore.collection("Users").document(direccion).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // El correo ya existe
                        Toast.makeText(SignUp.this, "El correo ya está en uso.", Toast.LENGTH_LONG).show();
                    } else {
                        // El correo no existe, proceder a crear el usuario
                        mAuth.createUserWithEmailAndPassword(direccion, contra)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Map<String, Object> map = new HashMap<>();
                                            map.put("correo",direccion);
                                            mFirestore.collection("Users").document(direccion).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Intent i = new Intent(SignUp.this, MainActivity.class);
                                                        startActivity(i);
                                                        Toast.makeText(SignUp.this, "El usuario se registró correctamente.", Toast.LENGTH_LONG).show();
                                                        //mandarCorreo();
                                                    } else {
                                                        Toast.makeText(SignUp.this, "Error al registrar el usuario en Firestore: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
                                        } else {
                                            Toast.makeText(SignUp.this, "Error al registrar el usuario: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                } else {
                    Toast.makeText(SignUp.this, "Error al verificar el número de control: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}