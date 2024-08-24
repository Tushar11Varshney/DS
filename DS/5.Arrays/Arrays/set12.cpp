// delete element such that left sum = right sum
// Removing Minimum and Maximum From Array
// Minimum number of moves required to make all array elements equal by increasing n-1 element by 1
// Minimum number of moves required to make all array elements equal by inc/dec any element by 1
// Merge two array without extra space
// Max score from picking k cards(2)
// Maximum Product of Three Numbers (2)
// best meeting pt
// Set Matrix Zeroes
// wiggle sort a<=b>=c<=d
// wiggle sort a<b>c<d
// can become increasing by modifying at most 1 element
// Radius Heater
// Complete Ckt


//1664 nc
int waysToMakeFair(vector<int>& nums) {
    int es=0,os=0,n=nums.size();  //evensum,oddsum
    
    vector<int>osum(n); //oddsum,evensum..stores upto that element
    vector<int>esum(n);
    for(int i=0;i<n;i++)
    {
        if(i%2==0)
            es+=nums[i];
        else os+=nums[i];
        
        osum[i]=os;
        esum[i]=es;
    }
    
    int count=0;
    for(int i=0;i<n;i++)
    {
        int even_sum=(i==0?0:esum[i-1])+osum[n-1]-osum[i];
        int odd_sum=(i==0?0:osum[i-1])+esum[n-1]-esum[i];
        
        if(even_sum==odd_sum)count++;
    }
    return count;
}

//2091 nc
public int minimumDeletions(int[] nums) {
    int max=0,min=0,n=nums.length;
    
    if(n==1)return 1;
    for(int i=1;i<nums.length;i++)
    {
        if(nums[i]>nums[max])
            max=i;
        if(nums[i]<nums[min])
            min=i;
    }
    
    //separately delete
    int sep=Math.min(max+1,n-max)+Math.min(min+1,n-min);
    int tog_1=Math.max(max+1,min+1); //together from front
    int tog_2=Math.max(n-max,n-min);  //together from back
    return Math.min(sep,Math.min(tog_1,tog_2));
}

//453 nc https://leetcode.com/problems/minimum-moves-to-equal-array-elements/discuss/2219692/EASY-C%2B%2B-SOLUTION-FULLY-EXPLAINED nc
public int minMoves(int[] nums) {
    int min=nums[0];
    for(int ele:nums)
        min=Math.min(min,ele);
    
    int count=0;
    for(int ele:nums)
        count+=ele-min;
    
    return count;
}

//462 nc
int minMoves2(vector<int>& nums) {
    sort(nums.begin(),nums.end());
    int i=0,j=nums.size()-1,count=0;
    while(i<j)
    {
        count+=nums[j--]-nums[i++];
    }
    return count;
}


//https://practice.geeksforgeeks.org/problems/merge-two-sorted-arrays-1587115620/1 nc
void merge(long long arr1[], long long arr2[], int n, int m) 
{ 
    int gap=(int)(ceil(float(n+m)/2)),total=n+m;
    while(gap!=0)
    {
        int i=0,j=gap;
        while(j<total)
        {
            if(i>=n && j>=n && arr2[i-n]>arr2[j-n])
            {
                swap(arr2[i-n],arr2[j-n]);
            }
            else if(j>=n && i<n && arr1[i]>arr2[j-n])
            {
                swap(arr1[i],arr2[j-n]);
            }
            else if(j<n && arr1[i]>arr1[j]){
                    swap(arr1[i],arr1[j]);
            }
            i++;j++;
        }
        if(gap==1)
        gap=0;
        else
        gap=(int)(ceil(float(gap)/2));
    }
} 


//1423
public int maxScore(int[] arr, int k) {
    int i=0,score=0,maxScore=0,j=arr.length-1;
    while(i<k)
        score+=arr[i++];
    i--;
    maxScore=score;
    while(i>-1)
    {
        score=score-arr[i--]+arr[j--];
        maxScore=Math.max(maxScore,score);
    }
    return maxScore;
}

public int maxScore(int[] cardPoints, int k) {
    int sum=0;
    for(int ele:cardPoints)
    sum+=ele;

    int j=0;
    int tempSum=0, ans=-(int)1e9, count=1, n=cardPoints.length;
    for(int i=0;i<cardPoints.length;i++)
    {
        if(count<= n-k)
        {
            tempSum+=cardPoints[i];
            count++;

        }
        else{
            ans=Math.max(ans, sum-tempSum);
            tempSum = tempSum - cardPoints[j++] + cardPoints[i];
        }
    }
    ans=Math.max(ans, sum-tempSum);
    return ans;
}

// 628
public int maximumProduct(int[] nums) {
    if (nums.length == 3)
        return nums[0] * nums[1] * nums[2];

    int mx1 = -1001, mx2 = -1001, mx3 = -1001, mn1 = 1001, mn2 = 1001;
    for (int i = 0; i < nums.length; i++) {
        int val = nums[i];
        if (val >= mx1) {
            mx3 = mx2;
            mx2 = mx1;
            mx1 = val;
        } else if (val >= mx2) {
            mx3 = mx2;
            mx2 = val;
        } else if (val > mx3)
            mx3 = val;

        if (val <= mn1) {
            mn2 = mn1;
            mn1 = val;
        } else if (val < mn2) {
            mn2 = val;
        }
    }
    return Math.max(mn1 * mn2 * mx1, mx1 * mx2 * mx3);
}

