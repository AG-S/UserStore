package users.dao;

import users.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
}
