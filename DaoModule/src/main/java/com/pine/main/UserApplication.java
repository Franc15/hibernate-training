package com.pine.main;

import com.pine.dao.Dao;
import com.pine.dao.users.UserDao;
import com.pine.users.User;

import java.util.Optional;

public class UserApplication {
    private static Dao<User> userDao;
    public static void main(String[] args) {
        userDao = new UserDao();

        User user1 = getUser(0);
        System.out.println(user1);
        userDao.update(user1, new String[] {"Jake", "jake@greys.com"});

        User user2 = getUser(1);
        userDao.delete(user2);
        userDao.save(new User("Julie", "juliess@gmail.com"));

        userDao.getAll().forEach(user -> System.out.println(user.getName()));
    }

    private static User getUser(long id) {
        Optional<User> user = userDao.get(id);

        return user.orElseGet(
                () -> new User("non-existing user", "no-email"));
    }
}