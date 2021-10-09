import java.util.*;

import array.pair;

public class graphs {
    static class Edge {
        int nbr;
        int wt;

        Edge(int nbr, int wt) {
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int v1, int v2, int wt) {
        graph[v1].add(new Edge(v2, wt));
        graph[v2].add(new Edge(v1, wt));
    }

    public static void solve() {
        int n = 7;
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        addEdge(graph, 0, 1, 10);
        // and so on....
    }

    public static boolean dfs(ArrayList<Edge>[] graph, int src, int dst, boolean vis[], String psf) {
        if (src == dst) {
            psf += src;
            System.out.println(psf);
            return true;
        }
        vis[src] = true;
        boolean res = false;
        for (Edge e : graph[src]) {
            if (vis[e.nbr] != true) {
                res = res || dfs(graph, e.nbr, dst, vis, psf + src);
            }
        }
        return res;
    }

    public static boolean bfs(ArrayList<Edge>[] graph, int src, int dst, boolean[] vis) {
        Queue<BPair> qu = new LinkedList<>();
        qu.add(new BPair(src, src + "", 0));
        boolean res = false;
        while (qu.size() > 0) {
            // 1.get+remove
            BPair rem = qu.remove();

            // 2.Mark*
            if (vis[rem.vtx] != true)
                vis[rem.vtx] = true;
            else
                continue;

            // 3.Work
            System.out.println(rem.vtx + " " + rem.psf + "@" + rem.wsf);
            if (src == dst) {
                res = true;
                break;
            }

            // 4.Add neighbours
            for (Edge e : graph[rem.vtx]) {
                if (vis[e.nbr] != true)
                    qu.add(new BPair(e.nbr, rem.psf + "" + e.nbr, rem.wsf + e.wt));
            }
        }
        return res;

    }

    static class BPair {
        int vtx;
        String psf;
        int wsf;

        BPair(int vtx, String psf, int wsf) {
            this.vtx = vtx;
            this.psf = psf;
            this.wsf = wsf;
        }
    }

    // Leetcode-200. Number of Islands
    // https://leetcode.com/problems/number-of-islands/
    static int xdir[] = { -1, 0, 1, 0 };
    static int ydir[] = { 0, 1, 0, -1 };

