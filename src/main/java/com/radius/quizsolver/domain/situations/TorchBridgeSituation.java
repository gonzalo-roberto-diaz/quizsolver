package com.radius.quizsolver.domain.situations;

import com.radius.quizsolver.domain.enums.TorchPeople;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gdiaz on 6/18/16.
 */
public class TorchBridgeSituation extends Situation {
    public Set<TorchPeople> leftBank;
    public Set<TorchPeople> rightBank;

    @Override
    public boolean isWinning() {
        return rightBank.containsAll(Arrays.asList(TorchPeople.values()));
    }

    @Override
    public boolean isValid() {
        return true;
    }


    @Override
    public Object clone() {
        TorchBridgeSituation sit = new TorchBridgeSituation();
        sit.leftBank = new HashSet<>();
        sit.rightBank = new HashSet<>();
        this.leftBank.forEach(p -> sit.leftBank.add(p));
        this.rightBank.forEach(p -> sit.rightBank.add(p));
        return sit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        TorchPeople[] leftArr = this.leftBank.toArray(new TorchPeople[]{});
        TorchPeople[] rightArr = this.rightBank.toArray(new TorchPeople[]{});
        Arrays.sort(leftArr);
        Arrays.sort(rightArr);
        sb.append("left=").append(Arrays.toString(leftArr));
        sb.append("right=").append(Arrays.toString(rightArr));
        return sb.toString();
    }

    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object situation){
        if (situation == null){
            return false;
        }else if (!(situation instanceof TorchBridgeSituation)){
            return false;
        }else{
            TorchBridgeSituation other  = (TorchBridgeSituation)situation;
            String otherString = other.toString();
            String thisString = this.toString();
            return otherString.equals(thisString);
        }
    }



}