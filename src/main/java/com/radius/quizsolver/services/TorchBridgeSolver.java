package com.radius.quizsolver.services;

import com.radius.quizsolver.domain.TorchBridgeSituation;
import com.radius.quizsolver.domain.enums.TorchPeople;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * Created by gdiaz on 6/18/16.
 */
public class TorchBridgeSolver extends Solver {

    public TorchBridgeSolver(){
        this.tran = new TorchBridgeTransitionManager();
        this.maximumCost = Optional.of(15.0);
    }

}
