import java.util.*;

public class BFS {
    public static List<Integer> bfs(List<List<Integer>> graph, int start) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        List<Integer> order = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            order.add(v);
            for (int u : graph.get(v)) {
                if (!visited[u]) {
                    visited[u] = true;
                    queue.add(u);
                }
            }
        }
        return order;
    }

    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 6; i++) graph.add(new ArrayList<>());

        // неориентированный граф
        graph.get(0).addAll(Arrays.asList(1, 2));
        graph.get(1).addAll(Arrays.asList(0, 3, 4));
        graph.get(2).addAll(Arrays.asList(0, 4));
        graph.get(3).addAll(Arrays.asList(1, 5));
        graph.get(4).addAll(Arrays.asList(1, 2, 5));
        graph.get(5).addAll(Arrays.asList(3, 4));
        System.out.println(bfs(graph, 0));
    }
}
