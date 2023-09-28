package AdjucencyList;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class KruskalAlgorithm {

    public static class Edge implements Comparable<Edge>{
        public int src;
        public int dst;
        public int weight;

        public Edge(int src, int dst, int weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "src=" + src +
                    ", dst=" + dst +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }




    }
    public static void main(String[] args) {

        /*
6 11
0 1 2
0 2 4
2 3 6
1 3 1
2 4 9
4 5 5
3 5 7
4 3 11
2 5 10
2 1 8
0 3 3
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter number of vertices");
        int n = scanner.nextInt();
        System.out.println("Please enter the numbe of edges");
        int E = scanner.nextInt();

        Edge[] input = new Edge[E];
        Edge[] output = new Edge[n-1];

        int[] parent = new int[n];
        int count=0;

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {

            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            input[i] = new Edge(source,destination,weight);
        }

        Arrays.sort(input);

        for (Edge edge : input) {
            System.out.println(edge);
        }
        int k = 0;
        while (count != n-1){

            Edge currentEdge = input[k];
            int srcParent = parentFind(currentEdge.src,parent);
            int destinationParent = parentFind(currentEdge.dst,parent);
            if(srcParent != destinationParent){
                output[count] = currentEdge;
                count++;
                parent[srcParent] = destinationParent;
            }

            k++;
        }

        for (int i = 0; i < n-1; i++) {
            if(output[i].src < output[i].dst){
                System.out.println(output[i].src+" "+output[i].dst+" "+output[i].weight);
            }else {
                System.out.println(output[i].dst+" "+output[i].src+" "+output[i].weight);

            }
        }
    }


    private static int parentFind(int v, int[] parent) {
        if(parent[v] == v){
            return v;
        }

        return parentFind(parent[v],parent);
    }
}
