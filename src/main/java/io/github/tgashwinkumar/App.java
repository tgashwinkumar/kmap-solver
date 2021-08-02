package io.github.tgashwinkumar;

import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.kmap.KMap;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        InputArray inputArray = new InputArray('A', 'B', 'C');
        String exprStr = "A+B+C";
        KMap kmap = new KMap(inputArray, exprStr);
        kmap.showKMap();
    }
}
