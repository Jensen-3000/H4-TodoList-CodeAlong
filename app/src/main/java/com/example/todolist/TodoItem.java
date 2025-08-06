package com.example.todolist;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TodoItem implements Serializable {
    // Enums
    public enum Priority {LOW, MEDIUM, HIGH, CRITICAL}
    public enum Status {TODO, DOING, DONE}
    public enum RepeatType {FIXED, DAILY, WEEKLY, MONTHLY, YEARLY}

    // Properties
    private String name;
    private int point;
    private Priority priority;
    private Status status;
    private RepeatType repeatType;
    private boolean repeatable;
    private int repeatInterval; // in seconds

    // Timestamps
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedAt;
    private LocalDateTime deadline;

    // Constructor

    public TodoItem(String name, int point, Priority priority, Status status, RepeatType repeatType, boolean repeatable, int repeatInterval, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime completedAt, LocalDateTime deadline) {
        this.name = name;
        this.point = point;
        this.priority = priority;
        this.status = status;
        this.repeatType = repeatType;
        this.repeatable = repeatable;
        this.repeatInterval = repeatInterval;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.completedAt = completedAt;
        this.deadline = deadline;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public RepeatType getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(RepeatType repeatType) {
        this.repeatType = repeatType;
    }

    public boolean isRepeatable() {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }

    public int getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(int repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
