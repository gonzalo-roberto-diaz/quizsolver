package com.radius.quizsolver.services.transitionmanagers;

import com.radius.quizsolver.domain.situations.WolfGooseCabaggeSituation;
import com.radius.quizsolver.domain.enums.Pieces;
import com.radius.quizsolver.services.transitionmanagers.WolfGooseCabbageTransitionManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gdiaz on 6/19/16.
 */
public class WolfGooseCabbageTransitionManagerTest {

    private WolfGooseCabbageTransitionManager tran;

    @BeforeTest
    public void setUp(){
        tran = new WolfGooseCabbageTransitionManager();
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
    public void validSituations(){
        WolfGooseCabaggeSituation original = createSituation();
        Set<WolfGooseCabaggeSituation> validResults = tran.validSituations(original);
        assertNotNull(validResults);
        assertFalse(validResults.contains(original));
    }


    @Test
    public void historykept(){
        WolfGooseCabaggeSituation original = createSituation();
        Set<WolfGooseCabaggeSituation> validResults = tran.validSituations(original);
        WolfGooseCabaggeSituation child = validResults.iterator().next();
        Set<WolfGooseCabaggeSituation> grandChildren = tran.validSituations(child);
        WolfGooseCabaggeSituation grandChild = grandChildren.iterator().next();

        List<WolfGooseCabaggeSituation> grandChildHistory =  tran.getPastSituations(grandChild);

        assertEquals(grandChildHistory.size(), 3);
    }

}