// 628 paper gc
public int maximumProduct(int[] nums) {
    if (nums.length == 3)
        return nums[0] * nums[1] * nums[2];
    int n = nums.length;
    Integer[] num = new Integer[n];
    for (int i = 0; i < n; i++)
        num[i] = nums[i];
    Arrays.sort(num, (a, b) -> {
        return b - a;
        //return a-b;
    });

    if (num[0] > 0) {
        // [98,4,3,2,-1,-100]
        return Math.max(num[0] * num[n - 1] * num[n - 2], num[0] * num[1] * num[2]);
        // return num[n-1]*num[n-2]*num[n-3];
    }else if(num[0]<=0)
    //[0,-1,-2,-3]
    return num[0]*num[1]*num[2];
    // return Math.max(num[0]*num[1]*num[n-1],num[n-1]*num[n-2]*num[n-3]);

    return 0;
}

//https://www.lintcode.com/problem/912/description
int minTotalDistance(vector<vector<int>> &grid)
{
    int n = grid.size();
    int m = grid[0].size();

    vector<int> xcord;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 1)
                xcord.push_back(i);
        }
    }

    vector<int> ycord;
    for (int j = 0; j < m; j++)
    {
        for (int i = 0; i < n; i++)
        {
            if (grid[i][j] == 1)
                ycord.push_back(j);
        }
    }

    int xmeetPt = xcord[xcord.size() / 2];
    int ymeetPt = ycord[ycord.size() / 2];

    int distance = 0;
    for (int i = 0; i < xcord.size(); i++)
        distance += abs(xcord[i] - xmeetPt) + abs(ycord[i] - ymeetPt);

    return distance;
}

//73
public void setZeroes(int[][] matrix) {
    boolean fr=false,fc=false;
    int n=matrix.length,m=matrix[0].length;
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<m;j++)
        {
            if(matrix[i][j]==0)
            {
                if(i==0)fr=true;
                if(j==0)fc=true;
                matrix[0][j]=0;
                matrix[i][0]=0;
            }
        }
    }
    
    for(int i=1;i<n;i++)
    {
        for(int j=1;j<m;j++)
        {
            if(matrix[0][j]==0||matrix[i][0]==0)
                matrix[i][j]=0;
        }
    }
    
    if(fr)
    {
        for(int i=0;i<m;i++)
            matrix[0][i]=0;
    }
    
    if(fc)
    {
        for(int i=0;i<n;i++)
            matrix[i][0]=0;
    }
}

// https://www.lintcode.com/problem/wiggle-sort/
void wiggleSort(vector<int> &a) {
    int n=a.size();
    for(int i=0;i<n;i++)
    {
        if(i%2==0 && i+1<n && a[i]>a[i+1])
        swap(a[i],a[i+1]);

        else if(i%2!=0 && i+1<n && a[i]<a[i+1])
        swap(a[i],a[i+1]);
    }
}

//324
void wiggleSort(vector<int>& nums) {
    sort(nums.begin(),nums.end());
    int i=1,j=nums.size()-1,k=0,n=nums.size();
    vector<int>ans(n);
    while(i<n)
    {
        ans[i]=nums[j--];
        i+=2;
    }
    i=0;
    while(i<n)
    {
        ans[i]=nums[j--];
        i+=2;
    }

    for(int i=0;i<nums.size();i++)
    nums[i]=ans[i];
}

//665 nc
bool checkPossibility(vector<int>& arr) {
    int n=arr.size(),violation=0;
    for(int i=1;i<n;i++)
    {
        if(arr[i-1]>arr[i])
        {
            if(violation==1)return false; 
            violation++;
            
            if(i<2 || arr[i-2]<=arr[i])
                arr[i-1]=arr[i];
            else 
                arr[i]=arr[i-1];
        }
    }
        
    return true;
}

// 475
public int findRadius(int[] houses, int[] heaters)  
{
    TreeSet<Integer> ts = new TreeSet<>();
    for (int heater : heaters)
        ts.add(heater); // mlogm

    int ans = 0;
    for (int i = 0; i < houses.length; i++) // n+nlogm
    {
        int RightmeinSabse_Pass = ts.ceiling(houses[i]) != null ? ts.ceiling(houses[i]) - houses[i]
                                                                : Integer.MAX_VALUE;
        int leftmeinSabse_Pass = ts.floor(houses[i]) != null ? houses[i] - ts.floor(houses[i]) : Integer.MAX_VALUE;

        ans = Math.max(ans, Math.min(leftmeinSabse_Pass, RightmeinSabse_Pass));
    }
    return ans;
}

// 134
int canCompleteCircuit(vector<int> &gas, vector<int> &cost)
{
    int deficit = 0, extraGas = 0, sp = 0;
    for (int i = 0; i < gas.size(); i++)
    {
        extraGas += gas[i] - cost[i];
        if (extraGas < 0)
        {
            deficit += extraGas;
            extraGas = 0;
            sp = i + 1;
        }
    }
    return (deficit + extraGas < 0) ? -1 : sp;
}

