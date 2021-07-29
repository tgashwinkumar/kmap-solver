package io.github.tgashwinkumar.parser;

import io.github.tgashwinkumar.binaryExp.BinaryNode;
import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.definitions.Position;
import io.github.tgashwinkumar.definitions.TokenType;

public class Parser {
    
    private BinaryNode[] tokenList;
    private InputArray inputArray;
    private BinaryNode[] operandStack;
    private BinaryNode[] operatorStack;
    private BinaryNode currentToken;
    private Position currentPos = new Position(-1);

    public Parser(InputArray inputArr, BinaryNode[] tokens){
        this.inputArray = inputArr;
        this.tokenList = tokens;
    }

    private void printStack(BinaryNode[] stack){
        if(stack == null){
            System.out.printf("No element");
        }else{
            for(BinaryNode e: stack){
                System.out.printf("%d, ", e);
            }
            System.out.println("");
        }
    }

    private BinaryNode getCurrentFromOperandStack(){
        int len = 0;
        if (this.operandStack != null) {
            len = this.operandStack.length;
            return this.operandStack[len-1];
        }
        return null;
    }

    private BinaryNode getCurrentFromOperatorStack() {
        int len = 0;
        if (this.operatorStack != null) {
            len = this.operatorStack.length;
            return this.operatorStack[len - 1];
        }
        return null;
    }

    private BinaryNode pushToOperandStack(BinaryNode newToken){
        int len = 0;
        if (this.operandStack != null) {
            len = this.operandStack.length;
        }
        BinaryNode[] newStack = new BinaryNode[len+1];
        for(int i = 0; i < len; i++){
            newStack[i] = this.operandStack[i];
        } 
        newStack[len] = newToken;
        this.operandStack = newStack;
        return newToken;
    }

    private BinaryNode popFromOperandStack(){
        int len = 0;
        if (this.operandStack != null) {
            len = this.operandStack.length;
        }
        BinaryNode[] newStack = new BinaryNode[len - 1];
        for (int i = 0; i < len - 1; i++) {
            newStack[i] = this.operandStack[i];
        }
        BinaryNode popElement = this.operandStack[len-1];
        this.operandStack = newStack;
        return popElement;
    }

    private BinaryNode pushToOperatorStack(BinaryNode newToken) {
        int len = 0;
        if (this.operatorStack != null) {
            len = this.operatorStack.length;
        }
        BinaryNode[] newStack = new BinaryNode[len + 1];
        for (int i = 0; i < len; i++) {
            newStack[i] = this.operatorStack[i];
        }
        newStack[len] = newToken;
        this.operatorStack = newStack;
        return newToken;
    }

    private BinaryNode popFromOperatorStack() {
        int len = 0;
        if (this.operatorStack != null) {
            len = this.operatorStack.length;
        }
        BinaryNode[] newStack = new BinaryNode[len - 1];
        for (int i = 0; i < len - 1; i++) {
            newStack[i] = this.operatorStack[i];
        }
        BinaryNode popElement = this.operatorStack[len - 1];
        this.operatorStack = newStack;
        return popElement;
    }

    private void nextToken(){
        this.currentPos.nextPosition();
        if(this.currentPos.position < this.tokenList.length){
            this.currentToken = this.tokenList[this.currentPos.position];
        }else{
            this.currentToken = null;
        }
    }

    private BinaryNode getBinaryExpression(BinaryNode operator, BinaryNode leftNode, BinaryNode rightNode){
        BinaryNode binaryExp = new BinaryNode(operator, leftNode, rightNode);
        return binaryExp;
    }

    private void runParser(){
        this.nextToken();
        while(this.currentToken != null){
            if(this.currentToken.tokenType == TokenType.INT || this.currentToken.tokenType == TokenType.INPUT){
                this.pushToOperandStack(this.currentToken);
                this.nextToken();
            } else if (this.currentToken.tokenType == TokenType.SOP || this.currentToken.tokenType == TokenType.POS){
                this.pushToOperatorStack(this.currentToken);
                this.nextToken();
            } else if (this.currentToken.getPrecedence() > 0) {
                while (this.getCurrentFromOperatorStack().tokenType != TokenType.LPAREN 
                    && this.currentToken.getPrecedence() <= this.getCurrentFromOperatorStack().getPrecedence()) {
                    BinaryNode operator = this.popFromOperatorStack();
                    BinaryNode rightNode = this.popFromOperandStack();
                    BinaryNode leftNode = this.popFromOperandStack();
                    BinaryNode newExp = this.getBinaryExpression(operator, leftNode, rightNode);
                    this.pushToOperandStack(newExp);
                }
                this.pushToOperatorStack(this.currentToken);
                this.nextToken();
            } else if (this.currentToken.tokenType == TokenType.LPAREN){
                this.pushToOperatorStack(this.currentToken);
                this.nextToken();
            } else if (this.currentToken.tokenType == TokenType.RPAREN) {
                while (this.getCurrentFromOperatorStack().tokenType != TokenType.LPAREN) {
                    BinaryNode operator = this.popFromOperatorStack();
                    BinaryNode rightNode = this.popFromOperandStack();
                    BinaryNode leftNode = this.popFromOperandStack();
                    BinaryNode newExp = this.getBinaryExpression(operator, leftNode, rightNode);
                    this.pushToOperandStack(newExp);
                }
            }
        }
    }
}
