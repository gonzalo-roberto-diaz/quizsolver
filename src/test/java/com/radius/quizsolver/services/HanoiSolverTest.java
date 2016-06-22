package com.radius.quizsolver.services;

import com.radius.quizsolver.domain.HanoiSituation;
import com.radius.quizsolver.domain.TorchBridgeSituation;
import com.radius.quizsolver.domain.enums.TorchPeople;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by gdiaz on 6/20/16.
 */
public class HanoiSolverTest {

    @Test
    public void solveQuiz(){
        HanoiSolver sol = new HanoiSolver();
        HanoiSituation sit = new HanoiSituation(4);

        sol.process(sit);
        assertNotNull(sol.winnerPath);
        sol.tran.printHistory((List<HanoiSolver>) sol.winnerPath.get());

    }
}
