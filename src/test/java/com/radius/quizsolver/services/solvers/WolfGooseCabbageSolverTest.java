package com.radius.quizsolver.services.solvers;

import com.radius.quizsolver.domain.situations.WolfGooseCabaggeSituation;
import com.radius.quizsolver.domain.enums.Pieces;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

/**
 * Created by gdiaz on 6/20/16.
 */
public class WolfGooseCabbageSolverTest {

    @Test
    public void solveQuiz(){
        WolfGooseCabbageSolver sol = new WolfGooseCabbageSolver();
        WolfGooseCabaggeSituation sit = new WolfGooseCabaggeSituation();
        sit.leftBank = new HashSet<>();
        sit.rightBank = new HashSet<>();
        sit.leftBank.addAll(Arrays.asList(Pieces.values()));

        sol.process(sit);
        assertNotNull(sol.getWinnerPath());
        sol.printHistory((List<WolfGooseCabaggeSituation>) sol.getWinnerPath().get());

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
        WolfGooseCabbageSolver sol = new WolfGooseCabbageSolver();
        WolfGooseCabaggeSituation original = createSituation();
        Set<WolfGooseCabaggeSituation> validResults = sol.validSituations(original);
        assertNotNull(validResults);
        assertFalse(validResults.contains(original));
    }


    @Test
    public void historykept(){
        WolfGooseCabaggeSituation original = createSituation();
        WolfGooseCabbageSolver sol = new WolfGooseCabbageSolver();
        Set<WolfGooseCabaggeSituation> validResults = sol.validSituations(original);
        WolfGooseCabaggeSituation child = validResults.iterator().next();
        Set<WolfGooseCabaggeSituation> grandChildren = sol.validSituations(child);
        WolfGooseCabaggeSituation grandChild = grandChildren.iterator().next();

        List<WolfGooseCabaggeSituation> grandChildHistory =  sol.getPastSituations(grandChild);

        assertEquals(grandChildHistory.size(), 3);
    }
}
