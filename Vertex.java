package suanfa8;

import java.util.Scanner;

/**
 * 邻接矩阵表示图
 */
public class Vertex {
    private int[][] list = new int[20][20];
    private char[] vertexList = new char[20];
    private int nVerts;    //当前顶点下标
    Vertex() {
        this.nVerts = 0;
    }
    //增加一个顶点
    public void addVertex(char lable) {
        vertexList[nVerts++] = lable;
    }
    //增加一条边
    public void addEdge(int start, int end) {
        list[start-1][end-1] = 1;
        list[end-1][start-1] = 1;
    }
    //打印矩阵
    public void printMatrix() {
        for(int i = 0; i < nVerts; i ++) {
            for(int j = 0; j < nVerts; j ++) {
                System.out.print(list[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Vertex v = new Vertex();
        Scanner br=new Scanner(System.in);
        int n=br.nextInt();
        for(int i=0;i<n;i++) {
        	v.addVertex('i');
        }
        int side=br.nextInt();
        for(int i=0;i<side;i++) {
        	v.addEdge(br.nextInt(), br.nextInt());
        }
        br.close();
        v.printMatrix();
    }
}

/*
使用链接矩阵实现图的表示，并输出如图所示的图。
第一行代表节点数，第二行代表边数，接下来输入各边节点
输入：
4
5
1 2
2 3
3 4
4 1
1 3
输出：
0 1 1 1 
1 0 1 0 
1 1 0 1 
1 0 1 0
*/