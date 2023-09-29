package AdjucencyMatrix;

import java.util.Arrays;
import java.util.Scanner;

public class DijkstraAlgo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter number of nodes");
        int V = sc.nextInt();
        System.out.println("Please Enter number of edges");
        int e = sc.nextInt();
        int [][] edge = new int[V][V];
        for (int i = 0; i < e; i++) {
            int fv = sc.nextInt();
            int sv = sc.nextInt();
            int wt = sc.nextInt();
            edge[fv][sv] = wt;
            edge[sv][fv] = wt;
        }
        boolean []visited = new boolean[V];
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        djkstra(visited,distance,0,V,edge);

        for (int i = 0; i < distance.length; i++) {
            System.out.println(i +" "+distance[i]);
        }
/*
Please Enter number of nodes
5
Please Enter number of edges
7
0 1 4
0 2 8
1 3 5
1 2 2
2 3 5
3 4 4
2 4 9
 */

    }

    private static void djkstra(boolean[] visited, int[] distance, int source, int V, int[][] graph) {
        distance[source] = 0;

        for (int i = 0; i < V; i++) {
            int minDistanceVertex = findMinDistVertex(distance,visited);
            visited[minDistanceVertex] = true;
            for (int j = 0; j < V; j++) {
                if(graph[minDistanceVertex][j] != 0 && !visited[j] && distance[minDistanceVertex] != Integer.MAX_VALUE){
                   int newDist = distance[minDistanceVertex] + graph[minDistanceVertex][j];
                   if( newDist < distance[j]){
                       distance[j] = newDist;
                   }
                }
            }
        }
    }

    private static int findMinDistVertex(int[] distance, boolean[] visited) {
        int mind = -1;

        for (int i = 0; i < distance.length; i++) {
            if(!visited[i] && (mind==-1 || distance[i] < distance[mind])){
                mind = i;
            }
        }

        return mind;
    }
}
