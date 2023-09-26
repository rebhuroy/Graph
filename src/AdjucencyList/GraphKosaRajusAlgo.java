package AdjucencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class GraphKosaRajusAlgo {

    public List<Edge>[] graph ;

    public GraphKosaRajusAlgo(int V) {
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
        GraphKosaRajusAlgo graph1 = createGraph(V, scanner);
        System.out.println("AdjucencyList.DFS Traversal is");
        boolean[] visited = new boolean[V];
        //Writing loop for disconnected components

        // step 1 perform topological sort
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if(!visited[i])
                printGraphDFS(graph1,i,visited,stack);
        }

        // create a new copy graph
        for (int i = 0; i < V; i++) {
            if(!visited[i])
                printGraphDFS(graph1,i,visited,stack);
        }

        GraphKosaRajusAlgo cpTranspose = new GraphKosaRajusAlgo(V);

        visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < graph1.graph[i].size(); j++) {
                Edge edge = graph1.graph[i].get(j);
                cpTranspose.graph[edge.dest].add(new Edge(edge.dest, edge.src));
            }
        }

        System.out.println("Strongly connected components are");
        while (!stack.isEmpty()){
            int curr = stack.pop();
            if(!visited[curr]){
                dfs(cpTranspose,visited,curr);
                System.out.println();
            }
        }


    }

    private static void dfs(GraphKosaRajusAlgo cpTranspose, boolean[] visited, int curr) {
        System.out.print(curr+" ");
        visited[curr] =true;
        for (int i = 0; i < cpTranspose.graph[curr].size(); i++) {
            Edge edge = cpTranspose.graph[curr].get(i);
            if(!visited[edge.dest])
                dfs(cpTranspose,visited,edge.dest);
        }
    }

    public static void printGraphDFS(GraphKosaRajusAlgo graph, int current, boolean[] visited, Stack<Integer> stack){
        System.out.println(current);
        visited[current] = true;
        for (int i = 0; i < graph.graph[current].size(); i++) {
            Edge e = graph.graph[current].get(i);
            if(!visited[e.dest])
                printGraphDFS(graph,e.dest,visited, stack);
        }
        stack.add(current);
    }

    private static GraphKosaRajusAlgo createGraph(int V, Scanner scanner) {
        GraphKosaRajusAlgo graph1 = new GraphKosaRajusAlgo(V);
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
