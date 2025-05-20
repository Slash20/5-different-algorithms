import java.util.*;


class Edge implements Comparable<Edge> {
    int u, v;
    int weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}


class UnionFind {
    private final int[] parent;
    private final int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank   = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i]   = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx == ry) {
            return false;
        }

        if (rank[rx] < rank[ry]) {
            parent[rx] = ry;
        } else if (rank[rx] > rank[ry]) {
            parent[ry] = rx;
        } else {
            parent[ry] = rx;
            rank[rx]++;
        }
        return true;
    }
}

public class Kruskal {

    public static List<Edge> kruskal(int n, List<Edge> edges) {
        Collections.sort(edges);

        UnionFind uf = new UnionFind(n);
        List<Edge> mst = new ArrayList<>();

        for (Edge e : edges) {
            if (uf.union(e.u, e.v)) {
                mst.add(e);
                if (mst.size() == n - 1) {
                    break;
                }
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        int n = 6;
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 4),
                new Edge(0, 2, 3),
                new Edge(1, 2, 1),
                new Edge(1, 3, 2),
                new Edge(2, 3, 4),
                new Edge(3, 4, 2),
                new Edge(4, 5, 6)
        );

        List<Edge> mst = kruskal(n, edges);
        System.out.println("Рёбра: ");
        for (Edge e : mst) {
            System.out.printf("(%d — %d) weight=%d%n", e.u, e.v, e.weight);
        }
    }
}
