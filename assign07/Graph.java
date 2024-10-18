package assign07;

import java.util.*;

public class Graph <T> {
    private Map<T, Vertex<T>> map;

    public Graph() {
        map = new HashMap<>();
    }

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

    public List<T> BFS(T source, T destination) {
        if (!map.containsKey(source) || !map.containsKey(destination)) {
            throw new IllegalArgumentException();
        }

        Vertex<T> current = map.get(source);
        for (Vertex<T> v : map.values()) {
            v.setDistanceFromStart(-1);
        }
        Queue<Vertex<T>> queue = new LinkedList<>();

        queue.offer(current);
        current.setDistanceFromStart(0);
        while(!queue.isEmpty()) {
            current = queue.poll();
            for (Edge<T> edge : current.getEdges()) {
                if (edge.getDestination().getDistanceFromStart() == -1) {
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
        while(map.get(currentData).getPrevious() != null) {
            list.addLast(currentData);
            currentData = map.get(currentData).getPrevious().getData();
        }
        list.addLast(currentData);
        return list;
    }

    public List<T> shortestWeightedDistance(T source, T destination) {
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
        while(map.get(currentData).getPrevious() != null) {
            list.addLast(currentData);
            currentData = map.get(currentData).getPrevious().getData();
        }
        list.addLast(currentData);
        return list;
    }

}
