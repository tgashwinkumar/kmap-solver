package io.github.tgashwinkumar.definitions;

public class Token implements NodeType{

    public TokenType tokenType;
    public String tokenValue;

    public RETURN_TYPE typeOf() {
        return RETURN_TYPE.TOKEN;
    }

    public Token(TokenType ttype, String tvalue){
        this.tokenType = ttype;
        this.tokenValue = tvalue;
    }

    @Override
    public String toString() {
        return this.tokenType.name() + "-Token@\"" + this.tokenValue + "\"";
    }

    public int getPrecedence(){
        if(this.tokenType == TokenType.AND) {
            return 2;
        } else if (this.tokenType == TokenType.OR){
            return 1;
        }
        return -1;
    }
}
