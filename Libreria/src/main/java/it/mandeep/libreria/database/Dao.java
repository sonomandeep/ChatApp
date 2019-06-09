package it.mandeep.libreria.database;

import java.util.List;

public interface Dao<T> {
    // Create table
    void createTable();
    // Add
    void add(T t);
    // Read
    T read(int id);
    List<T> readAll();
    // Update
    void update(T t);
    // Delete
    void delete(T t);
}
