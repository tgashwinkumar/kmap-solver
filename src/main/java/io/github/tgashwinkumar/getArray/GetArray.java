package io.github.tgashwinkumar.getArray;

import java.util.HashMap;

import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.truthtable.Truthtable;

public class GetArray {
    public static int[] exprToSOP(InputArray inputArr, String exprStr) {
        Truthtable table = new Truthtable(inputArr, exprStr);
        int length = table.tableLength;
        HashMap<Integer, Integer> tableMap = table.tableMap;
        int arrLen = 0;
        for(int i = 0; i < length; i++){
            if(tableMap.get(i) == 1){
                arrLen += 1;
            }
        }
        int[] array = new int[arrLen];
        int count = 0;
        for(int i = 0; i < length; i++){
            if(tableMap.get(i) == 1){
                array[count] = i;
                count += 1;
            }
        }
        return array;
    }
}
