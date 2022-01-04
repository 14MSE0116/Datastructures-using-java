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
                    if (grid[i][j] == 1) count++;
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

        if (count == 0) return count;

        int ans = 0;
        while (qu.size() > 0) {
            //get+remove
            Pair rem = qu.remove();

            //mark*
            if (grid[rem.x][rem.y] == -1) continue;
            else grid[rem.x][rem.y] = -1;

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

    //815. Bus Routes
    //https://leetcode.com/problems/bus-routes/
    class Solution {
        public int numBusesToDestination(int[][] routes, int source, int target) {
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            makebusstopvsbusnomap(routes, map);
            System.out.println(map);

            Queue<Integer> qu = new LinkedList<>();
            HashSet<Integer> visbus = new HashSet<>();
            HashSet<Integer> visbusstop = new HashSet<>();

            qu.add(source);
            visbusstop.add(source);
            int lvl = 0;

            while (qu.size() > 0) {
                int sz = qu.size();
                while (sz-- > 0) {
                    Integer rem = qu.remove();
                    if (rem == target) return lvl;
                    for (int busnum : map.get(rem)) {
                        if (visbus.contains(busnum)) continue;
                        else {
                            for (int busstopp : routes[busnum]) {
                                if (visbusstop.contains(busstopp) == false) {
                                    visbusstop.add(busstopp);
                                    qu.add(busstopp);
                                }
                            }
                            visbus.add(busnum);
                        }
                    }
                }
                lvl++;
            }
            return -1;
        }

        void makebusstopvsbusnomap(int routes[][], HashMap<Integer, ArrayList<Integer>> map) {
            for (int r = 0; r < routes.length; r++) {
                int busno = r;
                for (int c = 0; c < routes[r].length; c++) {
                    int busstop = routes[r][c];
                    if (map.containsKey(busstop)) {
                        map.get(busstop).add(busno);
                    } else {
                        ArrayList<Integer> subbusno = new ArrayList<>();
                        subbusno.add(busno);
                        map.put(busstop, subbusno);
                    }
                }
            }
        }
    }

    //ACQUIRE AND HOLD STRATEGY
    //the smallest substring of string containing all characters of another string
    public static String solution(String s1, String s2) {
        // write your code here

        //freq map for string 2
        HashMap<Character, Integer> fmap = new HashMap<>();
        for (int i = 0; i < s2.length(); i++)
            fmap.put(s2.charAt(i), fmap.getOrDefault(s2.charAt(i), 0) + 1);

        int acq = -1; //acquire
        int rel = -1; //release

        int count = 0;
        int requirement = s2.length();
        String ans = "";
        HashMap<Character, Integer> map = new HashMap<>();
        while (true) {
            boolean acflag = false;
            boolean relflag = false;
            //acquire
            while (acq < s1.length() - 1 && count < requirement) {
                acq++;
                char ch = s1.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                //conditional increment in count
                if (map.get(ch) <= fmap.getOrDefault(ch, 0)) count++;
                acflag = true;

            }


            //release
            while (rel < acq && count == requirement) {
                //hold answer,if smallest then update the result
                String tempans = s1.substring(rel + 1, acq + 1);
                ans = ans.length() == 0 || tempans.length() < ans.length() ? tempans : ans;

                //get the character and remove from map
                rel++;
                char ch = s1.charAt(rel);
                map.put(ch, map.get(ch) - 1);

                if (map.get(ch) == 0) map.remove(ch);

                //decrement in count if release is invalid
                if (map.getOrDefault(ch, 0) < fmap.getOrDefault(ch, 0)) count--;

                relflag = true;
            }

            if (acflag == false && relflag == false) break;
        }


        return null;
    }

    public static int smallestuniquesubstritself(String str) {
        // write your code here
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++)
            set.add(str.charAt(i));

        int acq = -1;
        int rel = -1;
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        while (true) {
            boolean acflag = false;
            boolean reflag = false;
            //acquire
            while (acq + 1 < str.length() && map.size() < set.size()) {
                acq++;
                char ch = str.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                acflag = true;
            }

            //release
            while (map.size() == set.size()) {
                int templen = acq - rel;
                ans = Math.min(ans, templen);

                rel++;
                char ch = str.charAt(rel);
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0) map.remove(ch);
                reflag = true;
            }

            if (acflag == false && reflag == false) break;
        }
        return ans;
    }

    //Longest Substring with non repeating characters
    public static int longsubstrwithnonrepeatchar(String str) {
        // write your code here
        HashMap<Character, Integer> map = new HashMap<>();
        int rel = -1;
        int acq = -1;
        int anslen = 0;
        while (true) {
            boolean acflag = false;
            boolean reflag = false;

            //acquire
            while (acq + 1 < str.length()) {
                acq++;
                acflag = true;
                char ch = str.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                if (map.get(ch) == 2) break;
                else {
                    int templen = acq - rel;
                    if (templen > anslen) anslen = templen;
                }

            }

            //release
            while (rel < acq) {
                rel++;
                char ch = str.charAt(rel);
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 1) {
                    //after reducing if freq is 1 that means,its repeating
                    break;
                }
                reflag = true;
            }
            if (acflag == false && reflag == false) break;

        }
        return anslen;
    }

    public static void main(String[] args) {

    }
}