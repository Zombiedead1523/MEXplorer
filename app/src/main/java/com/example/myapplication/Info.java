package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCarousel);

        // Lista de imágenes (coloca aquí los IDs de tus imágenes)
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.gto1);
        imageList.add(R.drawable.gto2);
        imageList.add(R.drawable.gto3);
        imageList.add(R.drawable.gto4);

        // Configura el RecyclerView
        CarouselAdapter adapter = new CarouselAdapter(imageList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        Button btnReservar = findViewById(R.id.reservar1);
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarBottomSheetReserva();
            }
        });

    }

    private void mostrarBottomSheetReserva() {
        // Crear el BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Info.this);
        View sheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_reserva, null);
        bottomSheetDialog.setContentView(sheetView);

        EditText etNombre = sheetView.findViewById(R.id.etNombre);
        EditText etTelefono = sheetView.findViewById(R.id.etTelefono);
        EditText etCorreo = sheetView.findViewById(R.id.etCorreo);
        EditText etFecha = sheetView.findViewById(R.id.etFecha);
        Button btnFinalizar = sheetView.findViewById(R.id.btnFinalizar);

        // Selección de la fecha
        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Info.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                etFecha.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        // Finalizar reserva
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                String telefono = etTelefono.getText().toString();
                String correo = etCorreo.getText().toString();
                String fecha = etFecha.getText().toString();

                if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty() || fecha.isEmpty()) {
                    Toast.makeText(Info.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Aquí puedes manejar la lógica de la reserva, como enviar los datos a tu servidor
                    Toast.makeText(Info.this, "Reserva realizada", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                }
            }
        });

        bottomSheetDialog.show();
    }
}