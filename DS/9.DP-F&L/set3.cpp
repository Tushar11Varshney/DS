// Target Set
// 1.Coin Permutation, Min coin reqd
// 2.Unbounded knapsack - use any weight any # of time
// 3.Coin combination
// 4. Linear eqn of n variable
// 5.Target sum-each coin only once(2 ways), print all, print 1
// 6. diffrn exprn to reach target


// 1

#include <iostream>
#include <vector>
#include <math.h>
#include <string>
using namespace std;

void print(vector<int> arr)
{
    for (int ele : arr)
        cout << ele << " ";
    cout << endl;
}

void print2d(vector<vector<int>> arr)
{
    for (vector<int> ar : arr)
    {
        print(ar);
    }
}

//377
int coinchangePermutation(int arr[], int n, int targetSum, vector<int> &dp)
{
    if (targetSum == 0)   
        return dp[targetSum] = 1;

    if (dp[targetSum] != 0)  
        return dp[targetSum];

    int count = 0;
    for (int i = 0; i < n; i++)
    {
        if (targetSum - arr[i] >= 0) 
            count += coinchangePermutation(arr, n, targetSum - arr[i], dp);
    }
    return dp[targetSum] = count;
}

//**********dont initilaise with -1 for iterative
int coinchangePermutationDP(int arr[], int n, int targetSum, vector<int> &dp)
{
    for (int i = 0; i <= targetSum; i++)
    {
        if (i == 0)
        {
            dp[i] = 1;   //jaise x raise to 0 1 vaise hi humne maanlo kisi ko 0 rupee dene the tou kuch do hi mt so ye ek tarikka hai..pr if coin 2 3 5 7 then 1 ko pay krne ka koi tarikka ni
            continue;
        }

        int count = 0;
        for (int coinIdx= 0; coinIdx< n; coinIdx++)
        {
            if (i - arr[coinIdx] >= 0)
                dp[i] += dp[i - arr[coinIdx]];
        }
    }
    return dp[targetSum];
}

void coinchangePermutationProb()    
{
    int arr[] = {2,3,5,7};
    int n = sizeof(arr) / sizeof(arr[0]);
    int targetSum = 10;
    vector<int> dp(targetSum + 1, 0);
    // cout << coinchangePermutation(arr, n, targetSum, dp) << endl;
    // cout << coinchangePermutationDP(arr, n, targetSum, dp) << endl;
    // print(dp);
}

//leetcode 322-permutation
// You may assume that you have an infinite number of each kind of coin.
int minCoinsRequired(vector<int> &arr, int n, int targetSum, vector<int> &dp)
{
    if (targetSum == 0)
        return dp[targetSum] = 0;

    if (dp[targetSum] != 1e8)
        return dp[targetSum];

    for (int i = 0; i < n; i++)
    {
        if (targetSum - arr[i] >= 0)
        dp[targetSum] = min(dp[targetSum], minCoinsRequired(arr, n, targetSum - arr[i], dp) + 1);
    }
    return dp[targetSum];
}

int minCoinsRequiredDP(vector<int> &arr, int n, int amount, vector<int> &dp)
{
    for (int targetSum = 0; targetSum <= amount; targetSum++)
    {
        if (targetSum == 0)
        {
            dp[targetSum] = 0;
            continue;
        }

        for (int i = 0; i < n; i++)
        {
            if (targetSum - arr[i] >= 0)
                dp[targetSum] = min(dp[targetSum], dp[targetSum - arr[i]] + 1);
        }
    }
    return dp[amount];
}

void minCoinsRequiredProb()
{
    vector<int> nums = {1,2,5};
    int target = 11;
    vector<int> dp(target + 1, 1e8);
    int ans = minCoinsRequired(nums, nums.size(), target, dp);
    ans = ans != 1e8 ? ans : -1;
    cout << ans << endl;
    print(dp);
}

//bc
//https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/ //permutation
int unboundedKnapsack(vector<int> &wgt, vector<int> &value, int n, int target, vector<int> &dp)  
{
    if (target == 0)
        return dp[target] = 0;

    if (dp[target] != -1)
        return dp[target];

    int maxValue = 0;
    for (int i = 0; i < n; i++)
    {
        if (target - wgt[i] >= 0)
        {
            maxValue = max(maxValue, unboundedKnapsack(wgt, value, n, target - wgt[i], dp) + value[i]);
        }
    }
    return dp[target] = maxValue;
}

