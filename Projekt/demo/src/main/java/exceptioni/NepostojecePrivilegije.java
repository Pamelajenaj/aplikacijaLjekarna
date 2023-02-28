package exceptioni;

public class NepostojecePrivilegije extends RuntimeException{
    public NepostojecePrivilegije() {
    }

    public NepostojecePrivilegije(String message) {
        super(message);
    }

    public NepostojecePrivilegije(String message, Throwable cause) {
        super(message, cause);
    }

    public NepostojecePrivilegije(Throwable cause) {
        super(cause);
    }

    public NepostojecePrivilegije(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
