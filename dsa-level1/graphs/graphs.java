import java.io.*;
import java.util.*;

public class graphs {
    public static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static class Pair implements Comparable<Pair>
    {
        int wt;
        String psf;

        Pair(String psf,int wt)
        {
            this.psf=psf;
            this.wt=wt;
        }

        public int compareTo(Pair p)
        {
            return this.wt-p.wt;
        }
    }

    public static void display(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print("[" + i + "]" + "->");
            for (Edge e : graph[i]) {
                System.out.print("[" + e.src + "-" + e.nbr + " @ " + e.wt + "], ");
            }
            System.out.println();
        }
    }

    private static void addEdge(ArrayList<Edge>[] graph, int src, int nbr, int wt) {
        graph[src].add(new Edge(src, nbr, wt));
        graph[nbr].add(new Edge(nbr, src, wt));
    }

    public static ArrayList<Edge>[] createGraph() {
        int n = 7;
        int[][] data = { { 0, 1, 10 }, { 1, 2, 10 }, { 2, 3, 10 }, { 0, 3, 40 }, { 3, 4, 2 }, { 4, 5, 3 }, { 5, 6, 3 },
                { 4, 6, 8 }, { 2, 5, 5 } };

        // addEdge(graph, 0, 1, 10);
        // addEdge(graph, 0, 3, 40);
        // addEdge(graph, 1, 2, 10);
        // addEdge(graph, 2, 3, 10);
        // addEdge(graph, 3, 4, 2);
        // addEdge(graph, 4, 5, 3);
        // addEdge(graph, 4, 6, 8);
        // addEdge(graph, 5, 6, 3);
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int arr[] : data) {
            addEdge(graph, arr[0], arr[1], arr[2]);
        }
        return graph;

    }

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dst, boolean vis[]) {
        if (src == dst) {
            return true;
        }
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (vis[e.nbr] == false) {
                boolean rres = hasPath(graph, e.nbr, dst, vis);
                if (rres == true)
                    return true;
            }
        }

        return false;
    }

    public static void printAllPaths(ArrayList<Edge>[] graph, int src, int dst, boolean vis[], String ans) {
        if (src == dst) {
            System.out.println(ans + dst);
            return;
        }
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (vis[e.nbr] != true)
                printAllPaths(graph, e.nbr, dst, vis, ans + src);
        }
        vis[src] = false;

    }

    public static void printAllPathswithsum(ArrayList<Edge>[] graph, int src, int dst, boolean vis[], String ans,
            int sum) {
        if (src == dst) {
            System.out.println("path is:" + ans + dst);
            System.out.println("Weght is :" + sum);
            return;
        }
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (vis[e.nbr] != true)
                printAllPathswithsum(graph, e.nbr, dst, vis, ans + src, sum + e.wt);
        }
        vis[src] = false;

    }

    static String maxpath;
    static String minpath;
    static String justmaxpath;
    static String justminpath;
    static int mwt=Integer.MIN_VALUE;
    static int miwt=Integer.MAX_VALUE;
    static int ceil=Integer.MAX_VALUE;
    static int floor=Integer.MIN_VALUE;
    static PriorityQueue<Pair>pq=new PriorityQueue<>();
    public static void multisolver(ArrayList<Edge>[] graph, int src, int dst, boolean vis[], String psf, int sos,int criteria,int k) {
        if (src == dst) {
            psf += src;
            if (mwt < sos) {
                maxpath = psf;
                mwt = sos;
            }

            if(miwt>sos)
            {
                minpath=psf;
                miwt=sos;
            }

            //to find ceil and floor
            if(sos>criteria)
            {
                if(sos<ceil)
                    {
                  ceil=sos;
                  justmaxpath=psf;
                    }
            }

            if(sos<criteria)
            {   
                if(sos>floor)
                {
                    floor=sos;
                    justminpath=psf;
                   }
               }
   
               if(pq.size()<k)
               {
                   pq.add(new Pair(psf, sos));
               }
   
               else
               {
                   if(pq.size()>0 && pq.peek().wt<sos)
                   {
                       pq.remove();
                       pq.add(new Pair(psf, sos));
                   }
               }
               
   
           }

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (vis[e.nbr] != true) {
                multisolver(graph, e.nbr, dst, vis, psf + src, sos + e.wt,criteria,k);
            }
        }
        vis[src] = false;
    }

    public static void fun() {

        ArrayList<Edge> graph[] = createGraph();
        // display(graph);
        boolean vis[] = new boolean[7];
        // System.out.println(hasPath(graph, 0, 6, vis));
        // printAllPaths(graph, 0, 6, vis, "");
        // printAllPathswithsum(graph, 0, 6, vis, "", 0);
        multisolver(graph, 0, 6, vis, "", 0,30,4);
        System.out.println("Smallest Path:="+minpath+"@"+miwt);
        System.out.println("Largest Path:="+maxpath+"@"+mwt);
        System.out.println("Just Larger Path than 30="+justmaxpath+"@"+ceil);
        System.out.println("Just Smaller Path than 30="+justminpath+"@"+floor);
        System.out.println(4 + "th largest path = " + pq.peek().psf + "@" + pq.peek().wt);
    }

    public static void main(String[] args) {
        fun();
    }
}
