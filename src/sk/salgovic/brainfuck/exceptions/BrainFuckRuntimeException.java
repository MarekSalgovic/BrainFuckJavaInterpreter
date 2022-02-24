package sk.salgovic.brainfuck.exceptions;

public class BrainFuckRuntimeException extends Exception {
    public BrainFuckRuntimeException(String errorMessage) {
        super(errorMessage);
    }
}