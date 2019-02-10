package users.service.impl;

import users.dao.UserDao;
import users.entity.User;
import users.service.IUserService;

import java.util.List;

public class UserService implements IUserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User findById(int id) {
        return userDao.getById(id);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);

    }

    @Override
    public void deleteById(int id) {
        userDao.delete(id);

    }

    @Override
    public void update(User user) {
        userDao.update(user);

    }
}
