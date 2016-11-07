import java.sql.ResultSet;

/**
 * Created by clouway on 04.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public interface RowFetcher<T> {
    T fetchRow(ResultSet resultSet);
}
