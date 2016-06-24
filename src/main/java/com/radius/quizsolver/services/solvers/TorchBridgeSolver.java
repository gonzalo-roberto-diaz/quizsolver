package com.radius.quizsolver.services.solvers;

import com.radius.quizsolver.services.transitionmanagers.TorchBridgeTransitionManager;

import java.util.Optional;

/**
 * Created by gdiaz on 6/18/16.
 * This solver finds the most efficient solution for the following quiz:
 * A group of four people can walk at different speeds, and they have to cross a bridge at night, that can support
 * at most 2 poeple at a time. They have one torch (lamp) that has to be used on every crossing.
 * The speed of any group of people crossing is determined by the slowest person.
 * The 4 people can cross the bridge in 1, 2, 5 and 8 minutes respectively. Find a combination of trips that gets all
 * persons from one end of the bridge to the other in up to 15 minutes.
 */
public class TorchBridgeSolver extends Solver {

    public TorchBridgeSolver(){
        this.tran = new TorchBridgeTransitionManager();
        this.maximumCost = Optional.of(15.0);
    }

}
