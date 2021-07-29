package io.github.tgashwinkumar.definitions;

public interface NodeType {
    public static enum NODE_TYPE{
        TOKEN, BINARY_NODE;
    }
    public NODE_TYPE typeOf();
}
