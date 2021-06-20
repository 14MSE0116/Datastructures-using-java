import java.net.Inet4Address;
import java.util.*;

public class perfectFriend {

    public static void gcc(ArrayList<Integer>[] graph, int src, boolean vis[], ArrayList<Integer> comp) {
        vis[src] = true;
        comp.add(src);
        for (int nbr : graph[src]) {
            if (vis[nbr] == false) {
                gcc(graph, nbr, vis, comp);
            }

        }
    }

    public static int perfectFr(ArrayList<Integer>[] graph) {
        int n = graph.length;
        boolean vis[] = new boolean[n];
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        for (int v = 0; v < n; v++) {
            if (vis[v] != true) {
                ArrayList<Integer> comp = new ArrayList<>();
                gcc(graph, v, vis, comp);
                comps.add(comp);
            }
        }

        // find count with different appraoches
        // method 1
        // int count = 0;
        // for (int i = 0; i < comps.size(); i++) {
        //     int sz1 = comps.get(i).size();
        //     for (int j = i + 1; j < comps.size(); j++) {
        //         int sz2 = comps.get(j).size();
        //         count += (sz1 * sz2);

        //     }
        // }

        // return count;

        //method 2;
        int count=0;
        int sum=comps.get(comps.size()-1).size();
        for(int i=comps.size()-2;i>=0;i--)
        {
            int s=comps.get(i).size();
            count+=sum*s;
            sum+=s;
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt(); // number of vertices
        int k = scn.nextInt(); // number of edges

        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        // add edges in graph k times;
        for (int i = 0; i < k; i++) {
            int src = scn.nextInt();
            int nbr = scn.nextInt();

            graph[src].add(nbr);
            graph[nbr].add(src);
        }

        // no of pairs of perfect friens,count so that both are not in same club
        int count = perfectFr(graph);
        System.out.println(count);
    }
}
