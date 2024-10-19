package assign07;

import java.util.ArrayList;

/**
 * this class contains tests for the GraphUtility class
 *  * @version 10/18/2024
 *  * @author Wallace McCarthy and Isaac Buehner
 */
public class GraphUtilityTest {
    public static void main(String[] args) {
        ArrayList<Integer> sources = new ArrayList<>();
        ArrayList<Integer> destinations = new ArrayList<>();
        ArrayList<Integer> weights = new ArrayList<>();

        sources.add(1);
        sources.add(2);
        sources.add(4);
        sources.add(1);
        sources.add(4);

        destinations.add(2);
        destinations.add(3);
        destinations.add(3);
        destinations.add(4);
        destinations.add(1);


        System.out.println(GraphUtility.shortestPath(sources, destinations, 1, 3));
    }
}
