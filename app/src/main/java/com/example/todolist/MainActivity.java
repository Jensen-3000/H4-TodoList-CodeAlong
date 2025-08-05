package com.example.todolist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<TodoItem> todoItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.fob_add).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
            startActivity(intent);

            // Teacher Shenanigans
//            Intent intent2 = new Intent(Intent.ACTION_SENDTO);
//            intent.setData(Uri.parse("mailto:"));
//            intent2.putExtra(Intent.EXTRA_EMAIL, "");
        });
    }
}