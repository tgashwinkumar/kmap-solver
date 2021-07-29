package io.github.tgashwinkumar.lexer;

import io.github.tgashwinkumar.binaryExp.BinaryNode;
import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.definitions.Position;
import io.github.tgashwinkumar.definitions.TokenType;

public class Lexer {

    private InputArray inputArray;
    private String expressionStr;
    private Position currentPos = new Position(-1);
    private char currentToken = '\u0000';
    private BinaryNode[] tokenList;


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

    private void addTokenToList(BinaryNode newToken){
        int length;
        if(this.tokenList == null){
            length = 0;
        }else{
            length = this.tokenList.length;
        }
        BinaryNode newList[] = new BinaryNode[length+1];
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

    private String fetchNumber(){
        String number = "";
        while(Character.isDigit(this.currentToken)){
            number += this.currentToken;
            this.nextToken();
        }
        return number;
    }

    private void runLexer() {
        this.nextToken();
        while (this.currentToken != '\u0000') {
            if ("\n\t ;\"".indexOf(this.currentToken) != -1){
                this.nextToken();
            } else if (this.currentToken == '*') {
                BinaryNode andToken = new BinaryNode(TokenType.AND, Character.toString(this.currentToken));
                this.addTokenToList(andToken);
                this.nextToken();
            } else if (this.currentToken == '+') {
                BinaryNode orToken = new BinaryNode(TokenType.OR, Character.toString(this.currentToken));
                this.addTokenToList(orToken);
                this.nextToken();
            } else if(this.currentToken == ',') {
                BinaryNode commaToken = new BinaryNode(TokenType.COMMA, Character.toString(this.currentToken));
                this.addTokenToList(commaToken);
                this.nextToken();
            } else if (this.currentToken == '(') {
                BinaryNode lparenToken = new BinaryNode(TokenType.LPAREN, Character.toString(this.currentToken));
                this.addTokenToList(lparenToken);
                this.nextToken();
            } else if (this.currentToken == ')') {
                BinaryNode rparenToken = new BinaryNode(TokenType.RPAREN, Character.toString(this.currentToken));
                this.addTokenToList(rparenToken);
                this.nextToken();
            } else if (this.currentToken == '!') {
                BinaryNode notToken = new BinaryNode(TokenType.NOT, Character.toString(this.currentToken));
                this.addTokenToList(notToken);
                this.nextToken();
            } else if (new String(this.inputArray.array).indexOf(this.currentToken) != -1) {
                BinaryNode inputToken = new BinaryNode(TokenType.INPUT, Character.toString(this.currentToken));
                this.addTokenToList(inputToken);
                this.nextToken();
            } else if (Character.isLetter(this.currentToken)){
                String word = this.fetchWord();
                if(word.equalsIgnoreCase("sop")){
                    BinaryNode sopToken = new BinaryNode(TokenType.SOP, "sop");
                    this.addTokenToList(sopToken);
                } else if (word.equalsIgnoreCase("pos")){
                    BinaryNode posToken = new BinaryNode(TokenType.POS, "pos");
                    this.addTokenToList(posToken);
                }
            } else if(Character.isDigit(this.currentToken)) {
                String number = this.fetchNumber();
                BinaryNode numToken = new BinaryNode(TokenType.INT, number);
                this.addTokenToList(numToken);
            }
        }
    }
    
    public BinaryNode[] getTokens(){
        this.runLexer();
        return this.tokenList;
    }

}
