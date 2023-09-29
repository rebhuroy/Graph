package AdjucencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph {

    public List<Edge>[] graph ;

    public Graph(int V) {
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
        Graph graph1 = createGraph(V, scanner);
        System.out.println("Please enter which one vertex's neighoubur you want");
        int vertex = scanner.nextInt();
        printGraph(graph1,vertex);
    }

    public static void printGraph(Graph graph,int src){
        System.out.print("Neighbour of "+src+" are [");
        for (int i = 0; i < graph.graph[src].size(); i++) {
            Edge e = graph.graph[src].get(i);
            //System.out.println(e);
            System.out.print(e.dest+"->");
        }
        System.out.print("]");
        System.out.println();

    }

    private static Graph createGraph(int V, Scanner scanner) {
        Graph graph1 = new Graph(V);
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
