package assign07;

import java.util.Iterator;
import java.util.LinkedList;

public class Vertex <T>{
    private T data;
    private LinkedList<Edge<T>> adjList = new LinkedList<>();
    private Vertex<T> previous = null;
    private double distanceFromStart;

    public Vertex(T data){
        this.data = data;
    }

    public void addEdge(Vertex<T> destination, double weight){
        adjList.add(new Edge<T>(destination, weight));
    }

    public T getData(){
        return this.data;
    }

    public LinkedList<Edge<T>> getEdges(){
        return this.adjList;
    }
    public void setDistanceFromStart(double distanceFromStart){
        this.distanceFromStart = distanceFromStart;
    }

    public double getDistanceFromStart() {
        return distanceFromStart;
    }

    public Vertex<T> getPrevious() {
        return this.previous;
    }

    public void setPrevious(Vertex<T> vertex) {
        this.previous = vertex;
    }
}
