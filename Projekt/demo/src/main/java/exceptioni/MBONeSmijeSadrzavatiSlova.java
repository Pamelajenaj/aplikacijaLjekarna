package exceptioni;

public class MBONeSmijeSadrzavatiSlova extends Exception{

    public MBONeSmijeSadrzavatiSlova() {
    }

    public MBONeSmijeSadrzavatiSlova(String message) {
        super(message);
    }

    public MBONeSmijeSadrzavatiSlova(String message, Throwable cause) {
        super(message, cause);
    }

    public MBONeSmijeSadrzavatiSlova(Throwable cause) {
        super(cause);
    }

    public MBONeSmijeSadrzavatiSlova(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
