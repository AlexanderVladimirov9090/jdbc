import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by clouway on 25.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class JDBCConnector {
    private final String JDBC_DRIVER;
    private final String DB_URL;
    private final String USER;
    private final String PASSWORD;

    public JDBCConnector(String jDBCDriver, String databaseURL, String user, String password) {
        this.JDBC_DRIVER = jDBCDriver;
        this.DB_URL = databaseURL;
        this.USER = user;
        this.PASSWORD = password;
    }

    public Connection getConnection() throws NoConnectionException {
        try {
            Class.forName(JDBC_DRIVER);
            isUserProvided();
            isPasswordProvided();
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            throw new NoConnectionException("You did not provide SQL driver");
        } catch (SQLException e) {
            throw new NoConnectionException("You did not provide URL for Database");
        }
    }

    private void isPasswordProvided() throws NoConnectionException {
        if (PASSWORD == null) {
            throw new NoConnectionException("Did not provide password.");
        }
    }

    private void isUserProvided() throws NoConnectionException {
        if (USER == null) {
            throw new NoConnectionException("Did not provide user.");
        }
    }
}