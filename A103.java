package suanfa8;

import java.util.*;

class Edge{
 int source, dest, weight;
 public Edge(int source, int dest, int weight) {
     this.source = source;
     this.dest = dest;
     this.weight = weight;
 }
};

class Node {
 int vertex, weight;
 public Node(int vertex, int weight) {
     this.vertex = vertex;
     this.weight = weight;
 }
};

class Graph{
 List<List<Edge>> adjList = null;
 Graph(List<Edge> edges, int N){
     adjList = new ArrayList<>(N);
     for (int i = 0; i < N; i++) {
         adjList.add(i, new ArrayList<>());
     }
     for (Edge edge: edges) {
         adjList.get(edge.source).add(edge);
     }
 }
}

public class A103{
 private static void printRoute(int prev[], int i){
     if (i < 0)
         return;
     printRoute(prev, prev[i]);
     System.out.print(i + " ");
 }
 public static void shortestPath(Graph graph, int source, int N){
     PriorityQueue<Node> minHeap = new PriorityQueue<>((lhs, rhs) -> lhs.weight - rhs.weight);
     minHeap.add(new Node(source, 0));
     List<Integer> dist = new ArrayList<>(Collections.nCopies(N, Integer.MAX_VALUE));
     dist.set(source, 0);
     boolean[] done = new boolean[N];
     done[0] = true;
     int prev[] = new int[N];
     prev[0] = -1;
     while (!minHeap.isEmpty()){
         Node node = minHeap.poll();
         int u = node.vertex;
         for (Edge edge: graph.adjList.get(u)){
             int v = edge.dest;
             int weight = edge.weight;
             if (!done[v] && (dist.get(u) + weight) < dist.get(v)){
                 dist.set(v, dist.get(u) + weight);
                 prev[v] = u;
                 minHeap.add(new Node(v, dist.get(v)));
             }
         }
         done[u] = true;
     }

     for (int i = 1; i < N; ++i){
         System.out.print("Path from vertex 0 to vertex " + i + " has minimum cost of "
                             + dist.get(i) + " and the route is - ");
         printRoute(prev, i);
         System.out.println();
     }
 }

 public static void main(String[] args){
     List<Edge> edges = Arrays.asList(
             new Edge(0, 1, 10), new Edge(0, 4, 3),
             new Edge(1, 2, 2), new Edge(1, 4, 4),
             new Edge(2, 3, 9), new Edge(3, 2, 7),
             new Edge(4, 1, 1), new Edge(4, 2, 8),
             new Edge(4, 3, 2)
     );
     final int N = 5;
     Graph graph = new Graph(edges, N);
     shortestPath(graph, 0, N);
 }
}
