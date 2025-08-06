package com.example.todolist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private TodoListManager todoListManager;
    private TodoAdapter adapter;
    private ActivityResultLauncher<Intent> createLauncher;

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

        todoListManager = TodoListManager.getInstance();
        todoListManager.load(this);
        adapter = new TodoAdapter(todoListManager.getTodoList());
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        createLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    TodoItem item = (TodoItem) result.getData().getSerializableExtra("todo_item");
                    if (item != null) {
                        todoListManager.addTodo(item);
                        adapter.notifyItemInserted(todoListManager.getTodoList().size() - 1);
                        todoListManager.save(this);
                    }
                }
            }
        );

        findViewById(R.id.fob_add).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
            createLauncher.launch(intent);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        todoListManager.save(this);
    }
}