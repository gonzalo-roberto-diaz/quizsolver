package com.radius.quizsolver.domain;

import com.radius.quizsolver.domain.enums.Pieces;

import java.util.Arrays;
import java.util.HashSet;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Created by gdiaz on 6/19/16.
 */
public class WolfGooseCabbageSituationTest {



    @Test
    public void gooseEatsCabbage(){
        WolfGooseCabaggeSituation sit = new WolfGooseCabaggeSituation();
        sit.leftBank = new HashSet<>();
        sit.rightBank = new HashSet<>();
        sit.leftBank.addAll(Arrays.asList(Pieces.FARMER, Pieces.WOLF));
        sit.rightBank.addAll(Arrays.asList(Pieces.GOOSE, Pieces.CABBAGE));
        assertFalse(sit.isValid());
    }


    @Test
    public void gooseEatsCabbageButFarmerPresent(){
        WolfGooseCabaggeSituation sit = new WolfGooseCabaggeSituation();
        sit.leftBank = new HashSet<>();
        sit.rightBank = new HashSet<>();
        sit.leftBank.addAll(Arrays.asList(Pieces.WOLF));
        sit.rightBank.addAll(Arrays.asList(Pieces.GOOSE, Pieces.CABBAGE, Pieces.FARMER));
        assertTrue(sit.isValid());
    }


}
