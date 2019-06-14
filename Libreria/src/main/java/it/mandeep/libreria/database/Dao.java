package it.mandeep.libreria.database;

import java.util.List;

public interface Dao<T> {
    // Add
    void add(T t);
    // Read
    T read(int id);
    List<T> readAll();
    // Update
    void update(T t);
    // Delete
    boolean delete(T t);
}
