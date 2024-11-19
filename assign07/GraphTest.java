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
    private Vertexs<Integer> intVertex;

    @BeforeEach
    public void setUp() {
        intVertex = new Vertexs<>(1);
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
        intVertex.addEdge(new Vertexs<>(2), 1);
        assertEquals(1, intVertex.getEdges().size());
    }

    @Test
    public void testAddEdgeToSelf() {
        intVertex.addEdge(intVertex, 1);
        assertEquals(1, intVertex.getEdges().size());
    }

    @Test
    public void testAddEdgesMultipleConnected() {
        intVertex.addEdge(new Vertexs<>(2), 1);
        intVertex.addEdge(new Vertexs<>(3), 1);
        intVertex.addEdge(new Vertexs<>(4), 1);

        assertEquals(3, intVertex.getEdges().size());
    }

    @Test
    public void testAddEdgesMultipleDisconnected() {
        intVertex.addEdge(new Vertexs<>(2), 1);
        intVertex.addEdge(new Vertexs<>(3), 1);
        intVertex.addEdge(new Vertexs<>(4), 1);

        Vertexs<Integer> newVertex = new Vertexs<>(0);
        newVertex.addEdge(new Vertexs<>(5), 1);
        newVertex.addEdge(new Vertexs<>(6), 1);
        newVertex.addEdge(intVertex, 1);

        assertEquals(3, intVertex.getEdges().size());
        assertEquals(3, newVertex.getEdges().size());
    }

    @Test
    public void testAddEdgesCyclic() {
        Vertexs<Integer> newVertex = new Vertexs<>(0);
        intVertex.addEdge(newVertex, 1);
        newVertex.addEdge(intVertex, 1);

        assertEquals(1, intVertex.getEdges().size());
    }

    @Test
    public void testGetAdjacencyListSimple() {
        intVertex.addEdge(new Vertexs<>(2), 1);

        assertEquals(2, intVertex.getEdges().get(0).getDestination().getData());
        assertEquals(1, intVertex.getEdges().get(0).getWeight());
    }

    @Test
    public void testGetAdjacencyListComplexConnected() {
        intVertex.addEdge(new Vertexs<>(2), 0);
        intVertex.addEdge(new Vertexs<>(3), 1);
        intVertex.addEdge(new Vertexs<>(4), 2);

        for (int i = 0; i < intVertex.getEdges().size(); i++) {
            assertEquals(i + 2, intVertex.getEdges().get(i).getDestination().getData());
            assertEquals(i, intVertex.getEdges().get(i).getWeight());
        }
    }

    @Test
    public void testGetAdjacencyListComplexDisconnected() {
        intVertex.addEdge(new Vertexs<>(2), 0);
        intVertex.addEdge(new Vertexs<>(3), 1);
        Vertexs<Integer> newVertex = new Vertexs<>(0);
        newVertex.addEdge(new Vertexs<>(4), 0);
        newVertex.addEdge(new Vertexs<>(5), 1);

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
        intVertex.setPrevious(new Vertexs<>(2));
        assertEquals(2, intVertex.getPrevious().getData());
    }

    @Test
    public void testEdgeGetWeight() {
        Edges<Integer> edge = new Edges<>(intVertex, 1);
        assertEquals(1, edge.getWeight());
    }

    @Test
    public void testEdgeGetDestination() {
        Edges<Integer> edge = new Edges<>(intVertex, 1);
        assertEquals(1, edge.getDestination().getData());
    }
}
