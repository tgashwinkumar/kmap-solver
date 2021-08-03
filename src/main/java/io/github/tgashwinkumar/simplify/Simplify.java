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
    private int[] sopList;
    private int sopLen = 0;

    public Simplify(InputArray inputArr, String exprStr) {
        this.inputArray = inputArr;
        this.expressionString = exprStr;
        this.inputLen = this.inputArray.array.length;
        this.combLen = (int) Math.pow(2, inputLen);
        this.sopList = new int[this.combLen];
        this.evaluateExpression();
    }

    private void evaluateExpression() {
        Lexer lexer = new Lexer(this.inputArray, this.expressionString);
        BinaryNode[] tokens = lexer.getTokens();
        Parser pars = new Parser(tokens);
        BinaryNode finalNode = pars.getFinalTree();
        System.out.println(finalNode);
        for (int i = 0; i < combLen; i++) {
            if(finalNode.getBooleanValue(i, this.inputArray) == 1){
                this.sopList[this.sopLen] = i;
                this.sopLen += 1;
            }
        }
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

    public void simplify(){

        System.out.println(Arrays.toString(this.sopList));

        int[] tempList = new int[this.sopLen];
        for(int i = 0; i < this.sopLen; i++){
            tempList[i] = this.sopList[i];
        }
        this.sopList = tempList;
        System.out.println(Arrays.toString(this.sopList));

        for(int i: this.sopList){
            int[] temp = {i};
            this.binaryData.put(temp, BooleanUtils.intToBinaryArray(i, this.inputLen));
        }

        for(Map.Entry<int[], int[]> mp: this.binaryData.entrySet()){
            System.out.println(Arrays.toString(mp.getKey()) + ": " + Arrays.toString(mp.getValue()));
        }
        System.out.println("\n\n\n");

        for(int i = 0; i < 2; i++){
            HashMap<int[], int[]> tempData = new HashMap<>();
            for (Map.Entry<int[], int[]> mp1 : this.binaryData.entrySet()) {
                for (Map.Entry<int[], int[]> mp2 : this.binaryData.entrySet()) {
                    if (mp1 == mp2)
                        continue;
                    int val = this.differByOnePlace(mp1.getKey(), mp2.getKey());
                    if (val == -1)
                        continue;
                    int temp[] = mp1.getValue();
                    temp[val] = -1;
                    tempData.put(mergeArray(mp1.getKey(), mp2.getKey()), temp);
                }
            }
            this.binaryData = tempData;
            for (Map.Entry<int[], int[]> mp : this.binaryData.entrySet()) {
                System.out.println(Arrays.toString(mp.getKey()) + ": " + Arrays.toString(mp.getValue()));
            }
            System.out.println("\n\n\n");
        }
    }

    private int[] mergeArray(int[] arr1, int[] arr2){
        int[] result = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        Arrays.sort(result);
        return result;
    }


}
