package suanfa8;

import java.util.*;
//定义节点
class Edge{
 int source, dest;
 public Edge(int source, int dest) {
     this.source = source;
     this.dest = dest;
 }
};
//定义图
class Graph{
 List<List<Integer>> adjList = null;
 Graph(List<Edge> edges, int N){ //节点列表以及节点个数
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

public class A092{
 public static boolean DFS(Graph graph, int v, boolean[] discovered, int parent) {
     discovered[v] = true;
     for (int w : graph.adjList.get(v)){
         if (!discovered[w])
         {
             if (!DFS(graph, w, discovered, v))
                 return false;
         }
         else if (w != parent){
             return false;
         }
     }
     return true;
 }

 public static void main(String[] args){
     List<Edge> edges = Arrays.asList(
             new Edge(0, 1), new Edge(1, 2), new Edge(2, 3),
             new Edge(3, 4), new Edge(4, 5), new Edge(5, 0)
     );
     final int N = 6;
     Graph graph = new Graph(edges, N);
     boolean[] discovered = new boolean[N];
     boolean isTree = true;
     isTree = DFS(graph, 0, discovered, -1);
     for (int i = 0; isTree && i < N; i++){
         if (!discovered[i])
             isTree = false;
     }
     if (isTree)
         System.out.println("Graph is a Tree");
     else
         System.out.println("Graph is not a Tree");
 }
}
