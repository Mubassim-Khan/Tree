import java.util.Arrays;

class Edge implements Comparable<Edge> {
    int source, destination, weight;

    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

class Graph {
    int vertices, edges;
    Edge[] edge;

    Graph(int v, int e) {
        vertices = v;
        edges = e;
        edge = new Edge[e];
        for (int i = 0; i < e; i++) {
            edge[i] = new Edge();
        }
    }

    int find(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return find(parent, parent[i]);
    }

    void union(int[] parent, int x, int y) {
        int xSet = find(parent, x);
        int ySet = find(parent, y);
        parent[xSet] = ySet;
    }

    void kruskalMST() {
        Edge result[] = new Edge[vertices];
        int e = 0;
        int i = 0;
        for (i = 0; i < vertices; ++i) {
            result[i] = new Edge();
        }
        Arrays.sort(edge);
        int parent[] = new int[vertices];
        for (i = 0; i < vertices; ++i)
            parent[i] = i;
        i = 0;
        while (e < vertices - 1) {
            Edge next_edge = new Edge();
            next_edge = edge[i++];
            int x = find(parent, next_edge.source);
            int y = find(parent, next_edge.destination);
            if (x != y) {
                result[e++] = next_edge;
                union(parent, x, y);
            }
        }
        System.out.println("Minimum Spanning Tree of graph using \"Kruskal Algorithm\"");
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].source + " --> " + result[i].destination + " == " + result[i].weight);
        }
    }
}

public class Program_1 {
    public static void main(String[] args) {
        int vertices = 6; // Number of vertices in the graph
        int edges = 9; // Number of edges in the graph
        Graph graph = new Graph(vertices, edges);

        // Add edges
        graph.edge[0].source = 0;
        graph.edge[0].destination = 1;
        graph.edge[0].weight = 4;

        graph.edge[1].source = 0;
        graph.edge[1].destination = 2;
        graph.edge[1].weight = 4;

        graph.edge[2].source = 1;
        graph.edge[2].destination = 2;
        graph.edge[2].weight = 2;

        graph.edge[3].source = 2;
        graph.edge[3].destination = 3;
        graph.edge[3].weight = 3;

        graph.edge[4].source = 2;
        graph.edge[4].destination = 5;
        graph.edge[4].weight = 2;

        graph.edge[5].source = 2;
        graph.edge[5].destination = 4;
        graph.edge[5].weight = 4;

        graph.edge[6].source = 3;
        graph.edge[6].destination = 5;
        graph.edge[6].weight = 3;

        graph.edge[7].source = 4;
        graph.edge[7].destination = 5;
        graph.edge[7].weight = 3;

        graph.edge[8].source = 0;
        graph.edge[8].destination = 3;
        graph.edge[8].weight = 5;

        graph.kruskalMST();
    }
}
