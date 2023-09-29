package AdjucencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphDFSCycleDetectionInUNDirectedGraph {

    public List<Edge>[] graph ;

    public GraphDFSCycleDetectionInUNDirectedGraph(int V) {
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
        GraphDFSCycleDetectionInUNDirectedGraph graph1 = createGraph(V, scanner);
        boolean[] visited = new boolean[V];
        boolean[] recursionStack = new boolean[V];
        //Writing loop for disconnected components
        boolean ans=false;
        for ( int i = 0; i < V; i++) {
            if(!visited[i])
                if(detectCycleInUnDirectedGraph(graph1,0,-1,visited)){
                    ans = true;
                    break;
                }
        }

        System.out.println(ans);

    }

    private static boolean detectCycleInUnDirectedGraph(GraphDFSCycleDetectionInUNDirectedGraph graph1, int current, int parent , boolean[] visited) {

        visited[current] = true;
        boolean ans = false;

        for (int i = 0; i < graph1.graph[current].size(); i++) {
            Edge edge = graph1.graph[current].get(i);

            if(visited[edge.dest] && parent!=edge.dest){
                return true;
            }
              if (!visited[edge.dest]) {
                 ans = detectCycleInUnDirectedGraph(graph1,edge.dest,current,visited);
            }
        }

        return ans;
    }



    private static GraphDFSCycleDetectionInUNDirectedGraph createGraph(int V, Scanner scanner) {
        GraphDFSCycleDetectionInUNDirectedGraph graph1 = new GraphDFSCycleDetectionInUNDirectedGraph(V);
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
