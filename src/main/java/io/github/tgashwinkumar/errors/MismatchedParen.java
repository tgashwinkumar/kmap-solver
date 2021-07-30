package io.github.tgashwinkumar.errors;

public class MismatchedParen extends Exception{
    public MismatchedParen(){
        super(" MISMATCHED PARENTHESES identified: Top of the stack has left parenthesis.");
    }
}
