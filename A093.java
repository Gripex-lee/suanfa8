package suanfa8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Edge{
    int source, dest;
    public Edge(int source, int dest) {
        this.source = source;
        this.dest = dest;
    }
};
class Graph{
    List<List<Integer>> adjList = null;
    Graph(List<Edge> edges, int N){
        adjList = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            adjList.add(i, new ArrayList<>());
        }
        for (int i = 0; i < edges.size(); i++) {
            int src = edges.get(i).source;
            int dest = edges.get(i).dest;
            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }
    }
}

public class A093{
    private static String COLORS[] = {"", "BLUE", "GREEN", "RED"};
    private static boolean isSafe(Graph graph, int[] color, int v, int c){
        for (int u : graph.adjList.get(v))
            if (color[u] == c)
                return false;

        return true;
    }

    public static void kColorable(Graph g, int[] color, int k, int v, int N){
    	if (v == N){
            for (v = 0; v < N; v++)
                System.out.printf("%-8s" , COLORS[color[v]]); 
            System.out.println();
            return;
        }
        for (int c = 1; c <= k; c++){
            if (isSafe(g, color, v, c)){
                color[v] = c;
                kColorable(g, color, k, v + 1, N);
                color[v] = 0;
            }
        }
    }
    public static void main(String[] args){
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1), new Edge(0, 4),
                new Edge(0, 5), new Edge(4, 5),
                new Edge(1, 4), new Edge(1, 3),
                new Edge(2, 3), new Edge(2, 4)
        );
        final int N = 6;
        Graph g = new Graph(edges, N);
        int k = 3;
        int[] color = new int[N];
        kColorable(g, color, k, 0, N);
    }
}
