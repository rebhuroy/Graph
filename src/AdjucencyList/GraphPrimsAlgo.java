package AdjucencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class GraphPrimsAlgo {

    public List<Edge>[] graph ;

    public GraphPrimsAlgo(int V) {
        this.graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            this.graph[i] = new ArrayList<>();
        }

    }

    public static class Edge{


        public int src;
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
        GraphPrimsAlgo graph1 = createGraph(V, scanner);
        System.out.println("Please enter which one vertex's neighbour you want");
        int srcVertex = scanner.nextInt();
        boolean[] visited =new boolean[V];
        System.out.println("Mst Cost is::"+prims(graph1,srcVertex,visited));
    }

    public static class Pair implements Comparable<Pair>{
        public int node;
        public int cost;

        public Pair(int src, int cost) {
            this.node = src;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair o) {
            return this.cost - o.cost;
        }
    }

    public static int prims(GraphPrimsAlgo graph, int srcVertex, boolean[] visited){

        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();// TC --> ElogE

        priorityQueue.add(new Pair(srcVertex,0));
        int cost = 0;

        ArrayList<Integer> integers = new ArrayList<>();

        while (!priorityQueue.isEmpty()){
            Pair current = priorityQueue.remove();

            if(!visited[current.node]){
                visited[current.node] = true;
                cost += current.cost;
                integers.add(current.node);
                for (int i = 0; i < graph.graph[current.node].size(); i++) {
                    Edge edge = graph.graph[current.node].get(i);
                    if(!visited[edge.dest]){
                        priorityQueue.add(new Pair(edge.dest,edge.weight));
                    }
                }
            }
        }

        System.out.println("path is"+integers);

        return cost;
    }

    private static GraphPrimsAlgo createGraph(int V, Scanner scanner) {
        GraphPrimsAlgo graph1 = new GraphPrimsAlgo(V);
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
