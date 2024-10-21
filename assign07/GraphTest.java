package assign07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class contains test for the Graph, Vertex, and Edge classes
 *  * @version 10/18/2024
 *  * @author Wallace McCarthy and Isaac Buehner
 */
public class GraphTest {
    private Vertex<Integer> intVertex;
    private Graph<Integer> intGraph;

    @BeforeEach
    public void setUp() {
        intGraph = new Graph<>();
        intVertex = new Vertex<>(1);
    }

    @Test
    public void testGetDataVertex() {
        assertEquals(1, intVertex.getData());
    }

    @Test
    public void testGetAdjacencyListEmpty() {
        assertEquals(Arrays.asList(), intVertex.getEdges());
        assertEquals(0, intVertex.getEdges().size());
    }

    @Test
    public void testAddEdgesOnEmptyVertex() {
        intVertex.addEdge(new Vertex<>(2), 1);
        assertEquals(1, intVertex.getEdges().size());
    }

    @Test
    public void testAddEdgeToSelf() {
        intVertex.addEdge(intVertex, 1);
        assertEquals(1, intVertex.getEdges().size());
    }

    @Test
    public void testAddEdgesMultipleConnected() {
        intVertex.addEdge(new Vertex<>(2), 1);
        intVertex.addEdge(new Vertex<>(3), 1);
        intVertex.addEdge(new Vertex<>(4), 1);

        assertEquals(3, intVertex.getEdges().size());
    }

    @Test
    public void testAddEdgesMultipleDisconnected() {
        intVertex.addEdge(new Vertex<>(2), 1);
        intVertex.addEdge(new Vertex<>(3), 1);
        intVertex.addEdge(new Vertex<>(4), 1);

        Vertex<Integer> newVertex = new Vertex<>(0);
        newVertex.addEdge(new Vertex<>(5), 1);
        newVertex.addEdge(new Vertex<>(6), 1);
        newVertex.addEdge(intVertex, 1);

        assertEquals(3, intVertex.getEdges().size());
        assertEquals(3, newVertex.getEdges().size());
    }

    @Test
    public void testAddEdgesCyclic() {
        Vertex<Integer> newVertex = new Vertex<>(0);
        intVertex.addEdge(newVertex, 1);
        newVertex.addEdge(intVertex, 1);

        assertEquals(1, intVertex.getEdges().size());
    }

    @Test
    public void testGetAdjacencyListSimple() {
        intVertex.addEdge(new Vertex<>(2), 1);

        assertEquals(2, intVertex.getEdges().get(0).getDestination().getData());
        assertEquals(1, intVertex.getEdges().get(0).getWeight());
    }

    @Test
    public void testGetAdjacencyListComplexConnected() {
        intVertex.addEdge(new Vertex<>(2), 0);
        intVertex.addEdge(new Vertex<>(3), 1);
        intVertex.addEdge(new Vertex<>(4), 2);

        for (int i = 0; i < intVertex.getEdges().size(); i++) {
            assertEquals(i + 2, intVertex.getEdges().get(i).getDestination().getData());
            assertEquals(i, intVertex.getEdges().get(i).getWeight());
        }
    }

    @Test
    public void testGetAdjacencyListComplexDisconnected() {
        intVertex.addEdge(new Vertex<>(2), 0);
        intVertex.addEdge(new Vertex<>(3), 1);
        Vertex<Integer> newVertex = new Vertex<>(0);
        newVertex.addEdge(new Vertex<>(4), 0);
        newVertex.addEdge(new Vertex<>(5), 1);

        for (int i = 0; i < intVertex.getEdges().size(); i++) {
            assertEquals(i + 2, intVertex.getEdges().get(i).getDestination().getData());
            assertEquals(i, intVertex.getEdges().get(i).getWeight());
        }

        for (int i = 0; i < newVertex.getEdges().size(); i++) {
            assertEquals(i + 4, newVertex.getEdges().get(i).getDestination().getData());
            assertEquals(i, newVertex.getEdges().get(i).getWeight());
        }
    }

    @Test
    public void testGetDistanceFromStart() {
        assertEquals(0, intVertex.getDistanceFromStart());
    }

    @Test
    public void testSetDistanceFromStart() {
        intVertex.setDistanceFromStart(1);
        assertEquals(1, intVertex.getDistanceFromStart());
    }

    @Test
    public void testSetDistanceFromStartNegative() {
        assertThrows(IllegalArgumentException.class, () -> intVertex.setDistanceFromStart(-1));
    }

    @Test
    public void testGetPrevious() {
        assertEquals(null, intVertex.getPrevious());
    }

    @Test
    public void testSetPrevious() {
        intVertex.setPrevious(new Vertex<>(2));
        assertEquals(2, intVertex.getPrevious().getData());
    }

    @Test
    public void testEdgeGetWeight() {
        Edge<Integer> edge = new Edge<>(intVertex, 1);
        assertEquals(1, edge.getWeight());
    }

    @Test
    public void testEdgeGetDestination() {
        Edge<Integer> edge = new Edge<>(intVertex, 1);
        assertEquals(1, edge.getDestination().getData());
    }
}
