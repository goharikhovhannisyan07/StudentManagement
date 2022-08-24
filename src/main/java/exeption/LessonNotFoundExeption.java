package exeption;

public class LessonNotFoundExeption extends Exception {

    public LessonNotFoundExeption() {
    }

    public LessonNotFoundExeption(String message) {
        super(message);
    }

    public LessonNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public LessonNotFoundExeption(Throwable cause) {
        super(cause);
    }

    public LessonNotFoundExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
