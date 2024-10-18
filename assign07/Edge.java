package assign07;

public class Edge <T> {
    private Vertex<T> destination;
    private double weight;

    public Edge(Vertex<T> destination, double weight){
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex<T> getDestination(){
        return this.destination;
    }
    public double getWeight(){
        return this.weight;
    }
}
