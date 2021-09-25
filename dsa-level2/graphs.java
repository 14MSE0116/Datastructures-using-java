import java.util.*;

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

    public static void main(String[] args) {

    }
}
