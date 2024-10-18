package assign07;

import java.util.Iterator;
import java.util.LinkedList;

public class Vertex <T> {
    private T data;
    private LinkedList<Edge<T>> adjList = new LinkedList<>();
    private Vertex<T> previous = null;
    private int distanceFromStart = -1;

    public Vertex(T data){
        this.data = data;
    }

    public void addEdge(Vertex<T> destination, double weight){
        adjList.add(new Edge<T>(destination, weight));
    }

    public T getData(){
        return this.data;
    }

    public Iterator<Edge<T>> edgeIterator(){
        return this.adjList.iterator();
    }
    public void setDistanceFromStart(int distanceFromStart){
        this.distanceFromStart = distanceFromStart;
    }
}
