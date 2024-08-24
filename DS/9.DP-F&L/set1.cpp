// LIS
// 1.LIS,LDS,Bitonic 
// 2.Maximum sum IS,DS,Bitonic
// 3.Min deln for sorted
// 4.Count of LIS 
// 5. Number of envelope that can be put together
// 6. Number of bridge can be connected without crossing each other

#include <iostream>
#include <vector>
#include <math.h>
#include <string>
#include <algorithm>
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

//o(n^2)
int LIS(vector<int> &arr, int i, vector<int> &dp)
{
    if (dp[i] != -1)
        return dp[i];

    int maxValue = 1;
    for (int j = i - 1; j >= 0; j--)
    {
        if (arr[j] < arr[i])
            maxValue = max(maxValue, LIS(arr, j, dp) + 1);
    }
    return dp[i] = maxValue;
}

void LIS_DP(vector<int> &arr, int n, vector<int> &dp)
{
    dp[0] = 1;
    int maxValue = dp[0];
    for (int i = 1; i < n; i++)
    {
        for (int j = i - 1; j >= 0; j--)
        {
            if (arr[j] < arr[i])
                dp[i] = max(dp[i], dp[j] + 1);
        }
        maxValue = max(maxValue, dp[i]);
    }
    cout << maxValue << endl;
}

void LISprob()  //n^2
{
    vector<int> arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14};
    // vector<int> arr = {2, 1, 4, 4, 2, 5, 6, 8};
    int n = arr.size();
    vector<int> dp(n, -1);
    int maxValue = 0;
    for (int i = 0; i < n; i++)
        maxValue = max(maxValue, LIS(arr, i, dp));
    cout << maxValue << endl;
    // LIS_DP(arr, n, dp);
    print(dp);
}

int LDS(vector<int> &arr, int i, vector<int> &dp)
{
    if (dp[i] != -1)
        return dp[i];

    int maxValue = 1;
    for (int j = i + 1; j < arr.size(); j++)
    {
        if (arr[j] < arr[i])
            maxValue = max(maxValue, LDS(arr, j, dp) + 1);
    }
    return dp[i] = maxValue;
}

int LDS_DP(vector<int> &arr, int n, vector<int> &dp)
{
    dp[n-1] = 1;
    int maxValue = 1;
    for(int i=n-1;i>=0;i--)
    {
        for (int j = i + 1; j < arr.size(); j++)
        {
            if (arr[j] <= arr[i])
            {
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }
        maxValue=max(maxValue,dp[i]);
    }
    return maxValue;
}

void LDSprob()
{
    vector<int> arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14};
    int n = arr.size();
    vector<int> dp(n, -1);
    int maxValue = 0;
    for (int i = n - 1; i >= 0; i--)
        maxValue = max(maxValue, LDS(arr, i, dp));
    cout << maxValue << endl;
    // print(dp);
}

void LBS()
{
    vector<int> arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14};
    // vector<int> arr = {1, 2, 5, 3, 2};
    int n = arr.size();
    vector<int> dpLIS(n, -1);
    vector<int> dpLDS(n, -1);
    for (int i = 0; i < n; i++)
    {
        LIS(arr, i, dpLIS);
    }
    for (int i = n - 1; i >= 0; i--)
    {
        LDS(arr, i, dpLDS);
    }

    print(dpLIS);
    print(dpLDS);
    int maxValue = 0;
    for (int i = 0; i < n; i++)
    {
        maxValue = max(maxValue, dpLIS[i] + dpLDS[i] - 1);
    }
    cout << maxValue;
}

//https://www.geeksforgeeks.org/maximum-sum-increasing-subsequence-dp-14/
//https://www.geeksforgeeks.org/maximum-sum-bi-tonic-sub-sequence/
int maximumSumIncreasingSubsequence(vector<int> &arr, int n, vector<int> &dp)
{
    dp[0] = arr[0];
    int maxValue = arr[0];
    for (int i = 1; i < n; i++)
    {
        dp[i] = arr[i];
        for (int j = i - 1; j >= 0; j--)
        {
            if (arr[j] < arr[i])   //= in pep portal
            {
                dp[i] = max(dp[i], dp[j] + arr[i]);
            }
        }
        maxValue = max(maxValue, dp[i]);
    }
    return maxValue;
}

