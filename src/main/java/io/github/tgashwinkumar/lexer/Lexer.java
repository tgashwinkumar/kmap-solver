package io.github.tgashwinkumar.lexer;

import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.definitions.Position;
import io.github.tgashwinkumar.definitions.Token;
import io.github.tgashwinkumar.definitions.TokenType;

public class Lexer {

    private InputArray inputArray;
    private String expressionStr;
    private Position currentPos = new Position(-1);
    private char currentToken = '\u0000';
    private Token[] tokenList;


    public Lexer(InputArray inputArray, String expressionStr){
        this.inputArray = inputArray;
        this.expressionStr = expressionStr;
    }

    private void nextToken(){
        this.currentPos.nextPosition();
        if(this.currentPos.position >= this.expressionStr.length()){
            this.currentToken = '\u0000';
        }else{
            this.currentToken = this.expressionStr.charAt(this.currentPos.position);
        }
    }

    private void addTokenToList(Token newToken){
        int length;
        if(this.tokenList == null){
            length = 0;
        }else{
            length = this.tokenList.length;
        }
        Token newList[] = new Token[length+1];
        for(int i = 0; i < length; i++){
            newList[i] = this.tokenList[i];
        }
        newList[length] = newToken;
        this.tokenList = newList;
    }

    private String fetchWord(){
        String word = "";
        while(Character.isLetter(this.currentToken)){
            word += this.currentToken;
            this.nextToken();
        }
        return word;
    }

    private void runLexer() {
        this.nextToken();
        while (this.currentToken != '\u0000') {
            if ("\n\t ,;\"".indexOf(this.currentToken) != -1){
                this.nextToken();
            } else if (this.currentToken == '*') {
                Token andToken = new Token(TokenType.AND, Character.toString(this.currentToken));
                this.addTokenToList(andToken);
                this.nextToken();
            } else if (this.currentToken == '+') {
                Token orToken = new Token(TokenType.OR, Character.toString(this.currentToken));
                this.addTokenToList(orToken);
                this.nextToken();
            } else if (this.currentToken == '(') {
                Token lparenToken = new Token(TokenType.LPAREN, Character.toString(this.currentToken));
                this.addTokenToList(lparenToken);
                this.nextToken();
            } else if (this.currentToken == ')') {
                Token rparenToken = new Token(TokenType.RPAREN, Character.toString(this.currentToken));
                this.addTokenToList(rparenToken);
                this.nextToken();
            } else if (this.currentToken == '1' || this.currentToken == 'T' || this.currentToken == 't') {
                Token trueToken = new Token(TokenType.TRUE, Character.toString('1'));
                this.addTokenToList(trueToken);
                this.nextToken();
            } else if (this.currentToken == '0' || this.currentToken == 'F' || this.currentToken == 'f') {
                Token falseToken = new Token(TokenType.FALSE, Character.toString('0'));
                this.addTokenToList(falseToken);
                this.nextToken();
            } else if (new String(this.inputArray.array).indexOf(this.currentToken) != -1) {
                Token inputToken = new Token(TokenType.INPUT, Character.toString(this.currentToken));
                this.addTokenToList(inputToken);
                this.nextToken();
            } else if (Character.isLetter(this.currentToken)){
                String word = this.fetchWord();
                if(word.toLowerCase() == "sop"){
                    Token sopToken = new Token(TokenType.SOP, "sop");
                    this.addTokenToList(sopToken);
                } else if (word.toLowerCase() == "pos"){
                    Token posToken = new Token(TokenType.POS, "pos");
                    this.addTokenToList(posToken);
                }
            }

            //Shifaa do this work eeeeeeeh
        }
    }
    
    public Token[] getTokens(){
        this.runLexer();
        return this.tokenList;
    }

}
