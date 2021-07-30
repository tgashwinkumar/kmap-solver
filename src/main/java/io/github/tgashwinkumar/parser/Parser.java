package io.github.tgashwinkumar.parser;

import io.github.tgashwinkumar.binaryExp.BinaryNode;
import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.definitions.Position;
import io.github.tgashwinkumar.definitions.TokenType;

public class Parser {

    private BinaryNode[] tokenList;
    private BinaryNode[] operandStack;
    private BinaryNode[] operatorStack;
    private BinaryNode currentToken;
    private Position currentPos = new Position(-1);
    private int insideFunc = 0;
    private BinaryNode[] functionArgs;

    public Parser(BinaryNode[] tokens) {
        this.tokenList = tokens;
    }

    public BinaryNode getFinalTree() {
        this.runParser();
        return this.popFromOperandStack();
    }

    private BinaryNode getCurrentFromOperatorStack() {
        int len = 0;
        if (this.operatorStack != null) {
            len = this.operatorStack.length;
            if (len == 0)
                return null;
            return this.operatorStack[len - 1];
        }
        return null;
    }

    private BinaryNode pushToOperandStack(BinaryNode newToken) {
        int len = 0;
        if (this.operandStack != null) {
            len = this.operandStack.length;
        }
        BinaryNode[] newStack = new BinaryNode[len + 1];
        for (int i = 0; i < len; i++) {
            newStack[i] = this.operandStack[i];
        }
        newStack[len] = newToken;
        this.operandStack = newStack;
        System.out.println("Operand Pushed: " + newToken);
        return newToken;
    }

    private BinaryNode popFromOperandStack() {
        int len = 0;
        if (this.operandStack != null) {
            len = this.operandStack.length;
        }
        if(len == 0){
            return null;
        }
        BinaryNode[] newStack = new BinaryNode[len - 1];
        for (int i = 0; i < len - 1; i++) {
            newStack[i] = this.operandStack[i];
        }
        BinaryNode popElement = this.operandStack[len - 1];
        this.operandStack = newStack;
        System.out.println("Operand Popped: " + popElement);
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
        System.out.println("Operator pushed: " + newToken);
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
        System.out.println("Operator Popped: " + popElement);
        return popElement;
    }

    private BinaryNode pushAsFuncArg(BinaryNode newToken) {
        int len = 0;
        if (this.functionArgs != null) {
            len = this.functionArgs.length;
        }
        BinaryNode[] newList = new BinaryNode[len + 1];
        for (int i = 0; i < len; i++) {
            newList[i] = this.functionArgs[i];
        }
        newList[len] = newToken;
        this.functionArgs = newList;
        System.out.println("Function Arg pushed: " + newToken);
        return this.functionArgs[len];
    }

    private void nextToken() {
        this.currentPos.nextPosition();
        if (this.currentPos.position < this.tokenList.length) {
            this.currentToken = this.tokenList[this.currentPos.position];
            System.out.println("\n\nCurrent Token: " + this.currentToken + "\tIsInsidefunc: " + (this.insideFunc>0));
        } else {
            this.currentToken = null;
        }
    }

    private BinaryNode getBinaryExpression() {
        BinaryNode operator = this.popFromOperatorStack();
        BinaryNode rightNode = this.popFromOperandStack();
        BinaryNode leftNode = this.popFromOperandStack();
        BinaryNode binaryExp = new BinaryNode(operator, leftNode, rightNode);
        System.out.println("BinaryExp created.: " + binaryExp);
        return binaryExp;
    }

    private void runParser() {
        this.nextToken();
        while (this.currentToken != null) {
            if (this.currentToken.tokenType == TokenType.INT) {
                if (this.insideFunc > 0) {
                    this.pushAsFuncArg(this.currentToken);
                    this.nextToken();
                } else {
                    this.pushToOperandStack(this.currentToken);
                    this.nextToken();
                }
            } else if (this.currentToken.tokenType == TokenType.INPUT) {
                if (this.getCurrentFromOperatorStack() != null
                        && this.getCurrentFromOperatorStack().tokenType == TokenType.NOT) {
                    BinaryNode operator = this.popFromOperatorStack();
                    BinaryNode newExp = new BinaryNode(operator, this.currentToken);
                    this.pushToOperandStack(newExp);
                    this.nextToken();
                } else {
                    this.pushToOperandStack(this.currentToken);
                    this.nextToken();
                }
            } else if (this.currentToken.tokenType == TokenType.SOP || this.currentToken.tokenType == TokenType.POS) {
                this.insideFunc += 1;
                this.pushToOperatorStack(this.currentToken);
                this.nextToken();
            } else if (this.currentToken.tokenType == TokenType.NOT) {
                this.pushToOperatorStack(this.currentToken);
                this.nextToken();
            } else if (this.currentToken.tokenType == TokenType.COMMA) {
                this.nextToken();
            } else if (this.currentToken.getPrecedence() > 0) {
                while (this.getCurrentFromOperatorStack() != null
                        && this.getCurrentFromOperatorStack().tokenType != TokenType.LPAREN
                        && this.currentToken.getPrecedence() < this.getCurrentFromOperatorStack().getPrecedence()) {
                    BinaryNode newExp = this.getBinaryExpression();
                    this.pushToOperandStack(newExp);
                    this.nextToken();
                }
                this.pushToOperatorStack(this.currentToken);
                this.nextToken();
            } else if (this.currentToken.tokenType == TokenType.LPAREN) {
                this.pushToOperatorStack(this.currentToken);
                this.nextToken();
            } else if (this.currentToken.tokenType == TokenType.RPAREN) {
                if (this.insideFunc > 0) {
                    if (this.getCurrentFromOperatorStack() != null
                            && this.getCurrentFromOperatorStack().tokenType == TokenType.LPAREN) {
                        this.popFromOperatorStack();
                    }
                    BinaryNode funcExp = new BinaryNode(this.popFromOperatorStack(), this.functionArgs);
                    this.pushToOperandStack(funcExp);
                    this.insideFunc = 0;
                    this.functionArgs = null;
                } else {
                    while (this.getCurrentFromOperatorStack() != null
                            && this.getCurrentFromOperatorStack().tokenType != TokenType.LPAREN) {
                        BinaryNode newExp = this.getBinaryExpression();
                        this.pushToOperandStack(newExp);
                    }
                    System.out.println(this.getCurrentFromOperatorStack());
                    if (this.getCurrentFromOperatorStack() != null
                    && this.getCurrentFromOperatorStack().tokenType == TokenType.LPAREN) {
                        this.popFromOperatorStack();
                    }
                    this.nextToken();
                }
            }
        }

        System.out.println("\n\n\n");

        while (this.operatorStack.length > 0) {
            if (this.getCurrentFromOperatorStack().tokenType == TokenType.LPAREN) {
                // throw new MismatchedParen();
            } else {
                BinaryNode newExp = this.getBinaryExpression();
                this.pushToOperandStack(newExp);
            }
        }
    }
}