int maximumSumDecreasingSubsequence(vector<int> &arr, int n, vector<int> &dp)
{
    dp[n - 1] = arr[n - 1];
    int maxValue = arr[n - 1];
    for (int i = n - 2; i >= 0; i--)
    {
        dp[i] = arr[i];
        for (int j = i + 1; j < n; j++)
        {
            if (arr[j] < arr[i])
                dp[i] = max(dp[i], dp[j] + arr[i]);
        }
        maxValue = max(maxValue, dp[i]);
    }
    return maxValue;
}

void maximumSumBitonicSubsequence()
{
    // vector<int> arr = {80, 60, 30, 40, 20, 10};
    // vector<int> arr = {0,8,4,12,2,10};
    vector<int> arr = {35,35,97,25,97,54,46,22,26,86,13,48,99,63,38};
    int n = arr.size();
    vector<int> dpLIS(n, -1);
    // vector<int> dpLDS(n, -1);

    cout<<maximumSumIncreasingSubsequence(arr, n, dpLIS);
    // maximumSumDecreasingSubsequence(arr, n, dpLDS);

    print(dpLIS);
    // print(dpLDS);
    // int maxValue = 0;
    // for (int i = 0; i < n; i++)
    // {
    //     maxValue = max(maxValue, dpLIS[i] + dpLDS[i] - arr[i]);
    // }
    // cout << maxValue;
}

void maximumSumSubsequenceProb()
{
    // vector<int> arr = {1, 101, 2, 3, 100, 4, 5};
    // vector<int> arr =  {10, 5, 4, 3};
    // vector<int> arr =  {3, 4, 5, 10};
    // vector<int> arr =  {44,42,38,21,15,22,13,27};
    vector<int> arr = {1, 15, 51, 45, 33, 100, 12, 18, 9};
    int n = arr.size();
    vector<int> dp(n, -1);
    cout << maximumSumIncreasingSubsequence(arr, n, dp) << endl;
    cout << maximumSumDecreasingSubsequence(arr, n, dp) << endl;
}

