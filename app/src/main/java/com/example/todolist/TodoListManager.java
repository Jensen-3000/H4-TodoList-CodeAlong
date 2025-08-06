package com.example.todolist;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TodoListManager {
    private static final String PREFS_NAME = "todo_prefs";
    private static final String KEY_TODO_LIST = "todo_list";
    private static TodoListManager instance;
    private final List<TodoItem> todoList = new ArrayList<>();
    private TodoListManager() {}

    public static TodoListManager getInstance() {
        if (instance == null) {
            instance = new TodoListManager();
        }
        return instance;
    }

    public List<TodoItem> getTodoList() {
        return todoList;
    }

    public void addTodo(TodoItem item) {
        todoList.add(item);
    }

    private static class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        @Override
        public void write(JsonWriter out, LocalDateTime value) throws IOException {
            out.value(value == null ? null : value.format(formatter));
        }
        @Override
        public LocalDateTime read(JsonReader in) throws IOException {
            if (in == null || in.peek() == null) return null;
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            if (in.peek() != com.google.gson.stream.JsonToken.STRING) {
                in.skipValue();
                return null;
            }
            String str = in.nextString();
            if (str == null || str.isEmpty()) return null;
            try {
                return LocalDateTime.parse(str, formatter);
            } catch (Exception e) {
                return null;
            }
        }
    }

    private Gson getGson() {
        return new Gson().newBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }

    public void save(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = getGson();
        String json = gson.toJson(todoList);
        editor.putString(KEY_TODO_LIST, json);
        editor.apply();
    }

    public void load(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_TODO_LIST, null);
        if (json != null) {
            Gson gson = getGson();
            Type type = new TypeToken<ArrayList<TodoItem>>(){}.getType();
            List<TodoItem> loaded = gson.fromJson(json, type);
            todoList.clear();
            if (loaded != null) todoList.addAll(loaded);
        }
    }
}
