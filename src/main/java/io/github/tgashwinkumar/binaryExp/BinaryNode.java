package io.github.tgashwinkumar.binaryExp;

import io.github.tgashwinkumar.definitions.NodeType;
import io.github.tgashwinkumar.definitions.Token;

public class BinaryNode implements NodeType{
    private Token rootNode;
    private Token leftNodeToken;
    private Token rightNodeToken;
    private BinaryNode leftBinaryNode;
    private BinaryNode rightBinaryNode;

    public RETURN_TYPE typeOf() {
        return RETURN_TYPE.BINARY_NODE;
    }

}
