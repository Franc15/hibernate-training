package com.pine.main;

import com.pine.dao.Dao;
import com.pine.dao.users.JpaUserDao;
import com.pine.users.User;

import java.util.List;
import java.util.Optional;

public class JpaUserApplication {
    private static Dao<User> jpaUserDao;
    public static void main(String[] args) {
        jpaUserDao = new JpaUserDao();

        // add users to db
        addUser(new User("Bella", "isabel@email.com"));
        addUser(new User("Lisa", "lisaa@brk.com"));
        addUser(new User("Dylan", "dylan@alu.com"));

        // display first user information
        System.out.println(getUser(0));

        // update third user information
        updateUser(getUser(2), new String[] {"Julie", "julien42@king.com"});

        // delete second user
        User toDeleteUser = getUser(1);
        System.out.println("Deleting user: " + toDeleteUser.getName());
        deleteUser(toDeleteUser);
        System.out.println("User deleted successfully");

        getAllUsers().forEach(user -> System.out.println(user.getName()));
    }

    public static User getUser(long id) {
        Optional<User> user = jpaUserDao.get(id);

        return user.orElseGet(
                () -> new User("non-existing user", "no-email"));
    }

    public static void addUser(User user) {
        jpaUserDao.save(user);
    }

    public static void deleteUser(User user) {
        jpaUserDao.delete(user);
    }

    public static List<User> getAllUsers() {
        return jpaUserDao.getAll();
    }

    public static void updateUser(User user, String[] params) {
        jpaUserDao.update(user, params);
    }
}
