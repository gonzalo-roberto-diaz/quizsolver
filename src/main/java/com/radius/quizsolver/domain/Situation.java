package com.radius.quizsolver.domain;

/**
 * Created by gdiaz on 6/19/16.
 */
public abstract class Situation implements Cloneable {
    public Situation parent;

    public abstract boolean isWinning();

    public abstract boolean isValid();

    public abstract Object clone();

    public abstract String toString();

    public abstract int hashCode();

    public abstract boolean equals(Object situation);

    public abstract double getCost();

    public abstract void setCost(double value);
}
