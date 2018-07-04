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

     for (int i = 0; i < edges.size(); i++){
         int src = edges.get(i).source;
         int dest = edges.get(i).dest;

         adjList.get(src).add(dest);
         adjList.get(dest).add(src);
     }
 }
}
public class A090{
 public static void iterativeDFS(Graph graph, int v, boolean[] discovered){
     Stack<Integer> stack = new Stack<>();
     stack.push(v);
     while (!stack.empty()){
         v = stack.pop();
         if (discovered[v])
             continue;
         discovered[v] = true;
         System.out.print(v + " ");
         List<Integer> adj = graph.adjList.get(v);
         for (int i = adj.size() - 1; i >= 0; i--)
         {
             int u = adj.get(i);
             if (!discovered[u]) {
                 stack.push(u);
             }
         }
     }
 }

 public static void main(String[] args){
     List<Edge> edges = Arrays.asList(
             new Edge(0, 1), new Edge(1, 2), new Edge(2, 1),
             new Edge(2, 0), new Edge(3, 2), new Edge(4, 5),
             new Edge(5, 4)
     );

     final int N = 6;
     Graph graph = new Graph(edges, N);
     boolean[] discovered = new boolean[N];
     for (int i = 0; i < N; i++) {
         if (!discovered[i]) {
             iterativeDFS(graph, i, discovered);
         }
     }
 }
}
