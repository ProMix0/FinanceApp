package com.example.financeapp.db;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories",
        indices = {@Index(value = {"name"}, unique = true)})
public class Category {
    @PrimaryKey(autoGenerate = true)
    long id;
    String name;

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Category clone() {
        Category clone = new Category(getId(), getName());
        return clone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
