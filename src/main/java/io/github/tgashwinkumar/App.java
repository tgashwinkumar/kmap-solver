package io.github.tgashwinkumar;

import java.util.HashMap;
import java.util.Map;

import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.kmap.KMap;
import io.github.tgashwinkumar.simplify.Simplify;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        InputArray inputArray = new InputArray('A', 'B', 'C', 'D');
        String exprStr = "SOP(2,6,8,9,10,11,14,15)";
        Simplify simp = new Simplify(inputArray, exprStr);
        simp.simplify();
    }
}
