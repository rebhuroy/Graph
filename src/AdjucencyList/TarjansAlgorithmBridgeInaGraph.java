package AdjucencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TarjansAlgorithmBridgeInaGraph {

    public List<Edge>[] graph ;

    public TarjansAlgorithmBridgeInaGraph(int V) {
        this.graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            this.graph[i] = new ArrayList<>();
        }

    }

    public static class Edge{
        @Override
        public String toString() {
            return "Edge{" +
                    "src=" + src +
                    ", dest=" + dest +
                    '}';
        }

        public int src;
        public int dest;

        public Edge(int i, int i1) {
            this.src = i;
            this.dest = i1;
        }
    }

    public static void main(String[] args) {
        System.out.println("Please Enter the number of vertices you want to add");
        Scanner scanner  = new Scanner(System.in);
        int V = scanner.nextInt();
        TarjansAlgorithmBridgeInaGraph graph1 = createGraph(V, scanner);
        boolean[] visited = new boolean[V];
        int []dt=new int[V];
        int []low=new int[V];
        //Writing loop for disconnected components
        int parent = -1;
        int time = 0;
        for (int i = 0; i < V; i++) {
            if(!visited[i])
                printGraphDFS(graph1,i,visited,dt,low,parent,time);
        }
    }

    private static void printGraphDFS(TarjansAlgorithmBridgeInaGraph graph1, int curr, boolean[] visited, int[] dt, int[] low, int parent,int time) {

        visited[curr] = true;
        low[curr] = dt[curr] = ++time;

        for (int i = 0; i < graph1.graph[curr].size(); i++) {
            Edge edge = graph1.graph[curr].get(i);
            if(edge.dest == parent){
                continue;
            }
            else if(!visited[edge.dest]){
                printGraphDFS(graph1,edge.dest,visited,dt,low,curr,time);
                low[curr]= Math.min(low[curr],low[edge.dest]);
                if (dt[curr] < low[edge.dest]){
                    System.out.println("Bride is ::"+curr+"--"+edge.dest);
                }
            }
            else {
                low[curr]= Math.min(low[curr],dt[edge.dest]);
            }
        }

    }



    private static TarjansAlgorithmBridgeInaGraph createGraph(int V, Scanner scanner) {
        TarjansAlgorithmBridgeInaGraph graph1 = new TarjansAlgorithmBridgeInaGraph(V);
        for (int i = 0; i < V; i++) {
            System.out.println("number of edges you want to add for src:"+i);
            int k = scanner.nextInt();
            for (int j = 0; j < k; j++) {
                System.out.println("Please enter dest");
                int dest = scanner.nextInt();
                graph1.graph[i].add(new Edge(i,dest));
            }
            System.out.println(graph1.graph[i]);
        }
        return graph1;
    }
}
