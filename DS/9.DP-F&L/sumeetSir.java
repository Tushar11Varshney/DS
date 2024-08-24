import java.util.*;

public class sumeetSir {

    public static void main(String[] args) throws Exception {
        // Scanner sc=new Scanner(System.in);
        // int n=sc.nextInt();int val;
        // int jump[]=new int[n];
        // for(int i=0;i<n;i++)
        // {
        // val=sc.nextInt();
        // jump[i]=val;
        // }
        // int n=6;
        // int jump[]={2,3,0,2,2,3};
        // int []dp=new int[n+1];
        // Arrays.fill(dp,-1);
        // int n=10;
        // int jump[]={3,3,0,2,1,2,4,2,0,0};

        // Integer []dp=new Integer[n+1]; //return null if no tarikka to pahunch at top
        // System.out.println(climbStairWithVariableJump(jump,n,0,dp));
        // System.out.println(climbStairWithVariableJumpMinMoves(jump,n,0,dp));
        // display(dp);
        // System.out.println(climbStairWithVariableJumpMinMoves_DP(jump,n,dp));
        // display(dp);

        // int cost[][]=new int[n][3];

        // for(int i=0;i<n;i++)
        // {
        // for(int j=0;j<3;j++)
        // {
        // cost[i][j]=sc.nextInt();
        // }
        // }

        // System.out.println(paintHouse(cost,n));

    }


    //313
    public int nthSuperUglyNumber(int n, int[] primes) {
        
        int m=primes.length;
        int []dp=new int[n];
        int []pointers=new int[m];
        dp[0]=1;
        
        for(int i=1;i<n;i++)
        {
            dp[i]=Integer.MAX_VALUE;
            for(int j=0;j<m;j++)
            dp[i]=Math.min(dp[i],primes[j]*dp[pointers[j]]);
            for(int j=0;j<m;j++)
            {
                if(dp[i]==primes[j]*dp[pointers[j]])
                    pointers[j]++;
            }
        }    
        
        return dp[n-1];
    }

    // 682-take a arraylist and sum variable o(n) time and space
    public int calPoints(String[] ops) {

        List<Integer> record = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < ops.length; i++) {
            String str = ops[i];
            if (str.equals("+")) {
                int newR = record.get(record.size() - 1) + record.get(record.size() - 2);
                sum += newR;
                record.add(newR);
            } else if (str.equals("D")) {
                record.add(2 * record.get(record.size() - 1));
                sum += record.get(record.size() - 1);
            } else if (str.equals("C")) {
                sum -= record.get(record.size() - 1);
                record.remove(record.size() - 1);
            } else {
                record.add(Integer.parseInt(str));
                sum += record.get(record.size() - 1);
            }
        }

        return sum;
    }



    //303
    class NumArray
    {
    public:
        vector<int> arr;
        vector<vector<int>> sumDp;
        NumArray(vector<int> &nums)
        {
            arr = nums;
            int n = arr.size();
            sumDp.resize(n + 1, vector<int>(n + 1, -1));
            calculate(n);
        }

        void calculate(int n)
        {
            for (int gap = 0; gap < n; gap++)
            {
                for (int i = 0, j = gap; j < n; j++, i++)
                {
                    if (gap == 0)
                        sumDp[i][j] = arr[i];
                    else if (gap == 1)
                        sumDp[i][j] = arr[i] + arr[j];
                    else
                    {
                        sumDp[i][j] = sumDp[i][j - 1] + arr[j];
                    }
                }
            }
        }

        int sumRange(int left, int right)
        {
            return sumDp[left][right];
        }
    };

    class NumArray
    {
    public:
        vector<int> arr;
        NumArray(vector<int> &nums)
        {

            for (int i = 1; i < nums.size(); i++)
            {
                nums[i] += nums[i - 1];
            }

            arr = nums;
        }

        int sumRange(int left, int right)
        {
            return left == 0 ? arr[right] : arr[right] - arr[left - 1];
        }
    };

    public int minSteps(int n) {
        if(n==1)return 0;
        if(n==2)return 2;
        
        int []dp=new int[n+1];
        dp[2]=2;
        for(int i=3;i<=n;i++)
        {
            dp[i]=i;
            for(int j=i/2;j>=1;j--)
            {
                if(i%j==0)
                {
                    dp[i]=dp[j]+(i/j);
                    break;
                }
            }
        }
        
        return dp[n];
    }

}


// arithmetic slices-413
public int numberOfArithmeticSlices(int[] arr) { 
    if (arr.length <= 1)
        return 0;
    int[] dp = new int[arr.length];

    dp[0] = 0;
    dp[1] = 0;
    int ans = 0;
    for (int i = 2; i < arr.length; i++) {
        if (arr[i] - arr[i - 1] == arr[i - 1] - arr[i - 2]) {
            dp[i] = dp[i - 1] + 1;
            ans += dp[i];
        }
    }
    return ans;
}

//446
public int numberOfArithmeticSlices2(int[] arr) {
    int count=0;
    HashMap<Long,Integer>[]map=new HashMap[arr.length];
    for(int i=0;i<arr.length;i++)
    map[i]=new HashMap<>();
    
    for(int i=1;i<arr.length;i++)
    {
        for(int j=0;j<i;j++)
        {
            long d=(long)arr[i]-(long)arr[j]; 
            if(d<=Integer.MIN_VALUE||d>=Integer.MAX_VALUE)continue;
            if(map[j].containsKey(d))
            {
                int apsEndingwithJ=map[j].get(d);
                int apsEndingwithI=map[i].getOrDefault(d,0);
                count+=apsEndingwithJ;
                map[i].put(d,apsEndingwithI+apsEndingwithJ+1);
            }
            else
            map[i].put(d,map[i].getOrDefault(d,0)+1);
        }
    }
    return count;
}