//by sir
public static int minimumDeletionsToSorted(int []arr){
    int n = arr.length;
    
    int []dp=new int[n];
    Arrays.fill(dp,1);
    dp[0] = 1;
    int maxValue = dp[0];
    for (int i = 1; i < n; i++)
    {
        for (int j = i - 1; j >= 0; j--)
        {
            if (arr[j] <= arr[i])
            {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        maxValue = Math.max(maxValue, dp[i]);
    }
    return n - maxValue;
}

//673
int numberofLIS(vector<int> &arr, int n, vector<int> &dp, vector<int> &count)
{
    dp[0] = 1;
    count[0] = 1;
    int maxlength = 1, maxcount = 1;
    for (int i = 1; i < n; i++)
    {
        dp[i] = 1;
        count[i] = 1;
        for (int j = i - 1; j >= 0; j--)
        {
            if (arr[j] < arr[i])
            {
                int res = dp[j] + 1;
                if (res > dp[i])
                {
                    dp[i] = res;
                    count[i] = count[j];
                }
                else if (res == dp[i])
                {
                    count[i] += count[j];
                }
            }
        }
        if (dp[i] > maxlength)
        {
            maxlength = dp[i];
            maxcount = count[i];
        }
        else if (dp[i] == maxlength)
            maxcount += count[i];
    }
    return maxcount;
}

void numberofLISProb()
{
    vector<int> arr = {1, 1, 1, 2, 2, 2, 3, 3, 3};
    // vector<int> arr = {2, 2, 2, 2, 2};
    int n = arr.size();
    vector<int> dp(n, -1);
    vector<int> count(n, -1);
    cout << numberofLIS(arr, n, dp, count) << endl;
}

//354
int RussianDollEnvelope(vector<vector<int>> arr)
{
    int n = arr.size();
    if(n==0)return 0;
    sort(arr.begin(), arr.end(), [](auto a, auto b) {   
        // if (a[0] == b[0])
        //     return b[1] < a[1];
        return a[0] < b[0]; //this-other
    });
    vector<int> dp(n, 1);

    int maxValue = dp[0]; 
    for (int i = 1; i < n; i++) 
    {
        dp[i] = 1;
        for (int j = i - 1; j >= 0; j--)
        {
            if (arr[j][0] < arr[i][0] && arr[j][1] < arr[i][1])
            // if (arr[j][1] < arr[i][1])
                dp[i] = max(dp[i], dp[j] + 1);
        }
        maxValue = max(maxValue, dp[i]);
    }
    return maxValue;
}

void RussianDollProblem()
{
    vector<vector<int>> arr = {{5, 4}, {6, 4}, {6, 7}, {2, 3}, {1, 2}, {1, 4}, {3, 8}};
    cout << RussianDollEnvelope(arr) << endl;
}

//https://www.geeksforgeeks.org/dynamic-programming-building-bridges/
int buildingBridges(vector<vector<int>> arr)
{
    sort(arr.begin(), arr.end(), [](auto a, auto b) {
        // if(a[0]==b[0])  //north point same
    //     return a[1]<b[1];
        return a[0] < b[0];
    });
    int n = arr.size();
    vector<int> dp(n, 1);
    int maxValue = 0;
    for (int i = 0; i < n; i++)
    {
        dp[i] = 1;
        for (int j = i - 1; j >= 0; j--)
        {
            if (arr[j][1] < arr[i][1])
                dp[i] = max(dp[i], dp[j] + 1);
        }
        maxValue = max(maxValue, dp[i]);
    }
    return maxValue;
}

void buildingBridgesProb()
{
    // vector<vector<int>> arr = {{8, 1}, {1, 2}, {4, 3}, {3, 4}, {5, 5}, {2, 6}, {6, 7}, {7, 8}};
    vector<vector<int>> arr = {{6, 2}, {4, 3}, {2, 6}, {1, 5}};
    cout << buildingBridges(arr) << endl;
}

int main()
{
    // LISprob();
    // LDSprob();
    // LBS();
    // maximumSumSubsequenceProb();
    // maximumSumBitonicSubsequence();
    // numberofLISProb();
    // minDeletion();
    // RussianDollProblem();
    // buildingBridgesProb();
    return 0;
}

//LIS VARIATION
public static int highwayBillBoard(int m , int[] x, int[] rev, int d) {  //m square
    //x:posn of board,m:number of board,d:distance  btw board should be greater than d
    int []dp=new int[m];
    
    dp[0]=rev[0];int maxValue=dp[0];
    for(int i=1;i<m;i++)
    {
        int max=0;
        dp[i]=rev[i];
        for(int j=0;j<i;j++)
        {
            int distance=x[i]-x[j];
            if(distance>d)
            {
                max=Math.max(max,dp[j]);                        
            }
        }
        dp[i]+=max;
        maxValue=Math.max(maxValue,dp[i]);
    }
    return maxValue;
}

public static int highwayBillBoard2(int n , int[] x, int[] rev, int d) {
    //m:number of miles
    int []dp=new int[n+1];
    HashMap<Integer,Integer>map=new HashMap<>();
    for(int i=0;i<x.length;i++)
    map.put(x[i],rev[i]);
    
    for(int i=1;i<dp.length;i++)
    {
        if(map.containsKey(i)==false)
        dp[i]=dp[i-1];
        else{
            int boardNotInstalled=dp[i-1];
            int boardInstall=map.get(i);
            if(i-d-1>=0)
                boardInstall+=dp[i-d-1];
            dp[i]=Math.max(boardNotInstalled,boardInstall);
        }
    }
    return dp[n];
}

//646
public int findLongestChain(int[][] pairs) { 
    Arrays.sort(pairs, (int  []a,int []b)->{
        return a[1]-b[1];
    });  
    
    int len=1,cur=pairs[0][1];
    for(int i=1;i<pairs.length;i++)
    {   
        if(cur<pairs[i][0]){
            cur=pairs[i][1];
            len++;
        }
    }
    return len;
}

//1048
bool isCompare(string &a,string &b)
{
    if(b.length()!=a.length()+1)return false;  //["ab","abaaa"]
    int i=0,j=0;
    while(j<b.length())
    {
        if(a[i]==b[j])
        {
            i++;
            j++;
        }
        else j++;
    }
    
    if(i==a.length() && j==b.length())
        return true;
    return false;
}


int longestStrChain(vector<string>& words) {
    sort(words.begin(),words.end(),[](auto a,auto b){
        return a.length()<b.length();  //we solved like subsequence but here given in qs sequence
    });
    
    int n=words.size();    
    vector<int>dp(n);
    
    dp[0]=1;
    int maxlen=1;
    for(int i=1;i<n;i++)
    {
        dp[i]=1;
        for(int j=i-1;j>=0;j--)
        {
            if(isCompare(words[j],words[i]))
            {
                dp[i]=max(dp[i],dp[j]+1);
            }
        }
        maxlen=max(maxlen,dp[i]);
    }
    
    return maxlen;
}

// ==========LIS
/*
37.(2)O(n2)LIS-M1:1.In main fn for every index call LIS...in helper fn..memo..take maxSub=1..
          2.Loop for j=i-1;j>=0 ..if value small of ar[j] than ar[i] update maxSub=max(maxSub,call(j)+1)
          3.r->dp[i]=maxSub..in main fn update max(maxIS) each time
      M2:take max=dp[0]=1,for every index[1 to n) traverse and j=i-1;j>=0..if ar[j]<ar[i]..update dp[i]=max(dp[i],dp[j]+1)..and after loop update maxLength
        r->maxLength
    LDS-M1:call for every index but from end..just run the loop from j=i+1
        M2:dp[n-1]=1.....outer loop i=n-1..inner j loop j=i+1
    LBS-run lis,lds,and traverse till size..update maxLength=max(maxLength,dp[i]+dp[j]-1)

38.MaxSum LIS-dp[0]=max=arr[0]..in dp[i]=arr[i]...update dp[i] in inner loop dp[i]=max(dp[i],dp[j]+arr[i])..update max
   MaxSum LDS-dp[n-1]=max=arr[n-1]..in dp[i]=arr[i]...update dp[i] in inner loop dp[i]=max(dp[i],dp[j]+arr[i])..update max
   MaxSum LBS-call both mslis,mslds and traverse till size and update maxValue=max(maxValue,dp1[i]+dp2[i]-arr[i])

39.Mindeletion-run Lis(see in qs 1 2 2 allowed)and minDeletion-n-maxIS

39.total Lis of longest length-1.make dpLongestLengthTillI,dpcountOfLongestlengthTillI
                              2.maxL=maxC=dp1[0]=dp2[0]=1
                              3.traverse [1,n) dp1[i]=dp2[i]=1
                                3.1.inner loop j=i-1 till j>=0 calc lis using j..
                                    3.1.1 if lis>dp1[i]..dp1[i]=lis..dp2[i]=dp2[j]
                                    3.1.2 if = dp2[i]+=dp2[j]
                                3.2 if dp[i]>maxLength update maxLength and count..else if = update maxCount+=dp2[i];
                              4.r->maxCount

41.Russian doll envelope-1.sort on any dmsn and if equal sort in desc on other dmsn(we do when points can be same)..(2n^2->n^2 comp)
                        2.take max=0..traverse array[0,n) and dp[i]=1..check if other dmnsn of j<i 
                            2.1update dp[i]=max(dp[i],dp[j]+1)
                        3.update maxValue..and r->maxValue 

42.building bridges-same as russian doll....if st pt same allowed sort on sp & sort on ending point if equal sort in asc only..........if ep same allowed sort on ep & sp on = condn

82)(2)Highway BillBoard-(whichever m or n less apply that complexity algo)
                    1.o(m2) & o(m)-make a dp[#of board]..dp[0]=rev[0]
                    2.[1,n) dp[i]=rev[i] ..inner loop [0,i) if gap>d update max and add in dp[i]
                    3.update maxValue

            M2:o(n) & o(n):1.dp[miles+1]1.take hashmap posn:rev 
                           2.if board not present(map dont contain board at the posn):put previous val of dp
                           3.board present:max(boardNotinstalled,boardInstall(rev at that posn+dp[i-d-1 if it is >=0]))
                           4.r->dp[m]
*/