package assign07;

import java.util.Arrays;
import java.util.List;

/**
 * this class contains tests for the GraphUtility class
 *  * @version 10/18/2024
 *  * @author Wallace McCarthy and Isaac Buehner
 */
public class GraphUtilityTest {

    public static void main(String[] args) {
        List<String> sources = Arrays.asList("A", "A", "B", "C", "D", "A");
        List<String> destinations = Arrays.asList("B", "C", "D", "D", "E", "E");
        List<Double> weights = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
        List<String> list = GraphUtility.shortestWeightedPath(sources, destinations, weights, "A", "E");
        System.out.println(list);
    }

}
