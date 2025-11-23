import es.urjc.grafo.EDA.graphs.*;
import es.urjc.grafo.EDA.utils.DotGenerator;
import es.urjc.grafo.EDA.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


public class AlgorithmsTest {

    private static final String[][] edgesFirstExampleGraph = {
            {"A","B"}, {"A","E"}, {"A","F"}, {"B","C"}, {"B","F"},
            {"C","D"}, {"C","G"}, {"D","G"}, {"D","H"}, {"E","F"},
            {"E","I"}, {"F","I"}, {"G","J"}, {"G","K"}, {"G","L"},
            {"H","L"}, {"I","J"}, {"I","M"}, {"I","N"}, {"J","K"},
            {"K","N"}, {"K","O"}, {"L","P"}, {"M","N"},
    };
    private static final Set<String> availableVerticesFirstExample =
            new HashSet<>(
                    Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P")
            );

    private static AdjacencyMapGraph<String, Integer> directedGraphFirstExample;
    private static AdjacencyMapGraph<String, Integer> undirectedGraphFirstExample;

    @BeforeEach
    public void setUpDirectedGraph() {
        directedGraphFirstExample = AdjacencyMapGraph.graphFromEdgelist(edgesFirstExampleGraph, true);
    }

    @BeforeEach
    public void setUpUndirectedGraph() {
        undirectedGraphFirstExample = AdjacencyMapGraph.graphFromEdgelist(edgesFirstExampleGraph, false);
    }

    @Test
    public void testDFSDirectedGraph() {
        Pair<Set<Vertex<String>>, List<Pair<Edge<Integer>, Boolean>>> result = GraphAlgorithms.DFS(directedGraphFirstExample, directedGraphFirstExample.getVertex("A"));
        Set<Vertex<String>> known = result.getFirst();
        List<Pair<Edge<Integer>, Boolean>> exploratoryEdges = result.getSecond();
        assertEquals(availableVerticesFirstExample, known.stream().map(Vertex::getElement).collect(Collectors.toSet()));
        assertEquals(availableVerticesFirstExample.size(), known.size());
        // Check that the sum of exploratory edges and back edges is equal to the total number of edges
        assertEquals(directedGraphFirstExample.edges().size(), exploratoryEdges.size());

        Pair<Edge<Integer>, Boolean> edgeAB = exploratoryEdges.stream()
                .filter(pair -> directedGraphFirstExample.startVertex(pair.getFirst()).getElement().equals("A")
                        && directedGraphFirstExample.endVertex(pair.getFirst()).getElement().equals("B"))
                .findFirst()
                .orElse(null);
        assertNotNull(edgeAB);
        Pair<Edge<Integer>, Boolean> edgeAE = exploratoryEdges.stream()
                .filter(pair -> directedGraphFirstExample.startVertex(pair.getFirst()).getElement().equals("A")
                        && directedGraphFirstExample.endVertex(pair.getFirst()).getElement().equals("E"))
                .findFirst()
                .orElse(null);
        assertNotNull(edgeAE);
        Pair<Edge<Integer>, Boolean> edgeAF = exploratoryEdges.stream()
                .filter(pair -> directedGraphFirstExample.startVertex(pair.getFirst()).getElement().equals("A")
                        && directedGraphFirstExample.endVertex(pair.getFirst()).getElement().equals("F"))
                .findFirst()
                .orElse(null);
        assertNotNull(edgeAF);

        // Ensure that these are NOT the first three edges visited by the algorithm
        assertTrue(exploratoryEdges.indexOf(edgeAB) + exploratoryEdges.indexOf(edgeAE) + exploratoryEdges.indexOf(edgeAF) > 3);

        visualizePath(directedGraphFirstExample, directedGraphFirstExample.getVertex("A"), known, exploratoryEdges, true);
    }

