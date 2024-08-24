// product except self
// sum of all odd length subarray
// Partition in 3 parts
// leftmost pivot index // 724



// 238
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] left = new int[n];
    int[] right = new int[n];

    left[0] = nums[0];
    for (int i = 1; i < n; i++)
        left[i] = nums[i] * left[i - 1];

    right[n - 1] = nums[n - 1];
    for (int i = n - 2; i >= 0; i--)
        right[i] = nums[i] * right[i + 1];

    int[] ans = new int[n];

    for (int i = 0; i < n; i++) {
        int lprod = i == 0 ? 1 : left[i - 1];
        int rprod = i == n - 1 ? 1 : right[i + 1];
        ans[i] = lprod * rprod;
    }

    return ans;
}

public int[] productExceptSelf2(int[] nums) { // o(1) space

    int n = nums.length;
    int[] ans = new int[n];

    int left = 1;
    for (int i = 0; i < n; i++) {
        ans[i] = left;
        left = left * nums[i];
    }

    int right = 1;
    for (int i = n - 1; i >= 0; i--) {
        ans[i] = ans[i] * right;
        right *= nums[i];
    }

    return ans;
}

//1588 nc
// check optimised approach -- https://leetcode.com/problems/sum-of-all-odd-length-subarrays/solutions/2341230/easy-c-o-n-soln-faang-interview-optimized-code/
public int sumOddLengthSubarrays(int[] arr) {
    int sum=0,n=arr.length;
    int []prefixSum=new int[n];
    for(int i=0;i<n;i++)
    {
        sum+=arr[i];
        prefixSum[i]=i==0?arr[i]:prefixSum[i-1]+arr[i];
    }
    
    int sz=3;
    while(sz<=n)
    {
        int i=-1,j=sz-1;
        while(j<n)
        {
            sum+=prefixSum[j]-(i==-1?0:prefixSum[i]);
            j++;
            i++;
        }
        sz+=2;
    }
    
    return sum;
}

int partition(int A, vector<int> &B)
{ //1013 & on ib
    int sum = 0;
    vector<int> prefixSum(A);
    vector<int> suffixSum(A);

    for (int i = 0; i < A; i++)
    {
        sum += B[i];
        prefixSum[i] = i > 0 ? prefixSum[i - 1] + B[i] : B[0];
    }

    if (sum % 3 != 0)
        return 0;

    suffixSum[A - 1] = B[A - 1];
    for (int i = A - 2; i >= 0; i--)
    {
        suffixSum[i] = suffixSum[i + 1] + B[i];
    }

    int ans = 0;
    for (int i = 0; i < A; i++)
    {
        if (prefixSum[i] == sum / 3)
        {
            for (int j = i + 2; j < A; j++)
            {
                if (suffixSum[j] == sum / 3)
                    ans++;
            }
        }
    }
    return ans;
}

//gc
public int pivotIndex(int[] nums)
{
    int n = nums.length;
    int prefixSum[] = new int[n];
    int suffixSum[] = new int[n];

    for (int i = 1; i < n; i++)
        prefixSum[i] = prefixSum[i - 1] + nums[i - 1];

    for (int i = n - 2; i >= 0; i--)
        suffixSum[i] = suffixSum[i + 1] + nums[i + 1];

    for (int i = 0; i < n; i++)
    {
        if (prefixSum[i] == suffixSum[i])
            return i;
    }
    return -1;
}

public int pivotIndex_(int[] nums)
{
    int sum = 0, l = 0, r = 0;
    for (int ele : nums)
        sum += ele;

    r = sum;
    for (int i = 0; i < nums.length; i++)
    {
        r -= nums[i];
        if (l == r)
            return i;

        l += nums[i];
    }

    return -1;
}


