package exceptioni;

public class CijenaLijekaMoraBitiBroj extends RuntimeException{

    public CijenaLijekaMoraBitiBroj() {
    }

    public CijenaLijekaMoraBitiBroj(String message) {
        super(message);
    }

    public CijenaLijekaMoraBitiBroj(String message, Throwable cause) {
        super(message, cause);
    }

    public CijenaLijekaMoraBitiBroj(Throwable cause) {
        super(cause);
    }

    public CijenaLijekaMoraBitiBroj(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
