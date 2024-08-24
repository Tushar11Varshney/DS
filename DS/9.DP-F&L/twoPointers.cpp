#include <iostream>
#include <vector>
#include <math.h>
#include <list>
using namespace std;

//copy recursive code as it is in iterative when same result for a state multiple times
void print(vector<int> &arr)
{
    for (int ele : arr)
        cout << ele << " ";
    cout << endl;
}

void printlong(vector<long long> &arr)  //long==long int==long long==long long int-16bytes,long double(16 bytes)
{
    for (int ele : arr)
        cout << ele << " ";
    cout << endl;
}

void print2d(vector<vector<int>> &arr)
{
    for (vector<int> ar : arr)
    {
        print(ar);
    }
}

public static void display(int arr[]) {
    for (int ele : arr)
        System.out.print(ele + " ");

    System.out.println();
}

int main()
{
    // fibo();
    // friendsproblem();
    // climbingStairsproblem();
    // decodeways();
    // boardpathproblem();
    // decodewaystwoprob();
    // mp();
    // minCostClimbingStairsProb();
    // goldmineproblem();
    return 0;
}

//==================
public static void countBinaryString(int n) {
    int cendWithZeros = 1;
    int cendWithOne = 1;

    for (int i = 2; i <= n; i++) {
        int nendWithZeros = cendWithOne;
        int nendWithOne = cendWithOne + cendWithZeros;  //new

        cendWithZeros = nendWithZeros;
        cendWithOne = nendWithOne;  //old
    }

    System.out.println(cendWithZeros + cendWithOne);
}

public static void arrangeBuilding(int n) {
    long cBuilding = 1;
    long cSpace = 1;

    for (int i = 2; i <= n; i++) {  //i->plots
        long ncBuilding = cSpace;
        long ncSpace = cBuilding + cSpace;

        cSpace = ncSpace;
        cBuilding = ncBuilding;
    }

    long totalWays = cSpace + cBuilding;
    System.out.println(totalWays * totalWays);
}

public static int countSubsequenceABC(String str) {
    int a = 0, ab = 0, abc = 0;
    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);

        if (ch == 'a')
            a = 2 * a + 1;
        else if (ch == 'b')
            ab = 2 * ab + a;
        else if (ch == 'c')
            abc = 2 * abc + ab;
    }
    return abc;
}

public static int maxSumNonAdjacentElement(int[] arr, int n) {
    int inc = arr[0];
    int exc = 0;

    for (int i = 1; i < n; i++) {
        int nexc = Math.max(inc, exc); // newExclude
        int ninc = exc + arr[i];

        inc = ninc;
        exc = nexc;
    }

    return Math.max(inc, exc);
}

public static int paintHouse(int cost[][],int n)
{
    for(int i=1;i<n;i++)
    {
        cost[i][0]=Math.min(cost[i-1][1],cost[i-1][2])+cost[i][0];
        cost[i][1]=Math.min(cost[i-1][0],cost[i-1][2])+cost[i][1];
        cost[i][2]=Math.min(cost[i-1][0],cost[i-1][1])+cost[i][2];
    }
    
    return Math.min(Math.min(cost[n-1][0],cost[n-1][1]),cost[n-1][2]);
}

public static int paintHouse2(int cost[][], int n, int k) {  //N HOUSE //K COLOR
    int dp[][] = new int[n][k];

    for (int i = 0; i < k; i++)
        dp[0][i] = cost[0][i];

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < k; j++) {
            int min = Integer.MAX_VALUE;

            for (int l = 0; l < k; l++) {
                if (l != j)
                    min = Math.min(min, dp[i][l]);
            }
            dp[i][j] = min + cost[i][j];
        }
    }

    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < k; i++)
        ans = Math.min(ans, dp[n - 1][i]);

    return ans;
}

