import java.util.*;

public class Dijkstra {
    public static class Edge {
        int to, weight;
        public Edge(int to, int weight) { this.to = to; this.weight = weight; }
    }

    public static int[] dijkstra(List<List<Edge>> graph, int source) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] u = pq.poll();
            int v = u[0], d = u[1];
            if (d > dist[v]) continue;
            for (Edge e : graph.get(v)) {
                int nd = dist[v] + e.weight;
                if (nd < dist[e.to]) {
                    dist[e.to] = nd;
                    pq.add(new int[]{e.to, nd});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        // Добавляем рёбра: graph.get(u).add(new Edge(v, w));
        graph.get(0).add(new Edge(1, 10));
        graph.get(0).add(new Edge(4, 5));
        graph.get(1).add(new Edge(2, 1));
        graph.get(4).add(new Edge(1, 3));
        graph.get(4).add(new Edge(2, 9));
        graph.get(2).add(new Edge(3, 4));
        int[] dist = dijkstra(graph, 0);
        System.out.println(Arrays.toString(dist)); // [0,8,9,13,5]
    }
}
