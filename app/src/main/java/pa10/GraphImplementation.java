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
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i, visited, stack);
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
    
    private void dfs(int v, boolean[] visited, Stack<Integer> stack) {
        if (visited[v]) {
            return;
        }
    
        visited[v] = true;
        
        for (int[] edge : edges) {
            if (edge[0] == v) {
                dfs(edge[1], visited, stack);
            }
        }
    
        stack.push(v);
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