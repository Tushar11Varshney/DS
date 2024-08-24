// Power
// Fibonacci(R,DP, 2PTR)
// Climb stair(1/2 jump, 1/2/3 jump)(R,DP)
// Climb stair with variable jump(R,DP)
// Climb stair with variable jump using min moves(R,DP)
// Number of ways friend can be single or paired up(R,DP, 2PTR)
// Min cost to climb stair(R,DP)
// Board path(R,DP,LL,Array)
// Decode ways(R,DP,2ptr)
// Decode ways *C(R,DP,2ptr)
// Numeric Keypad
// Jump game 1 - can reach last index?
// Jump game 2 - min jump reqd to reach last index?
// Jump game 3 - idx+jump, idx-jump can it reach array any index with value 0
// Jump game 6 - idx+min_jump to idx+max_jump only travel through 0 and reach last


int power(int n,vector<int>&dp)
{
    if(n==0) return dp[n]= 1;
    if(dp[n]!=0)return dp[n];
    int smallAns=power(n/2,dp);
    return dp[n]=n%2==0?smallAns*smallAns:smallAns*smallAns*2;
}

void power()
{
    int n=10;
    vector<int>dp(n+1);
    cout<<power(n,dp);
}

//509
int fib(int N, vector<int> &dp)
{
    if (dp[N] != -1)
        return dp[N];
    if (N <= 1)
        return dp[N] = N;

    int a = fib(N - 1, dp);
    int b = fib(N - 2, dp);
    return dp[N] = a + b;
}

int fibDp(int m, vector<int> &dp)
{
    for (int N = 0; N <= m; N++)
    {
        if (N <= 1)
        {
            dp[N] = N;
            continue;
        }
        int a = dp[N - 1];
        int b = dp[N - 2];
        dp[N] = a + b;
    }
    return dp[m];
}

int fibOPTI(int n)    
{
    if (n <= 1)
        return n;
    int a = 0;
    int b = 1;
    int sum = 0;
    for (int i = 2; i <= n; i++)
    {
        sum = a + b;
        a = b;
        b = sum;
    }
    return sum;
}

void fibo()
{
    int n = 10;
    vector<int> dp(n + 1, -1); //0 se bhi chljaayga but avoid
    cout << fib(n, dp) << endl;
    print(dp);
    cout << fibDp(n, dp) << endl;
    print(dp);
}

//70
int climbingStairs(int n, vector<int> &dp)
{
    if (n <= 1)
        return dp[n] = 1;

    if (dp[n] != -1)
        return dp[n];

    int a = climbingStairs(n - 1, dp);
    int b = climbingStairs(n - 2, dp);

    return dp[n] = a + b;
}

int climbingStairsDP(int N, vector<int> &dp)
{
    for (int n = 0; n <= N; n++)
    {
        if (n <= 1)
        {
            dp[n] = 1;
            continue;
        }

        int a = dp[n - 1];
        int b = dp[n - 2];

        dp[n] = a + b;
    }
    return dp[N];
}

//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/climb-stairs-official/ojquestion
int climbingStairsPep(int n, vector<int> &dp)
{
    if(n<0)return 0;
    if (n <= 1)
        return dp[n] = 1;

    if (dp[n] != -1)
        return dp[n];

    int a = climbingStairsPep(n - 1, dp);
    int b = climbingStairsPep(n - 2, dp);
    int c = climbingStairsPep(n - 3, dp);

    return dp[n] = a + b + c;
}

void climbingStairsproblem()
{
    // int n = 3;
    // vector<int> dp(n + 1, -1);
    // cout << climbingStairs(n, dp) << endl;
    // print(dp);
    // cout << climbingStairsDP(n, dp) << endl;

    int n;
    cin >> n;
    vector<int> dp(n + 1, -1);
    cout << climbingStairsPep(n, dp) << endl;
    print(dp);
}

