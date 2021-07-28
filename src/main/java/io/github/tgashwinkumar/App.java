package io.github.tgashwinkumar;

import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.definitions.Token;
import io.github.tgashwinkumar.lexer.Lexer;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        // System.out.println("Hello World!");
        InputArray inputArray = new InputArray('A', 'B');
        Lexer lexer = new Lexer(inputArray, "A + (A*B) + SOP(1)");
        Token tokens[] =  lexer.getTokens();
        System.out.println("Token Length: " + tokens.length + "\n" + "A + (A*B) + SOP(1)" + "\n");
        for(Token t: tokens){
            System.out.println(t);
        }
    }
}
