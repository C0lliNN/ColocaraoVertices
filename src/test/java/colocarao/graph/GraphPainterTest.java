package colocarao.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphPainterTest {

    @Nested
    @DisplayName("paintGraph")
    class PaintGraphClass {
        GraphPainter painter;

        @BeforeEach
        void setUp() {
            this.painter = new GraphPainter();
        }

        @Test
        @DisplayName("when the graph is empty, it should return a valid result")
        void whenTheGraphIsEmpty_shouldReturnAValidResult() {
            Graph<Integer> graph = new Graph<>();

            Map<Integer, Integer> solution = painter.paintGraph(graph);

            validateSolution(graph, solution);
        }

        @Test
        @DisplayName("when the graph has one element, it should return a valid result")
        void whenTheGraphHasOneElement_shouldReturnAValidResult() {
            Graph<Integer> graph = new Graph<>();
            graph.addVertex(5);

            Map<Integer, Integer> solution = painter.paintGraph(graph);

            validateSolution(graph, solution);
        }

        @Test
        @DisplayName("when the graph has two elements, it should return a valid result")
        void whenTheGraphHasTwoElements_shouldReturnTheCorrectMap() {
            Graph<Integer> graph = new Graph<>();
            graph.addEdge(8, 10);

            Map<Integer, Integer> solution = painter.paintGraph(graph);

            validateSolution(graph, solution);
        }

        @Test
        @DisplayName("when the graph has five elements, it should return a valid result")
        void whenTheGraphHasFiveElements_shouldReturnAValidResult() {
            Graph<Integer> graph = new Graph<>();
            graph.addEdge(8, 10);
            graph.addEdge(10, 12);
            graph.addEdge(4, 5);
            graph.addEdge(5, 10);
            graph.addEdge(5, 12);

            Map<Integer, Integer> solution = painter.paintGraph(graph);

            validateSolution(graph, solution);
        }

        @Test
        @DisplayName("when the graph has ten elements, it should return a valid result")
        void whenTheGraphHasTenElements_shouldReturnTheCorrectMap() {
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

            Map<Integer, Integer> solution = painter.paintGraph(graph);

            validateSolution(graph, solution);
        }

        @Test
        @DisplayName("when the graph has 11 vertexes and 20 edges, it should return a valid result")
        void whenTheGraphHas11VertexesAnd20Edges_shouldReturnAValidResult() {
            Graph<Integer> graph = createGraphFromFile("myciel3.col");

            Map<Integer, Integer> solution = painter.paintGraph(graph);

            validateSolution(graph, solution);
        }

        @Test
        @DisplayName("when the graph has 23 vertexes and 71 edges, it should return a valid result")
        void whenTheGraphHas23VertexesAnd71Edges_shouldReturnAValidResult() {
            Graph<Integer> graph = createGraphFromFile("myciel4.col");

            Map<Integer, Integer> solution = painter.paintGraph(graph);

            validateSolution(graph, solution);
        }

        @Test
        @DisplayName("when the graph has 47 vertexes and 236 edges, it should return a valid result")
        void whenTheGraphHas47VertexesAnd236Edges_shouldReturnAValidResult() {
            Graph<Integer> graph = createGraphFromFile("myciel5.col");

            Map<Integer, Integer> solution = painter.paintGraph(graph);

            validateSolution(graph, solution);
        }

        @Test
        @DisplayName("when the graph has 25 vertexes and 320 edges, it should return a valid result")
        void whenTheGraphHas25VertexesAnd320Edges_shouldReturnAValidResult() {
            Graph<Integer> graph = createGraphFromFile("queen5_5.col");

            Map<Integer, Integer> solution = painter.paintGraph(graph);

            validateSolution(graph, solution);
        }

        @Test
        @DisplayName("when the graph has 64 vertexes and 1456 edges, it should return a valid result")
        void whenTheGraphHas64VertexesAnd1456Edges_shouldReturnAValidResult() {
            Graph<Integer> graph = createGraphFromFile("queen8_8.col");

            Map<Integer, Integer> solution = painter.paintGraph(graph);

            validateSolution(graph, solution);
        }
    }

    private Graph<Integer> createGraphFromFile(String file) {
        try {
            Path path = Path.of(getClass().getClassLoader().getResource(file).toURI());
            List<String> lines = Files.readAllLines(path);

            Graph<Integer> graph = new Graph<>();

            lines.stream()
                    .filter(l -> l.startsWith("e"))
                    .map(l -> l.split(" "))
                    .map(e -> new int[]{Integer.parseInt(e[1]), Integer.parseInt(e[2])})
                    .forEach(e -> graph.addEdge(e[0], e[1]));

            return graph;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> void validateSolution(Graph<T> graph, Map<T, Integer> solution) {
        // O número de vértices no grafo tem que ser igual ao número de vértices da solução
        assertEquals(graph.getVertexCount(), solution.size());

        List<List<T>> edges = graph.getEdges();

        // Para cada aresta no grafo, verifique que as cores são diferentes
        for (List<T> edge : edges) {
            T source = edge.get(0);
            T destination = edge.get(1);

            assertTrue(solution.containsKey(source));
            assertTrue(solution.containsKey(destination));

            assertNotEquals(solution.get(source), solution.get(destination));
        }
    }
}