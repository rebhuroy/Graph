package AdjucencyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphDFSAllPathFromSrcToTarget {

    public List<Edge>[] graph ;

    public GraphDFSAllPathFromSrcToTarget(int V) {
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
        GraphDFSAllPathFromSrcToTarget graph1 = createGraph(V, scanner);
        System.out.println("AdjucencyList.DFS Traversal is");
        boolean[] visited = new boolean[V];
        //Writing loop for disconnected components
        System.out.println("Please enter src and target");
        int src = scanner.nextInt();
        int target = scanner.nextInt();
        printGraphDFSAllPaths(graph1,visited,src,target,""+src);

    }

    public static void printGraphDFSAllPaths(GraphDFSAllPathFromSrcToTarget graph, boolean[] visited,int curr,int target,String path){
        if(curr == target){
            System.out.println(path);
            return;
        }

        for (int i = 0; i < graph.graph[curr].size(); i++) {
            Edge edge = graph.graph[curr].get(i);
            if(!visited[edge.dest])
            {
                visited[curr] = true;
                printGraphDFSAllPaths(graph,visited,edge.dest,target,path+edge.dest);
                visited[edge.dest] = false;
            }
        }

    }

    private static GraphDFSAllPathFromSrcToTarget createGraph(int V, Scanner scanner) {
        GraphDFSAllPathFromSrcToTarget graph1 = new GraphDFSAllPathFromSrcToTarget(V);
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