public static int paintHouse2method(int cost[][], int n, int k) {
    int dp[][] = new int[n][k];
    int least = Integer.MAX_VALUE, sleast = Integer.MAX_VALUE;
    for (int i = 0; i < k; i++) {
        dp[0][i] = cost[0][i];
        if (dp[0][i] <= least) {
            sleast = least;
            least = dp[0][i];
        } else if (dp[0][i] < sleast)
            sleast = dp[0][i];
    }

    for (int i = 1; i < n; i++) {
        int nleast = Integer.MAX_VALUE;
        int nsleast = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) {
            if (dp[i - 1][j] != least)
                dp[i][j] = least + cost[i][j];
            else
                dp[i][j] = sleast + cost[i][j];

            if (dp[i][j] <= nleast) {
                nsleast = nleast;
                nleast = dp[i][j];
            } else if (dp[i][j] < nsleast)
                nsleast = dp[i][j];
        }

        least = nleast;
        sleast = nsleast;
    }

    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < k; i++)
        ans = Math.min(ans, dp[n - 1][i]);

    return ans;
}

public static void paintFence(int n, int k) {  //N HOUSE K COLOR
    int same = k;
    int different = k * (k - 1);
    int total = same + different;

    for (int i = 3; i <= n; i++) {
        same = different;
        different = total * (k - 1);
        total = same + different;
    }

    System.out.println(total);
}

public static int tilingwith2X1(int n, int[] dp) {
    if (n == 0 || n < 0)
        return n == 0 ? 1 : 0;

    if (dp[n] != -1)
        return dp[n];

    int a = tilingwith2X1(n - 1, dp);
    int b = tilingwith2X1(n - 2, dp);

    return dp[n] = a + b;
}

public static int tilingwith2X1_2(int n, int[] dp) {
    dp[1] = 1;
    dp[2] = 2;

    for (int i = 3; i <= n; i++)
        dp[i] = dp[i - 1] + dp[i - 2];

    return dp[n];
}

//i did 2d dp..wrong 2dmsn change hi ni horhi
public static int TilingwithMX1_dp(int n, int m, int[] dp) {
    for (int i = 1; i <= n; i++) {
        if (i < m)
            dp[i] = 1;
        else if (i == m)
            dp[i] = 2;
        else
            dp[i] = dp[i - 1] + dp[i - m];
    }

    return dp[n];
}

public static void optimalStrategy(int[] arr) { 
    int n = arr.length;
    int[][] dp = new int[n][n];

    for (int gap = 0; gap < n; gap++) {
        for (int i = 0, j = gap; j < n; i++, j++) {
            if (gap == 0)
                dp[i][j] = arr[j];
            else if (gap == 1)
                dp[i][j] = Math.max(arr[i], arr[j]);
            else
                dp[i][j] = Math.max(arr[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]),
                        arr[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]));
        }
    }
    System.out.println(dp[0][n - 1]);
}

// 887
public static int eggDrop(int e, int f) {
    int[][] dp = new int[e + 1][f + 1];
    for (int i = 1; i <= e; i++) {
        for (int j = 1; j <= f; j++) {
            if (i == 1)
                dp[i][j] = j;
            else if (j == 1) {
                dp[i][j] = 1;
            } else {
                int min = (int) 1e8, k = 0;
                for (int pr = i - 1, pc = j - 1; pc >= 0; pc--) {  //pr:prev row,pc:prev col
                    int val = Math.max(dp[pr][k], dp[i][pc]);
                    min = Math.min(min, val);
                    k++;
                }
                dp[i][j] = min + 1;
            }
        }
    }
    return dp[e][f];
}

public static int rodcutting(int[] prices) {
    int np[] = new int[prices.length + 1];  //new price

    for (int i = 0; i < prices.length; i++)
        np[i + 1] = prices[i];

    int[] dp = new int[prices.length + 1];
    dp[0] = 0;
    dp[1] = np[1];

    for (int i = 2; i < dp.length; i++) {
        dp[i] = np[i];
        for (int j = 1, k = i - 1; j <= i / 2; j++, k--)
            dp[i] = Math.max(dp[i], dp[j] + dp[k]);
    }

    return dp[prices.length];
}

