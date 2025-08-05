package com.example.todolist;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateActivity extends AppCompatActivity {

    NumberPicker pickPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initGui();
    }

    private void initGui() {
        pickPoints = findViewById(R.id.num_points);
        pickPoints.setMinValue(1);
        pickPoints.setMaxValue(10);
        pickPoints.setValue(5);
        pickPoints.setWrapSelectorWheel(false);

        pickPoints.setOnValueChangedListener((pickPoints, oldVal, newVal) -> {
            Toast.makeText(this, "Points selected: " + newVal, Toast.LENGTH_SHORT).show();
        });
    }
}