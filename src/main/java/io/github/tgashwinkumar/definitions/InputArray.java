package io.github.tgashwinkumar.definitions;

public class InputArray {

    public int length = 0;
    public char[] array;

    public InputArray(char ...inputs){
        this.length = inputs.length;
        this.array = inputs;
    }
}