void unboundedKnapsackProb()
{
    // vector<int> wgt = {1, 3, 4, 5};
    // vector<int> value = {10, 40, 50, 70};
    // int weight = 8;
    // vector<int> wgt = {1, 2};
    // vector<int> value = {10, 20};
    // int weight = 5;
    // vector<int> wgt = {1, 50};
    // vector<int> value = {10, 30};
    vector<int> wgt = {1,2,3,4};
    vector<int> value = {10, 40,50,70};
    int weight =6;
    int n = wgt.size();
    vector<int> dp(weight + 1, -1);
    cout << unboundedKnapsack(wgt, value, n, weight, dp) << endl;
}

//yhaan combination mein loop pehle coin waale ka hai jisse agr koi coin use hogya tou vo apne pichle waale ko use ni krskta.dont ini -1
int coinchangeCombination(int arr[], int n, int targetSum, vector<int> &dp) 
{
    dp[0] = 1;
    for (int j = 0; j < n; j++)
    {
        for (int i = arr[j]; i <= targetSum; i++)//i=1 & if (i - arr[j] >= 0)
        {
            dp[i] += dp[i - arr[j]];
        }
    }
    return dp[targetSum];
}

void coinchangeCombinationProb()
{
    int arr[] = {2, 3, 5, 7};
    int n = sizeof(arr) / sizeof(arr[0]);
    int targetSum = 10;
    vector<int> dp(targetSum + 1, 0);
    cout << coinchangeCombination(arr, n, targetSum, dp) << endl;
    print(dp);
}

//https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
//like combn
int linearEquationNVariable(vector<int> &coeff, int target, vector<int> &dp)
{
    dp[0] = 1;
    for (int j = 0; j < coeff.size(); j++)
    {
        for (int i = coeff[j]; i <= target; i++)
            dp[i] += dp[i - coeff[j]];
    }
    return dp[target];
}

void linearEquationNVariableProb()
{
    // vector<int>coeff={1,2};
    // int rhs=5;
    vector<int> coeff = {5, 7};
    int rhs = 43;
    vector<int> dp(rhs + 1, 0);
    cout << linearEquationNVariable(coeff, rhs, dp) << endl;
}

//each coin only once combn
int targetSum(vector<int> arr, int idx, int n, int target, vector<vector<int>> &dp)
{
    if (target == 0 || idx == arr.size())
        return dp[idx][target] = target == 0 ? 1 : 0;

    if (dp[idx][target] != 0)
        return dp[idx][target];

    int count = 0;
    if (target - arr[idx] >= 0)
        count += targetSum(arr, idx + 1, n, target - arr[idx], dp);
    count += targetSum(arr, idx + 1, n, target, dp);

    return dp[idx][target] = count; 
}

int targetSumDP(vector<int> arr, int amount, vector<vector<int>> &dp)]\
{
    for (int target = 0; target <= amount; target++)
    {
        for (int idx = arr.size(); idx >= 0; idx--)
        {
            if (target == 0 || idx == arr.size())    //value filled from bottom left to top
            {
                dp[idx][target] = target == 0 ? 1 : 0;
                continue;
            }

            int count = 0;
            if (target - arr[idx] >= 0)
                count += dp[idx + 1][target - arr[idx]];
            count += dp[idx + 1][target];

            dp[idx][target] = count;
        }
    }
    print2d(dp);
    return dp[0][amount];
}

int targetSum2(vector<int> arr, int n, int target, vector<vector<int>> &dp)
{
    if (target == 0 || n == 0)
        return dp[n][target] = target == 0 ? 1 : 0;

    if (dp[n][target] != 0)
        return dp[n][target];

    int count = 0;
    if (target - arr[n - 1] >= 0)
        count += targetSum2(arr, n - 1, target - arr[n - 1], dp);
    count += targetSum2(arr, n - 1, target, dp);

    return dp[n][target] = count;
}

int targetSum2DP(vector<int> arr, int amount, vector<vector<int>> &dp)
{
    for (int target = 0; target <= amount; target++)
    {
        for (int n = 0; n <= arr.size(); n++)  //value filled from top left to bottom
        {
            if (target == 0 || n == 0)
            {
                dp[n][target] = target == 0 ? 1 : 0;
                continue;
            }

            int count = 0;
            if (target - arr[n - 1] >= 0)
                count += dp[n - 1][target - arr[n - 1]];
            count += dp[n - 1][target];

            dp[n][target] = count;
        }
    }
    return dp[arr.size()][amount];
}

