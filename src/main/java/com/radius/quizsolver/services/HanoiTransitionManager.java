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
        Set<HanoiSituation> result = new HashSet<>();
        for (int i=0; i<3; i++){
            if (original.getPin(i).empty()){
                continue;
            }
            List<Integer> possibleDestinations = possibleTargetIndexes(original, i);
            for (int destIndex = 0; destIndex<possibleDestinations.size(); destIndex++){
                int destinationPin = possibleDestinations.get(destIndex);
                HanoiSituation newSit = moveDisc(original, i, destinationPin);
                if (newSit.isValid() && !pastSituations.contains(newSit)) {
                    result.add(newSit);
                }
            }
        }
        return result;
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

    protected HanoiSituation moveDisc(HanoiSituation original, int originPin, int destinationPin){
        HanoiSituation result = (HanoiSituation) original.clone();
        Integer disc = result.getPin(originPin).pop();
        result.getPin(destinationPin).push(disc);
        result.parent = original;
        return result;
    }










    public double calculateCost(List<HanoiSituation> history){
        return history.size();
    }

}
