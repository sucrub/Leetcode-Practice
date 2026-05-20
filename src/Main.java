import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    // 1345
    public static int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for(int i = 0; i < n; i++) {
            mp.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        boolean[] vis = new boolean[n];
        vis[0] = true;
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0];
            int dist = curr[1];

            if (node == n - 1) return dist;
            if (node - 1 >= 0 && !vis[node-1]) {
                vis[node-1] = true;
                q.offer(new int[]{node-1,dist+1});
            }
            if(node + 1 < n && !vis[node+1]) {
                vis[node+1] = true;
                q.offer(new int[]{node+1,dist+1});
            }
            for(int next : mp.get(arr[node])) {
                if(!vis[next]) {
                    vis[next] = true;
                    q.offer(new int[]{next, dist+1});
                }
            }
            mp.get(arr[node]).clear();
        }
        return -1;
    }

    // 2540
    public static int getCommon(int[] nums1, int[] nums2) {
        TreeSet<Integer> seen = new TreeSet<>();
        for (int j : nums1) {
            seen.add(j);
        }
        for (int j : nums2) {
            if (seen.contains(j)) return j;
        }
        return -1;
    }

    // 1306
    public static boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n];
        q.offer(start);
        while(!q.isEmpty()) {
            int i = q.poll();
            if(arr[i] == 0) return true;
            int forward = i + arr[i];
            int backward = i - arr[i];

            if(forward < n && !vis[forward]) {
                vis[forward] = true;
                q.offer(forward);
            }

            if(backward >= 0 && !vis[backward]) {
                vis[backward] = true;
                q.offer(backward);
            }
        }
        return false;
    }

    // 2657
    public static int[] findThePrefixCommonArray(int[] A, int[] B) {
        TreeSet<Integer> seen = new TreeSet<>();
        int n = A.length;
        int[] C = new int[n];
        for(int i = 0; i < n; i++) {
            int check1 = 0, check2 = 0;
            if(!seen.add(A[i])) check1 = 1;
            if(!seen.add(B[i])) check2 = 1;
            if(i == 0) C[i] = check1 + check2;
            else C[i] = C[i - 1] + check1 + check2;
        }
        return C;
    }

    public static void main(String[] args) {

    }
}