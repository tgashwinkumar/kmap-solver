package io.github.tgashwinkumar.binaryExp;

import io.github.tgashwinkumar.definitions.TokenType;

public class BinaryNode{
    private BinaryNode leftNode;
    private BinaryNode rightNode;
    public TokenType tokenType;
    public String tokenValue;

    public BinaryNode(TokenType ttype, String tvalue){
        this.tokenType = ttype;
        this.tokenValue = tvalue;
    }

    public BinaryNode(BinaryNode operator){
        this.tokenType = operator.tokenType;
        this.tokenValue = operator.tokenValue;
    }

    public BinaryNode(BinaryNode operator, BinaryNode leftNode, BinaryNode rightNode){
        this.tokenType = operator.tokenType;
        this.tokenValue = operator.tokenValue;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public BinaryNode(BinaryNode operator, BinaryNode leftNode) {
        this.tokenType = operator.tokenType;
        this.tokenValue = operator.tokenValue;
        this.leftNode = leftNode;
    }

    @Override
    public String toString() {
        return this.tokenType.name() + "-Token@\"" + this.tokenValue + "\"";
    }

    public int getPrecedence() {
        if (this.tokenType == TokenType.AND) {
            return 2;
        } else if (this.tokenType == TokenType.OR) {
            return 1;
        }
        return -1;
    }
}
