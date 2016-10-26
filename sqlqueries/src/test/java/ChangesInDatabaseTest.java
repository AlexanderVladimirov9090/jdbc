import com.clouway.jdbcqueries.Connector;
import com.clouway.jdbcqueries.DataStore;
import com.clouway.jdbcqueries.User;
import org.junit.Test;

import java.sql.SQLException;
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
    private DataStore dataStore = new DataStore(connector.getConnection());

    @Test
    public void newTable() {
        DataStore dataStore = new DataStore(connector.getConnection());
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

    @Test
    public void dropDataBase() {
        dataStore.update("DROP DATABASE someDatabaseName;");
    }

    @Test
    public void dropTable() {
        dataStore.update("DROP TABLE someTableName;");
    }

    @Test
    public void updateStatement() {
        dataStore.update("UPDATE SomeTable SET SomeText = 'Good to go' WHERE Age < 37 OR Status = divorced;");
    }

    @Test
    public void alterTable() {
        dataStore.update("ALTER TABLE someTable ADD Price FLOAT;");
    }

    @Test
    public void dropColumn() {
        dataStore.update("ALTER TABLE someTable DROP COLUMN PaperWork;");
    }
}
