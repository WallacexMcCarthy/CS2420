package assign07;

import java.util.*;

/**
 * this class uses the Edge and Vertex classes to create and traverse a directed weighted graph
 * @param <T> a generic type
 * @version 10/18/2024
 * @author Isaac Buehner and Wallace McCarthy
 */
public class Graph <T> {
    private final Map<T, Vertexs<T>> map;

    /**
     * the regular constructor for a Graph object
     */
    public Graph() {
        map = new HashMap<>();
    }

    /**
     * this method adds an edge to this graph object
     * if the source or destination vertices aren't in the map, add new vertices
     * @param source the vertex for the new edge's source
     * @param destination the vertex for the new edge's destination
     * @param weight the weight of the new edge
     */
    public void addEdge(T source, T destination, double weight) {
        Vertexs<T> sourceVertex;
        if(map.containsKey(source)) {
            sourceVertex = map.get(source);
        } else {
            sourceVertex = new Vertexs<>(source);
            map.put(source, sourceVertex);
        }

        Vertexs<T> destinationVertex;
        if(map.containsKey(destination)) {
            destinationVertex = map.get(destination);
        } else {
            destinationVertex = new Vertexs<>(destination);
            map.put(destination, destinationVertex);
        }
        sourceVertex.addEdge(destinationVertex, weight);
    }

    /**
     * this method finds and returns a list containing the shortest path of
     * vertices from source to destination for an unweighted graph
     * uses the breadth first search graph traversal algorithm
     * throws an IllegalArgumentException if the source or destination isn't in the map OR
     * if there is no path between the source and destination
     * @param source the starting vertex in the search
     * @param destination the ending vertex in the search
     * @return a linked list containing the path from source to destination
     */
    public List<T> shortestPath(T source, T destination) {
        if (!map.containsKey(source) || !map.containsKey(destination)) {
            throw new IllegalArgumentException();
        }

        for (Vertexs<T> v : map.values()) {
            v.setDistanceFromStart(Double.MAX_VALUE);
        }
        Vertexs<T> current = map.get(source);
        current.setDistanceFromStart(0);

        Queue<Vertexs<T>> queue = new LinkedList<>();
        queue.offer(current);
        while(!queue.isEmpty()) {
            current = queue.poll();
            for (Edges<T> edge : current.getEdges()) {
                // if a vertex has a distance from start of INF (not visited), set the distance from the start to 1 + the previous
                if (edge.getDestination().getDistanceFromStart() == Double.MAX_VALUE) {
                    edge.getDestination().setDistanceFromStart(current.getDistanceFromStart() + 1);
                    edge.getDestination().setPrevious(current);
                    queue.offer(edge.getDestination());
                }
            }
        }
        if (map.get(destination).getDistanceFromStart() == Double.MAX_VALUE) {
            throw new IllegalArgumentException();
        }

        LinkedList<T> list = new LinkedList<>();
        T currentData = destination;
        // from the destination vertex, work backwards through the 'previous' vertices until one is null
        while(map.get(currentData).getPrevious() != null) {
            list.addFirst(currentData);
            currentData = map.get(currentData).getPrevious().getData();
        }
        list.addFirst(currentData);
        return list;
    }

    /**
     * this method finds and returns a list containing the shortest path of
     * vertices from source to destination for a weighted graph
     * uses Dijkstra's graph traversal algorithm
     * throws an IllegalArgumentException if the source or destination isn't in the map OR
     * if there is no path between the source and destination
     * @param source the starting vertex in the search
     * @param destination the ending vertex in the search
     * @return a linked list containing the path from source to destination
     */
    public List<T> shortestWeightedPath(T source, T destination) {
        if (!map.containsKey(source) || !map.containsKey(destination)) {
            throw new IllegalArgumentException();
        }
        LinkedList<Vertexs<T>> unvisited = new LinkedList<>();
        for (Vertexs<T> v : map.values()) {
            v.setDistanceFromStart(Double.MAX_VALUE);
            unvisited.add(v);
        }

        Vertexs<T> current = map.get(source);
        current.setDistanceFromStart(0);
        while (!unvisited.isEmpty()) {
            unvisited.sort(new VertexComparator<>());
            Vertexs<T> closest = unvisited.getLast();
            for (Edges<T> edge : closest.getEdges()) {
                // if the smallest distance from the start plus its current edge's weight is less than the
                // edge's destination's current distance from the start, reset the destinations distance
                if (closest.getDistanceFromStart() + edge.getWeight() < edge.getDestination().getDistanceFromStart()) {
                    edge.getDestination().setDistanceFromStart(closest.getDistanceFromStart() + edge.getWeight());
                    edge.getDestination().setPrevious(closest);
                }
            }
            unvisited.removeLast();
        }

        if (map.get(destination).getDistanceFromStart() == Double.MAX_VALUE) {
            throw new IllegalArgumentException();
        }

        LinkedList<T> list = new LinkedList<>();
        T currentData = destination;
        // from the destination vertex, work backwards through the 'previous' vertices until one is null
        while(map.get(currentData).getPrevious() != null) {
            list.addFirst(currentData);
            currentData = map.get(currentData).getPrevious().getData();
        }
        list.addFirst(currentData);
        return list;
    }

    /**
     * this method finds a returns a list of the vertices in this graph sorted by topographical order
     * throws an IllegalArgumentException when the graph is not acyclic
     * @return a list of vertices sorted by number of input edges
     */
    public List<T> topologicalSort() {
        Queue<Vertexs<T>> queue = new LinkedList<>();
        for (Vertexs<T> vertex: map.values()) {
            if (vertex.getInDegree() == 0) {
                queue.offer(vertex);
            }
        }

        LinkedList<T> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            Vertexs<T> current = queue.poll();
            result.addLast(current.getData());
            for (Edges<T> edge : current.getEdges()) {
                edge.getDestination().decreaseInDegree();
                if (edge.getDestination().getInDegree() == 0) {
                    queue.offer(edge.getDestination());
                }
            }
        }

        if(result.size() != map.size()) {
            throw new IllegalArgumentException();
        }
        return result;
    }

}
