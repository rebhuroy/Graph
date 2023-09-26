package AdjucencyList;

import java.util.*;

public class GraphBFS {

    public List<Edge>[] graph ;

    public GraphBFS(int V) {
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
        GraphBFS graph1 = createGraph(V, scanner);
        System.out.println("Please enter which one vertex's neighbour you want");
        boolean[] visited = new boolean[V];
        //Writing loop for disconnected components
        for (int i = 0; i < V; i++) {
            if(!visited[i])
                printGraphBFS(graph1,i,visited);
        }
    }

    public static void printGraphBFS(GraphBFS graph, int start, boolean[] visited){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()){
            int curr = queue.remove();
            if(!visited[curr]){
                System.out.println(curr);
                visited[curr] = true;
                for (int i = 0; i < graph.graph[curr].size(); i++) {
                    Edge edge = graph.graph[curr].get(i);
                    queue.add(edge.dest);
                }
            }
        }
    }

    private static GraphBFS createGraph(int V, Scanner scanner) {
        GraphBFS graph1 = new GraphBFS(V);
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
