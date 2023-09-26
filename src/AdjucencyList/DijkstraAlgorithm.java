package AdjucencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraAlgorithm { public List<Edge>[] graph ;
    public DijkstraAlgorithm(int V) {
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
        DijkstraAlgorithm graph1 = createGraph(V, scanner);
        System.out.println("Please enter Source vertex");
        int src = scanner.nextInt();
        int[] distance = new int[V];
        boolean[] visited = new boolean[V];
        printGraph(graph1,src,visited,distance);

        System.out.println("The distance matrix is:");
        String distnacMatrix = "{";
        for (int i = 0; i < distance.length; i++) {
            distnacMatrix += distance[i]+",";
        }
        distnacMatrix = distnacMatrix.substring(0,distnacMatrix.length()-1) + "}";
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

    private static void printGraph(DijkstraAlgorithm graph1, int source, boolean[] visited, int[] distance) {
        ///Initialize with infinity
        for (int i = 0; i < distance.length; i++) {
            if(i != source){
                distance[i] = Integer.MAX_VALUE;
            }
        }
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Pair(0,0));
        while (!priorityQueue.isEmpty()){
            Pair current = priorityQueue.remove(); //sortest distance
            if(!visited[current.node]){
                visited[current.node] = true;
                for (int i = 0; i < graph1.graph[current.node].size(); i++) {
                    Edge edge = graph1.graph[current.node].get(i);
                    int u = edge.src;
                    int v= edge.dest;
                    //relaxation
                    if(distance[u]+edge.weight <distance[v]){
                        distance[v] = distance[u]+edge.weight;
                        priorityQueue.add(new Pair(v,distance[v]));
                    }

                }
            }
        }
    }



    private static DijkstraAlgorithm createGraph(int V, Scanner scanner) {
        DijkstraAlgorithm graph1 = new DijkstraAlgorithm(V);
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
