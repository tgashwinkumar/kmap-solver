package io.github.tgashwinkumar.parser;

import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.definitions.Token;

public class Parser {
    
    private Token[] tokenList;
    private InputArray inputArray;
    private Object[] operandStack;
    private Object[] operatorStack;


    public Parser(InputArray inputArr, Token[] tokens){
        this.inputArray = inputArr;
        this.tokenList = tokens;
    }

    private void printStack(Object[] stack){
        if(stack == null){
            System.out.printf("No element");
        }else{
            for(Object e: stack){
                System.out.printf("%d, ", e);
            }
            System.out.println("");
        }
    }

    private Object pushToOperandStack(Object newToken){
        int len = 0;
        if (this.operandStack != null) {
            len = this.operandStack.length;
        }
        Object[] newStack = new Object[len+1];
        for(int i = 0; i < len; i++){
            newStack[i] = this.operandStack[i];
        } 
        newStack[len] = newToken;
        this.operandStack = newStack;
        return newToken;
    }

    private Object popFromOperandStack(){
        int len = 0;
        if (this.operandStack != null) {
            len = this.operandStack.length;
        }
        Object[] newStack = new Object[len - 1];
        for (int i = 0; i < len - 1; i++) {
            newStack[i] = this.operandStack[i];
        }
        Object popElement = this.operandStack[len-1];
        this.operandStack = newStack;
        return popElement;
    }

    private Object pushToOperatorStack(Object newToken) {
        int len = 0;
        if (this.operatorStack != null) {
            len = this.operatorStack.length;
        }
        Object[] newStack = new Object[len + 1];
        for (int i = 0; i < len; i++) {
            newStack[i] = this.operatorStack[i];
        }
        newStack[len] = newToken;
        this.operatorStack = newStack;
        return newToken;
    }

    private Object popFromOperatorStack() {
        int len = 0;
        if (this.operatorStack != null) {
            len = this.operatorStack.length;
        }
        Object[] newStack = new Object[len - 1];
        for (int i = 0; i < len - 1; i++) {
            newStack[i] = this.operatorStack[i];
        }
        Object popElement = this.operatorStack[len - 1];
        this.operatorStack = newStack;
        return popElement;
    }

    public void runParser(){
        
    }
}
