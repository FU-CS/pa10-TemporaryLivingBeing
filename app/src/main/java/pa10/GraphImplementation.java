package pa10;
import java.util.*;

public class GraphImplementation implements Graph {
    private final int vertices;
    private final List<int[]> edges;

    public GraphImplementation(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    @Override
    public void addEdge(int v, int w) {
        edges.add(new int[]{v, w});
    }

    @Override
    public String topologicalSort() {
        int[] visited = new int[vertices];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < vertices; i++) {
            if (visited[i] == 0) {
                if (dfs(i, visited, stack)) {
                    return null;
                }
            }
        }

        String result = "[";
        while (!stack.isEmpty()) {
            result += stack.pop();
            if (!stack.isEmpty()) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }

    private boolean dfs(int v, int[] visited, Stack<Integer> stack) {
        if (visited[v] == 1) {
            return true;
        }
        if (visited[v] == 2) {
            return false;
        }

        visited[v] = 1;
        
        for (int[] edge : edges) {
            if (edge[0] == v) {
                if (dfs(edge[1], visited, stack)) {
                    return true;
                }
            }
        }

        visited[v] = 2;
        stack.push(v);
        return false;
    }

    @Override
    public String kahn() {
        int[] inDegree = new int[vertices];
        
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();
            result.add(v);

            for (int[] edge : edges) {
                if (edge[0] == v) {
                    inDegree[edge[1]]--;
                    if (inDegree[edge[1]] == 0) {
                        queue.add(edge[1]);
                    }
                }
            }
        }

        if (result.size() == vertices) {
            return result.toString();
        } else {
            return "Graph has a cycle";
        }
    }
}