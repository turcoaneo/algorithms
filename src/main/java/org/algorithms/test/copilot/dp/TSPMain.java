package org.algorithms.test.copilot.dp;

import org.algorithms.test.copilot.dp.tsp.SimulatedAnnealingTSP;
import org.algorithms.test.copilot.dp.tsp.TSPBranchBound;

import java.util.List;

public class TSPMain {

    static Integer[][] costMatrix = {
            { -1,  6,  5,  4,  8,  7,  3,  9 },
            {  7, -1,  2,  6,  5,  8,  4,  7 },
            {  5,  3, -1,  9,  2,  4,  6,  5 },
            {  4,  6,  7, -1,  3,  5,  8,  6 },
            {  8,  5,  2,  3, -1,  7,  9,  4 },
            {  7,  8,  4,  5,  6, -1,  2,  3 },
            {  3,  4,  6,  8,  9,  2, -1,  5 },
            {  9,  7,  5,  6,  4,  3,  5, -1 }
    };
    public static void main(String[] args) {

        TSPBranchBound tsp = new TSPBranchBound();
        tsp.setBranchBound(costMatrix);
        tsp.solve();
        tsp.printSolution();


        SimulatedAnnealingTSP saTSP = new SimulatedAnnealingTSP();
        saTSP.simulatedAnnealingTSP(costMatrix, 9, 0.995, false);
        List<Integer> bestPath = saTSP.solve();
        System.out.println("Approximate solution: " + bestPath);
        System.out.println("Estimated cost: " + saTSP.calculateCost(bestPath));

    }
}