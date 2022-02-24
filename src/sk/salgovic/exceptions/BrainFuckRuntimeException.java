package sk.salgovic.exceptions;

public class BrainFuckRuntimeException extends Exception {
    public BrainFuckRuntimeException(String errorMessage) {
        super(errorMessage);
    }
}