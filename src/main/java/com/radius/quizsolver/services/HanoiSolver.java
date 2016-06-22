package com.radius.quizsolver.services;

import java.util.Optional;

/**
 * Created by gdiaz on 6/18/16.
 */
public class HanoiSolver extends Solver {

    public HanoiSolver(){
        this.tran = new HanoiTransitionManager();
    }

}