    @Test
    public void testBFSDirectedGraph() {
        Pair<Set<Vertex<String>>, List<Pair<Edge<Integer>, Boolean>>> result = GraphAlgorithms.BFS(directedGraphFirstExample, directedGraphFirstExample.getVertex("A"));
        Set<Vertex<String>> known = result.getFirst();
        List<Pair<Edge<Integer>, Boolean>> exploratoryEdges = result.getSecond();
        assertEquals(availableVerticesFirstExample, known.stream().map(Vertex::getElement).collect(Collectors.toSet()));
        assertEquals(availableVerticesFirstExample.size(), known.size());
        // Check that the sum of exploratory edges and back edges is equal to the total number of edges
        assertEquals(directedGraphFirstExample.edges().size(), exploratoryEdges.size());

        Pair<Edge<Integer>, Boolean> edgeAB = exploratoryEdges.stream()
                .filter(pair -> directedGraphFirstExample.startVertex(pair.getFirst()).getElement().equals("A")
                        && directedGraphFirstExample.endVertex(pair.getFirst()).getElement().equals("B"))
                .findFirst()
                .orElse(null);
        assertNotNull(edgeAB);
        Pair<Edge<Integer>, Boolean> edgeAE = exploratoryEdges.stream()
                .filter(pair -> directedGraphFirstExample.startVertex(pair.getFirst()).getElement().equals("A")
                        && directedGraphFirstExample.endVertex(pair.getFirst()).getElement().equals("E"))
                .findFirst()
                .orElse(null);
        assertNotNull(edgeAE);
        Pair<Edge<Integer>, Boolean> edgeAF = exploratoryEdges.stream()
                .filter(pair -> directedGraphFirstExample.startVertex(pair.getFirst()).getElement().equals("A")
                        && directedGraphFirstExample.endVertex(pair.getFirst()).getElement().equals("F"))
                .findFirst()
                .orElse(null);
        assertNotNull(edgeAF);

        // Ensure that these are the first three edges visited by the algorithm
        assertTrue(exploratoryEdges.indexOf(edgeAB) + exploratoryEdges.indexOf(edgeAE) + exploratoryEdges.indexOf(edgeAF) <= 3);

        visualizePath(directedGraphFirstExample, directedGraphFirstExample.getVertex("A"), known, exploratoryEdges, true);
    }

    @Test
    public void testDFSUndirectedGraph() {
        Pair<Set<Vertex<String>>, List<Pair<Edge<Integer>, Boolean>>> result = GraphAlgorithms.DFS(undirectedGraphFirstExample, undirectedGraphFirstExample.getVertex("A"));
        Set<Vertex<String>> known = result.getFirst();
        List<Pair<Edge<Integer>, Boolean>> exploratoryEdges = result.getSecond();
        assertEquals(availableVerticesFirstExample, known.stream().map(Vertex::getElement).collect(Collectors.toSet()));
        assertEquals(availableVerticesFirstExample.size(), known.size());
        // Check that the sum of exploratory edges and back edges is equal to the total number of edges
        assertEquals(undirectedGraphFirstExample.edges().size(), exploratoryEdges.size());

        Pair<Edge<Integer>, Boolean> edgeAB = exploratoryEdges.stream()
                .filter(pair -> undirectedGraphFirstExample.endVertices(pair.getFirst()).getFirst().getElement().equals("A")
                        && undirectedGraphFirstExample.endVertices(pair.getFirst()).getSecond().getElement().equals("B"))
                .findFirst()
                .orElse(null);
        assertNotNull(edgeAB);
        Pair<Edge<Integer>, Boolean> edgeAE = exploratoryEdges.stream()
                .filter(pair -> undirectedGraphFirstExample.endVertices(pair.getFirst()).getFirst().getElement().equals("A")
                        && undirectedGraphFirstExample.endVertices(pair.getFirst()).getSecond().getElement().equals("E"))
                .findFirst()
                .orElse(null);
        assertNotNull(edgeAE);
        Pair<Edge<Integer>, Boolean> edgeAF = exploratoryEdges.stream()
                .filter(pair -> undirectedGraphFirstExample.endVertices(pair.getFirst()).getFirst().getElement().equals("A")
                        && undirectedGraphFirstExample.endVertices(pair.getFirst()).getSecond().getElement().equals("F"))
                .findFirst()
                .orElse(null);
        assertNotNull(edgeAF);

        // Ensure that these are NOT the first three edges visited by the algorithm
        assertTrue(exploratoryEdges.indexOf(edgeAB) + exploratoryEdges.indexOf(edgeAE) + exploratoryEdges.indexOf(edgeAF) > 3);

        visualizePath(undirectedGraphFirstExample, undirectedGraphFirstExample.getVertex("A"), known, exploratoryEdges, false);
    }

