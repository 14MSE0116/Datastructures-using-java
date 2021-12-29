import java.util.*;
import java.io.*;

public class HashMapp {
    //Number Of Employees Under Every Manager
    static int getsize(HashMap<String, HashSet<String>> map, String ceo) {
        if (map.get(ceo) == null) {
            System.out.println(ceo + " " + 0);
            return 1;
        }
        int count = 0;
        for (String child : map.get(ceo)) {
            count += getsize(map, child);
        }
        System.out.println(ceo + " " + count);
        return count + 1;
    }

    // Find Iternnary from tickets
    static void prinpath(HashMap<String, String> tickets) {
        // find starting point
        HashMap<String, Boolean> map = new HashMap<>();
        for (String city1 : tickets.keySet()) {
            String city2 = tickets.get(city1);
            map.put(city2, false);
            if (map.containsKey(city1) == false)
                map.put(city1, true);
        }
        String src = "";
        for (String city : map.keySet()) {
            if (map.get(city)) {
                src = city;
                break;
            }
        }

        String path = "";
        while (tickets.containsKey(src) == true) {
            path += src + "->";
            src = tickets.get(src);
        }
        path += src + ".";
        System.out.println(path);

    }

    // Count Distinct Elements In Every Window Of Size K
    public static ArrayList<Integer> solution(int[] arr, int k) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int j = 0;
        for (int i = 0; i < k - 1; i++)
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

        for (int i = k - 1; i < arr.length; i++, j++) {
            // add impact of ith index
            int fq = map.getOrDefault(arr[i], 0);
            map.put(arr[i], fq + 1);
            // make result
            res.add(map.size());

            // remove impact of j
            if (map.get(arr[j]) == 1) {
                map.remove(arr[j]);
            } else
                map.put(arr[j], map.get(arr[j]) - 1);
        }
        return res;

    }

    // Check If An Array Can Be Divided Into Pairs Whose Sum Is Divisible By K
    public static void ispairingpossible(int[] arr, int k) {
        // write your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int fq = map.getOrDefault(arr[i] % k, 0);
            map.put(arr[i] % k, fq + 1);
        }
        boolean res = true;
        for (int rem1 : map.keySet()) {
            if (rem1 == 0 || (k % 2 == 0 && rem1 == k / 2)) {
                if (map.get(rem1) % 2 != 0) {
                    res = false;
                }
            } else {
                if (map.containsKey(k - rem1) == false || map.get(rem1) != map.get(k - rem1)) {
                    res = false;
                    break;
                }
            }
        }
        System.out.println(res);

    }

    public static void main(String[] args) {

    }

}