void targetsumPath(vector<vector<int>> &dp, int n, string s, vector<int> &arr, int target)
{
    if (target == 0 || n == 0)
    {
        if (target == 0)
            cout << s << endl;
        return ;
    }

    if (target - arr[n - 1] >= 0 && dp[n - 1][target - arr[n - 1]] > 0)
    {
        targetsumPath(dp, n - 1, s + to_string(arr[n - 1]) + ",", arr, target - arr[n - 1]);
    }
    if (dp[n - 1][target] > 0)
        targetsumPath(dp, n - 1, s, arr, target);
}

bool targetsumPathOnecall(vector<vector<int>> &dp, int n, string s, vector<int> &arr, int target)
{
    if (target == 0 || n == 0)
    {
        if (target == 0)
            cout << s << endl;
        return target == 0 ? true : false;
    }

    int res = false;
    if (target - arr[n - 1] >= 0 && dp[n - 1][target - arr[n - 1]] > 0)
    {
        res = res || targetsumPathOnecall(dp, n - 1, s + to_string(arr[n - 1]) + ",", arr, target - arr[n - 1]);
    }
    if (dp[n - 1][target] > 0)
        res = res || targetsumPathOnecall(dp, n - 1, s, arr, target);

    return res;
}

void targetSumProblem()
{
    vector<int> arr = {2, 3, 5, 7};
    int target = 10;
    int n = arr.size();
    vector<vector<int>> dp(n + 1, vector<int>(target + 1, 0));
    // cout << targetSum(arr, 0, n, target, dp) << endl;
    // cout << targetSumDP(arr,target, dp) << endl;
    // cout << targetSum2(arr, n, target, dp) << endl;
    // cout << targetSum2DP(arr, target, dp) << endl;
    cout<< targetsumPath(dp,n,"",arr,target) << endl;
    // targetsumPathOnecall(dp, n, "", arr, target);
    // print2d(dp);
}

//494 * use all coin
int DiffExprnToReachTarget(vector<int>&nums,vector<vector<int>>&dp,int target,int sum,int n)
{
    if(n==0)
    {
        return dp[n][sum]=sum==target?1:0;
    }

    if(dp[n][sum]!=-1)
    return dp[n][sum];

    int count=0;
    count+=DiffExprnToReachTarget(nums,dp,target,sum+nums[n-1],n-1); //positive number
    count+=DiffExprnToReachTarget(nums,dp,target,sum-nums[n-1],n-1); //negative number

    return dp[n][sum]=count;
}

void targetSumProbLeetcode() 
{
    vector<int>nums={2,3,5,7};
    int target=-7;
    int sum=0;
    for(int ele:nums)sum+=ele;
    vector<vector<int>>dp(nums.size()+1,vector<int>(2*sum+1,-1));
    // if(target>sum || target<-sum)cout<<0;                       
    cout<<DiffExprnToReachTarget(nums,dp,target+sum,0+sum,nums.size());
}


//https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
//416
bool partitionEqualSubset(vector<int> arr)
{
    int sum = 0;
    for (int ele : arr)
        sum += ele;
    if (sum % 2 != 0)
        return false;  //why cant return else mein true [1,2,3,8]
    int amount = sum / 2;
    vector<vector<int>> dp(arr.size() + 1, vector<int>(amount + 1, -1));

    for (int target = 0; target <= amount; target++)
    {
        for (int n = 0; n <= arr.size(); n++)
        {
            if (target == 0 || n == 0)
            {
                dp[n][target] = target == 0 ? true : false;
                continue;
            }

            bool res = false;
            if (target - arr[n - 1] >= 0)
            {
                res = res || dp[n - 1][target - arr[n - 1]];
            }
            res = res || dp[n - 1][target];

            dp[n][target] = res;
        }
    }
    print2d(dp);
    return dp[arr.size()][amount];
}

void partitionEqualSubsetProb() 
{
    vector<int> nums = {1,2,3,8};
    cout << partitionEqualSubset(nums) << endl;
}

