package com.radius.quizsolver.domain.situations;

/**
 * This class represent a (valid)  snapshot of the position of players, pieces, etc.
 * The whole idea of this solver framework, is that a situation generates many other possible situations.
 */
public abstract class Situation implements Cloneable {
    public Situation parent;

    private double cost;

    public double getHistoryCost() {
        return historyCost;
    }

    public void setHistoryCost(double historyCost) {
        this.historyCost = historyCost;
    }

    private double historyCost;

    public abstract boolean isWinning();

    public abstract boolean isValid();

    public abstract Object clone();

    public abstract String toString();

    public abstract int hashCode();

    public abstract boolean equals(Object situation);


    public double getCost() {
        return cost;
    }


    public void setCost(double cost) {
        this.cost = cost;
    }
}
