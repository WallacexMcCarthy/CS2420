package assign07;

import org.junit.jupiter.api.BeforeEach;


import java.util.Arrays;
import java.util.List;

/**
 * this class contains tests for the GraphUtility class
 *  * @version 10/18/2024
 *  * @author Wallace McCarthy and Isaac Buehner
 */
public class GraphUtilityTest {
    static Graph<Integer> intGraph = new Graph<>();
    @BeforeEach
    public void setUp() {

    }

    public static void main(String[] args) {
        List<String> sources = Arrays.asList("A", "A", "B", "C", "D");
        List<String> destinations = Arrays.asList("B", "C", "D", "D", "E");
        List list = GraphUtility.sort(sources, destinations);
        System.out.println(list);
    }
}
