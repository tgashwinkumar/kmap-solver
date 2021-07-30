package io.github.tgashwinkumar.booleanUtils;

public class BooleanUtils {

    public static int log2(int number){
        return (int)(Math.log(number)/Math.log(2));
    }

    public static int[] intToBinaryArray(int number, int noOfInputs){
        if(Math.pow(2, noOfInputs) <= number){
            return null;
        }
        int[] array = new int[noOfInputs];
        for(int i = noOfInputs-1; i >= 0; i--){
            array[i] = number%2;
            number /= 2;
        }
        return array;
    }
}
