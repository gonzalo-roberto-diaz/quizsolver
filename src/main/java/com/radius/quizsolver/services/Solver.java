package com.radius.quizsolver.services;

import com.radius.quizsolver.domain.Situation;
import com.radius.quizsolver.domain.WolfGooseCabaggeSituation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by gdiaz on 6/19/16.
 */
public abstract class Solver  <S extends Situation,  T extends TransitionManager<S>>{
    protected T tran;
    protected Optional<List<S>> winnerPath = Optional.empty();

    protected Optional<Double> maximumCost = Optional.empty();



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
