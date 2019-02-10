package users.dao;

import users.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    User getById(int id);
    void insert(User user);
    void delete(int id);
    void update(User user);
}
