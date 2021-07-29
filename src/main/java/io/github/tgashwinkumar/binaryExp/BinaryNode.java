package io.github.tgashwinkumar.binaryExp;

import io.github.tgashwinkumar.definitions.NodeType;
import io.github.tgashwinkumar.definitions.Token;

public class BinaryNode implements NodeType{
    private Token rootNode;
    private Token leftNodeToken;
    private Token rightNodeToken;
    private BinaryNode leftBinaryNode;
    private BinaryNode rightBinaryNode;

    public NODE_TYPE typeOf() {
        return NODE_TYPE.BINARY_NODE;
    }

    public NodeType getLeftNode(){
        if(this.leftNodeToken != null){
            return this.leftNodeToken;
        } else {
            return this.leftBinaryNode;
        } 
    }

    public Token setLeftNode(Token leftNode) {
        if(leftNode.typeOf() == NODE_TYPE.TOKEN){
            this.leftNodeToken = leftNode;
            this.leftBinaryNode = null;
        }
        return this.leftNodeToken;
    }

    public BinaryNode setLeftNode(BinaryNode leftNode){
        if (leftNode.typeOf() == NODE_TYPE.TOKEN) {
            this.leftNodeToken = null;
            this.leftBinaryNode = leftNode;
        }
        return this.leftBinaryNode;
    }

    public NodeType getRightNode() {
        if (this.rightNodeToken != null) {
            return this.rightNodeToken;
        } else {
            return this.rightBinaryNode;
        }
    }

    public Token setRightNode(Token rightNode) {
        if (rightNode.typeOf() == NODE_TYPE.TOKEN) {
            this.rightNodeToken = rightNode;
            this.rightBinaryNode = null;
        }
        return this.rightNodeToken;
    }

    public BinaryNode setRightNode(BinaryNode rightNode) {
        if (rightNode.typeOf() == NODE_TYPE.TOKEN) {
            this.rightNodeToken = null;
            this.rightBinaryNode = rightNode;
        }
        return this.rightBinaryNode;
    }

}