public static long partitionintoSubset(int n, int k, long[][] dp) { 

    if (n == 0 || k == 0 || n < k)
        return 0;

    for (int t = 1; t <= k; t++) {
        for (int p = 1; p <= n; p++) {
            if (p < t)
                dp[t][p] = 0;
            else if (t == p)
                dp[t][p] = 1;
            else {
                dp[t][p] = t * dp[t][p - 1] + dp[t - 1][p - 1];
            }
        }
    }
    return dp[k][n];
}

public static int templeoffering(int[] height) {

    int n = height.length;
    int[] forward = new int[n];

    forward[0] = 1;   //detect upar jaate hue pahad ki wall
    for (int i = 1; i < n; i++) {
        if (height[i] > height[i - 1])
            forward[i] = forward[i - 1] + 1;
        else
            forward[i] = 1;  //taaki girte hue right se ans aaye
    }

    int[] backward = new int[n];   //detect niche jaate hue ki wall

    backward[n - 1] = 1;
    for (int j = n - 2; j >= 0; j--) {
        if (height[j] > height[j + 1])
            backward[j] = backward[j + 1] + 1;
        else
            backward[j] = 1;
    }

    int result = 0;
    for (int i = 0; i < n; i++)
        result += Math.max(forward[i], backward[i]);

    return result;
}

// 279
public int numSquares(int n) {

    int[] dp = new int[n + 1];
    Arrays.fill(dp, (int) 1e9);
    dp[0] = 0;  //0 cannot be represented by any number square so dp[0]=0;
    dp[1] = 1; 

    for (int i = 2; i <= n; i++) {
        for (int j = 1; j * j <= i; j++) {
            int sum = i - j * j;
            dp[i] = Math.min(dp[i], dp[sum] + 1);
        }
    }
    return dp[n];
}

//403 
public int canCross_helper(int []stones,int jump,int src,int [][]dp,HashMap<Integer,Integer>hs)
{
    if(src==stones[stones.length-1]){
        return 1;
    }
    
    int idx=hs.get(src); //we cant use stone[i] for dp size but can use hashmap
    if(dp[idx][jump]!=-1)return dp[idx][jump];
    
    boolean res=false;
    if(jump-1>0 && hs.containsKey(src+jump-1))
        res=res||(canCross_helper(stones,jump-1,src+jump-1,dp,hs)==1); 
    
    if(hs.containsKey(src+jump))
    res=res||(canCross_helper(stones,jump,src+jump,dp,hs)==1); 
    
    if(hs.containsKey(src+jump+1))
    res=res||(canCross_helper(stones,jump+1,src+jump+1,dp,hs)==1);
    
    return dp[idx][jump]=res==true?1:0;
}

public boolean canCross(int[] stones) {
    if(stones[1]!=1)return false;
    int n=stones.length;
    int [][]dp=new int[n][1000];
    
    for(int []a:dp)
        Arrays.fill(a,-1);
    
    HashMap<Integer,Integer>hs=new HashMap<>();
    for(int i=0;i<stones.length;i++)
        hs.put(stones[i],i);
    return canCross_helper(stones,1,1,dp,hs)==1;
}


//1306
public boolean canReach_helper(int[] arr, Boolean[] dp, int idx) {
    if (idx < 0 || idx >= arr.length)
        // if(idx<0 || idx>=arr.length || arr[idx]>arr.length)
        return false;

    if (arr[idx] == 0)
        return dp[idx] = true;

    if (dp[idx] != null)
        return dp[idx];

    // dp[idx]=false; //or use visited or same array
    // int jump=arr[idx];
    // arr[idx]+=arr.length;
    // res=canReach_helper(arr,dp,idx+jump)||canReach_helper(arr,dp,idx-jump);
    boolean res = false;
    res = canReach_helper(arr, dp, idx + arr[idx]) || canReach_helper(arr, dp, idx - arr[idx]);
    dp[idx] = res;

    return dp[idx];
}