public static int climbStairWithVariableJump(int[] jump, int n, int stair, int[] dp) {
    if (stair == n)
        return dp[stair] = 1;

    if (dp[stair] != -1)
        return dp[stair];

    int count = 0;
    for (int j = 1; j <= jump[stair]; j++) {
        if (stair + j <= n)
            count += climbStairWithVariableJump(jump, n, stair + j, dp);
    }
    return dp[stair] = count;
}

public static int climbStairWithVariableJump_DP(int[] jump, int n, int[] dp) {
    dp[n] = 1;
    for (int i = n - 1; i >= 0; i--) {
        int count = 0;
        for (int j = 1; j <= jump[i]; j++) {
            if (i + j <= n)
                count += dp[i + j];
        }
        dp[i] = count;
    }
    return dp[0];
}

public static int climbStairWithVariableJumpMinMoves(int[] jump, int n, int stair, Integer[] dp) {
    if (stair == n)
        return dp[stair] = 0; // chalo hi mat is 1 path with 0 moves

    if (dp[stair] != null)
        return dp[stair];

    int res = (int) 1e9;
    for (int j = 1; j <= jump[stair]; j++) {
        if (stair + j <= n)
        res = Math.min(res, climbStairWithVariableJumpMinMoves(jump, n, stair + j, dp) + 1);
    }
    return dp[stair] = res;
}

public static int climbStairWithVariableJumpMinMoves_DP(int[] jump, int n, int[] dp) {
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int res = (int) 1e9;
            for (int j = 1; j <= jump[i]; j++) {
                if (i + j <= n)
                    // res=Math.min(res,dp[i+j]+j); //moves pucha h isliye + 1 ..j for jumps
                    res = Math.min(res, dp[i + j] + 1);
            }
            dp[i] = res;
        }
        return dp[0];
}

//https://www.geeksforgeeks.org/friends-pairing-problem/
int friendPairing(int n, vector<int> &dp)
{
    if (n <= 1)
        return dp[n] = 1;  //when no person is there to remain single or pair up then 1 tarikka

    if (dp[n] != -1)
        return dp[n];

    int a = friendPairing(n - 1, dp);
    int b = friendPairing(n - 2, dp) * (n - 1);

    return dp[n] = a + b;
}

int friendPairingDP(int N, vector<int> &dp)
{
    for (int n = 0; n <= N; n++)
    {
        if (n <= 1)
        {
            dp[n] = 1;
            continue;
        }

        int a = dp[n - 1];
        int b = dp[n - 2] * (n - 1);

        dp[n] = a + b;
    }
    return dp[N];
}

long long int friendPairingOPTI(int N)
{
    int mod = 1e9 + 7;       //1e2-100 10e2-1000
    long long a=1,b=1,sum=a;
    for(int n=2;n<=N;n++)  //1 <= N <= 10^6..use in array long long agr overflow krjayga tou test case fail
    {    
        sum=((a*(n-1))%mod+b%mod)%mod;  //(a+b)%mod=(a%mod+b%mod)%mod
        a=b;
        b=sum;
    }
    return sum;
}

void friendsproblem()
{
    // int n = 100000;
    int n = 3;
    vector<int> dp(n + 1, -1);
    // cout << friendPairing(n, dp) << endl;
    // cout << friendPairingDP(n, dp) << endl;
    // cout << friendPairingOPTI(n) << endl;
    print(dp);
}

//746 
int minCostClimbingStairs(int n, vector<int> &cost, vector<int> &dp)
{
    if (n <= 1)  //bcz we can start from 0/1
    {
        return dp[n] = cost[n];
    }

    if (dp[n] != 1e8)
        return dp[n];

    // dp[n - 1] = minCostClimbingStairs(n - 1, cost, dp);
    // dp[n - 2] = minCostClimbingStairs(n - 2, cost, dp); //dp mein hum hr seedi tk pahuchne ki min value hi store krrhe hain tou no need to check if from stair1 or stair0 as starting point cost is minimum

    // if (n < cost.size()) return dp[n] = min(dp[n - 1], dp[n - 2]) + cost[n];
    // else  return dp[n] = min(dp[n - 1], dp[n - 2]);   

    int val = min(minCostClimbingStairs(n - 1, cost, dp), minCostClimbingStairs(n - 2, cost, dp));

    return dp[n] = val + ((n < cost.size()) ? cost[n] : 0);
}

