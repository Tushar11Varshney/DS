// Best Time to Buy and Sell Stock - 1 transaction
// Infinite transaction allowed
// Infinite transaction + fees
// Infinite transaction + cooldown
// at most two transaction allowed
// atmost k transaction


//4

#include <iostream>
#include <vector>
using namespace std;
int maxProfit(vector<int> &prices) //121 
{
    if (prices.size() == 0)
        return 0;
    int lsf = prices[0]; //least so far
    int maxProfit = 0;
    for (int i = 1; i < prices.size(); i++)
    {
        int profit = 0;
        if (prices[i] < lsf)
        {
            lsf = prices[i];
            continue;  //agar ek din humare share ka price sabse kam gya aur usdin bechna hi mandatory hai tou kyunki usse pehle waale din jab humne khrida tha tou usdin ka price aajke price se zyada tha isliye kabhi profit ho hini skta so continue.
        }
        profit = prices[i] - lsf;
        if (profit > maxProfit)
            maxProfit = profit;
    }
    return maxProfit;
}

int maxProfit(vector<int> &prices) //122
{
    if (prices.size() == 0)
        return 0;
    int totalProfit = 0;
    int bpt, spt;
    bpt = spt = prices[0]; //buying point today,selling point today
    for (int i = 1; i < prices.size(); i++)
    {
        if (prices[i] > spt)
            spt = prices[i];
        else if (prices[i] < spt)
        {
            totalProfit += spt - bpt;  //jab value giregi
            bpt = spt = prices[i];
        }
    }
    return totalProfit + spt - bpt; //this spt-bpt is because for last upstroke handling
}

int maxProfit(vector<int> &prices, int fee) //714
{
    int obsl = -10e8; //old buying state loan   //AeB means A × 10^B.
    int ossp = 0;     //old selling state profit

    for (int i = 0; i < prices.size(); i++)
    {
        int nbsl = ossp - prices[i];
        int nssp = prices[i] + obsl - fee;
        obsl = max(obsl, nbsl);
        ossp = max(ossp, nssp);
    }
    return ossp;
}

//309
int maxProfit(vector<int> &prices)
{
    int obsl = -10e8;
    int ossp = 0;
    int ocsp = 0;

    for (int i = 0; i < prices.size(); i++)
    {
        int nbsl = ocsp - prices[i];
        int nssp = prices[i] + obsl;
        int ncsp = ossp;
        obsl = max(obsl, nbsl);
        ossp = max(ossp, nssp);
        ocsp = max(ocsp, ncsp);
    }
    return ossp;
}

//123
int maxProfit(vector<int> &prices) //Declare variable at a place not alag alag like i,j in each loop 
{
    int n = prices.size();
    int lsf = prices[0];
    int dp1[n], dp2[n], i, j, profit;  
    dp1[0] = 0, dp2[n - 1] = 0;
    for (i = 1; i < n; i++)
    {
        if (prices[i] < lsf)
            lsf = prices[i];   //cant do continue because hume upto agr aaj bechna mandatory wo store krna hai dp mein.

        profit = prices[i] - lsf;
        if (profit > dp1[i - 1])   //dp1-maxprofit if sold upto today
            dp1[i] = profit;
        else
            dp1[i] = dp1[i - 1];
    }

    int hsp = prices[n - 1];   //hsp=highest selling price
    for (j = n - 2; j >= 0; j--)
    {
        if (prices[j] > hsp)
            hsp = prices[j];

        profit = hsp - prices[j];
        if (profit > dp2[j + 1])   //dp2-max profit if bought upto today
            dp2[j] = profit;
        else
            dp2[j] = dp2[j + 1];
    }

    int maximum = dp1[0] + dp2[0];
    for (i = 1; i < n; i++)
        maximum = max(maximum, dp1[i] + dp2[i]);
    return maximum;

    // return kTransactionOPTI(2, prices);
}

//188
int kTransaction(int k, vector<int> &prices)
{
    int n=prices.size();
    if(n==0)return 0;
    int dp[k+1][n],i,j,l;
    for(i=0;i<k+1;i++)
    {
        for(j=0;j<n;j++)
        {
            if(i==0 || j==0)
            dp[i][j]=0;
        }
    }
    // vector<vector<int>>dp(k+1,vector<int>(n,0));  //program gets slow

    for(i=1;i<k+1;i++)   //i-transaction
    {
        for(j=1;j<n;j++)   //j-day
        {
            dp[i][j]=dp[i][j-1];
            for(l=0;l<j;l++)   //l-previous day upto less than today
            {
                dp[i][j]=max(dp[i][j],dp[i-1][l]+prices[j]-prices[l]);  //max between  let say for [3][8] then  agr aaj se pichle din tak 3 transaction ho chuki and if agr nhi hui tou 
            }
        }
    }
    return dp[k][n-1];
}

