package io.github.tgashwinkumar.errors;

public class InputLimitExceeded extends Exception{
    public InputLimitExceeded() {
        super("INPUT LIMIT EXCEEDED: For KMap, only maximum of four inputs can be provided.");
    }
}