int minCostClimbingStairsDP(int N, vector<int> &cost, vector<int> &dp)
{
    for (int n = 0; n <= N; n++)
    {
        if (n <= 1)
        {
            dp[n] = cost[n];
            continue;
        }

        int val = min(dp[n - 1], dp[n - 2]);

        dp[n] = val + ((n < cost.size()) ? cost[n] : 0);
    }
    return dp[N];
}

void minCostClimbingStairsProb()
{
    // vector<int> cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
    vector<int> cost = {10, 15, 20};
    int n = cost.size();
    vector<int> dp(n + 1, 1e8);
    // vector<int> dp(n + 1, 0);
    // cout << minCostClimbingStairsDP(n, cost, dp) << endl;
    cout << minCostClimbingStairs(n, cost, dp) << endl;
    print(dp);
}

//T:o(6n) s:o(n)
int boardPath(int sp, int ep, vector<int> &dp)
{
    if (sp == ep)
        return dp[ep] = 1;

    if (dp[sp] != 0)
        return dp[sp];

    int count = 0;
    for (int i = 1; i <= 6; i++)
    {
        if (sp + i <= ep)
            count += boardPath(sp + i, ep, dp);
        else break;
    }
    return dp[sp] = count;
}

int boardPathDP(int sp, int ep, vector<int> &dp)
{
    for (int sp = ep; sp >= 0; sp--)
    {
        if (sp == ep)
        {
            dp[sp] = 1;
            continue;
        }

        int count = 0;
        for (int i = 1; i <= 6; i++)
        {
            if (sp + i <= ep)
                count += dp[sp + i];
            else
                break;
        }
        dp[sp] = count;
    }
    return dp[SP];
}

void showlist(list<int> l)
{
    list<int>::iterator itr = l.begin();
    for (; itr != l.end(); itr++)
    {
        cout << *itr << " ";
    }
    cout << endl;
}

//T:o(n) s:o(n)
int boardPathOpti()
{
    list<int> l;
    l.push_front(1);
    l.push_front(1);
    for (int i = 2; i <= 10; i++)
    {
        if (i >= 2 && i <= 6)
            l.push_front(2 * l.front());
        else
        {
            l.push_front(2 * l.front() - l.back());
            l.pop_back();
        }
    }
    // showlist(l);
    return l.front();
}

int boardPathOptiArr(int target)
{
    int n = 7;
    int arr[n] = {1, 1};
    int index = target % n;
    for (int i = 2; i <= 10; i++)
    {
        if (i >= 2 && i <= 6)
            arr[i] = 2 * arr[i - 1];
        else
            arr[i % n] = 2 * arr[(i - 1) % n] - arr[i % n];
    }
    // for(int ele:arr)
    // cout<<ele<<" ";
    // cout<<endl;
    return arr[index];
}

void boardpathproblem()
{
    int sp = 0, ep = 10;
    vector<int> dp(ep + 1, 0);
    // cout<<boardPathDP(sp,ep,dp)<<endl;
    cout<<boardPath(sp,ep,dp)<<endl;
    // print(dp);
    // cout << boardPathOpti() << endl;
    // cout << boardPathOptiArr(10) << endl;
}


