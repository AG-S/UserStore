package users.dao.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnection {
    private static final String PROPERTIES_PATH = "src/main/resources/config.properties";
    private static String db_url;
    private static String db_user;
    private static String db_pass;


    private static void readProperties() throws IOException {
        FileInputStream fileInputStream =null;
        Properties property = new Properties();

        try {
            fileInputStream = new FileInputStream(PROPERTIES_PATH);
            property.load(fileInputStream);
            db_url = property.getProperty("db.host");
            db_user = property.getProperty("db.login");
            db_pass = property.getProperty("db.password");
        } catch (IOException e) {
            System.err.println("config file not found");
        } finally {
            fileInputStream.close();
        }
    }
    public static Connection getConnection() throws SQLException, IOException {
        readProperties();
        return DriverManager.getConnection(db_url, db_user,db_pass);
    }

}
