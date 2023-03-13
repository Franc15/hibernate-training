package com.pine.dao.users;

import com.pine.dao.Dao;
import com.pine.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDao implements Dao<User> {
    List<User> users = new ArrayList<>();

    public UserDao() {
        users.add(new User("Bella", "bella@gmail.com"));
        users.add(new User("John", "john@alu.com"));
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(users.get((int) id));
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void update(User user, String[] params) {
        user.setName(Objects.requireNonNull(params[0], "Name cannot be null"));
        user.setEmail(Objects.requireNonNull(params[1], "Email cannot be null"));
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }
}
