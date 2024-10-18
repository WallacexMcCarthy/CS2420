package assign07;

import java.util.List;

public class GraphUtility {
    public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) {
        Graph<Type> graph = new Graph<>();
        for (int i = 0; i < sources.size(); i++) {
            graph.addEdge(sources.get(i), destinations.get(i), 1);
        }
        return graph.BFS(srcData, dstData);
    }

    public static <Type> List<Type> shortestWeightedPath(List<Type> sources, List<Type> destinations, List<Double> weights, Type srcData, Type dstData) {
        Graph<Type> graph = new Graph<>();
        for (int i = 0; i < sources.size(); i++) {
            graph.addEdge(sources.get(i), destinations.get(i), weights.get(i));
        }
        return graph.shortestWeightedDistance(srcData, dstData);
    }

    public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) {
        return null;
    }
}
