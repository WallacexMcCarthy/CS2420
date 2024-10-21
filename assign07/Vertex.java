package assign07;

import java.util.LinkedList;

/**
 * this class represents the vertices for a directed, weighted graph
 * @param <T> a generic type
 * @version 10/18/2024
 * @author Wallace McCarthy and Isaac Buehner
 */
public class Vertex <T>{
    private final T data;
    private final LinkedList<Edge<T>> adjList = new LinkedList<>();
    private Vertex<T> previous = null;
    private double distanceFromStart;
    private int inDegree = 0;

    /**
     * the regular constructor for a Vertex object
     * @param data the data to be stored in this vertex
     */
    public Vertex(T data){
        this.data = data;
    }

    /**
     * this method adds an edge to this vertex connecting it to another vertex or itself and adds 1 to its inDegree
     * the vertex connected is also add to this vertex's adjacency list
     * @param destination a vertex for a new edge to be connected to
     * @param weight the weight of the new edge
     */
    public void addEdge(Vertex<T> destination, double weight){
        // maybe worth making sure multiple of the same vertex cant be added
        adjList.add(new Edge<>(destination, weight));
        destination.increaseInDegree();
    }

    /**
     * this method returns the data stored in this vertex
     * @return this vertex's data
     */
    public T getData(){
        return this.data;
    }

    /**
     * this method returns the adjacency list associated with this vertex
     * @return the list of vertices connected to this vertex
     */
    public LinkedList<Edge<T>> getEdges(){
        return this.adjList;
    }

    /**
     * this method returns this vertex's distance from a starting vertex | to be used with traversal algorithms
     * @return a double representing this vertex's distance from a starting vertex
     */
    public double getDistanceFromStart() {
        return distanceFromStart;
    }

    /**
     * this method returns the vertex that comes before this vertex in relation to a starting vertex
     * to be used with traversal algorithms
     * @return a vertex that comes before this vertex in relation to a starting vertex
     */
    public Vertex<T> getPrevious() {
        return this.previous;
    }

    /**
     * this method returns this vertex's inDegree
     * @return the number of input edges attached to this vertex
     */
    public int getInDegree() {
        return this.inDegree;
    }

    /**
     * this method sets this vertex's distance from a starting vertex | to be used in traversal algorithms
     * @param distanceFromStart the new distance from the starting vertex
     */
    public void setDistanceFromStart(double distanceFromStart){
        if (distanceFromStart < 0) {
            throw new IllegalArgumentException();
        }
        this.distanceFromStart = distanceFromStart;
    }

    /**
     * this method sets this vertex's previous vertex in relation to a starting vertex
     * to be used in traversal algorithms
     * @param vertex the previous vertex to this vertex
     */
    public void setPrevious(Vertex<T> vertex) {
        this.previous = vertex;
    }

    /**
     * this method increases this vertex's inDegree by 1
     */
    public void increaseInDegree() {
        inDegree++;
    }

    /**
     * this method decreases this vertex's inDegree by 1
     */
    public void decreaseInDegree() {
        inDegree--;
    }
}
