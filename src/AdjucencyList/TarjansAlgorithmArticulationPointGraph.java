package AdjucencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TarjansAlgorithmArticulationPointGraph {

    public List<Edge>[] graph ;

    public TarjansAlgorithmArticulationPointGraph(int V) {
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
        TarjansAlgorithmArticulationPointGraph graph1 = createGraph(V, scanner);
        boolean[] visited = new boolean[V];
        int []dt=new int[V];
        int []low=new int[V];
        boolean []ap=new boolean[V];
        //Writing loop for disconnected components
        int parent = -1;
        int time = 0;
        for (int i = 0; i < V; i++) {
            if(!visited[i])
                findArticulationPointsInAGraph(graph1,i,visited,dt,low,parent,time,ap);
        }
        System.out.println("Articulation points are below");
        for (int i = 0; i < ap.length; i++) {
            if(ap[i]){
                System.out.println(i);
            }
        }
    }

    private static void findArticulationPointsInAGraph(TarjansAlgorithmArticulationPointGraph graph1,
                                                       int curr, boolean[] visited,
                                                       int[] dt, int[] low, int parent, int time, boolean[] ap) {

        visited[curr] = true;
        low[curr] = dt[curr] = ++time;
        int disconnectedChildren = 0;

        for (int i = 0; i < graph1.graph[curr].size(); i++) {
            Edge edge = graph1.graph[curr].get(i);
            int neighbour = edge.dest;

            if(parent == neighbour){
                continue;
            }
            else if(visited[neighbour]){
                low[curr] = Math.min(low[curr],dt[neighbour]);
            }
            else {
                findArticulationPointsInAGraph(graph1,neighbour,visited,dt,low,curr,time,ap);
                low[curr] = Math.min(low[curr],low[neighbour]);
                if( dt[curr] <= low[neighbour] && parent != -1){
                    ap[curr] = true;
                }
                disconnectedChildren++;
            }
        }

        if(parent==-1 && disconnectedChildren>1){
            ap[curr] = true;
        }



    }



    private static TarjansAlgorithmArticulationPointGraph createGraph(int V, Scanner scanner) {
        TarjansAlgorithmArticulationPointGraph graph1 = new TarjansAlgorithmArticulationPointGraph(V);
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
