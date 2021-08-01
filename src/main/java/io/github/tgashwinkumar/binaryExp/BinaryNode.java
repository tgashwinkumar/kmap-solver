package io.github.tgashwinkumar.binaryExp;

import io.github.tgashwinkumar.booleanUtils.BooleanUtils;
import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.definitions.TokenType;

public class BinaryNode {

    public BinaryNode leftNode;
    public BinaryNode rightNode;
    public TokenType tokenType;
    public int tokenValue;
    public BinaryNode[] funcArgs = null; 
    private boolean willLog = false;

    public BinaryNode(TokenType ttype) {
        this.tokenType = ttype;
        this.tokenValue = -1;
    }

    public BinaryNode(TokenType ttype, int tvalue) {
        this.tokenType = ttype;
        this.tokenValue = tvalue;
    }

    public BinaryNode(BinaryNode rootNode, BinaryNode[] functionArgs) {
        this.tokenType = rootNode.tokenType;
        this.tokenValue = rootNode.tokenValue;
        this.funcArgs = functionArgs;
    }

    public BinaryNode(BinaryNode rootNode) {
        this.tokenType = rootNode.tokenType;
        this.tokenValue = rootNode.tokenValue;
    }

    public BinaryNode(BinaryNode rootNode, BinaryNode leftNode, BinaryNode rightNode) {
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
        return this.tokenType.name() + "-Token@" + this.tokenValue
                + (this.leftNode != null ? " -> (" + this.leftNode + ")" : "")
                + (this.rightNode != null ? " + (" + this.rightNode + ")" : "")
                + (this.funcArgs != null ? " -> [" + this.funcArgs.length + "]" : "");
    }

    public int getPrecedence() {
        if (this.tokenType == TokenType.AND) {
            return 3;
        } else if (this.tokenType == TokenType.XOR) {
            return 2;
        } else if (this.tokenType == TokenType.OR) {
            return 1;
        }
        return -1;
    }

    public int getBooleanValue(int inputNumber, InputArray inputArr) {
        int leftInt = 0;
        int rightInt = 0;

        if (this.leftNode != null) {
            if (this.leftNode.tokenType != TokenType.INPUT) {
                leftInt = this.leftNode.getBooleanValue(inputNumber, inputArr);
            } else {
                leftInt = BooleanUtils.intToBinaryArray(inputNumber, inputArr.array.length)[this.leftNode.tokenValue];
            }
        }

        if (this.rightNode != null) {
            if (this.rightNode.tokenType != TokenType.INPUT) {
                rightInt = this.rightNode.getBooleanValue(inputNumber, inputArr);
            } else {
                rightInt = BooleanUtils.intToBinaryArray(inputNumber, inputArr.array.length)[this.rightNode.tokenValue];
            }
        }

        if (this.tokenType == TokenType.AND) {
            if (this.willLog)
                System.out.print(leftInt + " * " + rightInt + " = ");
            if (leftInt == 1 && rightInt == 1) {
                return 1;
            } else {
                return 0;
            }
        }

        if (this.tokenType == TokenType.OR) {
            if (this.willLog)
                System.out.print(leftInt + " + " + rightInt + " = ");
            if (leftInt == 0 && rightInt == 0) {
                return 0;
            } else {
                return 1;
            }
        }

        if (this.tokenType == TokenType.NOT) {
            if (this.willLog)
                System.out.print("!" + leftInt + " = ");
            if (leftInt == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        if (this.tokenType == TokenType.XOR) {
            if (this.willLog)
                System.out.print(leftInt + " ^ " + rightInt + " = ");
            if (leftInt == rightInt) {
                return 0;
            } else {
                return 1;
            }
        }

        return 0;
    }
}
