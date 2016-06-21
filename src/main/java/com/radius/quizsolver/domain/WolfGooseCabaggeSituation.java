package com.radius.quizsolver.domain;

import com.radius.quizsolver.domain.enums.Pieces;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by gdiaz on 6/18/16.
 */
public class WolfGooseCabaggeSituation extends Situation {
    public Set<Pieces> leftBank;
    public Set<Pieces> rightBank;

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }

    private double cost;

    @Override
    public boolean isWinning() {
        return rightBank.containsAll(Arrays.asList(Pieces.values()));
    }

    @Override
    public boolean isValid() {
        boolean leftValid = isBankValid(leftBank);
        boolean rightValid = isBankValid(rightBank);
        return leftValid && rightValid;
    }

    private boolean isBankValid(Set<Pieces> pieces) {
        boolean valid = true;
        if (pieces.contains(Pieces.FARMER)){
            return true;
        }
        for (Iterator<Pieces> it = pieces.iterator(); it.hasNext(); ) {
            Set<Pieces> minusThis = new HashSet<Pieces>(pieces);
            Pieces thisPiece = it.next();
            minusThis.remove(thisPiece);
            for (Iterator<Pieces> rest = minusThis.iterator(); rest.hasNext(); ) {
                Pieces oneOfrest = rest.next();
                if (thisPiece.eats(oneOfrest)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Object clone() {
        WolfGooseCabaggeSituation sit = new WolfGooseCabaggeSituation();
        sit.leftBank = new HashSet<>();
        sit.rightBank = new HashSet<>();
        this.leftBank.forEach(p -> sit.leftBank.add(p));
        this.rightBank.forEach(p -> sit.rightBank.add(p));
        return sit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Pieces[] leftArr = this.leftBank.toArray(new Pieces[]{});
        Pieces[] rightArr = this.rightBank.toArray(new Pieces[]{});
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
        }else if (!(situation instanceof WolfGooseCabaggeSituation)){
            return false;
        }else{
            WolfGooseCabaggeSituation other  = (WolfGooseCabaggeSituation)situation;
            String otherString = other.toString();
            String thisString = this.toString();
            return otherString.equals(thisString);
        }
    }

}