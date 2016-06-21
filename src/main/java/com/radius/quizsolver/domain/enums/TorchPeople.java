package com.radius.quizsolver.domain.enums;

/**
 * Created by gdiaz on 6/19/16.
 */
public enum TorchPeople {
    LAMP(0), P1(1), P2(2), P5(5), P8(8);

    TorchPeople(int minutes){
        this.minutes = minutes;
    }

    private int minutes;

    public int getMinutes(){
        return this.minutes;
    }
}
