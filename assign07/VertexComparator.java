package assign07;

import java.util.Comparator;

public class VertexComparator<T> implements Comparator<Vertexs<T>> {
    @Override
    public int compare(Vertexs<T> vertex1, Vertexs<T> vertex2) {
        if (vertex2.getDistanceFromStart() - vertex1.getDistanceFromStart() > 0) {
            return 1;
        } else if (vertex2.getDistanceFromStart() - vertex1.getDistanceFromStart() < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
