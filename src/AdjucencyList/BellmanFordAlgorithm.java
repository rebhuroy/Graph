package AdjucencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BellmanFordAlgorithm { public List<Edge>[] graph ;
    public BellmanFordAlgorithm(int V) {
        this.graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            this.graph[i] = new ArrayList<>();
        }
    }
    public static class Edge{ public int src;
        public int dest;
        @Override
        public String toString() {
            return "Edge{" +
                    "dest=" + dest +
                    ", weight=" + weight +
                    '}';
        }
        public int weight;
        public Edge(int i, int i1,int i3) {
            this.src = i;
            this.dest = i1;
            this.weight = i3;
        }
    }

    public static void main(String[] args) {
        System.out.println("Please Enter the number of vertices you want to add");
        Scanner scanner  = new Scanner(System.in);
        int V = scanner.nextInt();
        BellmanFordAlgorithm graph1 = createGraph(V, scanner);
        System.out.println("Please enter Source vertex");
        int src = scanner.nextInt();
        int[] distance = new int[V];
        printGraph(graph1,src,distance,V);

        System.out.println("The distance matrix is:");
        StringBuilder distnacMatrix = new StringBuilder("{");
        for (int j : distance) {
            distnacMatrix.append(j).append(",");
        }
        distnacMatrix = new StringBuilder(distnacMatrix.substring(0, distnacMatrix.length() - 1) + "}");
        System.out.print(distnacMatrix);
    }

    public static class Pair implements Comparable<Pair>{

        public int node;
        public int dist;

        public Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
        @Override
        public int compareTo(Pair o) {
            return this.dist - o.dist;
        }
    }

    private static void printGraph(BellmanFordAlgorithm graph1, int source, int[] distance,int V) {
        ///Initialize with infinity
        for (int i = 0; i < distance.length; i++) {
            if(i != source){
                distance[i] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < V-1; i++) {
            for (int j = 0; j < V; j++) {
                for (int k = 0; k < graph1.graph[i].size(); k++) {
                    Edge edges = graph1.graph[i].get(k);
                    int u = edges.src;
                    int v = edges.dest;

                    if(distance[u] != Integer.MAX_VALUE &&  distance[u] + edges.weight < distance[v])
                        distance[v] =  distance[u] + edges.weight;
                }
            }   
        }


    }



    private static BellmanFordAlgorithm createGraph(int V, Scanner scanner) {
        BellmanFordAlgorithm graph1 = new BellmanFordAlgorithm(V);
        for (int i = 0; i < V; i++) {
            System.out.println("number of edges you want to add for src:"+i);
            int k = scanner.nextInt();
            for (int j = 0; j < k; j++) {
                System.out.println("Please enter dest");
                int dest = scanner.nextInt();
                System.out.println("Please enter weight");
                int weight = scanner.nextInt();
                graph1.graph[i].add(new Edge(i,dest,weight));
            }
            System.out.println(graph1.graph[i]);
        }
        return graph1;
    }
}
