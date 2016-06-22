package com.radius.quizsolver.services;

import com.radius.quizsolver.domain.HanoiSituation;
import com.radius.quizsolver.domain.TorchBridgeSituation;
import com.radius.quizsolver.domain.enums.TorchPeople;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by gdiaz on 6/18/16.
 */
public class HanoiTransitionManager extends TransitionManager<HanoiSituation> {





    public  Set<HanoiSituation> validSituations(HanoiSituation original){
        Set<HanoiSituation> pastSituations = new HashSet<>(getPastSituations(original));

        return pastSituations;
    }

    /**
     * for a given situation and a pin index, returns all possible target pins
     */
    protected List<Integer> possibleTargetIndexes(HanoiSituation sit, int origin){
        List<Integer> targetIndexes = new ArrayList<>();
        Stack<Integer> originPin = sit.getPin(origin);
        if (originPin.empty()){
            return targetIndexes;
        }
        int topValue = originPin.peek();
        for (int i=0; i<3; i++){
            if (i!=origin){
                Stack<Integer> destPin = sit.getPin(i);
                if (destPin.empty()){
                    targetIndexes.add(i);
                    continue;
                }else{
                    Integer topElement = destPin.peek();
                    if (topElement > topValue){
                        targetIndexes.add(i);
                        continue;
                    }
                }
            }
        }
        return targetIndexes;
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

    public double calculateCost(List<HanoiSituation> history){
        return history.size();
    }

}
