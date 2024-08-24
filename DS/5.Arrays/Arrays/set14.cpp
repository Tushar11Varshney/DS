// subarray with sum k
// Binary subaaray with sum-2(sum-k, atmost concept)
// Longest subarray with equal # of 0,1 
// Count subarray with equal # of 0,1 
// Longest subarray with sum div by K 
// Count subarray with sum div by K(2-array, hashmap) 
// subarray with exactly k distinct
// number of subarray with k odd count
// Longest subaaray with atmost k 1
// Subarray Product Less Than K
// total fruits
// Min swaps to group all 1 together, in circular array

//560 //pc
int subarraySum(vector<int> &nums, int k)
{
    unordered_map<int, int> map;
    map[0] = 1;
    int sum = 0, count = 0;
    for (int i = 0; i < nums.size(); i++)
    {
        sum += nums[i];
        if (map.find(sum - k) != map.end())
            count += map[sum - k];
        map[sum]++;
    }
    return count;
}

//leetcode 930-Binary Subarrays With Sum
//Constraints:
//1 <= nums.length <= 3 * 10^4
//0 <= goal <= nums.length
int numSubarraysWithSum(vector<int> &A, int S)
{
    int n = A.size(), sum = 0, count = 0, val = 0;
    unordered_map<int, int> map; //sum vs freq
    map[0] = 1;
    for (int i = 0; i < n; i++)
    {
        sum += A[i];
        val = sum - S; //sum bhi kabhi negative ni hoga and S also always positive because 0<=S<=A.length.aur hashmap mein hum daal rhe hain sum tou val can be negative but if we search -ve value in hashmap then ans always false
        if (val >= 0 && map.find(val) != map.end())
            count += map[val];
        map[sum]++;
    }
    return count;
}

int numSubarraysWithSum(vector<int> &A, int S) //by array
{
    int n = A.size(), sum = 0, count = 0, val = 0;
    vector<int> map(30000 + 1, 0); //agar -1 se initialise then when value not available in map then it will add -1.Test case:0000001000 and S=0
    map[0] = 1;
    for (int i = 0; i < n; i++)
    {
        sum += A[i];
        val = sum - S;
        if (val >= 0)
            count += map[val];
        map[sum]++;
    }
    return count;
}

// LongestSubArray with equal no 0&1 leetcode-525
int findMaxLength(vector<int> &nums)
{
    unordered_map<int, int> map;
    int sum = 0, n = nums.size(), len = 0;
    map[0] = -1;
    for (int i = 0; i < n; i++)
    {
        int val = nums[i];
        if (val == 0)
            val = -1;
        sum += val;

        if (map.find(sum) != map.end())
            len = max(len, i - map[sum]);
        else
            map[sum] = i;
    }
    return len;
}

//https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s-1587115620/1
long long int CountSubArray(vector<int> &nums, int N)
{
    unordered_map<int, int> map;
    int sum = 0;
    long long int count = 0;
    map[0] = 1;
    for (int i = 0; i < N; i++)
    {
        int val = nums[i];
        if (val == 0)
            val = -1;
        sum += val;

        if (map.find(sum) != map.end())
        {
            count += map[sum];
            map[sum]++;
        }
        else
            map[sum] = 1;
    }
    // for (pair<int, int> key : map)
    //     count += (key.second) * (key.second - 1) / 2;
    return count;
}

int longSubarrWthSumDivByK(int arr[], int n, int k)
{
    int sum = 0, len = 0,rem=0;
    unordered_map<int, int> map;   //rem vs frequency
    map[0] = -1;
    for (int i = 0; i < n; i++)
    {
        sum += arr[i];
        rem = (sum % k + k) % k; //sum can be negative

        if (map.find(rem) != map.end())
            len = max(len, i - map[rem]);
        else
            map[rem] = i;
    }
    return len;
}

//974
int subarraysDivByK(vector<int> &A, int k)
{
    int sum = 0, count = 0, rem = 0;
    unordered_map<int, int> map;
    map[0] = 1;
    for (int i = 0; i < A.size(); i++)
    {
        sum += A[i];
        rem = (sum % k + k) % k;
        if (map.find(rem) != map.end())
        {
            count += map[rem];
            map[rem]++;
        }
        else
            map[rem] = 1;
    }
    return count;
}

int subarraysDivByK(vector<int> &A, int k) //by array faster
{
    int sum = 0, count = 0;
    vector<int> map(10000, -1); //2 <= K <= 10000
    map[0] = 1;
    for (int i = 0; i < A.size(); i++)
    {
        sum += A[i];
        sum = (sum % k + k) % k;
        if (map[sum] != -1)
        {
            count += map[sum];
            map[sum]++;
        }
        else
            map[sum] = 1;
    }
    return count;
}

int allSubarrayAtmostK(vector<int> &A, int S) //also on IB there they want strictly less than S so while(sum>=s)
{
    int si = 0, ei = 0, count = 0, sum = 0;
    while (ei < A.size())
    {
        sum += A[ei++];

        while (sum > S)
            sum -= A[si++];

        count += ei - si;
    }
    return count;
}

int numSubarraysWithSum2(vector<int> &A, int S) //method 2
{
    if (A.size() == 0)
        return 0;
    return allSubarrayAtmostK(A, S) - (S != 0 ? allSubarrayAtmostK(A, S - 1) : 0);
}


