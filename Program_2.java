import java.util.Arrays;

public class Program_2 {
    private int vertices;
    private int[][] graph;

    Program_2(int vertices) {
        this.vertices = vertices;
        graph = new int[vertices][vertices];
    }

    public void addEdge(int source, int destination, int weight) {
        graph[source][destination] = weight;
        graph[destination][source] = weight; // Graph is undirected
    }

    public void primMST() {
        int[] parent = new int[vertices];
        int[] key = new int[vertices];
        boolean[] mstSet = new boolean[vertices];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < vertices - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent);
    }

    private int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < vertices; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private void printMST(int[] parent) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < vertices; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
        Program_2 graph = new Program_2(vertices);
        System.out.println("Minimum Spanning Tree of graph using \"Prim\'s Algorithm\"");

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        graph.primMST();
    }
}