    @Test
    public void testBFSUndirectedGraph() {
        Pair<Set<Vertex<String>>, List<Pair<Edge<Integer>, Boolean>>> result = GraphAlgorithms.BFS(undirectedGraphFirstExample, undirectedGraphFirstExample.getVertex("A"));
        Set<Vertex<String>> known = result.getFirst();
        List<Pair<Edge<Integer>, Boolean>> exploratoryEdges = result.getSecond();
        assertEquals(availableVerticesFirstExample, known.stream().map(Vertex::getElement).collect(Collectors.toSet()));
        assertEquals(availableVerticesFirstExample.size(), known.size());
        // Check that the sum of exploratory edges and back edges is equal to the total number of edges
        assertEquals(undirectedGraphFirstExample.edges().size(), exploratoryEdges.size());

        Pair<Edge<Integer>, Boolean> edgeAB = exploratoryEdges.stream()
                .filter(pair -> undirectedGraphFirstExample.endVertices(pair.getFirst()).getFirst().getElement().equals("A")
                        && undirectedGraphFirstExample.endVertices(pair.getFirst()).getSecond().getElement().equals("B"))
                .findFirst()
                .orElse(null);
        assertNotNull(edgeAB);
        Pair<Edge<Integer>, Boolean> edgeAE = exploratoryEdges.stream()
                .filter(pair -> undirectedGraphFirstExample.endVertices(pair.getFirst()).getFirst().getElement().equals("A")
                        && undirectedGraphFirstExample.endVertices(pair.getFirst()).getSecond().getElement().equals("E"))
                .findFirst()
                .orElse(null);
        assertNotNull(edgeAE);
        Pair<Edge<Integer>, Boolean> edgeAF = exploratoryEdges.stream()
                .filter(pair -> undirectedGraphFirstExample.endVertices(pair.getFirst()).getFirst().getElement().equals("A")
                        && undirectedGraphFirstExample.endVertices(pair.getFirst()).getSecond().getElement().equals("F"))
                .findFirst()
                .orElse(null);
        assertNotNull(edgeAF);

        // Ensure that these are the first three edges visited by the algorithm
        assertTrue(exploratoryEdges.indexOf(edgeAB) + exploratoryEdges.indexOf(edgeAE) + exploratoryEdges.indexOf(edgeAF) <= 3);

        visualizePath(undirectedGraphFirstExample, undirectedGraphFirstExample.getVertex("A"), known, exploratoryEdges, false);
    }

    private static <V,E> void visualizePath(AdjacencyMapGraph<V,E> graph, Vertex<V> startVertex, Set<Vertex<V>> known, List<Pair<Edge<E>, Boolean>> exploratoryEdges, boolean isDirected) {
        System.out.println("Para ver  el recorrido realizado por el algoritmo, abre el siguiente enlace: \n" +
                "https://dreampuf.github.io/GraphvizOnline/#"
                + URLEncoder
                .encode(
                        DotGenerator.generateDotDiagram(
                                graph,
                                startVertex,
                                known,
                                exploratoryEdges,
                                isDirected),
                        StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20")
        );
    }

}
