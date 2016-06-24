package com.radius.quizsolver.services.solvers;

import com.radius.quizsolver.services.transitionmanagers.HanoiTransitionManager;

/**
 * Created by gdiaz on 6/18/16.
 * This Solver implementation finds a solution to the "Hanoi Towers" quiz, for 3 rods and a configurable number of discs.
 */
public class HanoiSolver extends Solver {

    public HanoiSolver(){
        this.tran = new HanoiTransitionManager();
    }

}
