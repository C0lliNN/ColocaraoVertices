package colocarao.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphPainter {
    private final int numberOfIterations;

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
        Map<T, Integer> solution = new HashMap<>();

        Map<T, List<T>> adjacencyList = graph.getAdjacencyList();

        for (T v : adjacencyList.keySet()) {
            Set<Integer> neighboorsColors = new HashSet<>();

            for (T e : adjacencyList.get(v)) {
                if (solution.containsKey(e)) {
                    neighboorsColors.add(solution.get(e));
                }
            }

            int max = 0;

            for (Integer color : neighboorsColors) {
                if (color > max) {
                    max = color;
                }
            }

            for (int i = 0; i <= max + 1; i++) {
                if (!neighboorsColors.contains(i)) {
                    solution.put(v, i);
                    break;
                }
            }

        }

        return solution;
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
