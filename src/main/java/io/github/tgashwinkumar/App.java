package io.github.tgashwinkumar;
import io.github.tgashwinkumar.QuineMcCluskey.Quin;
import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.getArray.GetArray;

public final class App {
    public static void main(String[] args) {
        InputArray inputArray = new InputArray('A', 'B', 'C');
        String exprStr = "A + B * C";
        // KMap kmap = new KMap(inputArray, exprStr);
        // kmap.showKMap();
        int[] sopArray = GetArray.exprToSOP(inputArray, exprStr);
        Quin q = new Quin();
        q.showSimplification(sopArray);
    }
}
