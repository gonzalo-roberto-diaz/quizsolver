package com.radius.quizsolver.domain.enums;

/**
 * Created by gdiaz on 6/18/16.
 */
public enum Pieces {
    FARMER, WOLF, GOOSE, CABBAGE;

    public boolean eats(Pieces two){
        switch (this){
            case WOLF:
                return two == GOOSE? true: false;
            case GOOSE:
                return two == CABBAGE?   true: false;
            default:
                return false;
        }
    }
}
