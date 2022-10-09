package colocarao.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        @DisplayName("when the graph is empty, it should return an empty map")
        void whenTheGraphIsEmpty_shouldReturnAnEmptyMap() {
            Graph<Integer> graph = new Graph<>();

            assertEquals(0, painter.paintGraph(graph).size());
        }

        @Test
        @DisplayName("when the graph has one element, it should return a map with one element")
        void whenTheGraphHasOneElement_shouldReturnAMapWithOneElement() {
            Graph<Integer> graph = new Graph<>();
            graph.addVertex(5);

            var expected = Map.of(5, Color.RED);
            var actual = painter.paintGraph(graph);

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("when the graph has two elements, it should return the correct map")
        void whenTheGraphHasTwoElements_shouldReturnTheCorrectMap() {
            Graph<Integer> graph = new Graph<>();
            graph.addEdge(8, 10);

            var expected = Map.of(8, Color.RED, 5, Color.BLACK);
            var actual = painter.paintGraph(graph);

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("when the graph has five elements, it should return the correct map")
        void whenTheGraphHasFiveElements_shouldReturnTheCorrectMap() {
            Graph<Integer> graph = new Graph<>();
            graph.addEdge(8, 10);
            graph.addEdge(10, 12);
            graph.addEdge(4, 5);
            graph.addEdge(5, 10);
            graph.addEdge(5, 12);

            var expected = Map.of(
                    8, Color.RED,
                    10, Color.BLACK,
                    12, Color.RED,
                    4, Color.RED,
                    5, Color.GREEN
            );
            var actual = painter.paintGraph(graph);

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("when the graph has ten elements, it should return the correct map")
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

            var expected = Map.of(
                    2, Color.RED,
                    4, Color.BLACK,
                    3, Color.RED,
                    8, Color.BLACK,
                    15, Color.RED,
                    12, Color.RED,
                    19, Color.BLACK,
                    9, Color.GREEN,
                    5, Color.RED,
                    13, Color.BLACK
            );
            var actual = painter.paintGraph(graph);

            assertEquals(expected, actual);
        }
    }

}