package AdjucencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class GraphDFSTS {

    public List<Edge>[] graph ;

    public GraphDFSTS(int V) {
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
        GraphDFSTS graph1 = createGraph(V, scanner);
        System.out.println("AdjucencyList.DFS Traversal is");
        boolean[] visited = new boolean[V];
        Stack<Integer> topologicalData = new Stack<>();
        //Writing loop for disconnected components
        for (int i = 0; i < V; i++) {
            if(!visited[i]){
                printGraphDFS(graph1,i,visited,topologicalData);
            }
        }
        while (!topologicalData.isEmpty()){
            System.out.println(topologicalData.pop());
        }
    }

    public static void printGraphDFS(GraphDFSTS graph, int current, boolean[] visited,Stack<Integer> topologicalData){
        visited[current] = true;
        for (int i = 0; i < graph.graph[current].size(); i++) {
            Edge edge = graph.graph[current].get(i);

            if(!visited[edge.dest]){
                printGraphDFS(graph,edge.dest,visited,topologicalData);
            }
        }
        topologicalData.push(current);
    }

    private static GraphDFSTS createGraph(int V, Scanner scanner) {
        GraphDFSTS graph1 = new GraphDFSTS(V);
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
