package pa10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void testTopologicalSortDFS() {
        Graph graph = new GraphImplementation(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        String result = graph.topologicalSort();
        boolean isValid = false;
        if (result.equals("[5, 4, 2, 3, 1, 0]")) {
            isValid = true;
        }
        if (result.equals("[4, 5, 2, 3, 1, 0]")) {
            isValid = true;
        }
        assertTrue(isValid);
    }

    @Test
    void testKahnAlgorithm() {
        Graph graph = new GraphImplementation(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        String result = graph.kahn();
        boolean isValid = false;
        if (result.equals("[4, 5, 2, 0, 3, 1]")) {
            isValid = true;
        }
        if (result.equals("[5, 4, 2, 0, 3, 1]")) {
            isValid = true;
        }
        assertTrue(isValid);
    }

    @Test
    void testKahnAlgorithmWithCycle() {
        Graph graph = new GraphImplementation(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        String result = graph.kahn();
        assertEquals("Graph has a cycle", result);
    }
}