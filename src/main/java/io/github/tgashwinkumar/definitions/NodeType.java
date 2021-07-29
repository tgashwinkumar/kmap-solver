package io.github.tgashwinkumar.definitions;

public interface NodeType {
    public static enum RETURN_TYPE{
        TOKEN, BINARY_NODE;
    }
    public RETURN_TYPE typeOf();
}