    public int numIslands(char[][] grid) {
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    dfs(grid, i, j);
                }
            }
        }
        return islands;
    }

    static void dfs(char[][] grid, int r, int c) {
        grid[r][c] = '2';
        for (int d = 0; d < xdir.length; d++) {
            int rr = r + xdir[d];
            int cc = c + ydir[d];
            if (rr >= 0 && rr < grid.length && cc >= 0 && cc < grid[0].length && grid[rr][cc] == '1') {
                dfs(grid, rr, cc);
            }
        }

    }

    // leetcode1020. Number of Enclaves
    public int numEnclaves(int[][] grid) {
        // for 0th row
        for (int c = 0; c < grid[0].length; c++) {
            if (grid[0][c] == 1) {
                numEnclaves_dfs(grid, 0, c);
            }
        }
        // for last row
        for (int c = 0; c < grid[0].length; c++) {
            if (grid[grid.length - 1][c] == 1) {
                numEnclaves_dfs(grid, grid.length - 1, c);
            }
        }

        // for 0th column
        for (int r = 0; r < grid.length; r++) {
            if (grid[r][0] == 1) {
                numEnclaves_dfs(grid, r, 0);
            }
        }

        // for last column
        for (int r = 0; r < grid.length; r++) {
            if (grid[r][grid[0].length - 1] == 1) {
                numEnclaves_dfs(grid, r, grid[0].length - 1);
            }
        }

        // count remaining 1's
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1)
                    count++;
            }
        }

        return count;
    }

    static void numEnclaves_dfs(int[][] grid, int x, int y) {
        grid[x][y] = 0;
        for (int d = 0; d < xdir.length; d++) {
            int rr = x + xdir[d];
            int cc = y + ydir[d];
            if (rr >= 0 && rr < grid.length && cc >= 0 && cc < grid[0].length && grid[rr][cc] == 1) {
                numEnclaves_dfs(grid, rr, cc);
            }
        }
    }

    // https://www.lintcode.com/problem/number-of-distinct-islands/description
    static StringBuilder str;
    static char[] dir = { 't', 'l', 'd', 'r' };

    public int numberofDistinctIslands(int[][] grid) {
        // write your code here
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    str = new StringBuilder("x");
                    distinctcount(grid, i, j);
                    set.add(str.toString());
                }
            }
        }
        return set.size();
    }

    static void distinctcount(int[][] grid, int x, int y) {
        grid[x][y] = 0;
        for (int d = 0; d < 4; d++) {
            int r = x + xdir[d];
            int c = y + ydir[d];
            if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1) {
                str.append("" + dir[d]);
                distinctcount(grid, r, c);
            }
        }
        str.append("z");
    }

    // Leetcode 815->Bus Routes
    // https://leetcode.com/problems/bus-routes/
    public int numBusesToDestination(int[][] routes, int source, int target) {

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        makemapwithstandandbus(routes, map);
        HashSet<Integer> visBus = new HashSet<>();
        HashSet<Integer> visStand = new HashSet<>();
        Queue<Integer> qu = new LinkedList<>();
        qu.add(source);
        int level = -1;

        while (qu.size() > 0) {
            int sz = qu.size();
            level++;
            while (sz-- > 0) {
                int rem = qu.remove();
                if (rem == target)
                    return level;

                for (int busNum : map.get(rem)) {
                    if (visBus.contains(busNum))
                        continue;
                    else {
                        for (int busStand : routes[busNum]) {
                            if (visStand.contains(busStand) == false) {
                                visStand.add(busStand);
                                qu.add(busStand);
                            }

                        }
                        visBus.add(busNum);
                    }
                }
            }
        }

        return -1;

    }

    static void makemapwithstandandbus(int[][] routes, HashMap<Integer, ArrayList<Integer>> map) {
        for (int r = 0; r < routes.length; r++) {
            // r->bus no
            for (int c = 0; c < routes[r].length; c++) {
                int stand = routes[r][c];
                if (map.containsKey(stand)) {
                    // Key is already there,so just add busno
                    map.get(stand).add(r);
                } else {
                    ArrayList<Integer> busNo = new ArrayList<>();
                    busNo.add(r);
                    map.put(stand, busNo);
                }
            }
        }
    }

    // https://leetcode.com/problems/01-matrix/
    // 542. 01 Matrix
    public int[][] updateMatrix(int[][] mat) {
        Queue<Pair01> qu = new ArrayDeque<>();
        boolean vis[][] = new boolean[mat.length][mat[0].length];
        // 1.travel and fill queue with initial 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    qu.add(new Pair01(i, j));
                    vis[i][j] = true;
                }
            }
        }

        // 2.Run BFS and mark level in given matrix and use a visited matrix
        int level = -1;
        while (qu.size() > 0) {
            level++;
            int sz = qu.size();
            while (sz-- > 0) {
                Pair01 rem = qu.remove();
                mat[rem.x][rem.y] = level;

                for (int d = 0; d < 4; d++) {
                    int r = rem.x + xdir[d];
                    int c = rem.y + ydir[d];
                    if (r >= 0 && r < mat.length && c >= 0 && c < mat[0].length && vis[r][c] == false) {
                        qu.add(new Pair01(r, c));
                        vis[r][c] = true;
                    }
                }
            }
        }

        return mat;
    }

    static class Pair01 {
        int x;
        int y;

        Pair01(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // leetcode 1162. https://leetcode.com/problems/as-far-from-land-as-possible/
    private static class pairD {
        int x;
        int y;

        pairD(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int maxDistance(int[][] grid) {
        // 1.Iterate on grid and add all one's in queue and mark it as well
        Queue<pairD> qu = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    qu.add(new pairD(i, j));
                    grid[i][j] = -1;
                }
            }
        }
        if (qu.size() == 0 || qu.size() == grid.length * grid[0].length)
            return -1;
        // Run BFS
        int level = -1;
        while (qu.size() > 0) {
            level++;
            int size = qu.size();
            while (size-- > 0) {
                pairD rem = qu.remove();
                for (int d = 0; d < 4; d++) {
                    int r = rem.x + xdir[d];
                    int c = rem.y + ydir[d];
                    if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] != -1) {
                        qu.add(new pairD(r, c));
                        grid[r][c] = -1;
                    }
                }
            }
        }

        return level;

    }

    // Pepcoding->Find Mother vertex->top of toplogical sort
    public static int findMotherVertex(int N, ArrayList<ArrayList<Integer>> graph) {
        boolean vis[] = new boolean[N];
        Stack<Integer> st = new Stack<>();
        for (int v = 0; v < N; v++) {
            if (vis[v] == false) {
                dfsForStack(graph, v, vis, st);
            }
        }
        int top = st.peek();
        count = 0;
        vis = new boolean[N];
        dfsForcount(graph, st.peek(), vis);
        return count == N ? st.peek() + 1 : -1;
    }

    private static int count = 0;

    private static void dfsForcount(ArrayList<ArrayList<Integer>> graph, int src, boolean vis[]) {
        vis[src] = true;
        count++;
        for (int nbr : graph.get(src)) {
            if (vis[nbr] == false) {
                dfsForcount(graph, nbr, vis);
            }
        }

    }

    private static void dfsForStack(ArrayList<ArrayList<Integer>> graph, int src, boolean vis[], Stack<Integer> st) {
        vis[src] = true;
        for (int nbr : graph.get(src)) {
            if (vis[nbr] == false) {
                dfsForStack(graph, nbr, vis, st);
            }
        }
        st.push(src);

    }

    // leetcode 934:https://leetcode.com/problems/shortest-bridge/
    public int shortestBridge(int[][] grid) {
        // 1.add all one in queue,using DFS from first on encountered
        Queue<pairD> qu = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            boolean flag = true;
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfsShortestBridge(grid, i, j, qu);
                    flag = false;
                    break;
                }
            }
            if (flag == false) {

                break;
            }
        }

        // 2.applly bfs and check first one eoncounteres
        int lvl = -1;
        while (qu.size() > 0) {
            lvl++;
            int sz = qu.size();
            while (sz-- > 0) {
                pairD rem = qu.remove();
                for (int d = 0; d < xdir.length; d++) {
                    int r = rem.x + xdir[d];
                    int c = rem.y + ydir[d];
                    if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] != -1) {
                        if (grid[r][c] == 1)
                            return lvl;
                        else {
                            grid[r][c] = -1;
                            qu.add(new pairD(r, c));
                        }
                    }
                }
            }
        }
        return 0;
    }

    static void dfsShortestBridge(int[][] grid, int x, int y, Queue<pairD> qu) {
        grid[x][y] = -1;
        qu.add(new pairD(x, y));
        for (int d = 0; d < xdir.length; d++) {
            int r = x + xdir[d];
            int c = y + ydir[d];
            if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1) {
                dfsShortestBridge(grid, r, c, qu);
            }
        }
    }

    // Course Scheduling 1
    // https://leetcode.com/problems/course-schedule/
    // Using Khans algorithm
    public boolean canFinish(int n, int[][] edges) {
        // prepare graph
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        // Make an indegree array from graph
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int nbr : graph.get(i)) {
                indegree[nbr]++;
            }
        }

        // add element in queue which have 0 indegree
        Queue<Integer> qu = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0)
                qu.add(i);
        }

        int count = 0;
        while (qu.size() > 0) {
            // 1.get+remove
            int rem = qu.remove();

            // 2.work
            count++;

            // Decrease indegree of nbr and if indegree becomes 0 ,add in queue
            for (int nbr : graph.get(rem)) {
                indegree[nbr]--;
                if (indegree[nbr] == 0)
                    qu.add(nbr);
            }

           

        }
        return count == n;

    }

    //course schedule 2
    public int[] findOrder(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        // Make an indegree array from graph
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int nbr : graph.get(i)) {
                indegree[nbr]++;
            }
        }

        // add element in queue which have 0 indegree
        Queue<Integer> qu = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0)
                qu.add(i);
        }

        int count = 0;
        Stack<Integer>st=new Stack<>();
        while (qu.size() > 0) {
            // 1.get+remove
            int rem = qu.remove();

            // 2.work
            st.push(rem);
            count++;

            // Decrease indegree of nbr and if indegree becomes 0 ,add in queue
            for (int nbr : graph.get(rem)) {
                indegree[nbr]--;
                if (indegree[nbr] == 0)
                    qu.add(nbr);
            }

           

        }
        if(count==n){
            int []res=new int[0];
            return res;
        }
        else{
            int idx=0;
            int res[]=new int[count];
            while(st.size()>0)
              res[idx++]=st.pop();
            return res;  
        }
    }

    public static void main(String[] args) {

    }
}
