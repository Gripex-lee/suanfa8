package suanfa8;

import java.util.*;

class Edge{
 int source, dest;
 public Edge(int source, int dest) {
     this.source = source;
     this.dest = dest;
 }
};


class Graph{
 List<List<Integer>> adjList = null;
 Graph(List<Edge> edges, int N) {
     adjList = new ArrayList<>(N);
     for (int i = 0; i < N; i++) {
         adjList.add(i, new ArrayList<>());
     }
     for (Edge edge: edges) {
         adjList.get(edge.source).add(edge.dest);
     }
 }
}


public class A091{
 public static int DFS(Graph graph, int v, boolean[] discovered,int[] arrival, int[] departure, int time) {
     arrival[v] = ++time;
     discovered[v] = true;

     for (int i : graph.adjList.get(v)) {
         if (!discovered[i]) {
             time = DFS(graph, i, discovered, arrival, departure, time);
         }
     }
     departure[v] = ++time;
     return time;
 }

 public static void main(String[] args){
     List<Edge> edges = Arrays.asList(
                             new Edge(0, 1), new Edge(0, 2), new Edge(2, 3),
                             new Edge(2, 4), new Edge(3, 1), new Edge(3, 5),
                             new Edge(4, 5), new Edge(6, 7)
                         );
     final int N = 8;
     Graph graph = new Graph(edges, N);
     int[] arrival = new int[N];
     int[] departure = new int[N];
     boolean[] discovered = new boolean[N];
     int time = -1;
     for (int i = 0; i < N; i++) {
         if (!discovered[i]) {
             time = DFS(graph, i, discovered, arrival, departure, time);
         }
     }
     for (int i = 0; i < N; i++) {
         System.out.println(arrival[i]+ " "+departure[i]);
	}
 }
}
