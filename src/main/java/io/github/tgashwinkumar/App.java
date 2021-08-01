package io.github.tgashwinkumar;

import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.truthtable.Truthtable;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        InputArray inputArray = new InputArray('A', 'B', 'C');
        String exprStr = "A*B+C*A";
        Truthtable truthTable = new Truthtable(inputArray, exprStr);
        truthTable.showTable();
    }
}
