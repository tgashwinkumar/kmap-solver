package io.github.tgashwinkumar.definitions;

public class Token {

    public TokenType tokenType;
    public String tokenValue;

    public Token(TokenType ttype, String tvalue){
        this.tokenType = ttype;
        this.tokenValue = tvalue;
    }

    @Override
    public String toString() {
        return this.tokenType.name() + "-Token@\"" + this.tokenValue + "\"";
    }
}
