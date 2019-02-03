package users.dao.jdbc;

import users.dao.UserDao;
import users.dao.mapper.UserRowMapper;
import users.entity.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    @Override
    public List<User> getAll()  {

        try(
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(new UserQueryGenerator().getAll(User.class));) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = (USER_ROW_MAPPER.mapRow(resultSet));
                users.add(user);

            }
            return users;
        } catch(SQLException e){
            throw new RuntimeException("Get all method failed",e);
        } catch(IOException e) {
            throw new RuntimeException("Read config file failed",e);

        }
    }

    private Connection getConnection() throws SQLException, IOException {
        return JdbcConnection.getConnection();

    }

}
