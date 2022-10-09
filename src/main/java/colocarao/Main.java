package colocarao;

import colocarao.graph.Graph;

public class Main {
    public static void main(String[] args) {
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

        System.out.println(graph);
    }
}
