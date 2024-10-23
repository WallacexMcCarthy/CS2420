package assign07;

/**
 * this class represents the edges for a directed, weighted graph
 * @param <T> a generic type
 * @version 10/18/2024
 * @author Wallace McCarthy and Isaac Buehner
 */
public class Edge <T> {
    private final Vertex<T> destination;
    private final double weight;

    /**
     * regular constructor for an Edge object
     * throws an IllegalArgumentException if the given weight is below 0
     * @param destination the edge's destination vertex
     * @param weight the weight of traversing the edge
     */
    public Edge(Vertex<T> destination, double weight){
        if (weight < 0) {
            throw new IllegalArgumentException();
        }
        this.destination = destination;
        this.weight = weight;
    }

    /**
     * this method returns this edge's destination vertex
     * @return a vertex at the end of this edge
     */
    public Vertex<T> getDestination(){
        return this.destination;
    }

    /**
     * this method returns the weight of traversing this edge's weight
     * @return a double representing this edge's weight
     */
    public double getWeight(){
        return this.weight;
    }
}
