package colocarao.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GraphPainter {
    private int numberOfIterations;

    public GraphPainter() {
        this(10);
    }

    public GraphPainter(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    public <T> Map<T, Integer>  paintGraph(Graph<T> graph) {
        Map<T, Integer> bestSolution = null;

        for (int i = 0; i < numberOfIterations; i++) {
            Map<T, Integer> currentSolution = buildInitialSolution(graph);
            applyLocalSearch(graph, currentSolution);

            if (hasImprovedSolution(bestSolution, currentSolution)) {
                bestSolution = currentSolution;
            }
        }

        return bestSolution;
    }

    private <T> Map<T, Integer>  buildInitialSolution(Graph<T> graph) {
        return new HashMap<>();
    }

    private <T> void applyLocalSearch(Graph<T> graph, Map<T, Integer> solution) {

    }

    private <T> boolean hasImprovedSolution(Map<T, Integer> currentBestSolution, Map<T, Integer> newSolution) {
        if (currentBestSolution == null) {
            return true;
        }

        int currentTotalColors = new HashSet<>(currentBestSolution.values()).size();
        int newTotalColors = new HashSet<>(newSolution.values()).size();

        return newTotalColors > currentTotalColors;
    }

}
