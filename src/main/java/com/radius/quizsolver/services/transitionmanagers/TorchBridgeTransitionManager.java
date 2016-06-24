package com.radius.quizsolver.services.transitionmanagers;

import com.radius.quizsolver.domain.situations.TorchBridgeSituation;
import com.radius.quizsolver.domain.enums.TorchPeople;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gdiaz on 6/18/16.
 */
public class TorchBridgeTransitionManager extends TransitionManager<TorchBridgeSituation> {





    public  Set<TorchBridgeSituation> validSituations(TorchBridgeSituation original){
        Set<TorchBridgeSituation> pastSituations = new HashSet<>(getPastSituations(original));

        Set<TorchBridgeSituation> result = new HashSet<>();

        if (original.leftBank.contains(TorchPeople.LAMP)){
            Set<Set<TorchPeople>> subsets = subsetsSize2to3ContainingLamp(original.leftBank);

            subsets.forEach(movingSubSet->{
                TorchBridgeSituation newSit = createMovingLeftToRight(original, movingSubSet);
                if (newSit.isValid() && !pastSituations.contains(newSit)) {
                    result.add(newSit);
                }
            });
        }else{
            Set<Set<TorchPeople>> subsets = subsetsSize2to3ContainingLamp(original.rightBank);

            subsets.forEach(movingSubSet->{
                TorchBridgeSituation newSit = createMovingRightToLeft(original, movingSubSet);
                if (newSit.isValid() && !pastSituations.contains(newSit)) {
                    result.add(newSit);
                }
            });

        }
        return result;
    }


    protected TorchBridgeSituation createMovingLeftToRight(TorchBridgeSituation original, Set<TorchPeople> movingSubSet){
        TorchBridgeSituation newSit = (TorchBridgeSituation) original.clone();
        newSit.leftBank.removeAll(movingSubSet);
        newSit.rightBank.addAll(movingSubSet);
        double subsetCost = movingSubSet.stream().mapToDouble(TorchPeople::getMinutes).reduce(0, Double::max);
        newSit.setCost(subsetCost);
        newSit.parent = original;
        return newSit;
    }



    protected TorchBridgeSituation createMovingRightToLeft(TorchBridgeSituation original, Set<TorchPeople> movingSubSet){
        TorchBridgeSituation newSit = (TorchBridgeSituation) original.clone();
        newSit.rightBank.removeAll(movingSubSet);
        newSit.leftBank.addAll(movingSubSet);
        double subsetCost = movingSubSet.stream().mapToDouble(TorchPeople::getMinutes).reduce(0, Double::max);
        newSit.setCost(subsetCost);
        newSit.parent = original;
        return newSit;
    }


    protected Set<Set<TorchPeople>> allSubsetsOfSize(Set<TorchPeople> original, int size){
        Set<Set<TorchPeople>> ret = new HashSet<>();

        ICombinatoricsVector<TorchPeople> originalVector = Factory.createVector(original);
        Generator<TorchPeople> gen = Factory.createSimpleCombinationGenerator(originalVector, size);
        for (ICombinatoricsVector<TorchPeople> perm : gen) {
            Set<TorchPeople> set = new HashSet<>(perm.getVector());
            ret.add(set);
        }

        return ret;
    }


    protected  Set<Set<TorchPeople>> subsetsSize2to3ContainingLamp(Set<TorchPeople> bank){
        Set<Set<TorchPeople>> ret = new HashSet<>();

        Set<TorchPeople> allButLamp = new HashSet<>(bank);
        allButLamp.remove(TorchPeople.LAMP);

        Set<Set<TorchPeople>> allSize1 = allSubsetsOfSize(allButLamp, 1);
        Set<Set<TorchPeople>> allSize2 = allSubsetsOfSize(allButLamp, 2);
        //Set<Set<TorchPeople>> allSize3 = allSubsetsOfSize(allButLamp, 3);

        allSize2.addAll(allSize1);

        Set<Set<TorchPeople>> allSubsets = allSize2;

        allSubsets.forEach(set -> set.add(TorchPeople.LAMP));

        return allSubsets;
    }

    public double calculateCost(List<TorchBridgeSituation> history){
        return history.stream().mapToDouble(TorchBridgeSituation::getCost).reduce(0, Double::sum);
    }

}