//https://www.interviewbit.com/problems/subarrays-with-distinct-integers/
/*public int subArraysWithAtMostK(int []A,int k)
{
    int []map=new int[A.length+1];
    int si=0,ei=0,disCount=0,count=0,n=A.length;
    while(ei<n)
    {
        if(map[A[ei++]]++ == 0)
            disCount++;
            
        while(disCount>k)
        {
            if(map[A[si++]]-- == 1)
            disCount--;
        }
        
        count+=ei-si;
    }
    
    return count;

    // return subArraysWithAtMostK(A,B)-(B-1!=0?subArraysWithAtMostK(A,B-1):0);
}*/

//1248
int numberOfSubarraysWithAtmostKOdd(vector<int> &nums, int k) //1 <= k <= nums.length
{
    int si = 0, ei = 0, count = 0, n = nums.size(), oddcount = 0, res = 0;
    while (ei < n)
    {
        if ((nums[ei++] & 1) != 0)
            oddcount++;

        while (oddcount > k)
        {
            if ((nums[si++] & 1) != 0)
                oddcount--;
        }
        res += ei - si;
    }
    return res;
}

int numberOfSubarrays(vector<int> &nums, int k)
{
    return numberOfSubarraysWithAtmostKOdd(nums, k) - numberOfSubarraysWithAtmostKOdd(nums, k - 1);
}

//leetcode 1004 
int longestOnes(vector<int> &A, int K)  //atmost k
{
    int si = 0, ei = 0, requirement = K, n = A.size(), len = 0, gsi = 0;
    while (ei < n)
    {
        if (A[ei++] == 0)
            requirement--;

        while (requirement < 0)
        {
            if (A[si++] == 0) //iske andar length ni update krskte because if requirement never become 0 so length will never update.
                requirement++;
        }
        if (ei - si > len)
        {
            len = ei - si;
            gsi = si;
        }
    }

    // for(int i=gsi;i<gsi+len;i++)
    //     ans.push_back(i);
    return len;
}

//904
int totalFruit(vector<int> &tree)
{
    // dont have to find count with exactly distinct 2 because we have only two basket if we will pick all type of 2 distinct fruit then we will not be able to collect previous unique 2.
    int si = 0, ei = 0, fruitCount = 0, distinctCount = 0, n = tree.size();
    unordered_map<int, int> map; //fruit type vs freq
    while (ei < n)
    {
        if (map[tree[ei++]]++ == 0)
            distinctCount++;

        while (distinctCount > 2)
        {
            if (map[tree[si++]]-- == 1)
                distinctCount--;
        }

        fruitCount = max(fruitCount, ei - si);
    }
    return fruitCount;
}

//713
public int numSubarrayProductLessThanK(int[] nums, int k) {
    if(k==0 || k==1)return 0; // 1 <= nums[i] <= 1000 cant make product less than 0 and 1
    int si=0, ei=0,count=0;
    long product=1;

    while(ei<nums.length)
    {
        product *= nums[ei++];

        while(product>=k)
        {
            product/=nums[si++];
        }

        count+=ei-si;
    }
    return count;
}

//https://www.geeksforgeeks.org/problems/minimum-swaps-required-to-group-all-1s-together2451/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
public static int minSwaps(int nums[], int n) {
    int si=0, ei=0, count1=0;
    for(int ele:nums)
    {
        if(ele==1)
        count1++;
    }    

    int ctr1=0, min=(int)1e9;
    while(ei<nums.length)
    {
        if(nums[ei++]==1)
        ctr1++;

        if(ei-si==count1)
        {
            min=Math.min(min, count1-ctr1);
            if(nums[si++]==1)
            ctr1--;
        }
    }
    return min==(int)1e9?-1:min; // if no 1 present return -1.
}

//2134
public int minSwaps(int[] nums) {
    if(nums.length==1)return 0;
    int si=0, ei=0, count1=0;
    for(int ele:nums)
    {
        if(ele==1)
        count1++;
    }    

    int ctr1=0, min=(int)1e9,n=nums.length;
    while(ei<2*n)
    {
        if(nums[ei%n]==1)
            ctr1++;
        ei++;

        if(((ei%n-si%n+n)%n)==count1)
        {
            min=Math.min(min, count1-ctr1);
            if(nums[si%n]==1)
            ctr1--;

            si++;
        }
    }
    return min==(int)1e9?0:min;
}


//1679 nc
public int maxOperations(int[] nums, int k) {
    Arrays.sort(nums);
    int i=0,j=nums.length-1;
    int count=0;
    while(i<j)
    {
        if(nums[i]+nums[j]==k)
        {
            count++;
            i++;j--;
        }
        else if(nums[i]+nums[j]>k)j--;
        else i++;
    }
    return count;
}

int maxOperations(vector<int>& nums, int k) {
    unordered_map<int,int>map;  //dont think of apply subarray sem equal k
    int count=0;
    for(int ele:nums)
    {
        if(map.find(k-ele)!=map.end() && map[k-ele]>0)
        {
            count++;
            map[k-ele]--;
        }
        else map[ele]++;
    }
    return count;
}

//1695
int maximumUniqueSubarray(vector<int>& nums) {
    int si=0,ei=0,sum=0,ans=0;
    unordered_map<int,int>map;
    while(ei<nums.size())
    {
        int val=nums[ei++];
        sum+=val;
        map[val]++;
        
        while(map[val]>1)
        {
            int val_=nums[si++];
            sum-=val_;
            if(map[val_]==1)
                map.erase(val_);
            else
                map[val_]--;
        }
        
        ans=max(ans,sum);
    }
    return ans;
}

