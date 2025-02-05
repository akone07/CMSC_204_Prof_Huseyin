package password_Package;

public class LengthException extends Exception {
    public LengthException() {
        super("The password must be at least 6 characters long");
    }
}

