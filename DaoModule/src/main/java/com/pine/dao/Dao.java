package com.pine.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<E> {
    Optional<E> get(long id);
    List<E> getAll();
    void save(E e);
    void update(E e, String[] params);
    void delete(E e);
}
