package assign07;

import java.util.*;

/**
 * this class uses the Edge and Vertex classes to create and traverse a directed weighted graph
 * @param <T> a generic type
 * @version 10/18/2024
 * @author Isaac Buehner and Wallace McCarthy
 */
public class Graph <T> {
    private final Map<T, Vertex<T>> map;

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
        Vertex<T> sourceVertex;
        if(map.containsKey(source)) {
            sourceVertex = map.get(source);
        } else {
            sourceVertex = new Vertex<>(source);
            map.put(source, sourceVertex);
        }

        Vertex<T> destinationVertex;
        if(map.containsKey(destination)) {
            destinationVertex = map.get(destination);
        } else {
            destinationVertex = new Vertex<>(destination);
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

        Vertex<T> current = map.get(source);
        for (Vertex<T> v : map.values()) {
            v.setDistanceFromStart(Double.MAX_VALUE);
        }

        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.offer(current);
        current.setDistanceFromStart(0);
        while(!queue.isEmpty()) {
            current = queue.poll();
            for (Edge<T> edge : current.getEdges()) {
                // if a vertex has a distance from start of -1 (not visited), set the distance from the start to 1 + the previous
                if (edge.getDestination().getDistanceFromStart() == Double.MAX_VALUE) {
                    edge.getDestination().setDistanceFromStart(current.getDistanceFromStart() + 1);
                    edge.getDestination().setPrevious(current);
                    queue.offer(edge.getDestination());
                }
            }
        }
        if (map.get(destination).getDistanceFromStart() == -1) {
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
     * @param source the starting vertex in the search
     * @param destination the ending vertex in the search
     * @return a linked list containing the path from source to destination
     */
    public List<T> shortestWeightedPath(T source, T destination) {
        if (!map.containsKey(source) || !map.containsKey(destination)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Vertex<T>> unvisited = new ArrayList<>();
        for (Vertex<T> v : map.values()) {
            v.setDistanceFromStart(Double.MAX_VALUE);
            unvisited.add(v);
        }

        Vertex<T> current = map.get(source);
        current.setDistanceFromStart(0);
        while (!unvisited.isEmpty()) {
            unvisited.sort(new VertexComparator<>());
            Vertex<T> closest = unvisited.getLast();
            for (Edge<T> edge : closest.getEdges()) {
                // if the smallest distance from the start plus its current edge's weight is less than the
                // edge's destination's current distance from the start, reset the destinations distance
                if (closest.getDistanceFromStart() + edge.getWeight() < edge.getDestination().getDistanceFromStart()) {
                    edge.getDestination().setDistanceFromStart(closest.getDistanceFromStart() + edge.getWeight());
                    edge.getDestination().setPrevious(current);
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

    public List<T> topologicalSort() {
        Queue<Vertex<T>> queue = new LinkedList<>();
        for (Vertex<T> vertex: map.values()) {
            if (vertex.getInDegree() == 0) {
                queue.offer(vertex);
            }
        }

        LinkedList<T> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();
            result.addLast(current.getData());
            for (Edge<T> edge : current.getEdges()) {
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
