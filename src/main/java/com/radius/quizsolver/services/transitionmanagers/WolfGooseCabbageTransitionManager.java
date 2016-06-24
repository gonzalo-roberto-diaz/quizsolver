package com.radius.quizsolver.services.transitionmanagers;

import com.radius.quizsolver.domain.situations.WolfGooseCabaggeSituation;
import com.radius.quizsolver.domain.enums.Pieces;
import com.radius.quizsolver.services.transitionmanagers.TransitionManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gdiaz on 6/18/16.
 */
public class WolfGooseCabbageTransitionManager extends TransitionManager<WolfGooseCabaggeSituation> {





    public  Set<WolfGooseCabaggeSituation> validSituations(WolfGooseCabaggeSituation original){
        Set<WolfGooseCabaggeSituation> pastSituations = new HashSet<>(getPastSituations(original));

        Set<WolfGooseCabaggeSituation> result = new HashSet<>();

        if (original.leftBank.contains(Pieces.FARMER)){
            Set<Set<Pieces>> subsets = subsetsSize1Or2ContainingFarmer(original.leftBank);

            subsets.forEach(movingSubSet->{
                WolfGooseCabaggeSituation newSit = createMovingLeftToRight(original, movingSubSet);
                if (newSit.isValid() && !pastSituations.contains(newSit)) {
                    result.add(newSit);
                }
            });
        }else{
            Set<Set<Pieces>> subsets = subsetsSize1Or2ContainingFarmer(original.rightBank);

            subsets.forEach(movingSubSet->{
                WolfGooseCabaggeSituation newSit = createMovingRightToLeft(original, movingSubSet);
                if (newSit.isValid() && !pastSituations.contains(newSit)) {
                    result.add(newSit);
                }
            });

        }
        return result;
    }

    protected WolfGooseCabaggeSituation createMovingLeftToRight(WolfGooseCabaggeSituation original, Set<Pieces> movingSubSet){
        WolfGooseCabaggeSituation newSit = (WolfGooseCabaggeSituation)original.clone();
        newSit.leftBank.removeAll(movingSubSet);
        newSit.rightBank.addAll(movingSubSet);
        newSit.parent = original;
        return newSit;
    }

    protected WolfGooseCabaggeSituation createMovingRightToLeft(WolfGooseCabaggeSituation original, Set<Pieces> movingSubSet){
        WolfGooseCabaggeSituation newSit = (WolfGooseCabaggeSituation)original.clone();
        newSit.rightBank.removeAll(movingSubSet);
        newSit.leftBank.addAll(movingSubSet);
        newSit.parent = original;
        return newSit;
    }



    protected  Set<Set<Pieces>> subsetsSize1Or2ContainingFarmer(Set<Pieces> bank){
        Set<Set<Pieces>> ret = new HashSet<>();
        bank.forEach(pieces -> {
            Set<Pieces> addendum = new HashSet<Pieces>();
            addendum.add(Pieces.FARMER);
            if (pieces != Pieces.FARMER){
                addendum.add(pieces);
            }
            ret.add(addendum);
        });
        return ret;
    }

    public double calculateCost(List<WolfGooseCabaggeSituation> history){
        return history.size();
    }

}
