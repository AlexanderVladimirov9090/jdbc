import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by clouway on 04.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class DataStore<T> {
    private final List list;
    private final Connection dbConnection;

    public DataStore(List list, Connection dbConnection) {
        this.list = list;
        this.dbConnection = dbConnection;
    }

    public void update(String query, Object... objects) {

        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            fillStatement(statement, objects);
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("Connection to the database wasn't established");
        } finally {
            close(dbConnection);
        }
    }

    public List<T> fetchRows(String query, RowFetcher<T> rowFetcher) {

        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Optional<T> possibleRow = Optional.of(rowFetcher.fetchRow(resultSet));
                list.add(possibleRow.get());
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Connection to the database wasn't established");
        } finally {
            close(dbConnection);
        }
<<<<<<< HEAD:src/main/java/com/clouway/jdbtqueries/DataStore.java
        return checkConsistency(list);
    }

    private List<T> checkConsistency(List<T> list) {
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new IllegalStateException("No record was found by that criteria.");
        }
=======
        return list;
>>>>>>> 8e00e3887f385bbf91711b7cbddc39d7d0371f88:travel_agency/src/main/java/DataStore.java
    }

    private void fillStatement(PreparedStatement statement, Object... objects) throws SQLException {
        for (int i = 0; i < objects.length; i++) {
            statement.setObject(i + 1, objects[i]);
        }
    }

    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new IllegalStateException("Connection to the database wasn't established");
            }
        }
    }
}
