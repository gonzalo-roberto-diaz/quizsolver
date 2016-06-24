package com.radius.quizsolver.services.transitionmanagers;

import com.radius.quizsolver.domain.situations.Situation;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by gdiaz on 6/19/16.
 * This class is basically a helper for Situation, that externalizes some methods in order to keep the Situation
 * istances as flyweight as possible.
 */
public abstract class TransitionManager <S extends Situation> {
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
}