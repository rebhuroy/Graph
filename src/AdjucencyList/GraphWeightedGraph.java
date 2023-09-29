package AdjucencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphWeightedGraph {

    public List<Edge>[] graph ;

    public GraphWeightedGraph(int V) {
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
        GraphWeightedGraph graph1 = createGraph(V, scanner);
        System.out.println("Please enter which one vertex's neighbour you want");
        int vertex = scanner.nextInt();
        printGraph(graph1,vertex);
    }

    public static void printGraph(GraphWeightedGraph graph, int src){
        System.out.print("Neighbour of "+src+" are [");
        for (int i = 0; i < graph.graph[src].size(); i++) {
            Edge e = graph.graph[src].get(i);
            System.out.println(e.src +"->"+e.dest+" :"+e.weight);
        }
        System.out.print("]");
        System.out.println();

    }

    private static GraphWeightedGraph createGraph(int V, Scanner scanner) {
        GraphWeightedGraph graph1 = new GraphWeightedGraph(V);
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
