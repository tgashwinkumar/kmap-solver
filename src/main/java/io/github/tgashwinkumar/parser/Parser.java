package io.github.tgashwinkumar.parser;

import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.definitions.NodeType;
import io.github.tgashwinkumar.definitions.Position;
import io.github.tgashwinkumar.definitions.Token;
import io.github.tgashwinkumar.definitions.TokenType;

public class Parser {
    
    private Token[] tokenList;
    private InputArray inputArray;
    private NodeType[] operandStack;
    private Token[] operatorStack;
    private Token currentToken;
    private Position currentPos = new Position(-1);

    public Parser(InputArray inputArr, Token[] tokens){
        this.inputArray = inputArr;
        this.tokenList = tokens;
    }

    private void printStack(NodeType[] stack){
        if(stack == null){
            System.out.printf("No element");
        }else{
            for(NodeType e: stack){
                System.out.printf("%d, ", e);
            }
            System.out.println("");
        }
    }

    private NodeType getCurrentFromOperandStack(){
        int len = 0;
        if (this.operandStack != null) {
            len = this.operandStack.length;
            return this.operandStack[len-1];
        }
        return null;
    }

    private Token getCurrentFromOperatorStack() {
        int len = 0;
        if (this.operatorStack != null) {
            len = this.operatorStack.length;
            return this.operatorStack[len - 1];
        }
        return null;
    }

    private NodeType pushToOperandStack(NodeType newToken){
        int len = 0;
        if (this.operandStack != null) {
            len = this.operandStack.length;
        }
        NodeType[] newStack = new NodeType[len+1];
        for(int i = 0; i < len; i++){
            newStack[i] = this.operandStack[i];
        } 
        newStack[len] = newToken;
        this.operandStack = newStack;
        return newToken;
    }

    private NodeType popFromOperandStack(){
        int len = 0;
        if (this.operandStack != null) {
            len = this.operandStack.length;
        }
        NodeType[] newStack = new NodeType[len - 1];
        for (int i = 0; i < len - 1; i++) {
            newStack[i] = this.operandStack[i];
        }
        NodeType popElement = this.operandStack[len-1];
        this.operandStack = newStack;
        return popElement;
    }

    private Token pushToOperatorStack(Token newToken) {
        int len = 0;
        if (this.operatorStack != null) {
            len = this.operatorStack.length;
        }
        Token[] newStack = new Token[len + 1];
        for (int i = 0; i < len; i++) {
            newStack[i] = this.operatorStack[i];
        }
        newStack[len] = newToken;
        this.operatorStack = newStack;
        return newToken;
    }

    private Token popFromOperatorStack() {
        int len = 0;
        if (this.operatorStack != null) {
            len = this.operatorStack.length;
        }
        Token[] newStack = new Token[len - 1];
        for (int i = 0; i < len - 1; i++) {
            newStack[i] = this.operatorStack[i];
        }
        Token popElement = this.operatorStack[len - 1];
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

    public void runParser(){
        this.nextToken();
        while(this.currentToken != null){
            if(this.currentToken.tokenType == TokenType.INT){
                this.pushToOperandStack(this.currentToken);
                this.nextToken();
            } else if (this.currentToken.tokenType == TokenType.SOP || this.currentToken.tokenType == TokenType.POS){
                this.pushToOperatorStack(this.currentToken);
                this.nextToken();
            } else if (this.currentToken.getPrecedence() > 0) {
                while (this.getCurrentFromOperatorStack().tokenType != TokenType.LPAREN) {
                    
                }
            }
        }
    }
}