// leetcode 91-only have to return answer so dont make ans and use dp to pass.
int decodeWays(string &s, int index, vector<int> &dp)  
{
    if (index == s.length())
        return dp[index] = 1;

    if (dp[index] != -1)
        return dp[index];

    if (s[index] == '0')
        return dp[index] = 0;

    int count = 0;
    count = decodeWays(s, index + 1, dp);

    if (index < s.length() - 1)
    {
        int num = (s[index] - '0') * 10 + s[index + 1] - '0';
        if (num <= 26)
            count += decodeWays(s, index + 2, dp);
    }

    return dp[index] = count;
}

int decodeWaysDP(string &s, vector<int> &dp)
{
    for (int index = s.length(); index >= 0; index--)
    {
        if (index == s.length())
        {
            dp[index] = 1;
            continue;
        }

        if (s[index] == '0')
        {
            dp[index] = 0;
            continue;
        }

        int count = 0;
        count += dp[index + 1];

        if (index < s.length() - 1)
        {
            int num = (s[index] - '0') * 10 + s[index + 1] - '0';
            if (num <= 26)
                count += dp[index + 2];
        }

        dp[index] = count;
    }
    return dp[0];
}

int decodeWaysOPTI(string &s)   //o(n) & o(1) space
{
    int a = 1, b = 0,sum;  //a 1 character kelie aur b do jano kelie
    for (int index = s.length() - 1; index >= 0; index--)
    {
        sum = 0;
        if (s[index] == '0')
        {
            sum = 0;
        }
        else
        {
            sum = a;
            // if(index<s.length()-1)
            // {
                int num = (s[index] - '0') * 10 + s[index + 1] - '0'; //null kelie 0-48(0 minus 48)
                if (num <= 26)
                    sum += b; //no need of if condition
            // }
        }
        b = a;
        a = sum;
    }
    return sum;
}

void decodeways()
{
    string s = "1442017";
    int length = s.length();
    vector<int> dp(length + 1, -1);
    // cout << decodeWays(s, 0, dp) << endl;
    // cout << decodeWaysDP(s, dp) << endl;
    cout << decodeWaysOPTI(s) << endl;
    
    //******** if (s.length() == 0 || s[0] == '0')
    // cout<<0;
    // print(dp);
}

//639
long long decodewaystwo(string &s, int index, vector<long long> &dp)
{
    int mod = 1e9 + 7;
    if (index == s.length())
        return dp[index] = 1;

    if (dp[index] != -1)
        return dp[index];

    if (s[index] == '0')
        return dp[index] = 0;

    long long count = 0;
    if (s[index] == '*')
    {
        count = (count % mod + 9 * decodewaystwo(s, index + 1, dp) % mod) % mod;
        if (index < s.length() - 1 && s[index + 1] == '*')
            count = (count % mod + 15 * decodewaystwo(s, index + 2, dp) % mod) % mod;
        else if (index < s.length() - 1 && s[index + 1] >= '0' && s[index + 1] <= '6')
            count = (count % mod + 2 * decodewaystwo(s, index + 2, dp) % mod) % mod;
        else if (index < s.length() - 1 && s[index + 1] >= '7')
            count = (count % mod + decodewaystwo(s, index + 2, dp) % mod) % mod;
    }
    else
    {
        count = (count % mod + decodewaystwo(s, index + 1, dp) % mod) % mod;
        if (index < s.length() - 1 && s[index + 1] == '*')
        {
            if (s[index] == '1')
                count = (count % mod + 9 * decodewaystwo(s, index + 2, dp) % mod) % mod;
            else if (s[index] == '2')
                count = (count % mod + 6 * decodewaystwo(s, index + 2, dp) % mod) % mod;
            //nhi tou call hi ni
        }
        else if (index < s.length() - 1)
        {
            int num = (s[index] - '0') * 10 + (s[index + 1] - '0');
            if (num <= 26)
                count = (count % mod + decodewaystwo(s, index + 2, dp) % mod) % mod;
        }
    }

    return dp[index] = count;
}

