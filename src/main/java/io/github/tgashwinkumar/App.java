package io.github.tgashwinkumar;

import io.github.tgashwinkumar.binaryExp.BinaryNode;
import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.lexer.Lexer;
import io.github.tgashwinkumar.parser.Parser;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        InputArray inputArray = new InputArray('A', 'B');
        String exprStr = "(!A+!B)";
        Lexer lexer = new Lexer(inputArray, exprStr);
        BinaryNode[] tokens = lexer.getTokens();
        Parser pars = new Parser(tokens);
        System.out.println(exprStr);
        BinaryNode finalNode = pars.getFinalTree();
        System.out.println("\n\nFINAL: " + finalNode);
        for(int i = 0; i < 4; i++)
        System.out.println(finalNode.getBooleanValue(i, inputArray));
    }
}
