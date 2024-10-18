package assign07;

import java.util.Comparator;

public class VertexComparator<T> implements Comparator<Vertex<T>> {
    @Override
    public int compare(Vertex<T> vertex1, Vertex<T> vertex2) {
        if(vertex2.getDistanceFromStart() - vertex1.getDistanceFromStart() > 0){
            return 1;
        }else if(vertex2.getDistanceFromStart() - vertex1.getDistanceFromStart() < 0){
            return -1;
        }else{
            return 0;
        }
    }
}