public boolean canReach(int[] arr, int start) {
    Boolean[] dp = new Boolean[arr.length];
    return canReach_helper(arr, dp, start);
}

//576
int mod=(int)1e9+7;
public int findPath_helper(int x,int y,int [][]dirs,int [][][]dp,int moves)
{
    if(moves<0)return 0;
    if(x<0 || x==dp.length || y<0 || y==dp[0].length)return 1;
    
    if(dp[x][y][moves]!=-1)
        return dp[x][y][moves];
    
    int count=0;
    for(int i=0;i<dirs.length;i++)
    {
        int r=x+dirs[i][0];
        int c=y+dirs[i][1];
        
        count=(count%mod+findPath_helper(r,c,dirs,dp,moves-1)%mod)%mod;
    }
    
    return dp[x][y][moves]=count;
}

public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
    int [][][]dp=new int[m][n][maxMove+1];
    
    for(int [][]d1:dp)
        for(int []d:d1)
        Arrays.fill(d,-1);
    
    int [][]dirs={{0,1},{0,-1},{1,0},{-1,0}};
    
    return findPath_helper(startRow,startColumn,dirs,dp,maxMove)%mod;
}


/*
1.(3)Fibonacci(O(n) & O(1) space)-1.Make array of n+1 size,initialise -1/0
                            2.if(n<=1)return dp[n]=n,memo
                            3.calc dp[n-1],dp[n-2]
                            4.r->dp[n]=dp[n-1]+dp[n-2]...Dirn->left to right
            
            M2-1.take a=0,b=1
            2.traverse from 2<=n and calc sum=a+b and shift a and b

2.(2+1+1+1+1)climbing stairs-1.base case n<=1 r->dp[n]=1 ...dirn left to right
            in pep qs can climb 1,2,3 stairs so if(n<0)r->0

            Climb Stair with Variable Moves-1.dp[n+1] bc:stair==n r->dp[n]=1(path 6->6)..memo
                                           2.c=0,for loop[1,jump[stairs]](stairs+j<=n)c+=call(s+j)
                                           3.dp[stairs]=c.....dirn right to left
                            
                            Minimum Moves-bc:dp[n]=0(chalo mat)
                                          2.res=min(res,call())
                                          3.dp[stairs]=res....right to left
            
            min cost climbing stairs-1.Initialise with 1e8.if n<=1 dp[n]=cost[n];
                        2.min(call(n-1),call(n-2))
                        3.dp[n]=min+(n<size?cost[n]:0)...dirn left to right

3.(3)Friends Pairing-1.base case n<=1 r->1.
                  2.call for(n-1) and call((n-2))*(n-1)
                  3.r->dp[n]=a+b.....loop left to right
                M3-a=1,b=1,sum=a loop 2 to <=n sum=a*(n-1)+b change a to b and b to sum

4.Power(log2(n))-1.dp[n+1]...initialise 0
                 2.if(n==0)r->dp[n]=1;memo
                 3.sAns=call(n/2);
                 4.if dp[n]=n%2==0?sAns*sAns:sAns*sAns*x

5.(3)Count Mazepath-M1-memoization-1.base condn:sr==er&&sc==ec return dp[er][ec]=1,
                        2.take count=0..check in if not out of matrix the call for each side(count+=call())
                        3.return dp[sr][sc]=count
            M2-dirnArray-Loop on dirn array
                    1.2.if(sr+d[i][0]<=er && sc+d[i][0]<=ec)
                            count+=call(sr+d[i][0],sc+d[i][0])
                    1.3 r->dp[sr][sc]=count
            M3-dirn from bottom right to left or bottom right to top.r->dp[SR][SC]

6.(3)Mazepath Multiple jump-memoization-same but loop
                                1.for(int j=1;sc+j<=ec;j++)call(sr,sc+j)
                    M2-dirnArray-same but inner loop of jump(1tomaxJump)
                        1.2 make x any y coord,and check if both exist then call
                        1.3 dp[sr][sc]=count
                    M3-dirns same

7*.(1)Mazepath obstacle-EC:check if at 0/0 obstacle value not present or at (endC/endR) if present r->0
                2.check if coordinates valid and the at those coordinates obstacle value not present.

8.(1)minimum path sum-initialise with 1e8
                   1.Base Case-dp[sr]][sc]=grid[sr][sc];
                   2.at call time dp[sr][sc]=min(dp[sr][sc],callls)
                   3.r->dp[sr][sc]+=grid[sr][sc]

9.(2)Gold Mine Problem-1.return case sc==ec return dp[sr][sc]=grid[sr][sc]
                    2.initialse max with -1e9 and update at each call.In main fn call from left row each time so sr will vary 

                    M2-Dirn will now be from bottom right to top  or from top right to down.same initialise and update for each row first column

11.(4)BoardPath:
        M1-Initialise with 0.Base case:if sp==ep r->dp[ep]=1;
            memo,for(1 to 1<=6)if sp+i<=ep c+=call(sp+i) else break
            r->dp[sp]=count
        M2-dirn from right to left(o(6n) time o(n)space)
        M3-1.take a list
           2.pushfront 2 time 1 value
           3.loop from 2 to <=10 if i[2,6] pushfront 2*front() else pushfront 2*front()-2*back() and popBack(T:o(n) s:o(n))
        M4:1.take array of size 7
           2.find idx(target%size(7))
           3.first two idx 1,1 and then loop[2,10] if[2,6] then a[i]=2*a[i-1] else a[i%sz]=2*a[i-1)%sz]-a[i%sz]       

12*.(3)decode ways(o(n),o(n) space)-EC:1.str length=0||first char 0 r->0
                                2.pass idx,dp(length+1)
                                3.bc:if idx==length r->dp[idx]=1;
                                4.memo,if s[idx]==0 r->0
                                5.count(c)=0,make call for idx+1,and if idx<l()-1 make num((s[idx]-'0)*10+s[idx+1]-'0') annd num<=26 c+=call(idx+2)..r->dp[n]=c
                M2-dirn loop right to left
                M3 O(1)space-Take a=1(single character call),b=0(double character call),sum=0 ..Iterate from last and if s[i]='0' sum=0
                else make num and if num<=26 then sum=a+b else sum=a...shift b=a and a=sum

            13.(3)decode ways-M1-same base case...if(s[idx]=='*)then c+=9*call(idx+1),check if next idx is * or digit
                                1.2 if(*) then 15*call(idx+2) (**)
                                1.3 if(digit>=0 & <=6)2*call(idx+2) (*d)
                                1.4 if(digit>=7)1*call(idx+2) (*d)
                            2.else c+=call(idx+1) check if next idx(*) 
                                2.1 if(s[idx] was 1)c+=9*call(idx+2) (d*)
                                    if(2)c+=6*call(idx+2)
                                2.2 (dd) call same as indecode way1 [check if next idx ava each time]
                            M2-iterate from last
                            M3-same as decodeways1

54.Rod Cutting-1.store cost at respective index in new array
               2.make dp[n+1]...dp[0=0]/1=arr[i]
               3.loop [2,n]..dp[i]=cost[i]..run j=1,k=i-1,j<=i/2
                    3.1 dp[i]=max(,dp[j]+dp[k])
               4.r->dp[n]

55.Perfect Square-1.src->dstn ..(dp[n+1],1e9) dp[0/1]=0/1
                  2.loop i=[2..n]...inner loop j=1;j*j<=i;j++
                    2.1 dp[i]=min(,dp[remSum]+1)
                  3.r->dp[n]

                  
*/