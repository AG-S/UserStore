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
    private static final String SELECT_ALL_USERS = "Select id, firstname, lastname, salary, dateofbirth from users;";
    private static final String SELECT_BY_ID = "Select id, firstname, lastname, salary, dateofbirth from users where id = ?;";
    private static final String INSERT_USER = "Insert into users (id, firstname, lastname, salary, dateofbirth) values (?, ?, ?, ?, ?);";
    private static final String UPDATE_USER = "Update users set firstname = ?, lastname = ?, salary = ?, dateofbirth = ? where id = ?;";
    private static final String DELETE_USER = "Delete from users where id = ?;";

    @Override
    public List<User> getAll()  {

        try(
        Connection connection = getConnection();
        Statement statement =connection.createStatement();

        ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);) {
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

    @Override
    public User getById(int id) {
        try(
                Connection connection = getConnection();
                PreparedStatement  preparedStatement =connection.prepareStatement(SELECT_BY_ID);

            ) {
            preparedStatement.setInt(1,id);

            try (
            ResultSet resultSet = preparedStatement.executeQuery();
            )  {User user = null;
                while (resultSet.next()) {
                user = (USER_ROW_MAPPER.mapRow(resultSet));
                if (resultSet.next()) {
                    throw new RuntimeException("More than one row were selected by id = "+id);
                }
            }
                System.out.println(user.getId());
                return user;
            }

        } catch(SQLException e){
            throw new RuntimeException("Select record by ID method failed",e);
        } catch(IOException e) {
            throw new RuntimeException("Read config file failed",e);

        }
    }

    @Override
    public void insert(User user) {
        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement =connection.prepareStatement(INSERT_USER);)
        {
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3,user.getLastName());
            preparedStatement.setDouble(4,user.getSalary());
            preparedStatement.setDate(5,Date.valueOf(user.getDateOfBirth()));
            preparedStatement.execute();

        } catch(SQLException e){
            throw new RuntimeException("INSERT method failed",e);
        } catch(IOException e) {
            throw new RuntimeException("Read config file failed",e);
        }

    }

    @Override
    public void delete(int id) {
        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement =connection.prepareStatement(DELETE_USER);)
        {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

        } catch(SQLException e){
            throw new RuntimeException("DELETE method failed",e);
        } catch(IOException e) {
            throw new RuntimeException("Read config file failed",e);
        }

    }

    @Override
    public void update(User user) {
        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement =connection.prepareStatement(UPDATE_USER);)
        {
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setDouble(3,user.getSalary());
            preparedStatement.setDate(4,Date.valueOf(user.getDateOfBirth()));
            preparedStatement.setInt(5,user.getId());
            preparedStatement.execute();

        } catch(SQLException e){
            throw new RuntimeException("Update method failed",e);
        } catch(IOException e) {
            throw new RuntimeException("Read config file failed",e);
        }

    }

    private Connection getConnection() throws SQLException, IOException {
        return JdbcConnection.getConnection();

    }
}
