package com.radius.quizsolver.services.solvers;

import com.radius.quizsolver.domain.situations.Situation;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by gdiaz on 6/19/16.
 * The main class of this solving framework. It consists of basically a single, recursive method that, for every source situation, evaluates
 * all possible derivate situations, and identifies it any of them is a "winning" situation.
 */
public abstract class Solver  <S extends Situation>{

    public Optional<S> hasWinner(Set<S> situations){
        return situations.stream().filter(p->p.isWinning()).findAny();
    }

    public void printHistory(List<S> history){
        for (int i=history.size()-1; i>=0; i--){
            System.out.println(history.get(i));
        }
    }

    public List<S> getPastSituations(S sit){
        List<S> pastSituations = new LinkedList<>();
        //add itself
        pastSituations.add(sit);
        S parent;
        do {
            parent = (S)sit.parent;
            if (parent !=null) {
                pastSituations.add(parent);
            }
            sit = parent;
        }while(sit!=null);
        return  pastSituations;
    }

    public abstract double calculateCost(List<S> history);



    public abstract Set<S> validSituations(S original);

    protected Optional<List<S>> winnerPath = Optional.empty();

    protected Optional<Double> maximumCost = Optional.empty();

    public Optional<List<S>> getWinnerPath(){
        return winnerPath;
    }

    public double getWinnerCost() {
        return winnerCost;
    }

    private double winnerCost;





    public void process(S source){
        Set<S> derivates = validSituations(source);
        Optional<S> optProspectiveWinSituation = hasWinner(derivates);
        if (optProspectiveWinSituation.isPresent()){
            S prospectiveWinSituation = optProspectiveWinSituation.get();
            List<S> prospectiveWinPath = getPastSituations(prospectiveWinSituation);
            double prospectiveWinCost = calculateCost(prospectiveWinPath);
            if (!winnerPath.isPresent() || calculateCost(winnerPath.get()) > prospectiveWinCost ){
                winnerPath = Optional.of(prospectiveWinPath);
            }
            return;
        }else {
            derivates.forEach(s -> {
                List<S> historyS = getPastSituations(s);
                double historyCost = calculateCost(historyS);
                boolean winnerPathCondition = !winnerPath.isPresent()
                        || historyCost < calculateCost(winnerPath.get());
                boolean maximumCostCondition  = !maximumCost.isPresent() || historyCost <= maximumCost.get();

                if (winnerPathCondition && maximumCostCondition) {
                    process(s);
                }
            });
        }

    }
}
