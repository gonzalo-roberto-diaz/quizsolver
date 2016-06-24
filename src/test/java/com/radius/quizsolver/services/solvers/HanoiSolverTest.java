package com.radius.quizsolver.services.solvers;

import com.radius.quizsolver.domain.situations.HanoiSituation;
import org.testng.annotations.Test;

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
        assertNotNull(sol.getWinnerPath());
        sol.getTran().printHistory((List<HanoiSolver>) sol.getWinnerPath().get());

    }
}
