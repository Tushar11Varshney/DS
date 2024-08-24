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

int MCM(vector<int> &arr, vector<vector<int>> &dp, int si, int ei)
{
    if (si + 1 == ei)
        return dp[si][ei] = 0;

    if (dp[si][ei] != -1)
        return dp[si][ei];

    int minCost = 1e9;
    for (int cut = si + 1; cut < ei; cut++)
    {
        int leftTree = MCM(arr, dp, si, cut);
        int rightTree = MCM(arr, dp, cut, ei);
        minCost = min(minCost, leftTree + (arr[si] * arr[cut] * arr[ei]) + rightTree);
    }
    return dp[si][ei] = minCost;
}

int MCM_DP(vector<int> &arr, vector<vector<int>> &dp, int SI, int EI)
{
    int n = arr.size();
    vector<vector<string>> sdp(n, vector<string>(n, ""));
    for (int gap = 1; gap < n; gap++)   //gap 0 matlab akela dmnsn that has no sense
    {
        for (int si = 0, ei = gap; ei < n; ei++, si++)
        {
            if (si + 1 == ei)
            {
                dp[si][ei] = 0;
                sdp[si][ei] = (si + 'A' );
                continue;
            }

            int minCost = 1e9;
            for (int cut = si + 1; cut < ei; cut++)
            {
                int leftTree = dp[si][cut];
                int rightTree = dp[cut][ei];
                int val = leftTree + (arr[si] * arr[cut] * arr[ei]) + rightTree;
                if (minCost > val)
                {
                    minCost = val;
                    sdp[si][ei] = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                }
            }
            dp[si][ei] = minCost;
        }
    }
    cout << sdp[SI][EI] << endl;
    print2d(dp);
    return dp[SI][EI];
}

void MCMProb()
{
    vector<int> arr = {40, 20, 30, 10, 30};
    // vector<int> arr = {10, 20, 30, 40, 30};
    int n = arr.size();
    vector<vector<int>> dp(n, vector<int>(n, -1));
    // cout << MCM(arr, dp, 0, 4) << endl;
    cout << MCM_DP(arr, dp, 0, 4) << endl;
}

// 1039
public int minScoreTriangulation(int[] A, int si, int ei, int[][] dp) {
    if (si + 1 == ei)
        return dp[si][ei] = 0;

    if (dp[si][ei] != -1)
        return dp[si][ei];

    int myAns = (int) 1e9;
    for (int cut = si + 1; cut < ei; cut++) {
        int leftAns = minScoreTriangulation(A, si, cut, dp);
        int rightAns = minScoreTriangulation(A, cut, ei, dp);

        myAns = Math.min(myAns, leftAns + (A[si] * A[cut] * A[ei]) + rightAns);
    }

    return dp[si][ei] = myAns;
}

public int minScoreTriangulation(int[] A) {
    int n = A.length;
    int[][] dp = new int[n][n];
    for (int[] ar : dp)
        Arrays.fill(ar, -1);
    return minScoreTriangulation(A, 0, n - 1, dp);
}

int main()
{
    MCMProb();
    return 0;
}

/*
=============Cut-Set
43(2).Matrix chain multiplication-bc:si+1==ei..dp[si][ei]=0
                               2.loop cut=si+1 till cut<ei
                                    2.1 left call(si,cut),right call(cut,ei)
                                    2.2 update mc=min(mc,left+a[si]*a[cut]*a[ei]+right)
                               3.r->dp[si][ei]=mc 
       M2:use gap strategy but gap=1...and for sdp of string..bc:sdp[si][ei]=(si+'A')
            2.sdp[si][ei]='('+ sdp[si][cut] +sdp[cut][ei] + ')'

    Q)Min score triangulation same code...

48.Min-Max Evaluation-1.pair class (min(1e9),max(-1e9))
                      2.evaluate(char,n1,n2)
                      3.dp[number.l][number.l]
                      4.bc:si==ei r->dp[si][ei]=a[si]..memo(pair->null)
                      5.cut=si,cut<ei..left call(si,cut),right call(cut+1,ei),update min and max myAns.min(,eval(op[cut],la.min,ra.min)) update
                      6.r->dp[si][ei]=myAns

49.Burst Balloon-1.burst balloon is postorder..dp[n][n]
                 2.loop cut=si till cut<=ei
                    2.1 leftans=cut==si?0:call(si,cut-1),rightans(cut+1,ei) and if cut=ei then 0
                    2.2 myans=max(myans,la+lv*a[cut]*rv+ra)[lv=si==0?1:a[si-1]  rv=ei==n-1?1:arr[ei+1]]
                 4.r->dp[][]=myans

50.(4)OBST-M1:3d dp[n][n][n (levelstPt->1)+1]
          2.loop,left right call same as above..if(dp[si[ei][level]!=-1])...increase level in each call
          3.update myans=min(,la+f[cut]*level+ra)
          4.r->dp[][][]=myans
        
        M2:make psum array in main fn and pass...myans=min(,la+p[ei]-(si==0?0:p[si-1])+ra)

        M3:in helper fn take sum=0..and in loop sum+=freq[cut]...update myans=min(,la+ra)
           r->dp[si][ei]=myans+sum
        
        M4:gap strategy...r->dp[SI][EI]
 
51.Boolean paranthesis-1.pair tcount,fcount(0,0)
                    2.Evaluate fn(lefta,righta,ans.ch)
                        2.1 tc=(tlc+flc)*(trc*frc)
                        2.2 according to ch calculate myans tcount and fcount
                    3.bc:if si==ei ch='t' t=1 else f=1 dp[si][ei]=pair(t,f)
                    4.cut=si+1;cut<ei;cut+=2
                    5.lcall(si,cut-1) rcall(cut+1,ei) call evalFn
                    6.r->dp[si][ei]=ans

52.palindrome partitioning-1.if str length<=1 r->0,make palindrome dp(gap strategy),pass in helper fn
                           2.declare result and smallans global
                           3.bc:si==n return
                           4.if(pdp[si][n-1]){download,add substring,add dwnload in result}
                           5.loop cut=si;cut<n
                                5.1 if(pdp[si][cut]){add in smallans,call(Cut+1),remove}
            For count-1.make dp[n]
                      2.bc:if(pdp[si][n-1])r->dp[si]=0..memo
                      3.loop(cut=si;cut<n)if(pdp[si][cut])update minAns(,call(cut+1)+1)
                      4.r->dp[si]=minAns

53.palindrome partitioning-length==0||length<=k r->0
                1.make pdp (gap=1)pdp[i][j]++ when character not equal (i,j) and add pdp[i+1][j-1]
                2.dp[k+1][n]
                3.helper fn bc:k==1 return dp[k][ei]=pdp[k][ei]
                            if(ei-si+1<=k)if <k r->inf else r->0 on equal...memo
                4.loop cut=si,cut<k...call(si,cut,k-1) divide this string into k-1..and 
                update myans=min(myans,call+pdp[cut+1][ei])
                5.r->pdp[k][ei]=myans
*/