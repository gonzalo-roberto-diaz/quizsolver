package com.radius.quizsolver.services.solvers;

import com.radius.quizsolver.services.transitionmanagers.WolfGooseCabbageTransitionManager;

/**
 * Created by gdiaz on 6/18/16.
 * This solver finds a solution for the quiz known as "the Farmer, the Goose, the Wolf (or fox), and the Cabbage (or bag of beans).
 * The farmer has a boat that can fit only himself and one other item at a time. In presence of the farmer there is no
 * problem, but left alone the goose would eat the cabbage, or the wolf would it the goose.
 * Find the minimum sequence of boat trips from one river bank to the other, that the farmer needs to safely cross all items.
 */
public class WolfGooseCabbageSolver extends Solver {

    public WolfGooseCabbageSolver(){
        this.tran = new WolfGooseCabbageTransitionManager();
    }

}
