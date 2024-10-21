package assign07;

import java.util.List;

/**
 * this class contains the driver methods for the Graph class's traversal and sorting algorithms
 *  * @version 10/18/2024
 *  * @author Isaac Buehner and Wallace McCarthy
 */
public class GraphUtility {

    /**
     * this method is the driver method for the shortestPath method in the Graph class
     * finds and returns a list of vertices representing the shortest path between the srcData's vertex and dstData's vertex
     * throws an IllegalArgumentException if the sources and destinations lists don't have equal length
     * @param sources a list of sources to construct an unweighted graph
     * @param destinations a list of destinations to construct an unweighted graph
     * @param srcData the data for a source or starting vertex
     * @param dstData the data for a destination or end vertex
     * @return a list representing the shortest path from the source data to the destination data
     * @param <Type> the generic type for the unweighted graph
     */
    public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) {
        if(sources.size() != destinations.size()) {
            throw new IllegalArgumentException();
        }
        Graph<Type> graph = new Graph<>();
        for (int i = 0; i < sources.size(); i++) {
            graph.addEdge(sources.get(i), destinations.get(i), 1);
        }
        return graph.shortestPath(srcData, dstData);
    }

    /**
     * this method is the driver method for the shortestWeightedPath method in the Graph class
     * finds and returns a list of vertices representing the shortest path between the srcData's vertex and dstData's vertex
     * throws an IllegalArgumentException if the sources, destinations, and weights lists don't have equal length
     * @param sources a list of sources to construct a weighted graph
     * @param destinations a list of destinations to construct a weighted graph
     * @param weights a list of weights to construct a weighted graph
     * @param srcData the data for a source or starting vertex
     * @param dstData the data for a destination or end vertex
     * @return a list representing the shortest path from the source data to the destination data
     * @param <Type> the generic type for the weighted graph
     */
    public static <Type> List<Type> shortestWeightedPath(List<Type> sources, List<Type> destinations, List<Double> weights, Type srcData, Type dstData) {
        if(sources.size() != destinations.size() || sources.size() != weights.size()) {
            throw new IllegalArgumentException();
        }
        Graph<Type> graph = new Graph<>();
        for (int i = 0; i < sources.size(); i++) {
            graph.addEdge(sources.get(i), destinations.get(i), weights.get(i));
        }
        return graph.shortestWeightedPath(srcData, dstData);
    }

    public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) {
        if(sources.size() != destinations.size()) {
            throw new IllegalArgumentException();
        }
        Graph<Type> graph = new Graph<>();
        for (int i = 0; i < sources.size(); i++) {
            graph.addEdge(sources.get(i), destinations.get(i), 1);
        }
        return graph.topologicalSort();
    }
}
