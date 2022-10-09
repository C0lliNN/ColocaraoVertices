package colocarao.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph<T> {
    // Estamos usando um Hashmap para armazenar as arestar de cada vértice
    private final Map<T, List<T> > map = new HashMap<>();

    // Esse método adiciona um vértice ao grafo
    public void addVertex(T s) {
        map.put(s, new LinkedList<>());
    }

    // Esse método adiciona uma aresta entre a origem e o destino
    public void addEdge(T source, T destination) {
        if (!map.containsKey(source)) {
            addVertex(source);
        }

        if (!map.containsKey(destination)) {
            addVertex(destination);
        }

        map.get(source).add(destination);
        map.get(destination).add(source);
    }

    // Esse método retorna a quantidade de vértices no grafo
    public int getVertexCount() {
        return map.keySet().size();
    }

    // Esse método retorna a quantidade de arestas no grafo
    public int getEdgesCount() {
        int count = 0;
        for (T v : map.keySet()) {
            count += map.get(v).size();
        }

        return count;
    }

    // Essé metodo verifica se um determinado vértice está presente ou não no grafo.
    public boolean hasVertex(T s) {
        return map.containsKey(s);
    }

    // Esse método retorna se uma determinada aresta está presente ou não no grafo.
    public boolean hasEdge(T s, T d) {
        return map.get(s).contains(d);
    }

    // Esse método retorna uma lista de adjacência para o grafo
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {
            builder.append(v.toString()).append(": ");
            for (T w : map.get(v)) {
                builder.append(w.toString()).append(" ");
            }
            builder.append("\n");
        }

        return (builder.toString());
    }
}
