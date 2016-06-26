package com.radius.quizsolver.services.solvers;

import com.radius.quizsolver.domain.situations.Situation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    final static Logger logger = LoggerFactory.getLogger(HanoiSolver.class);

    //for debugging purposes
    protected int situationsAnalized = 1;

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

    public void setWinnerPath(Optional<List<S>> winnerPath) {
        this.winnerPath = winnerPath;
        this.winnerCost = calculateCost(this.winnerPath.get());
    }

    public Optional<List<S>> getWinnerPath(){
        return winnerPath;
    }

    public double getWinnerCost() {
        return winnerCost;
    }

    private double winnerCost;





    public void process(S source){
        Set<S> derivates = validSituations(source);
//        situationsAnalized += derivates.size();
//        if (situationsAnalized == 1 || situationsAnalized % 10000 == 0){
//            logger.info("derivatesSize={} total={} situations analyzed", derivates.size(), situationsAnalized);
//        }
        Optional<S> optProspectiveWinSituation = hasWinner(derivates);
        if (optProspectiveWinSituation.isPresent()){
            S prospectiveWinSituation = optProspectiveWinSituation.get();
            double prospectiveWinCost = prospectiveWinSituation.getHistoryCost(); // calculateCost(prospectiveWinPath);
            if (!winnerPath.isPresent() || getWinnerCost() > prospectiveWinCost ){
                List<S> prospectiveWinPath = getPastSituations(prospectiveWinSituation);
                logger.info("found a winner size={}", prospectiveWinPath.size());
                setWinnerPath(Optional.of(prospectiveWinPath));
            }
            return;
        }else {
            derivates.stream().forEach(s -> {
                boolean noWinnerPath = !winnerPath.isPresent();
                double historyCost = s.getHistoryCost();

                //our path is already longer/more expensive than the winner path
                if (!noWinnerPath && historyCost > getWinnerCost()){
                    return;
                }

                //our path is more expensive than the maximum allowed cost
                if (maximumCost.isPresent() && historyCost> maximumCost.get()){
                    return;
                }

                //there is an "equal",  cheaper alternative already in the winner path
                if (!noWinnerPath && winnerPath.get().contains(s)){
                  S equalFromPath = winnerPath.get().stream().filter(ws -> ws.equals(s)).findFirst().get();
                  if (equalFromPath.getHistoryCost() <= s.getHistoryCost()){
                      //logger.info("eliminating because there is a cheaper item in the winner path");
                      return;
                  }
                }


                    process(s);

            });
        }

    }
}
