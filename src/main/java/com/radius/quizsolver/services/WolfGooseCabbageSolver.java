package com.radius.quizsolver.services;

import com.radius.quizsolver.domain.WolfGooseCabaggeSituation;
import com.radius.quizsolver.domain.enums.Pieces;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by gdiaz on 6/18/16.
 */
public class WolfGooseCabbageSolver extends Solver {

    public WolfGooseCabbageSolver(){
        this.tran = new WolfGooseCabbageTransitionManager();
    }

}
