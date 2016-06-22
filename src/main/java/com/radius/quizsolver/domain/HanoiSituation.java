package com.radius.quizsolver.domain;

import com.google.common.collect.Ordering;
import com.radius.quizsolver.domain.enums.TorchPeople;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Created by gdiaz on 6/21/16.
 */
public class HanoiSituation extends  Situation{

    private double cost;

    private Stack<Integer> pins[] = new Stack[3];

    public Stack<Integer> getPin(int index) {
        return this.pins[index];
    }

    public void setPin(int index,  Stack<Integer> pin) {
        this.pins[index] = pin;
    }

    public int getAmountOfPieces() {
        return amountOfPieces;
    }

    public void setAmountOfPieces(int amountOfPieces) {
        this.amountOfPieces = amountOfPieces;
    }

    private int amountOfPieces;

    public HanoiSituation(){
        this(0);
    }

    public HanoiSituation(int amountOfPieces){
        for (int i=0; i<3; i++){
            this.pins[i] = new Stack<>();
        }
        this.amountOfPieces = amountOfPieces;
        IntStream.range(0,amountOfPieces).forEach(val ->  pins[0].add(amountOfPieces - val));
    }


    @Override
    public boolean isWinning() {
        return pins[2].size() == this.amountOfPieces;
    }

    @Override
    public boolean isValid() {
        for (Stack<Integer> pin: this.pins){
            if  (!Ordering.natural().reverse().isOrdered(pin)){
                return false;
            }
        }
        return true;
    }

    @Override
    public Object clone() {
        HanoiSituation sit = new HanoiSituation(0);
        for (int i=0; i<3; i++){
            transferStack(this.getPin(i), sit.getPin(i));
        }
        sit.amountOfPieces = this.amountOfPieces;
        return sit;
    }


    private void transferStack(Stack<Integer> origin, Stack<Integer> destination){
        List<Integer> temp = new ArrayList<>(origin);
        for (int i=0; i<temp.size(); i++){
            destination.push(temp.get(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<3; i++){
            List<Integer> lstPin = new ArrayList<>(this.getPin(i));
            sb.append("pin" + i + "=").append(Arrays.toString(lstPin.toArray()));
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object situation) {
        if (situation == null){
            return false;
        }else if (!(situation instanceof HanoiSituation )){
            return false;
        }else{
            HanoiSituation sit = (HanoiSituation) situation;
            if (sit.getAmountOfPieces() != this.getAmountOfPieces()){
                return false;
            }
            for (int i=0; i<3; i++){
                if (!sit.getPin(i).equals(this.getPin(i))){
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }
}
