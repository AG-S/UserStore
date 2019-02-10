package users.service;

import users.entity.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();

    User findById(int id);

    void insert(User user);

    void deleteById(int id);

    void update(User user);
}
