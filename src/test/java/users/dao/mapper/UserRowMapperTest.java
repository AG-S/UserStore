package users.dao.mapper;

import org.junit.Test;
import users.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRowMapperTest {

    @Test
    public void testUserRow() throws SQLException {
        UserRowMapper userRowMapper = new UserRowMapper();
        ResultSet resultSetMock =mock(ResultSet.class);

        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getString("firstname")).thenReturn("Aleksander");
        when(resultSetMock.getString("lastname")).thenReturn("Kwaśniewski");
        when(resultSetMock.getDouble("salary")).thenReturn(12000.00);

        User actualUser = userRowMapper.mapRow(resultSetMock);

        assertEquals(1,actualUser.getId());
        assertEquals("Aleksander",actualUser.getFirstName());
        assertEquals("Kwaśniewski",actualUser.getLastName());
        assertEquals(12000.00, actualUser.getSalary(),0.001);

    }

}

