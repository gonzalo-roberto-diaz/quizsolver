package com.radius.quizsolver.domain;

/**
 * This class represent a (valid)  snapshot of the position of players, pieces, etc.
 * The whole idea of this solver framework, is that a situation generates many other possible situations.
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
