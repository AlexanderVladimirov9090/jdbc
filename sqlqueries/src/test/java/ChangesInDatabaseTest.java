import com.clouway.jdbtqueries.Connector;
import com.clouway.jdbtqueries.DataStore;
import com.clouway.jdbtqueries.User;
import org.junit.Test;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by clouway on 07.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class ChangesInDatabaseTest {
    private Connector connector = new Connector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jdbc_database", "root", "clouway.com");
    private DataStore dataStore = new DataStore(new LinkedList(), connector.getConnection());

    @Test
    public void newTable() {
        DataStore dataStore = new DataStore(new LinkedList(), connector.getConnection());
        dataStore.update("CREATE TABLE CUSTOMERS( Number INT, PRIMARY KEY (Number));");
    }

    @Test
    public void createNewRecord() {
        User expected = new User(9, "NoName", "NoPass", "NoMail");
        dataStore.update("INSERT INTO users VALUES (9 , 'NoName','NoPass','NoMail');");
        List actualUsers = dataStore.fetchRows("SELECT * FROM users WHERE ID = 9;", resultSet -> {
            try {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        User actualUser = (User) actualUsers.get(0);
        assertTrue(actualUser.equal(expected));
    }



    @Test
    public void deleteRecord() {
        dataStore.update("DELETE FROM users WHERE ID = 9;");
    }
}
