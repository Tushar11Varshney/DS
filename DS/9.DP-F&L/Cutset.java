package Done;
import java.util.*;

public class Cutset {

    // www.geeksforgeeks.org/minimum-maximum-values-expression/
    public static class pair {
        int minVal = 0;
        int maxVal = 0;

        pair(int minVal, int maxVal) {
            this.minVal = minVal;
            this.maxVal = maxVal;
        }
    };

    public static int Evaluate(char ch, int n1, int n2) {
        if (ch == '*')
            return n1 * n2;
        return n1 + n2;
    }

    public static pair minMaxEvaluation(int si, int ei, int[] arr, char[] operators, pair[][] dp) {
        if (si == ei)
            return new pair(arr[si], arr[si]);

        if (dp[si][ei] != null)
            return dp[si][ei];

        pair myAns = new pair((int) 1e9, -(int) 1e9);
        for (int cut = si; cut < ei; cut++) {
            pair leftAns = minMaxEvaluation(si, cut, arr, operators, dp);
            pair rightAns = minMaxEvaluation(cut + 1, ei, arr, operators, dp);

            char ch = operators[cut];
            myAns.minVal = Math.min(myAns.minVal, Evaluate(ch, leftAns.minVal, rightAns.minVal));
            myAns.maxVal = Math.max(myAns.maxVal, Evaluate(ch, leftAns.maxVal, rightAns.maxVal));
        }

        return dp[si][ei] = myAns;
    }

    public static void minMax() {
        int[] num = { 1, 2, 3, 4, 5 };
        char[] operator = { '+', '*', '+', '*' };
        int n = num.length;
        pair[][] dp = new pair[n][n];
        pair result = minMaxEvaluation(0, n - 1, num, operator, dp);  //passed (0,4) cuts on 0,1,2,3

        System.out.println("minVal :" + result.minVal + " maxVal :" + result.maxVal);
    }

    // https://practice.geeksforgeeks.org/problems/boolean-parenthesization/0#
    public static class pair_ {
        int Tcount = 0;
        int Fcount = 0;

        pair_(int T, int F) {
            this.Tcount = T;
            this.Fcount = F;
        }
    }

    static int mod = 1003;
    static void EvaluateBooleanExprsn(pair_ leftAns, pair_ rightAns, pair_ ans, char ch) {
        int totalCount = ((leftAns.Tcount % mod + leftAns.Fcount % mod) % mod
                * (rightAns.Tcount % mod + rightAns.Fcount % mod) % mod) % mod;
        if (ch == '&') {
            int trueCount = (leftAns.Tcount % mod * rightAns.Tcount % mod) % mod;
            ans.Tcount += trueCount;
            ans.Fcount += (totalCount % mod - trueCount % mod + mod) % mod;
        } else if (ch == '|') {
            int falseCount = (leftAns.Fcount % mod * rightAns.Fcount % mod) % mod;
            ans.Fcount += falseCount;
            ans.Tcount += (totalCount % mod - falseCount % mod + mod) % mod;
        } else {
            int trueCount = ((leftAns.Tcount % mod * rightAns.Fcount % mod) % mod
                    + (leftAns.Fcount % mod * rightAns.Tcount % mod) % mod) % mod;
            ans.Tcount += trueCount;
            ans.Fcount += (totalCount % mod - trueCount % mod + mod) % mod;
        }
    }

