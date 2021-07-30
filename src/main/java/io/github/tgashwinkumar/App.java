package io.github.tgashwinkumar;

import io.github.tgashwinkumar.binaryExp.BinaryNode;
import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.lexer.Lexer;
import io.github.tgashwinkumar.parser.Parser;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        // System.out.println("Hello World!");
        InputArray inputArray = new InputArray('A', 'B', 'C');
        String exprStr = " POS(4,1,2,3) + A^B + C + (A*B)";
        Lexer lexer = new Lexer(inputArray, exprStr);
        BinaryNode[] tokens = lexer.getTokens();
        Parser pars = new Parser(tokens);
        System.out.println("\n\nFINAL: " + pars.getFinalTree());
    }
}
