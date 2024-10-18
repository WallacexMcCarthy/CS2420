package assign07;

import java.util.ArrayList;

public class GraphUtilityTest {
    public static void main(String[] args) {
        ArrayList<Integer> sources = new ArrayList<>();
        ArrayList<Integer> destinations = new ArrayList<>();
        ArrayList<Integer> weights = new ArrayList<>();

        sources.add(1);
        sources.add(2);
        sources.add(4);
        sources.add(1);

        destinations.add(2);
        destinations.add(3);
        destinations.add(3);
        destinations.add(4);


        System.out.println(GraphUtility.shortestPath(sources, destinations, 1, 4));
    }
}
