package io.github.tgashwinkumar.definitions;

public class Position {
    public int position = 0;

    public Position(int value){
        this.position = value;
    }

    public void nextPosition(){
        this.position += 1;
    }
}
