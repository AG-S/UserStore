package users.dao.mapper;

import users.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UserRowMapper {
    public static User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("firstname"));
        user.setLastName(resultSet.getString("lastname"));
        user.setSalary(resultSet.getDouble("salary"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate dateOfBirth = LocalDate.parse(resultSet.getString("dateofbirth"), dateTimeFormatter);
        user.setDateOfBirth(dateOfBirth);
        return user;
    }
}
