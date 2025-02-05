package password_Package;

public class UnmatchedException extends Exception {
    public UnmatchedException() {
        super("The passwords do not match");
    }
}
