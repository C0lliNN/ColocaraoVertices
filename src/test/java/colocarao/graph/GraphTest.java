package colocarao.graph;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Nested
    @DisplayName("Add Vertex")
    class AddVertexTest {

        @Test
        @DisplayName("When the graph is empty, it should add a vertex successfully")
        void whenTheGraphIsEmpty_shouldAddAVertexSuccessfully() {
            Graph<String> graph = new Graph<>();
            assertFalse(graph.hasVertex("Raphael"));

            graph.addVertex("Raphael");
            assertTrue(graph.hasVertex("Raphael"));
        }

        @Test
        @DisplayName("When the graph is not empty, it should add a vertex successfully")
        void whenTheGraphIsNotEmpty_shouldAddAVertexSuccessfully() {
            Graph<String> graph = new Graph<>();
            graph.addVertex("Raphael");
            graph.addVertex("Thiago");

            assertTrue(graph.hasVertex("Raphael"));
            assertTrue(graph.hasVertex("Thiago"));
        }

        @Test
        @DisplayName("When the vertex is already present, it should overwrite it")
        void whenTheVertexIsAlreadyPresent_shouldOverwriteIt() {
            Graph<String> graph = new Graph<>();
            graph.addVertex("Raphael");

            graph.addVertex("Raphael");
            assertTrue(graph.hasVertex("Raphael"));
        }
    }

    @Nested
    @DisplayName("Add Edge")
    class AddEdgeClass {

        @Test
        @DisplayName("when the source and destination does not exit, it should add two vertex and the edge")
        void whenTheSourceAndDestinationDoesNotExist_shouldAddTwoVertexAndTheEdge() {
            Graph<String> graph = new Graph<>();
            graph.addEdge("Rio de Janeiro", "Minas Gerais");

            assertTrue(graph.hasVertex("Rio de Janeiro"));
            assertTrue(graph.hasVertex("Minas Gerais"));
            assertTrue(graph.hasEdge("Rio de Janeiro", "Minas Gerais"));
            assertTrue(graph.hasEdge("Minas Gerais", "Rio de Janeiro"));
        }

        @Test
        @DisplayName("when the source does not exist, it should add one vertex and the edge")
        void whenTheSourceDoesNotExist_shouldAddOneVertexAndTheEdge() {
            Graph<String> graph = new Graph<>();
            graph.addVertex("Rio de Janeiro");
            graph.addEdge("Rio de Janeiro", "Minas Gerais");

            assertTrue(graph.hasVertex("Minas Gerais"));
            assertTrue(graph.hasEdge("Rio de Janeiro", "Minas Gerais"));
            assertTrue(graph.hasEdge("Minas Gerais", "Rio de Janeiro"));
        }

        @Test
        @DisplayName("when the source and destination exist, it should the edge")
        void whenTheSourceAndDestinatitonExist_shouldTheEdge() {
            Graph<String> graph = new Graph<>();
            graph.addVertex("Rio de Janeiro");
            graph.addVertex("Minas Gerais");
            graph.addEdge("Rio de Janeiro", "Minas Gerais");

            assertTrue(graph.hasEdge("Rio de Janeiro", "Minas Gerais"));
            assertTrue(graph.hasEdge("Minas Gerais", "Rio de Janeiro"));
        }
    }

    @Nested
    @DisplayName("Get Vertex Count")
    class GetVertexCountClass {

        @Test
        @DisplayName("when there are no edges, it should return the amount of vertexes")
        void whenThereAreNoEdges_shouldReturnTheAmountOfVertexes() {
            Graph<String> graph = new Graph<>();
            graph.addVertex("Rio de Janeiro");
            graph.addVertex("Minas Gerais");

            assertEquals(2, graph.getVertexCount());
        }
    }

    @Nested
    @DisplayName("Get Edge Count")
    class GetEdgeCountClass {

        @Test
        @DisplayName("when called, it should return the amount of edges")
        void whenCalled_shouldReturnTheAmountOfEdges() {
            Graph<String> graph = new Graph<>();
            graph.addEdge("Rio de Janeiro", "Minas Gerais");
            graph.addEdge("São Paulo", "Minas Gerais");
            graph.addEdge("Rio de Janeiro", "São paylo");

            assertEquals(3, graph.getEdgesCount());
        }
    }

    @Nested
    @DisplayName("Get Edges")
    class GetEdgesClass {

        @Test
        @DisplayName("when called, it should return all the edges")
        void whenCalled_shouldReturnAllTheEdges() {
            Graph<Integer> graph = new Graph<>();
            graph.addEdge(4, 3);
            graph.addEdge(2, 4);
            graph.addEdge(1, 8);
            graph.addEdge(2, 9);
            graph.addEdge(10, 8);

            List<List<Integer>> edges = graph.getEdges();

            assertTrue(edges.contains(List.of(3, 4)));
            assertTrue(edges.contains(List.of(4, 3)));

            assertTrue(edges.contains(List.of(2, 4)));
            assertTrue(edges.contains(List.of(4, 2)));

            assertTrue(edges.contains(List.of(1, 8)));
            assertTrue(edges.contains(List.of(8, 1)));

            assertTrue(edges.contains(List.of(2, 9)));
            assertTrue(edges.contains(List.of(9, 2)));

            assertTrue(edges.contains(List.of(10, 8)));
            assertTrue(edges.contains(List.of(8, 10)));
        }
    }
}