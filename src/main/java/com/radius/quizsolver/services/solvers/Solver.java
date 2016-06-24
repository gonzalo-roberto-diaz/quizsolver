package com.radius.quizsolver.services.solvers;

import com.radius.quizsolver.domain.situations.Situation;
import com.radius.quizsolver.services.transitionmanagers.TransitionManager;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by gdiaz on 6/19/16.
 * The main class of this solving framework. It consists of basically a single, recursive method that, for every source situation, evaluates
 * all possible derivate situations, and identifies it any of them is a "winning" situation.
 */
public abstract class Solver  <S extends Situation,  T extends TransitionManager<S>>{

    public T getTran() {
        return tran;
    }

    protected T tran;
    protected Optional<List<S>> winnerPath = Optional.empty();

    protected Optional<Double> maximumCost = Optional.empty();

    public Optional<List<S>> getWinnerPath(){
        return winnerPath;
    }



    public void process(S source){
        Set<S> derivates = tran.validSituations(source);
        Optional<S> optProspectiveWinSituation = tran.hasWinner(derivates);
        if (optProspectiveWinSituation.isPresent()){
            S prospectiveWinSituation = optProspectiveWinSituation.get();
            List<S> prospectiveWinPath = tran.getPastSituations(prospectiveWinSituation);
            double prospectiveWinCost = tran.calculateCost(prospectiveWinPath);
            if (!winnerPath.isPresent() || tran.calculateCost(winnerPath.get()) > prospectiveWinCost ){
                winnerPath = Optional.of(prospectiveWinPath);
            }
            return;
        }else {
            derivates.forEach(s -> {
                List<S> historyS = tran.getPastSituations(s);
                double historyCost = tran.calculateCost(historyS);
                boolean winnerPathCondition = !winnerPath.isPresent()
                        || historyCost < tran.calculateCost(winnerPath.get());
                boolean maximumCostCondition  = !maximumCost.isPresent() || historyCost <= maximumCost.get();

                if (winnerPathCondition && maximumCostCondition) {
                    process(s);
                }
            });
        }

    }
}
