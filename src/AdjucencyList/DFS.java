package AdjucencyList;

import java.util.Scanner;

public class DFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter number of nodes");
        int n = sc.nextInt();
        System.out.println("Please Enter number of edges");
        int e = sc.nextInt();
        int [][] edge = new int[n][n];
        for (int i = 0; i < e; i++) {
            int fv = sc.nextInt();
            int sv = sc.nextInt();
            edge[fv][sv] = 1;
            edge[sv][fv] = 1;
        }
        printOriginalArray(edge);
        printDFS(edge);
    }

    private static void printDFS(int[][] edge) {
        int n = edge.length;
        boolean []visited = new boolean[n];
        _DFSPrint(edge,0,visited);

    }

    private static void _DFSPrint(int[][] edge, int sv, boolean[] visited) {

        System.out.println(sv);
        visited[sv]=true;
        for (int i = 0; i < edge.length; i++) {
            if(!visited[i] && edge[sv][i] == 1){
                _DFSPrint(edge,i,visited);
            }
        }
    }

    private static void printOriginalArray(int[][] edge) {
        for (int[] ints : edge) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }
}