long long decodewaystwoDP(string &s, vector<long long> &dp)
{
    int mod = 1e9 + 7;
    for (int index = s.length(); index >= 0; index--)
    {
        if (index == s.length())
        {
            dp[index] = 1;
            continue;
        }

        if (s[index] == '0')
        {
            dp[index] = 0;
            continue;
        }

        long long count = 0;
        if (s[index] == '*')
        {
            count = (count % mod + 9 * dp[index + 1] % mod) % mod;
            if (index < s.length() - 1 && s[index + 1] == '*')
                count = (count % mod + 15 * dp[index + 2] % mod) % mod;
            else if (index < s.length() - 1 && s[index + 1] >= '0' && s[index + 1] <= '6')
                count = (count % mod + 2 * dp[index + 2] % mod) % mod;
            else if (index < s.length() - 1 && s[index + 1] >= '7')
                count = (count % mod + dp[index + 2] % mod) % mod;
        }
        else
        {
            count = (count % mod + dp[index + 1] % mod) % mod;
            if (index < s.length() - 1 && s[index + 1] == '*')
            {
                if (s[index] == '1')
                    count = (count % mod + 9 * dp[index + 2] % mod) % mod;
                else if (s[index] == '2')
                    count = (count % mod + 6 * dp[index + 2] % mod) % mod;
            }
            else if (index < s.length() - 1)
            {
                int num = (s[index] - '0') * 10 + (s[index + 1] - '0');
                if (num <= 26)
                    count = (count % mod + dp[index + 2] % mod) % mod;
            }
        }

        dp[index] = count;
    }
    return dp[0];
}

long long decodewaystwoOPTI(string &s)
{
    int mod = 1e9 + 7;
    int a = 1, b = 0;
    long long count = 0;
    for (int index = s.length() - 1; index >= 0; index--)
    {
        count = 0;
        if (s[index] == '0')
            count = 0;

        else if (s[index] == '*')
        {
            count = (count % mod + 9 * a % mod) % mod;
            if (index < s.length() - 1 && s[index + 1] == '*')
                count = (count % mod + 15 * b % mod) % mod;
            else if (index < s.length() - 1 && s[index + 1] >= '0' && s[index + 1] <= '6')
                count = (count % mod + 2 * b % mod) % mod;
            else if (index < s.length() - 1 && s[index + 1] >= '7')
                count = (count % mod + b % mod) % mod;
        }
        else
        {
            count = (count % mod + a % mod) % mod;
            if (index < s.length() - 1 && s[index + 1] == '*')
            {
                if (s[index] == '1')
                    count = (count % mod + 9 * b % mod) % mod;
                else if (s[index] == '2')
                    count = (count % mod + 6 * b % mod) % mod;
            }
            else if (index < s.length() - 1)
            {
                int num = (s[index] - '0') * 10 + (s[index + 1] - '0');
                if (num <= 26)
                    count = (count % mod + b % mod) % mod;
            }
        }
        b = a;
        a = count;
    }
    return count;
}

void decodewaystwoprob()
{
    string s = "*56*3";
    int index = 0;
    int n = s.length();
    vector<long long> dp(n + 1, -1);
    // cout << decodewaystwo(s, index, dp) << endl;
    // cout << decodewaystwoDP(s, dp) << endl;
    // cout << decodewaystwoOPTI(s) << endl;
    // printlong(dp);
}

public static int numericKeypad(int n) {
    int[][] dp = new int[n + 1][10];

    int[][] keyWeCanPress = { { 0, 8 }, { 1, 2, 4 }, { 1, 2, 3, 5 }, { 2, 3, 6 }, { 1, 4, 5, 7 }, { 2, 4, 5, 6, 8 },{ 3, 5, 6, 9 }, { 4, 7, 8 }, { 5, 7, 9, 8, 0 }, { 6, 8, 9 } };
    for (int i = 0; i < n + 1; i++) {
        for (int j = 0; j < 10; j++) {
            if (i == 0)
                dp[i][j] = 0;
            else if (i == 1)
                dp[i][j] = 1;
            else {
                for (int k = 0; k < keyWeCanPress[j].length; k++) {
                    dp[i][j] += dp[i - 1][keyWeCanPress[j][k]];
                }
            }
        }
    }

    int count = 0;
    for (int i = 0; i < 10; i++)
        count += dp[n][i];
    return count;
}