    public static pair_ countWaysHelper(int si, int ei, pair_[][] dp, int n, String S) {
        if (si == ei) {
            int t = S.charAt(si) == 'T' ? 1 : 0;
            int f = S.charAt(si) == 'F' ? 1 : 0;
            pair_ base = new pair_(t, f);
            return dp[si][ei] = base;
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        pair_ ans = new pair_(0, 0);
        for (int cut = si + 1; cut < ei; cut += 2) {
            pair_ leftAns = countWaysHelper(si, cut - 1, dp, n, S);
            pair_ rightAns = countWaysHelper(cut + 1, ei, dp, n, S);

            char oper = S.charAt(cut);
            EvaluateBooleanExprsn(leftAns, rightAns, ans, oper); // ans pass kro kyunki hume btana hai...kitne different tarikko se true answer achieve kiya jaa skta hai..
        }
        return dp[si][ei] = ans;
    }

    static int countWays(int N, String S) {
        pair_[][] dp = new pair_[N][N];

        return (countWaysHelper(0, N - 1, dp, N, S).Tcount) % mod;  //mod krna pdega
    }

    // 312 leetcode
    public int maxCoinsBurstBalloon(int si, int ei, int[][] dp, int[] arr) {
        // if(si>ei)
        //     return 0;
        
        if (dp[si][ei] != -1)
            return dp[si][ei];

        int myAns = 0;
        for (int cut = si; cut <= ei; cut++) {
            int leftAns = (cut == si) ? 0 : maxCoinsBurstBalloon(si, cut - 1, dp, arr);
            int rightAns = (cut == ei) ? 0 : maxCoinsBurstBalloon(cut + 1, ei, dp, arr);

            int lVal = (si == 0) ? 1 : arr[si - 1];
            int rVal = (ei == arr.length - 1) ? 1 : arr[ei + 1];
            myAns = Math.max(myAns, leftAns + lVal * arr[cut] * rVal + rightAns);
        }
        return dp[si][ei] = myAns;
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return maxCoinsBurstBalloon(0, n - 1, dp, nums);
    }

    // www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/
    public static int OBST(int si, int ei, int level, int[] freq, int[][][] dp) // tle for big test case
    {
        if (dp[si][ei][level] != -1)
            return dp[si][ei][level];

        int myAns = (int) 1e9;
        for (int cut = si; cut <= ei; cut++) {
            int leftAns = (cut == si) ? 0 : OBST(si, cut - 1, level + 1, freq, dp);
            int rightAns = (cut == ei) ? 0 : OBST(cut + 1, ei, level + 1, freq, dp);

            myAns = Math.min(myAns, leftAns + freq[cut] * level + rightAns);
        }
        return dp[si][ei][level] = myAns;
    }

    public static int OBST2(int si, int ei, int[] freq, int[][] dp, int[] psum) {
        if (dp[si][ei] != -1)
            return dp[si][ei];

        int myAns = (int) 1e9;
        for (int cut = si; cut <= ei; cut++) {
            int leftAns = (cut == si) ? 0 : OBST2(si, cut - 1, freq, dp, psum);
            int rightAns = (cut == ei) ? 0 : OBST2(cut + 1, ei, freq, dp, psum);

            myAns = Math.min(myAns, leftAns + (psum[ei] - (si == 0 ? 0 : psum[si - 1])) + rightAns);
        }
        return dp[si][ei] = myAns;
    }

    public static int OBST3(int si, int ei, int[] freq, int[][] dp) {
        if (dp[si][ei] != -1)
            return dp[si][ei];

        int myAns = (int) 1e9;
        int sum = 0;
        for (int cut = si; cut <= ei; cut++) {
            int leftAns = (cut == si) ? 0 : OBST3(si, cut - 1, freq, dp);
            int rightAns = (cut == ei) ? 0 : OBST3(cut + 1, ei, freq, dp);
            sum += freq[cut];
            myAns = Math.min(myAns, leftAns + rightAns);
        }
        return dp[si][ei] = myAns + sum;
    }

    public static int OBST4(int SI, int EI, int[] freq, int[][] dp) {
        int n = freq.length;
        for (int gap = 0; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                int myAns = (int) 1e9;
                int sum = 0;
                for (int cut = si; cut <= ei; cut++) {
                    int leftAns = (cut == si) ? 0 : dp[si][cut - 1];
                    int rightAns = (cut == ei) ? 0 : dp[cut + 1][ei];
                    sum += freq[cut];
                    myAns = Math.min(myAns, leftAns + rightAns);
                }
                dp[si][ei] = myAns + sum;
            }
        }
        return dp[SI][EI];
    }

