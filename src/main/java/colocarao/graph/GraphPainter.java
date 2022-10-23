package colocarao.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GraphPainter {
    private final int numberOfIterations;

    public GraphPainter() {
        this(10);
    }

    public GraphPainter(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    public <T> Map<T, Integer> paintGraph(Graph<T> graph) {
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

    /* Estrátegia para solução inicial
    *  1. Percorrer a lista de ajacência
    *  2. Para cada vértice, gerar um cor (número) randomico
    *  3. Para cada cor gerada, verficar se algum vizinho não já está usando ela
    *  4. Atribuir a cor gerado ao vértice
    *  5. Próxima iteração
    */
    private <T> Map<T, Integer> buildInitialSolution(Graph<T> graph) {
        Map<T, Integer> solution = new HashMap<>();

        Map<T, List<T>> adjacencyList = graph.getAdjacencyList();

        for (T v : adjacencyList.keySet()) {
            Set<Integer> neighboorsColors = new HashSet<>();

            for (T e : adjacencyList.get(v)) {
                if (solution.containsKey(e)) {
                    neighboorsColors.add(solution.get(e));
                }
            }

            int color;
            do {
                color = new Random().nextInt(graph.getVertexCount());
            } while (neighboorsColors.contains(color));

            solution.put(v, color);
        }

        return solution;
    }

    /* Estrátegia para Busca Local inicial
     *  1. Percorrer a lista de ajacência
     *  2. Para cada vértice, obter as cores dos vizinhos
     *  3. Obter a maior cor utilizada pelos vizinhos
     *  4. Percorrer de 0 até maior cor buscando a menor cor disponível (não utilizada pelos vizinhos).
     *     Assim, busca-se reduzir o número de cores utilizadas, pois na fase de construção podem ser gerados valores
     *     muito grandes.
     */
    private <T> void applyLocalSearch(Graph<T> graph, Map<T, Integer> solution) {
        Map<T, List<T>> adjacencyList = graph.getAdjacencyList();

        for (T v : adjacencyList.keySet()) {
            Set<Integer> neighboorsColors = new HashSet<>();

            int max = 0;
            for (T e : adjacencyList.get(v)) {
                int color = solution.get(e);
                neighboorsColors.add(color);

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
    }

    private <T> boolean hasImprovedSolution(Map<T, Integer> currentBestSolution, Map<T, Integer> newSolution) {
        if (currentBestSolution == null) {
            return true;
        }

        int currentTotalColors = new HashSet<>(currentBestSolution.values()).size();
        int newTotalColors = new HashSet<>(newSolution.values()).size();

        return newTotalColors < currentTotalColors;
    }
}
