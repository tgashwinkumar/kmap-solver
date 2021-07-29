package io.github.tgashwinkumar.binaryExp;

import io.github.tgashwinkumar.definitions.TokenType;

public class BinaryNode{
    public BinaryNode leftNode;
    public BinaryNode rightNode;
    public TokenType tokenType;
    public String tokenValue;

    /*
    * Has multiple constructors. USed as Tokenizing class.
    */
    public BinaryNode(TokenType ttype, String tvalue){
        this.tokenType = ttype;
        this.tokenValue = tvalue;
    }

    public BinaryNode(BinaryNode rootNode){
        this.tokenType = rootNode.tokenType;
        this.tokenValue = rootNode.tokenValue;
    }

    public BinaryNode(BinaryNode rootNode, BinaryNode leftNode, BinaryNode rightNode){
        this.tokenType = rootNode.tokenType;
        this.tokenValue = rootNode.tokenValue;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public BinaryNode(BinaryNode rootNode, BinaryNode leftNode) {
        this.tokenType = rootNode.tokenType;
        this.tokenValue = rootNode.tokenValue;
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
