package com.radius.quizsolver.domain;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by gdiaz on 6/19/16.
 */
public class HanoiSituationTest {



    @Test
    public void initialization(){
        HanoiSituation sit = new HanoiSituation(5);
        assertEquals(sit.getPin(0).size(), 5);
        assertEquals(sit.getPin(1).size(), 0);
        assertEquals(sit.getPin(2).size(), 0);
    }

    @Test
    public void testClone(){
        HanoiSituation sit = new HanoiSituation(0);
        sit.getPin(0).push(4);
        sit.getPin(0).push(2);
        sit.getPin(1).push(3);
        HanoiSituation newSit = (HanoiSituation)sit.clone();
        assertEquals(newSit.getPin(0), sit.getPin(0));
        assertEquals(newSit.getPin(1), sit.getPin(1));
        assertEquals(newSit.getPin(2), sit.getPin(2));
        assertEquals(newSit.getAmountOfPieces(), sit.getAmountOfPieces());
    }


}
