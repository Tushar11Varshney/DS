// concatenate array by itself
// min distance to achieve target
// shuffle array
// Kids With the Greatest Number of Candies

// Minimize Maximum Pair Sum in Array
// Maximum element after decreasing and rearranging with min num of oprn
// Container with most water
// 3 ptrs
// Element in every window of size k

//1
// Maximum Distance Between a Pair of Values //1855

//1929 nc
vector<int> getConcatenation(vector<int>& nums) {
    vector<int>ans=nums; //new copy of nums
    for(int ele:nums)
        ans.push_back(ele);
    return ans;
}

// 1848 yc 
public int getMinDistance(int[] nums, int target, int start) {
    int minDistance = (int) 1e9;
    for (int i = 0; i < nums.length; i++) {
        if (target == nums[i])
            minDistance = Math.min(minDistance, Math.abs(i - start));
            // why not break? 0 <= start < nums.length
    }

    return minDistance;
}

//1470 nc
vector<int> shuffle(vector<int>& nums, int n) {
    vector<int>ans(2*n);
    int k=0;
    for(int i=0,j=n;j<2*n;j++,i++)
    {
        ans[k++]=nums[i];
        ans[k++]=nums[j];
    }
    return ans;
}

//1431 nc
vector<bool> kidsWithCandies(vector<int>& candies, int extraCandies) {
    int max_=0;
    for(int ele:candies)
        max_=max(max_,ele);
    
    vector<bool>ans;
    for(int ele:candies)
    {
        if(ele+extraCandies>=max_)
            ans.push_back(true);
        else ans.push_back(false);
    }
    
    return ans;
}



// 1877
public int minPairSum(int[] nums) {
    Arrays.sort(nums);
    int i = 0, j = nums.length - 1;
    int max = -(int) 1e8;
    while (i < j) {
        max = Math.max(max, nums[i] + nums[j]);
        i++;
        j--;
    }
    return max;
}

// 1846-why arr.len not ans?[2,2,1,2,1] gc
public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
    Arrays.sort(arr);  //rearrange
    int max = 1;
    arr[0] = 1;
    for (int i = 1; i < arr.length; i++) {
        int d = arr[i] - arr[i - 1];  //The absolute difference between any 2 adjacent elements must be less than or equal to 1
        if (d > 1)
            arr[i] = arr[i - 1] + 1;  //decrease to a smaller value
        max = Math.max(max, arr[i]);
    }

    return max;
}

//11
int maxArea(vector<int> &height)
{
    int i = 0, j = height.size() - 1, maxArea = 0;
    while (i < j)
    {
        int width = j - i;
        int ht = min(height[i], height[j]);

        maxArea = max(maxArea, width * ht);
        if (height[i] <= height[j]) //chotti height waala apna best ans de chuka hai
            i++;
        else
            j--;
    }

    return maxArea;
}

//https://www.interviewbit.com/problems/array-3-pointers/ gc
public int pointers3(final int[] A, final int[] B, final int[] C) {
    
    int i=0,j=0,k=0;
    
    int min=(int)1e8;
    while(i<A.length && j<B.length && k<C.length)
    {
        int fv=Math.abs(A[i]-B[j]);  //first value
        int sv=Math.abs(B[j]-C[k]);
        int tv=Math.abs(C[k]-A[i]);
        min=Math.min(min,Math.max(Math.max(fv,sv),tv));
        
        if(A[i]<B[j] && A[i] <C[k])i++;
        else if(B[j]<C[k])j++;
        else k++;
    }
    return min;
}

// yc
// https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1#
public long[] printFirstNegativeInteger(long A[], int N, int K) // space can be O(k) using queue...store only
//negative number and que first element will be answer if queue empty then no first negative pop when first element on queue out of range.
{
    int si = 0, ei = K - 1;
    long result[] = new long[N - K + 1];

    for (int i = 0; i < (N - K + 1); i++) {
        while (si <= ei && A[si] >= 0)
            si++;
        if (si > ei) // mila hi ni
        {
            result[i] = 0;
            ei++;
        } else if (ei - si == K - 1) // pehla element window ka
        {
            result[i] = A[si];  
            ei++;
            si++; // just shift the window
        } else if (si < ei) {
            result[i] = A[si];
            ei++;
        } else if (ei == si) {
            result[i] = A[si];
            ei = i + K;
        }
    }
    return result;
}

