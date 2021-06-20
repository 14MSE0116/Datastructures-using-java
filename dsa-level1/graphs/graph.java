import java.io.*;
import java.util.*;

import org.graalvm.compiler.lir.gen.PhiResolver;

public class graph {
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

    public static class Pair implements Comparable<Pair> {
        int wt;
        String psf;

        Pair(String psf, int wt) {
            this.psf = psf;
            this.wt = wt;
        }

        public int compareTo(Pair p) {
            return this.wt - p.wt;
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
    static int mwt = Integer.MIN_VALUE;
    static int miwt = Integer.MAX_VALUE;
    static int ceil = Integer.MAX_VALUE;
    static int floor = Integer.MIN_VALUE;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    public static void multisolver(ArrayList<Edge>[] graph, int src, int dst, boolean vis[], String psf, int sos,
            int criteria, int k) {
        if (src == dst) {
            psf += src;
            if (mwt < sos) {
                maxpath = psf;
                mwt = sos;
            }

            if (miwt > sos) {
                minpath = psf;
                miwt = sos;
            }

            // to find ceil and floor
            if (sos > criteria) {
                if (sos < ceil) {
                    ceil = sos;
                    justmaxpath = psf;
                }
            }

            if (sos < criteria) {
                if (sos > floor) {
                    floor = sos;
                    justminpath = psf;
                }
            }

            if (pq.size() < k) {
                pq.add(new Pair(psf, sos));
            }

            else {
                if (pq.size() > 0 && pq.peek().wt < sos) {
                    pq.remove();
                    pq.add(new Pair(psf, sos));
                }
            }

        }

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (vis[e.nbr] != true) {
                multisolver(graph, e.nbr, dst, vis, psf + src, sos + e.wt, criteria, k);
            }
        }
        vis[src] = false;
    }

    public static ArrayList<ArrayList<Integer>> getConnectedComponents(ArrayList<Edge>[] graph) {
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        int n = graph.length;
        boolean vis[] = new boolean[n];
        for (int v = 0; v < n; v++) {
            if (vis[v] != true) {
                ArrayList<Integer> comp = new ArrayList<>();
                getConnectedComp(graph, v, vis, comp);
                comps.add(comp);
            }
        }
        return comps;
    }

    public static void getConnectedComp(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> comp) {
        vis[src] = true;
        comp.add(src);

        for (Edge e : graph[src]) {
            int nbr = e.nbr;
            if (vis[nbr] != true)
                getConnectedComp(graph, nbr, vis, comp);
        }
    }

    public static boolean isgraphconnected(ArrayList<Edge>[] graph) {
        ArrayList<ArrayList<Integer>> res = getConnectedComponents(graph);
        if (res.size() > 1)
            return false;

        else
            return true;
    }

    public static boolean isgraphconnected2(ArrayList<Edge>[] graph) {
        int n = graph.length;
        boolean vis[] = new boolean[n];
        int count = 0;

        for (int v = 0; v < n; v++) {
            if (vis[v] != true) {
                count++;
                if (count > 1)
                    return false;
                gcc(graph, v, vis);
            }
        }
        return true;
    }

