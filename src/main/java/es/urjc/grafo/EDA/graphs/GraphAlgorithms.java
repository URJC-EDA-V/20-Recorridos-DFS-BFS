package es.urjc.grafo.EDA.graphs;

import es.urjc.grafo.EDA.utils.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * A collection of graph algorithms.
 */
public class GraphAlgorithms {

    /**
     * Performs depth-first search of the unknown portion of Graph g starting at Vertex u.
     *
     * @param g Graph instance
     * @param u Vertex of graph g that will be the source of the search
     *
     * As an outcome, this method returns the set of vertices discovered, and a list of the edges used during the
     *          exploration in the order in which they were encountered.
     *          If the edge was explored, the boolean value is True. Otherwise, if the edge was a back edge
     *          to an already known vertex, it is marked as False.
     * La salida de este método es el conjunto de vértices descubiertos y una lista de las aristas
     *          utilizadas durante la exploración en el orden en que fueron encontradas.
     *          Si la arista, al ser explorada, llevó a un vértice que aún no había sido visto, el valor booleano
     *          asociado a dicha arista es True. De lo contrario, si la arista era una arista de retroceso
     *          a un vértice ya conocido, se marca como False.
     */
    public static <V,E> Pair<Set<Vertex<V>>, List<Pair<Edge<E>, Boolean>>> DFS(AdjacencyMapGraph<V,E> g, Vertex<V> u) {
        Set<Vertex<V>> known = new HashSet<>();
        List<Pair<Edge<E>, Boolean>> exploratoryEdges = new LinkedList<>();
        Set<Edge<E>> visitedEdges = new HashSet<>(); // To avoid traversing edges twice in undirected graphs
        DFS(g, u, known, exploratoryEdges, visitedEdges);
        return new Pair<>(known, exploratoryEdges);
    }

    public static <V,E> void DFS(AdjacencyMapGraph<V,E> g, Vertex<V> u, Set<Vertex<V>> known,
                                 List<Pair<Edge<E>, Boolean>> exploratoryEdges,
                                 Set<Edge<E>> visitedEdges) {
        // TODO
        throw new  UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Performs breadth-first search of the undiscovered portion of Graph g starting at Vertex s.
     *
     * @param g Graph instance
     * @param u Vertex of graph g that will be the source of the search
     *
     * As an outcome, this method returns the set of vertices discovered, and a list of the edges used during the
     *          exploration in the order in which they were encountered.
     *          If the edge was explored, the boolean value is True. Otherwise, if the edge was a back edge
     *          to an already known vertex, it is marked as False.
     * La salida de este método es el conjunto de vértices descubiertos y una lista de las aristas
     *          utilizadas durante la exploración en el orden en que fueron encontradas.
     *          Si la arista, al ser explorada, llevó a un vértice que aún no había sido visto, el valor booleano
     *          asociado a dicha arista es True. De lo contrario, si la arista era una arista de retroceso
     *          a un vértice ya conocido, se marca como False.
     */
    public static <V,E> Pair<Set<Vertex<V>>, List<Pair<Edge<E>, Boolean>>> BFS(AdjacencyMapGraph<V,E> g, Vertex<V> u) {
        Set<Vertex<V>> known = new HashSet<>();
        List<Pair<Edge<E>, Boolean>> exploratoryEdges = new LinkedList<>();
        BFS(g, u, known, exploratoryEdges);
        return new Pair<>(known, exploratoryEdges);
    }

    public static <V,E> void BFS(AdjacencyMapGraph<V,E> g, Vertex<V> s, Set<Vertex<V>> known,
                                 List<Pair<Edge<E>, Boolean>> exploratoryEdges) {
        // TODO
        throw new  UnsupportedOperationException("Not supported yet.");
    }

}
