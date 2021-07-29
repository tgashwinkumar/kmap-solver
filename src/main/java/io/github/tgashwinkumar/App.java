package io.github.tgashwinkumar;

import io.github.tgashwinkumar.binaryExp.BinaryNode;
import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.lexer.Lexer;
import io.github.tgashwinkumar.parser.Parser;

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
        String exprStr = "A + (A*B) + SOP(1,2,3)";
        Lexer lexer = new Lexer(inputArray, exprStr);
        BinaryNode tokens[] =  lexer.getTokens();
        Parser pars = new Parser(inputArray, tokens);
        // pars.runParser();
    }
}
