package es.urjc.grafo.EDA.utils;

import es.urjc.grafo.EDA.graphs.AdjacencyMapGraph;
import es.urjc.grafo.EDA.graphs.Edge;
import es.urjc.grafo.EDA.graphs.Vertex;

import java.util.List;
import java.util.Set;

public class DotGenerator {

    /**
     * Common features for all files.
     * Layout is set as "neato", other layout as "dot", "fdp", "sfdp" "twopi" or "circo" can be selected.
     * More information: <a href="https://www.graphviz.org">Graphviz website</a>
     */
    private static final String graphHeader = """
            graph G {
              layout="neato"
              node[style=filled fillcolor="white", fixedsize=true, shape=circle]""";

    private static final String digraphHeader = """
            digraph G {
              layout="neato"
              node[style=filled fillcolor="white", fixedsize=true, shape=circle]""";

    /**
     * Common feature for all files.
     */
    private static final String footer = "\n}";

    public static <V,E> String generateDotDiagram(AdjacencyMapGraph<V,E> graph,
                                                  Vertex<V> startVertex,
                                                  Set<Vertex<V>> reachedVertices,
                                                  List<Pair<Edge<E>, Boolean>> exploratoryEdges,
                                                  boolean isDirected) {
        String header = isDirected ? digraphHeader : graphHeader;
        return String.join("\n",
                header,
                insertVertices(graph, reachedVertices),
                insertEdges(graph, exploratoryEdges, isDirected),
                modifyNonReachedVertices(graph, reachedVertices),
                modifyStartVertex(graph, startVertex),
                footer);
    }

    private static <V,E> String insertVertices(AdjacencyMapGraph<V,E> graph, Set<Vertex<V>> reachedVertices) {
        StringBuilder vertices = new StringBuilder();
        for (Vertex<V> vertex : graph.vertices()) {
            vertices.append(vertex.getElement())
                    .append("[shape = \"circle\"];\n");
        }
        return vertices.toString();
    }

    private static <V,E> String modifyNonReachedVertices(AdjacencyMapGraph<V,E> graph, Set<Vertex<V>> reachedVertices) {
        StringBuilder vertices = new StringBuilder();
        for (Vertex<V> vertex : graph.vertices()) {
            if (!reachedVertices.contains(vertex)) {
                vertices.append(vertex.getElement())
                        .append("[style = \"filled\", fillcolor = \"red\"];\n");
            }
        }
        return vertices.toString();
    }

    private static <V,E> String modifyStartVertex(AdjacencyMapGraph<V,E> graph, Vertex<V> startVertex) {
        return startVertex.getElement() +
                "[style = \"filled\", fillcolor = \"gray\"];\n";
    }

    private static <V,E> String insertEdges(AdjacencyMapGraph<V,E> graph, List<Pair<Edge<E>, Boolean>> exploratoryEdges, boolean isDirected) {
        StringBuilder edges = new StringBuilder();
        for (Edge<E> edge : graph.edges()) {
            if (exploratoryEdges.stream().noneMatch(p -> p.getFirst().equals(edge))) {
                edges.append(graph.endVertices(edge).getFirst().getElement())
                        .append(isDirected ? " -> " : " -- ")
                        .append(graph.endVertices(edge).getSecond().getElement())
                        .append("[color = \"red\", label = \"Not visited\"];\n");
            }
        }
        for (Pair<Edge<E>, Boolean> edge : exploratoryEdges) {
            edges.append(graph.endVertices(edge.getFirst()).getFirst().getElement())
                    .append(isDirected ? " -> " : " -- ")
                    .append(graph.endVertices(edge.getFirst()).getSecond().getElement())
                    .append("[color = ")
                    .append(edge.getSecond() ? "\"darkgreen\"" : "\"black\"")
                    .append(", label = \"")
                    .append(exploratoryEdges.indexOf(edge))
                    .append("\", style = \"")
                    .append(edge.getSecond() ? "solid" : "dashed")
                    .append("\"];\n");
        }
        return edges.toString();
    }

}