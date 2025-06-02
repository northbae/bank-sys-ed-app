package kz.osu.cinimex.exception;

public class WrongAccountStateException extends RuntimeException {
    public WrongAccountStateException(String message) {
        super(message);
    }
}
