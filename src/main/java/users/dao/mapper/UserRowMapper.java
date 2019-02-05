package users.dao.mapper;

import users.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class UserRowMapper {
    public static User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("firstname"));
        user.setLastName(resultSet.getString("lastname"));
        user.setSalary(resultSet.getDouble("salary"));
       // user.setDateOfBirth(resultSet.getDate("dateofBirst", Calendar.getInstance() ));
        return user;
    }
}
