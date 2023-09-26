package AdjucencyList;

import java.util.*;

public class GraphDFS {

    public List<Edge>[] graph ;

    public GraphDFS(int V) {
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
        GraphDFS graph1 = createGraph(V, scanner);
        System.out.println("AdjucencyList.DFS Traversal is");
        boolean[] visited = new boolean[V];
        //Writing loop for disconnected components
        for (int i = 0; i < V; i++) {
            if(!visited[i])
                printGraphDFS(graph1,i,visited);
        }
    }

    public static void printGraphDFS(GraphDFS graph, int current, boolean[] visited){
        System.out.println(current);
        visited[current] = true;
        for (int i = 0; i < graph.graph[current].size(); i++) {
            Edge e = graph.graph[current].get(i);
            if(!visited[e.dest])
                printGraphDFS(graph,e.dest,visited);
        }
    }

    private static GraphDFS createGraph(int V, Scanner scanner) {
        GraphDFS graph1 = new GraphDFS(V);
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
