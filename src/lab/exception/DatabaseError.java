package lab.exception;

/**
 * Created by Roman Kolesnik on 21.05.2017.
 */
public class DatabaseError extends RuntimeException {
    public DatabaseError(String message) {
        super(message);
    }

    public DatabaseError(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseError(Throwable cause) {
        super(cause);
    }
}