    public static void OBSTProb() {
        int[] freq = { 34, 50 };
        int n = freq.length;
        // int [][][]dp=new int[n][n][n+1]; //level start from 1
        // for(int [][]a:dp)
        // for(int []b:a)
        // Arrays.fill(b,-1);
        // System.out.println(OBST(0,n-1,1, freq, dp));

        // int []psum=new int[n];
        // int prev=0;
        int[][] dp = new int[n][n];
        for (int[] b : dp)
            Arrays.fill(b, -1);
        // for(int i=0;i<n;i++)
        // {
        // psum[i]=prev+freq[i];
        // prev=psum[i];
        // }
        // System.out.println(OBST2(0,n-1,freq, dp,psum));

        // System.out.println(OBST3(0,n-1,freq, dp));
        System.out.println(OBST4(0, n - 1, freq, dp));
    }

    // 131 
    List<List<String>> result = new ArrayList<>();
    List<String> ans = new ArrayList<>();
    public void palindromePartition(int si, boolean[][] pdp, int n, String s) {
        if (si == n)
            return;
        if (pdp[si][n - 1]) {
            List<String> base = new ArrayList<>(ans);
            base.add(s.substring(si));
            result.add(base);  //return nahi
        }

        for (int cut = si; cut < n; cut++) {
            if (pdp[si][cut]) {
                ans.add(s.substring(si, cut + 1));
                palindromePartition(cut + 1, pdp, n, s);
                ans.remove(ans.size() - 1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] pdp = new boolean[n][n];
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; j++, i++) {
                if (gap == 0)
                    pdp[i][j] = true;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    pdp[i][j] = true;
                else {
                    if (s.charAt(i) == s.charAt(j) && pdp[i + 1][j - 1])
                        pdp[i][j] = true;
                }
            }
        }
        palindromePartition(0, pdp, n, s);
        return result;
    }

    // 132 
    public int minCut(int si, int[] dp, boolean[][] pdp, int n) {
        if (pdp[si][n - 1]) {
            return dp[si] = 0;
        }

        if (dp[si] != -1)
            return dp[si];

        int ans = (int) 1e8;
        for (int cut = si; cut < n; cut++) {
            if (pdp[si][cut]) {
                int minCount = minCut(cut + 1, dp, pdp, n);
                ans = Math.min(ans, minCount + 1);
            }
        }
        return dp[si] = ans;
    }

    public int minCut(String s) {
        if (s.length() <= 1)
            return 0;

        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        boolean[][] pdp = new boolean[n][n];
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; j++, i++) {
                if (gap == 0)
                    pdp[i][j] = true;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    pdp[i][j] = true;
                else {
                    if (s.charAt(i) == s.charAt(j) && pdp[i + 1][j - 1])
                        pdp[i][j] = true;
                }
            }
        }
        return minCut(0, dp, pdp, n);
    }

    // 1278 
    public int palindromePartition(String s, int k, int si, int ei, int[][] dp, int[][] pdp) {
        if (k == 1)
            return dp[k][ei] = pdp[si][ei];
        if (ei - si + 1 <= k)
            return dp[k][ei] = (ei - si + 1 < k) ? (int) 1e9 : 0;

        if (dp[k][ei] != -1)
            return dp[k][ei];

        int changes = (int) 1e9;
        for (int cut = si; cut < ei; cut++) {
            int recAns = palindromePartition(s, k - 1, si, cut, dp, pdp);
            changes = Math.min(changes, pdp[cut + 1][ei] + recAns);
        }
        return dp[k][ei] = changes;
    }

    public int palindromePartition(String str, int k) {
        if (str.length() == 0 || str.length() <= k)
            return 0;

        int n = str.length();

        int[][] pdp = new int[n][n];

        for (int gap = 1; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; j++, i++) {
                pdp[i][j] = pdp[i + 1][j - 1];
                if (str.charAt(i) != str.charAt(j))
                    pdp[i][j]++;
            }
        }

        int[][] dp = new int[k + 1][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return palindromePartition(str, k, 0, n - 1, dp, pdp);
    }
}
