/**
 * Created by clouway on 02.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class NoTableOrDroppedTableException extends RuntimeException {

    public NoTableOrDroppedTableException(String message) {
        super(message);
    }
}