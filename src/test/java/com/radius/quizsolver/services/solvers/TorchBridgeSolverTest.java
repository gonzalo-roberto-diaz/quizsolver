package com.radius.quizsolver.services.solvers;

import com.radius.quizsolver.domain.situations.TorchBridgeSituation;
import com.radius.quizsolver.domain.enums.TorchPeople;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Created by gdiaz on 6/20/16.
 */
public class TorchBridgeSolverTest {

    @Test
    public void solveQuiz(){
        TorchBridgeSolver sol = new TorchBridgeSolver();
        TorchBridgeSituation sit = new TorchBridgeSituation();
        sit.leftBank = new HashSet<>();
        sit.rightBank = new HashSet<>();
        sit.leftBank.addAll(Arrays.asList(TorchPeople.values()));


        sol.process(sit);
        assertNotNull(sol.getWinnerPath());
        sol.getTran().printHistory((List<TorchBridgeSituation>) sol.getWinnerPath().get());

    }
}
