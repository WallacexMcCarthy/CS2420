package assign07;

import java.util.*;

public class Graph <T> {
    private Map<T, Vertex<T>> map;
    private boolean weighted;

    public Graph(boolean weighted) {
        map = new HashMap<>();
        this.weighted = weighted;
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
            map.put(source, destinationVertex);
        }
        if (weighted) {
            sourceVertex.addEdge(destinationVertex, weight);
        } else {
            sourceVertex.addEdge(destinationVertex, 1);
        }
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
        return list;
    }

}
