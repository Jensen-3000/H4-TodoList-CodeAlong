package com.example.todolist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class CreateActivity extends AppCompatActivity {

    NumberPicker pickPoints;
    EditText editTitle;
    LocalDateTime selectedDateTime;

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
        editTitle = findViewById(R.id.edit_title);

        pickPoints = findViewById(R.id.num_points);
        pickPoints.setMinValue(1);
        pickPoints.setMaxValue(10);
        pickPoints.setValue(5);
        pickPoints.setWrapSelectorWheel(false);

        Button btnPickDateTime = findViewById(R.id.btn_pick_datetime);
        btnPickDateTime.setOnClickListener(v -> showDateTimePicker());

        findViewById(R.id.fob_create).setOnClickListener(v -> {
            String title = editTitle.getText().toString().trim();
            if (title.isEmpty()) {
                Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
                return;
            }
            if (selectedDateTime == null) {
                Toast.makeText(this, "Please pick a deadline", Toast.LENGTH_SHORT).show();
                return;
            }
            int points = pickPoints.getValue();
            TodoItem item = new TodoItem(
                title,
                points,
                TodoItem.Priority.MEDIUM, // default
                TodoItem.Status.TODO, // default
                TodoItem.RepeatType.FIXED, // default
                false, // repeatable
                0, // repeatInterval
                java.time.LocalDateTime.now(), // createdAt
                java.time.LocalDateTime.now(), // updatedAt
                null, // completedAt
                selectedDateTime // deadline
            );
            android.content.Intent data = new android.content.Intent();
            data.putExtra("todo_item", item);
            setResult(RESULT_OK, data);
            finish();
        });
    }

    private void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        final Calendar date = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            date.set(year, month, dayOfMonth);
            new TimePickerDialog(this, (view1, hourOfDay, minute) -> {
                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                date.set(Calendar.MINUTE, minute);
                selectedDateTime = LocalDateTime.of(
                        year, month + 1, dayOfMonth, hourOfDay, minute
                );
                // Format date/time for display
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                Button btn = findViewById(R.id.btn_pick_datetime);
                btn.setText(selectedDateTime.format(formatter));
            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true).show();
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }
}