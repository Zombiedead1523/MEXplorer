package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Filtro extends AppCompatActivity {

    private RadioGroup rgBudget, rgGroupSize, rgTypeOf;
    private Button btnResetAll, btnApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);

        rgBudget = findViewById(R.id.rgBudget);
        rgGroupSize = findViewById(R.id.rgGroupSize);
        rgTypeOf = findViewById(R.id.rgTypeOf);

        btnResetAll = findViewById(R.id.btnResetAll);
        btnApply = findViewById(R.id.btnApply);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedBudget = getSelectedRadioText(rgBudget);
                String selectedGroupSize = getSelectedRadioText(rgGroupSize);
                String selectedTypeOf = getSelectedRadioText(rgTypeOf);

                if (selectedBudget != null && selectedGroupSize != null && selectedTypeOf != null) {
                    // Enviar las selecciones de vuelta a Catalogo
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("BUDGET", selectedBudget);
                    resultIntent.putExtra("GROUP_SIZE", selectedGroupSize);
                    resultIntent.putExtra("TYPE_OF", selectedTypeOf);

                    setResult(RESULT_OK, resultIntent);
                    finish(); // Cierra la actividad Filtro
                } else {
                    Toast.makeText(Filtro.this, "Please select all options", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnResetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgBudget.clearCheck();
                rgGroupSize.clearCheck();
                rgTypeOf.clearCheck();
                Toast.makeText(Filtro.this, "Selections reset", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getSelectedRadioText(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            return selectedRadioButton.getText().toString();
        }
        return null;
    }

    public void aplicarFiltros(View v) {
        // Obtén los valores seleccionados por el usuario
        String presupuesto = rgBudget.toString();
        String tamañoGrupo = rgGroupSize.toString();
        String tipoLugar = rgTypeOf.toString();

        // Crea un Intent para devolver los resultados a la actividad principal
        Intent resultIntent = new Intent();
        resultIntent.putExtra("BUDGET", presupuesto);
        resultIntent.putExtra("GROUP_SIZE", tamañoGrupo);
        resultIntent.putExtra("TYPE_OF", tipoLugar);

        setResult(RESULT_OK, resultIntent);
        finish(); // Cierra la actividad de filtros y vuelve a la actividad principal
    }

}