int kTransactionOPTI(int k, vector<int> &prices)
{
    int n=prices.size();
    if(n==0)return 0;
    int dp[k+1][n+1],i,j,l;
    for(i=0;i<k+1;i++)
    {
        for(j=0;j<n;j++)
        {
            if(i==0 || j==0)
            dp[i][j]=0;
        }
    }

    for(i=1;i<k+1;i++)
    {
        int maximum=-1e9;
        for(j=1;j<n;j++)
        {
            if(dp[i-1][j-1]-prices[j-1]>maximum)
            maximum=dp[i-1][j-1]-prices[j-1];

            dp[i][j]=max(dp[i][j-1],maximum+prices[j]);
        }
    }

    return dp[k][n-1];
}


//===============
//Catalan Number
public static int numberOfWaysTriangulation(int n) {
    int[] dp = new int[n + 1];
    dp[2] = 1; // 1 isliye kyunki 2 vertices se humare pass 1 tariika hai that is no triangle.
    dp[3] = 1;
    for (int i = 4; i <= n; i++) {
        int l = 2, r = i - 1;
        while (l <= i - 1) {
            dp[i] += dp[l] * dp[r];
            l++;
            r--;
        }
    }

    return dp[n];
}

//https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/dynamic-programming/circle-and-chords-official/ojquestion
public static long NumberOfChords(int n){
    long dp[]=new long[n+1];
    
    dp[0]=1;dp[1]=1;
    for(int i=2;i<=n;i++)
    {
        int l=0,r=i-1;
        while(l<=i-1)
        {
            dp[i]+=dp[l]*dp[r];
            l++;
            r--;
        }
    }
    return dp[n];
}

public static int CountofValleyandMountain(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;

    for (int i = 2; i <= n; i++) {
        int inside = i - 1, outside = 0;
        while (inside >= 0) {
            dp[i] += dp[inside] * dp[outside];
            inside--;
            outside++;
        }
    }
    return dp[n];
}

public static int countBrackets(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;

    for (int i = 2; i <= n; i++) {
        int inside = i - 1, outside = 0;
        while (inside >= 0) {
            dp[i] += dp[inside] * dp[outside];

            inside--;
            outside++;
        }
    }

    return dp[n];
}

int main()
{
    // vector<int>prices={11,6,7,19,4,1,6,18,4};
    // cout<<maxProfit(prices)<<endl;
    // int k=3;
    // cout<<kTransaction(k,prices);

    // int n,val;
    // cin>>n;
    // vector<int>prices(n,0);
    // for(int i=0;i<n;i++)
    // {
    //     cin>>val;
    //     prices[i]=val;
    // }
    // cout<<maxProfit(prices)<<endl;
    return 0;
}

// ============Buy & Sell+Catalan Number
/*
31.best Time to buy and sell 1 transaction-1.if prices size 0 r->0
                                           2.take lsf=first ele,mp=0..traverse on array[1.to n) and if ele<lsf update lsf & cont
                                            2.2 calculate profit and update maxProfit
                                           3.r->mp

32.best Time to buy and sell infinite transaction-bpt=spt=arr[0]..traverse array and update spt on upstroke 
                                                2.when when value<spt collect profit and spt=bpt=arr[i]
                                                3.r->tp+spt-bpt           

33.best Time to buy and sell infinite transaction with transaction fee-1.obsp=-1e8,ossp=0
        2.traverse array..obsl=max(obsl(continue old buying state),ossp-prices[i](new buy on old profit))
            ....ossp=max(ossp(continue old selling state),prices[i]+obsp(already -ve)-fee(sell on old bought state))
       3.r->ossp

34.best Time to buy and sell with cooldown infinite transaction(Cooldown means between a transaction do cooldown for 1 day means take 1 day break)-1.obsl=-1e8,ossp=0,ocsp=0...
            2.traverse array..obsp=max(obsp,ocsp-prices[i](buy on old cooldownProfit)).ncsp=ossp
                            ossp=max(ossp,(prices[i]+obsp)).
                            ocsp=max(ocsp,ncsp)

35.Two transaction allowed-1.MPSUT[0]=0..traverse from [1,n) update lsf calc profit and update MPSUT[i](previous,profit)
                    2.MPBUT[n-1]=0..traverse [n-2,0] update hsp calc profit and MPBUT[i](next,profit)
                    3.calc mp=max(mp,MPSUT[i]+MPBUT[i])

36.(2)K transaction allowed-M1-O(n3)-1.dp[k+1][n]..Fill dp..with 0 if i==0||j==0
                                     2.loop(i=1;i<k+1)..inner(j=1;j<n;)..dp[i][j]..loop for L
                                        max(k transaction upto previous day and (k-1 trnsn)dp[i-1][l]+p[j]-p[l](transaction on today & L day))....r->dp[k][n-1]
                    M2-O(n2)-same intitalisation
                            2.now store maximum(dp[i-1][j-1]-prices[j-1])...dp[i][j]=max(k trasaction previous day,max+prices[j])

==========Catalan Number
44.Number of ways Triangulation-c(n-2)-take a polygon and take any base and join it with different  vertex.
            2.now leaving base c+=triangulation(1st side)+triangulation(2nd side) and take dp[2]=1

45.Count of Valley and Mountain-ans:c(n)....take dp[0]=dp[1]=1..2-->1pair(0 p)1p..+1p(1p)
46.Count Bracket-ans:c(n)...(1)0+()1
47.Circle and chord-2n points c(n) ans
*/