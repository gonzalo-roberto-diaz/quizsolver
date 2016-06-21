package com.radius.quizsolver.services;

import com.radius.quizsolver.domain.TorchBridgeSituation;
import com.radius.quizsolver.domain.WolfGooseCabaggeSituation;
import com.radius.quizsolver.domain.enums.Pieces;
import com.radius.quizsolver.domain.enums.TorchPeople;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
        assertNotNull(sol.winnerPath);
        sol.tran.printHistory((List<WolfGooseCabaggeSituation>) sol.winnerPath.get());

    }
}