//https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/  //each coin only once
int knapsack(vector<int> &wgt, vector<int> &value, int n, int target, vector<vector<int>> &dp)
{
    if (target == 0 || n == 0)
        return dp[n][target] = 0;

    if (dp[n][target] != -1)
        return dp[n][target];

    if (target - wgt[n - 1] >= 0) //-ve index ni chlega base case n=0 hai..vahan se return
        dp[n][target] = knapsack(wgt, value, n - 1, target - wgt[n - 1], dp) + value[n - 1];

    dp[n][target] = max(dp[n][target], knapsack(wgt, value, n - 1, target, dp));

    return dp[n][target];
}

void knapsackProb()
{
    vector<int> wgt = {10, 20, 30};
    vector<int> value = {60, 100, 120};
    int n = wgt.size();
    int weight = 50;
    vector<vector<int>> dp(n + 1, vector<int>(weight + 1, -1));
    cout << knapsack(wgt, value, n, weight, dp) << endl;
    print2d(dp);
}

//1641
public int countvowel_helper(int idx,int n,int [][]dp)
{
    if(n==0)
        return 1;
    
    if(dp[idx][n]!=0)
        return dp[idx][n];
    
    int count=0;
    for(int i=idx;i<5;i++)
        count+=countvowel_helper(i,n-1,dp);
    
    return dp[idx][n]=count;   
}

public int countVowelStrings(int n) {
    int [][]dp=new int[5][n+1];
    return countvowel_helper(0,n,dp);
}

int main()
{
    coinchangePermutationProb();
    // coinchangeCombinationProb();
    // minCoinsRequiredProb();
    // targetSumProblem();
    // partitionEqualSubsetProb();
    // knapsackProb();
    // unboundedKnapsackProb();
    // linearEquationNVariableProb();
    // targetSumProbLeetcode();
    return 0;
}

/*
============Target Set
22.(2)Target permutation infinite coin-M1.bc:make dp[target+1] if tsum==0 r->1
                                     2.for[0 to n)if tsum-a[i]>=0 c+=call(t-a[i])
                                     3.r->dp[tsum]=c
                                M2:for loop 0 to <=target i=0=>dp[i]=1 count for loop on given coinsArr and check if i-arr[j]>=0
                                    r->dp[target]

23.(2)Min coins required permutation infinite coin-fill dp by(1e8).bc:tsum==0 r->0 ..for loop=>dp[tsum]=min(dp[tsum],call(tsum-arr[i]))
        r->dp[tsum] ........        ====Check in helper fn if ans 1e8 then r->-1 

24.(1)Target combination infinite coin-M1:first loop of coinArray then second of target (start i=arr[j])  r->dp[tsum]       
          
25.linearEquationofNVariable-1.check if any arr ele divide the target then make flag 1 and break..if flag=0 r->0..combn method 

26.(6)combination each coin only once-M1:dp[n+1][target+1] 1.base case:t==0 || idx==n)dp[idx][target]=1 & 0 resp 
                                                        2.Two call:2.1:if come if(t-arr[i]>=0)c+=call(t-arr[i],idx+1)  
                                                                   2.2:c+=call(t,idx+1) 
                                                        3.r->dp[0][target]=c

            M2:value filled from bottom left to top outer loop [0 to target] and inner [n to 0]

            M3:do from back if(t==0||n==0)..calls c+=call(t-a[n-1],n-1)(first check) & c+=call(t,n-1)

            M4:outer loop same and inner of (n=0;n<=size)..ans now at bottom right

            M5:M3+target=0 print s..and check for both if dp[n-1][t-arr[i]////t]>0

            M6:for only 1 answer:take bool=false and at call time res=re||call().......last r->Res

27.Partition equal subset sum-1.add all ele sum and if sum%2!=0 r->f ...dp[n+1][sum/2+1]..targetSum

28*.Diffn Exprn to Reach Target-make new target target+sum,ans pass sum
                make dp[n+1][2*Sum+1]..t>sum||t<-sum r->0
                2.Bc:if n==0 if target==sum r->1 else r->0
                3.dp[n][sum]=call(idx+1,sum-arr[n-1])+call(idx+1,sum+arr[n-1])

29.Knapsack Bounded-dp[n+1][target+1].....bc:if n==0||t==0 r->0
                        2.if included t-wt[n-1]>=0 call(t-wt[n-1],n-1)+value[n-1];
                            not included max(max,call(t,n-1))

30.Knapsack unbounded-1.dp[avWt+1]..target=0 r->0..loop on n..if avWt-wt[i]>=0 
                            max(max,call(avWt-wt[i])+val[i])
                      2.dp[avWt]=maxValue

*/