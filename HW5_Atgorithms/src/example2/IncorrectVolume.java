package example2;

public class IncorrectVolume extends Exception {
    public IncorrectVolume() {
        super();
    }

    public IncorrectVolume(String message) {
        super(message);
    }

    public IncorrectVolume(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectVolume(Throwable cause) {
        super(cause);
    }
}
