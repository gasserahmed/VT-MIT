package business;

public class BookstoreDbException extends RuntimeException {

    public BookstoreDbException(String message) {
        super(message);
    }

    public BookstoreDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class BookstoreConnectionDbException extends BookstoreDbException {
        public BookstoreConnectionDbException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class BookstoreQueryDbException extends BookstoreDbException {
        public BookstoreQueryDbException(String message) {
            super(message);
        }

        public BookstoreQueryDbException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
