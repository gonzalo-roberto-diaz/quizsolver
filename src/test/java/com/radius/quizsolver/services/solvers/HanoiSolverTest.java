package com.radius.quizsolver.services.solvers;

import com.radius.quizsolver.domain.enums.Pieces;
import com.radius.quizsolver.domain.situations.HanoiSituation;
import com.radius.quizsolver.domain.situations.WolfGooseCabaggeSituation;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by gdiaz on 6/20/16.
 */
public class HanoiSolverTest {

    @Test
    public void solveQuiz(){
        HanoiSolver sol = new HanoiSolver();
        HanoiSituation sit = new HanoiSituation(4);

        sol.process(sit);
        assertNotNull(sol.getWinnerPath());
        sol.printHistory(sol.getWinnerPath().get());

    }


    private WolfGooseCabaggeSituation createSituation(){
        WolfGooseCabaggeSituation sit = new WolfGooseCabaggeSituation();
        sit.leftBank = new HashSet<>();
        sit.rightBank = new HashSet<>();
        sit.leftBank.addAll(Arrays.asList(Pieces.CABBAGE, Pieces.WOLF));
        sit.rightBank.addAll(Arrays.asList(Pieces.FARMER, Pieces.GOOSE));
        return sit;
    }

    @Test
    public void getTwoTargetPins(){
        HanoiSolver sol = new HanoiSolver();
        HanoiSituation sit = new HanoiSituation(0);
        sit.getPin(0).push(4);
        sit.getPin(0).push(3);
        sit.getPin(1).push(2);
        List<Integer> targets = sol.possibleTargetIndexes(sit, 1);  //top disc==2, possible targets are pins 0 and 2
        assertTrue(targets.contains(0));
        assertFalse(targets.contains(1));
        assertTrue(targets.contains(2));
    }

    @Test
    public void getOneTargetPin(){
        HanoiSolver sol = new HanoiSolver();
        HanoiSituation sit = new HanoiSituation(0);
        sit.getPin(0).push(2);
        sit.getPin(0).push(1);
        sit.getPin(2).push(3);
        List<Integer> targets = sol.possibleTargetIndexes(sit, 2);  //top disc==3, possible target is only 1
        assertFalse(targets.contains(0));
        assertTrue(targets.contains(1));
        assertFalse(targets.contains(2));
    }

    @Test
    public void moveDisc(){
        HanoiSituation sit = new HanoiSituation(0);
        sit.getPin(0).push(4);
        sit.getPin(0).push(3);
        sit.getPin(1).push(2);
        HanoiSolver sol = new HanoiSolver();
        HanoiSituation newSit =  sol.moveDisc(sit, 0, 2);
        assertEquals(newSit.getPin(0).size(), 1);
        assertEquals(newSit.getPin(0).peek(), Integer.valueOf(4));
        assertEquals(newSit.getPin(1).size(), 1);
        assertEquals(newSit.getPin(1).peek(), Integer.valueOf(2));
        assertEquals(newSit.getPin(2).size(), 1);
        assertEquals(newSit.getPin(2).peek(), Integer.valueOf(3));
    }

    @Test
    public void anotherMoveDisc(){
        HanoiSituation sit = new HanoiSituation(0);
        sit.getPin(0).push(4);
        sit.getPin(0).push(3);
        sit.getPin(1).push(2);
        HanoiSolver sol = new HanoiSolver();
        HanoiSituation newSit =  sol.moveDisc(sit, 1, 0);
        assertEquals(newSit.getPin(0).size(), 3);
        assertEquals(newSit.getPin(0).peek(), Integer.valueOf(2));
        assertEquals(newSit.getPin(1).size(), 0);
        assertEquals(newSit.getPin(2).size(), 0);
    }
}