    public static void gcc(ArrayList<Edge>[] graph, int src, boolean vis[]) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (vis[e.nbr] != true)
                gcc(graph, e.nbr, vis);
        }
    }

    // order of direction->top,left,down,right
    static int xdir[] = { -1, 0, 1, 0 };
    static int ydir[] = { 0, -1, 0, 1 };

    public static void gccforisland(int[][] graph, int x, int y) {
        graph[x][y] = -1;
        for (int dir = 0; dir < xdir.length; dir++) {
            int r = x + xdir[dir];
            int c = y + ydir[dir];

            if (r >= 0 && r < graph.length && c >= 0 && c < graph[0].length && graph[r][c] == 0) {
                gccforisland(graph, r, c);
            }
        }

    }

    public static int numofIsland(int[][] graph) {
        int count = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] == 0) {
                    count++;
                    gccforisland(graph, i, j);
                }
            }
        }
        return count;
    }

    static int rdir[] = { -1, 0, 0, 1 };
    static int cdir[] = { 0, 1, -1, 0 };

    public static int numofIsland2(int arr[][]) {
        boolean vis[][] = new boolean[arr.length][arr[0].length];
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (vis[i][j] != true && arr[i][j] != 1) {
                    count++;
                    gcc2(i, j, arr, vis);
                }
            }
        }
        return count;
    }

    public static void gcc2(int i, int j, int arr[][], boolean vis[][]) {
        vis[i][j] = true;
        for (int dir = 0; dir < rdir.length; dir++) {
            int rr = i + rdir[dir];
            int cc = j + cdir[dir];

            if (rr >= 0 && rr < arr.length && cc >= 0 && cc < arr[0].length) {
                if (vis[rr][cc] != true && arr[rr][cc] == 0)
                    gcc2(rr, cc, arr, vis);
            }
        }

    }

    public static void hamiltonian(ArrayList<Edge>[] graph, int src, int osrc, HashSet<Integer> vis, String psf) {
        if (vis.size() == graph.length - 1) {
            System.out.print(psf + src);

            boolean isCyclic = false;
            for (Edge e : graph[osrc]) {
                if (e.nbr == src) {
                    isCyclic = true;
                    break;
                }
            }

            if (isCyclic == true) {
                System.out.println("*");
            } else {
                System.out.println(".");
            }
            return;
        }
        vis.add(src);
        for (Edge e : graph[src]) {
            int nbr = e.nbr;
            if (vis.contains(nbr) == false) {
                hamiltonian(graph, nbr, osrc, vis, psf + src);
            }
        }
        vis.remove(src);
    }

    public static class BFSPair {
        int vtx;
        String psf;

        BFSPair(int vtx, String psf) {
            this.vtx = vtx;
            this.psf = psf;
        }
    }

    public static void bfs(ArrayList<Edge>[] graph, int src) {
        Queue<BFSPair> qu = new LinkedList<>();
        qu.add(new BFSPair(src, ""));
        boolean vis[] = new boolean[graph.length];

        while (qu.size() > 0) {
            // get+remove
            BFSPair rem = qu.remove();

            // mark
            if (vis[rem.vtx] == true) {
                continue;
            } else {
                vis[rem.vtx] = true;
            }

            // work->printing
            System.out.println(rem.vtx + "@" + rem.psf);

            // add unvisited neighbours;
            for (Edge e : graph[rem.vtx]) {
                if (vis[e.nbr] == false) {
                    qu.add(new BFSPair(e.nbr, rem.psf + e.nbr));
                }
            }

        }
    }

    public static boolean isCyclic(ArrayList<Edge>[] graph) {
        boolean vis[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (vis[i] != true) {
                boolean res = bfsForCycle(graph, i, vis);
                if (res == true)
                    return true;
            }
        }
        return false;
    }

    public static boolean bfsForCycle(ArrayList<Edge>[] graph, int src, boolean vis[]) {
        Queue<Integer> qu = new LinkedList<>();
        qu.add(src);

        while (qu.size() > 0) {
            // get+remove
            int rem = qu.remove();

            // mark
            if (vis[rem] == true) {
                // already visited so cycle is detected
                return true;
            } else {
                // mark
                vis[rem] = true;
            }

            // // work->printing
            // System.out.println(rem.vtx + "@" + rem.psf);

            // add unvisited neighbours;
            for (Edge e : graph[rem]) {
                if (vis[e.nbr] == false) {
                    qu.add(e.nbr);
                }
            }

        }
        return false;
    }

    public static boolean isCyclic2(ArrayList<Edge>[] graph) {

        boolean vis[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (vis[i] != true) {
                boolean res = dfs(graph, i, -1, vis);
                if (res == true)
                    return true;
            }
        }
        return false;

    }

    public static boolean dfs(ArrayList<Edge>[] graph, int src, int parent, boolean vis[]) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            int nbr = e.nbr;
            if (vis[nbr] == true && nbr != parent) {
                // cycle is present
                return true;
            }
            if (vis[nbr] != true) {
                boolean res = dfs(graph, nbr, src, vis);
                if (res == true)
                    return true;
            }
        }
        return false;

    }

    public static class BipPair {
        int vtx;
        int dtime;

        BipPair(int vtx, int dtime) {
            this.vtx = vtx;
            this.dtime = dtime;
        }
    }

    public static boolean bipartite(ArrayList<Edge>[] graph) {
        int vis[] = new int[graph.length];
        Arrays.fill(vis, -1);
        for (int i = 0; i < graph.length; i++) {
            if (vis[i] == -1) {
                boolean res = bfscyclic(graph, vis, i);
                if (res == false)
                    return false;
            }
        }
        return true;
    }

    public static boolean bfscyclic(ArrayList<Edge>[] graph, int vis[], int src) {
        Queue<BipPair> qu = new LinkedList<>();
        qu.add(new BipPair(src, 0));
        while (qu.size() > 0) {
            BipPair rem = qu.remove();

            if (vis[rem.vtx] == -1) {
                // mark
                vis[rem.vtx] = rem.dtime;
            } else {
                // already discovered
                // 1.if discovered with sam level;
                if (vis[rem.vtx] == rem.dtime)
                    continue;
                else
                    return false;

                // 2.if again discovered with different dtime->return false
                // becuase cycle is odd

            }

            for (Edge e : graph[rem.vtx]) {
                int nbr = e.nbr;
                if (vis[nbr] == -1) {
                    qu.add(new BipPair(nbr, rem.dtime + 1));
                }
            }

        }
        return true;

    }

    public static int spreadInfection(ArrayList<Edge>[] graph, int src, int t) {
        int count = 0;
        Queue<BipPair> qu = new LinkedList<>();
        int[] vis = new int[graph.length];
        qu.add(new BipPair(src, 1));
        while (qu.size() > 0) {
            BipPair rem = qu.remove();
            if (vis[rem.vtx] != 0) {
                // marked
                continue;
            } else {
                vis[rem.vtx] = rem.dtime;
            }

            if (rem.dtime > t)
                break;
            count++;

            for (Edge e : graph[rem.vtx]) {
                if (e.nbr != 0) {
                    qu.add(new BipPair(e.nbr, rem.dtime + 1));
                }
            }
        }

        return count;
    }

    public static class DPair implements Comparable<DPair> {
        int vtx;
        String psf;

        int wsf;

        DPair(int vtx, String psf, int wsf) {
            this.vtx = vtx;
            this.wsf = wsf;
            this.psf = psf;
        }

        public int compareTo(DPair o) {
            return this.wsf - o.wsf;
        }
    }

    public static void dijikstras(ArrayList<Edge>[] graph, int src) {
        PriorityQueue<DPair> qu = new PriorityQueue<>();
        qu.add(new DPair(src, "" + src, 0));
        boolean vis[] = new boolean[graph.length];
        while (qu.size() > 0) {
            // 1.get+remove
            DPair rem = qu.remove();

            if (vis[rem.vtx] == true)
                continue;

            // 2.Mark *
            if (vis[rem.vtx] == true)
                continue;

            vis[rem.vtx] = true;

            // 3.Work-Print paths
            System.out.println(rem.vtx + " via " + rem.psf + " @ " + rem.wsf);

            // 4.Add neighbours
            for (Edge e : graph[rem.vtx]) {
                int nbr = e.nbr;
                if (vis[nbr] != true) {
                    qu.add(new DPair(nbr, rem.psf + nbr, rem.wsf + e.wt));
                }
            }
        }
    }

    public static class Phelper implements Comparable<Phelper> {
        int vtx;
        int parent;
        int wt;

        Phelper(int vtx, int parent, int wt) {
            this.vtx = vtx;
            this.parent = parent;
            this.wt = wt;
        }

        public int compareTo(Phelper o) {
            return this.wt - o.wt;
        }
    }

    // minimum spanning tree to connect all the edges
    // should be acyclic and connected
    public static void Prims(ArrayList<Edge>[] graph) {
        PriorityQueue<Phelper> pq = new PriorityQueue<>();
        ArrayList<Edge>[] mst = new ArrayList[graph.length];
        boolean vis[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++)
            mst[i] = new ArrayList<>();

        pq.add(new Phelper(0, -1, 0));
        while (pq.size() > 0) {
            // 1.get+remove
            Phelper rem = pq.remove();

            // 2.mark
            if (vis[rem.vtx] == true)
                continue;

            vis[rem.vtx] = true;

            // 3work->print for question + add edge for mst graph
            if (rem.parent != -1) {
                System.out.println("[" + rem.vtx + "-" + rem.parent + "@" + rem.wt + "]");
                addEdge(mst, rem.vtx, rem.parent, rem.wt);
            }

            // 4add siblings
            for (Edge e : graph[rem.vtx]) {
                if (vis[e.nbr] != true) {
                    pq.add(new Phelper(e.nbr, rem.vtx, e.wt));

                }
            }
        }
        display(mst);

    }

    public static void fun() {

        ArrayList<Edge> graph[] = createGraph();
        // display(graph);
        // boolean vis[] = new boolean[7];
        // // System.out.println(hasPath(graph, 0, 6, vis));
        // // printAllPaths(graph, 0, 6, vis, "");
        // // printAllPathswithsum(graph, 0, 6, vis, "", 0);
        // multisolver(graph, 0, 6, vis, "", 0, 30, 4);
        // System.out.println("Smallest Path:=" + minpath + "@" + miwt);
        // System.out.println("Largest Path:=" + maxpath + "@" + mwt);
        // System.out.println("Just Larger Path than 30=" + justmaxpath + "@" + ceil);
        // System.out.println("Just Smaller Path than 30=" + justminpath + "@" + floor);
        // System.out.println(4 + "th largest path = " + pq.peek().psf + "@" +
        // pq.peek().wt);
        // HashSet<Integer> vis = new HashSet<>();
        // hamiltonian(graph, 0, 0, vis, "");
        // dijikstras(graph, 0);
        Prims(graph);
    }

    public static void main(String[] args) {
        fun();
    }
}