//55
public int canJump_helper(int si,int ei,int []nums,int []dp)
{
    if(si==ei)
        return dp[si]=1;
    
    if(dp[si]!=-1)return dp[si];
    
    int ans=0;
    for(int i=1;i<=nums[si];i++)
    {
        ans=canJump_helper(si+i,ei,nums,dp);
        if(ans==1)return ans;
    }    
    
    return dp[si]=ans;
}

public boolean canJump(int[] nums) {
    int []dp=new int[nums.length];
    Arrays.fill(dp,-1);
    return canJump_helper(0,nums.length-1,nums,dp)==1?true:false;
}

//55
bool canJump(vector<int>& nums) {
    
    int reachablesoFar=0;
    for(int i=0;i<nums.size();i++)
    {
        if(reachablesoFar<i)return false;
        reachablesoFar=max(reachablesoFar,i+nums[i]);
        if(reachablesoFar>=nums.size()-1)return true;
    }
    
    return false;
}

//45
public int jump_helper(int si,int ei,int []nums,int []dp)
{
    if(si==ei)
        return dp[si]=0;
    
    if(dp[si]!=(int)1e8)
        return dp[si];
    
    int min=(int)1e8;
    for(int i=1;i<=nums[si];i++)
        if(si+i<=ei)
        min=Math.min(min,jump_helper(si+i,ei,nums,dp)+1);
    
    return dp[si]=min;
}

public int jump(int[] nums) {
    
    int []dp=new int[nums.length];
    Arrays.fill(dp,(int)1e8);
    return jump_helper(0,nums.length-1,nums,dp);
}

int jump(vector<int>& nums) {
    int currentReach=0,jump=0,maxReach=0;
    for(int i=0;i<nums.size()-1;i++)
    {
        maxReach=max(maxReach,i+nums[i]);
        if(i==currentReach)  //guaranteed we can reach n-1
        {
            jump++;
            currentReach=maxReach;
        }
    }
    
    return jump;
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
    // arr[idx]+=arr.length; // as a marker ki already visited hai
    // res=canReach_helper(arr,dp,idx+jump)||canReach_helper(arr,dp,idx-jump);
    boolean res = false;
    res = canReach_helper(arr, dp, idx + arr[idx]) || canReach_helper(arr, dp, idx - arr[idx]);
    dp[idx] = res; 

    return dp[idx];
}

public boolean canReach(int[] arr, int start) {
    Boolean[] dp = new Boolean[arr.length]; // by default none
    return canReach_helper(arr, dp, start);
}

//1871 TODO: ek naya testcase fatrha h
bool canReach_helper(string &s,int &minJump,int &maxJump,int idx,vector<int>&dp,int n)
{
    if(idx==s.length()-1)
    return dp[idx]=1;
    
        
    int idx_minRange=idx+minJump;
    int idx_maxRange=min(idx+maxJump,n-1);
    
    for(int j=idx_minRange;j<=idx_maxRange;j++)
    {
        if(s[j]!='1')
        {
            if(dp[j]!=-1)
                return dp[idx]=dp[j];
            else if(canReach_helper(s,minJump,maxJump,j,dp,n))
                return dp[idx]=1;       
        }
    }
    
    return dp[idx]=0;
}

bool canReach(string s, int minJump, int maxJump) {
    if(s[s.length()-1]=='1')return false;
    vector<int>dp(s.length(),-1);
    
    return canReach_helper(s,minJump,maxJump,0,dp,s.length());
}

