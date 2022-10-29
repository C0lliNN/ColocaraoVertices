package colocarao;

import colocarao.graph.Graph;
import colocarao.graph.GraphPainter;

import java.util.List;
import java.util.Map;

import static colocarao.helper.Colors.ANSI_CYAN;
import static colocarao.helper.Colors.ANSI_GREEN;
import static colocarao.helper.Colors.ANSI_PURPLE;
import static colocarao.helper.Colors.ANSI_RED;
import static colocarao.helper.Colors.ANSI_RESET;
import static colocarao.helper.Colors.ANSI_WHITE;
import static colocarao.helper.Colors.ANSI_YELLOW;

public class Main {
    public static void main(String[] args) {
        Graph<Integer> graph = createGraph();

        GraphPainter graphPainter = new GraphPainter();
        Map<Integer, Integer> solution = graphPainter.paintGraph(graph);

        printSolution(graph, solution);
    }

    private static Graph<Integer> createGraph() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(2);
        graph.addVertex(4);
        graph.addVertex(3);
        graph.addVertex(8);
        graph.addVertex(9);
        graph.addVertex(12);
        graph.addVertex(13);
        graph.addVertex(5);
        graph.addVertex(19);
        graph.addVertex(15);

        graph.addEdge(2, 4);
        graph.addEdge(8, 15);
        graph.addEdge(3, 8);
        graph.addEdge(3, 4);
        graph.addEdge(9, 2);
        graph.addEdge(9, 12);
        graph.addEdge(12, 13);
        graph.addEdge(13, 5);
        graph.addEdge(5, 19);
        graph.addEdge(19, 9);
        graph.addEdge(19, 15);

        return graph;
    }

    private static void printSolution(Graph<Integer> graph, Map<Integer, Integer> solution) {
        System.out.println("Printing Graph Colouring Solution...");

        Map<Integer, List<Integer>> adjacencyList = graph.getAdjacencyList();
        for (Integer v : adjacencyList.keySet()) {
            System.out.printf("%s%d%s -> ", getColor(solution.get(v)), v, ANSI_RESET);

            List<Integer> adjacents = adjacencyList.get(v);
            for (int i = 0; i < adjacents.size(); i++) {
                System.out.printf("%s%d%s", getColor(solution.get(adjacents.get(i))), adjacents.get(i), ANSI_RESET);
                if (i < adjacents.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }

    private static String getColor(int i) {
        switch (i) {
            case 0:
                return ANSI_RED;
            case 1:
                return  ANSI_GREEN;
            case 2:
                return ANSI_CYAN;
            case 3:
                return ANSI_PURPLE;
            case 4:
                return ANSI_YELLOW;
            default:
                return ANSI_WHITE;
        }
    }
}
