import java.util.*;

class graphs {
    static int xdir[] = {-1, 0, 1, 0};
    static int ydir[] = {0, 1, 0, -1};
    static char dirs[] = {'t', 'r', 'd', 'l'};

    //https://leetcode.com/problems/number-of-enclaves/
    class NumberofEnclaves {
        static int xdir[] = {-1, 0, 1, 0};
        static int ydir[] = {0, 1, 0, -1};

        public int numEnclaves(int[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1) {
                        if (grid[i][j] == 1) {
                            dfs(grid, i, j);
                        }
                    }
                }
            }

            int count = 0;
            for (int i = 0; i < grid.length - 1; i++) {
                for (int j = 0; j < grid[0].length - 1; j++) {
                    if (grid[i][j] == 1)
                        count++;
                }
            }
            return count;

        }

        void dfs(int[][] grid, int r, int c) {
            grid[r][c] = 0;
            for (int d = 0; d < xdir.length; d++) {
                int rr = r + xdir[d];
                int cc = c + ydir[d];
                if (rr >= 0 && rr < grid.length && cc >= 0 && cc < grid[0].length && grid[rr][cc] == 1) {
                    dfs(grid, rr, cc);
                }
            }

        }
    }

    //Number of distinct islands->Portal question
    static StringBuilder psf;

    public static int numDistinctIslands(int[][] arr) {
        //write your code here
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1) {
                    psf = new StringBuilder();
                    psf.append('x');
                    dfsdistincislandhelper(arr, i, j);
                    set.add(psf.toString());
                }
            }
        }
        return set.size();
    }

    static void dfsdistincislandhelper(int grid[][], int r, int c) {
        grid[r][c] = 0;
        for (int d = 0; d < xdir.length; d++) {
            int rr = r + xdir[d];
            int cc = c + ydir[d];
            if (rr >= 0 && rr < grid.length && cc >= 0 && cc < grid[0].length && grid[rr][cc] == 1) {
                psf.append(dirs[d]);
                dfsdistincislandhelper(grid, rr, cc);
            }
        }
        psf.append('#');
    }

    //Rotten Oranges->use BFS strategy
    public static class Pair {
        int x;
        int y;
        int time;

        Pair(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

    }

    public static int orangesRotting(int[][] grid) {

        int count = 0;
        Queue<Pair> qu = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    count++;
                    qu.add(new Pair(i, j, 0));

                } else if (grid[i][j] == 1) {
                    count++;
                }
            }
        }

        if (count == 0)
            return count;

        int ans = 0;
        while (qu.size() > 0) {
            //get+remove
            Pair rem = qu.remove();

            //mark*
            if (grid[rem.x][rem.y] == -1)
                continue;
            else
                grid[rem.x][rem.y] = -1;

            //work
            ans = rem.time;
            count--;

            //add children
            for (int d = 0; d < xdir.length; d++) {
                int r = rem.x + xdir[d];
                int c = rem.y + ydir[d];
                if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length) {
                    if (grid[r][c] != -1 && grid[r][c] == 1) {
                        qu.add(new Pair(r, c, rem.time + 1));
                    }
                }
            }
        }
        return count == 0 ? ans : -1;
    }

    public static void main(String[] args) {

    }
}