package suanfa8;

public class A094{
    private static void printPath(int[][] path, int v, int u){
        if (path[v][u] == v)
            return;
        printPath(path, v, path[v][u]);
        System.out.print(path[v][u] + " ");
    }
    private static void printSolution(int[][] cost, int[][] path, int N){
        for (int v = 0; v < N; v++){
            for (int u = 0; u < N; u++){
                if (u != v && path[v][u] != -1){
                    System.out.print("Shortest Path from vertex " + v +
                            " to vertex " + u + " is (" + v + " ");
                    printPath(path, v, u);
                    System.out.println(u + ")");
                }
            }
        }
    }
    public static void FloydWarshell(int[][] adjMatrix, int N){
        int[][] cost = new int[N][N];
        int[][] path = new int[N][N];

        for (int v = 0; v < N; v++){
            for (int u = 0; u < N; u++){
                cost[v][u] = adjMatrix[v][u];
                if (v == u)
                    path[v][u] = 0;
                else if (cost[v][u] != Integer.MAX_VALUE)
                    path[v][u] = v;
                else
                    path[v][u] = -1;
            }
        }
        for (int k = 0; k < N; k++){
            for (int v = 0; v < N; v++){
                for (int u = 0; u < N; u++){
                    if (cost[v][k] != Integer.MAX_VALUE
                            && cost[k][u] != Integer.MAX_VALUE
                            && (cost[v][k] + cost[k][u] < cost[v][u]))
                    {
                        cost[v][u] = cost[v][k] + cost[k][u];
                        path[v][u] = path[k][u];
                    }
                }
                if (cost[v][v] < 0)
                {
                    System.out.println("Negative Weight Cycle Found!!");
                    return;
                }
            }
        }
        printSolution(cost, path, N);
    }

    public static void main(String[] args){
        final int N = 4;
        int M = Integer.MAX_VALUE;
        int[][] adjMatrix = new int[][]{
            { 0,  M, -2, M },
            { 4,  0,  3, M },
            { M,  M,  0, 2 },
            { M, -1,  M, 0 }
        };
        FloydWarshell(adjMatrix, N);
    }
}
