package users.dao.jdbc;

import org.junit.Test;
import users.entity.User;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcUserDaoITest {
    @Test
    public void testGetAll(){
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        List<User> users = jdbcUserDao.getAll();
        for (User user: users) {
            assertNotEquals(user.getId(),0);
            assertNotNull(user.getFirstName());
            assertNotNull(user.getLastName());
            assertNotEquals(user.getSalary(),0);
        }

    }

}