package io.github.tgashwinkumar.simplify;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.github.tgashwinkumar.binaryExp.BinaryNode;
import io.github.tgashwinkumar.booleanUtils.BooleanUtils;
import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.lexer.Lexer;
import io.github.tgashwinkumar.parser.Parser;

public class Simplify {

    private InputArray inputArray;
    private String expressionString;
    private int inputLen;
    private int combLen;
    private HashMap<int[], int[]> binaryData = new HashMap<>();
    public HashMap<Integer, Integer> tableMap = new HashMap<>();

    public Simplify(InputArray inputArr, String exprStr) {
        this.inputArray = inputArr;
        this.expressionString = exprStr;
        this.inputLen = this.inputArray.array.length;
        this.combLen = (int) Math.pow(2, inputLen);
    }

    private void evaluateExpression() {
        Lexer lexer = new Lexer(this.inputArray, this.expressionString);
        BinaryNode[] tokens = lexer.getTokens();
        Parser pars = new Parser(tokens);
        BinaryNode finalNode = pars.getFinalTree();
        System.out.println(finalNode);
        for (int i = 0; i < combLen; i++) {
            this.tableMap.put(i, finalNode.getBooleanValue(i, inputArray));
        }
    }

    private int getOnesCount(int number){
        int arr[] = BooleanUtils.intToBinaryArray(number, this.inputLen);
        int count = 0;
        for(int i: arr){
            if(i == 1) count += 1;
        }
        return count;
    }

    private int differByOnePlace(int[] numA, int[] numB){
        int arrA[] = this.binaryData.get(numA);
        int arrB[] = this.binaryData.get(numB);
        int sumA = 0;
        int sumB = 0;
        int count = 0;
        for(int i = 0; i < this.inputLen; i++){
            if(arrA[i] == arrB[i]) count += 1;
            sumA += (int)Math.pow(2, arrA[i]);
            sumB += (int)Math.pow(2, arrB[i]);
        }
        if(count != this.inputLen-1){
            return -1;
        }else{
            return BooleanUtils.log2(Math.abs(sumA - sumB));
        }
    }

    private void simplify(){
        for(int i = 0; i < this.combLen; i++){
            int[] temp = {i};
            this.binaryData.put(temp, BooleanUtils.intToBinaryArray(i, this.inputLen));
        }
        this.convertDC(0);
    }

    private int[] mergeArray(int[] arr1, int[] arr2){
        int[] result = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        Arrays.sort(result);
        return result;
    }

    private void convertDC(int time){
        if(time >= this.inputLen) return;
        HashMap<int[], int[]> tempData = new HashMap<>();
        for(Map.Entry<int[], int[]>  mp1: this.binaryData.entrySet()){
            for(Map.Entry<int[], int[]> mp2: this.binaryData.entrySet()){
                if(mp1 == mp2) continue;
                int val = this.differByOnePlace(mp1.getKey(), mp2.getKey());
                if(val == -1) continue;
                int temp[] = mp1.getValue();
                temp[val] = -1;
                tempData.put(mergeArray(mp1.getKey(), mp2.getKey()), temp);
            }
        }
        this.binaryData = tempData;
        convertDC(time+1);
    }
}
