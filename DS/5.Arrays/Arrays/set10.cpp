// Largest/Longest Sum Contiguos array(Kadane Algo)(2) //53
// Number of subMatrix that sum to target //1074 
// Max sum square //ib
// Max product subarray

//3
// K-Concatenation(2)
// Maximum sum subMatrix no larger than k //363 
// Flip //ib

//leetcode 53
int kadaneAlgoSubArray(vector<int> &nums) 
{
    int gmax = -1e9, runningSum = 0, gsi = 0, gei = 0, si = 0, ei = 0, n = nums.size(),maxLength;
    for (; ei < n; ei++)
    {
        runningSum += nums[ei];

        if (runningSum >= gmax)
        {
            gmax = runningSum;
            gsi = si;
            gei = ei;
            maxLength=ei-si+1;  //longest max subarray
        }

        if (runningSum <= 0)
        {
            runningSum = 0;
            si = ei + 1;
        }
    }
    return gmax;
}

int kadaneAlgo2Generic(vector<int> &nums) //vector is passed by refrence to avoid copy constructor from firing.
{
    int gmax = 0, runningSum = 0;
    for (int ei = 0; ei < nums.size(); ei++)
    {
        runningSum = max(nums[ei], runningSum + nums[ei]);
        gmax = max(gmax, runningSum);
    }
    return gmax;
}

//1074
int numSubmatrixSumTarget(vector<vector<int>> &matrix, int target)
{
    int n = matrix.size();
    int m = matrix[0].size();
    for (int i = 1; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            matrix[i][j] += matrix[i - 1][j];
        }
    }

    int count = 0;
    for (int base = 0; base < n; base++)
    {
        for (int row = base; row < n; row++)
        {
            unordered_map<int, int> map;
            int sum = 0;
            map[0] = 1;
            for (int j = 0; j < m; j++)
            {
                sum += matrix[row][j] - (base != 0 ? matrix[base - 1][j] : 0);
                if (map.find(sum - target) != map.end())
                    count += map[sum - target];
                map[sum]++;
            }
        }
    }
    return count;
}

//ib
public int maximum_SumSquare(int[][] A, int B) {
        int n=A.length,m=A[0].length;
        
        for(int i=1;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                A[i][j]+=A[i-1][j];
            }
        }
        
        int gsum=-(int)1e8;
        for(int i=B-1;i<n;i++)
        {
            int sum=0;
            for(int j=0;j<B;j++)
                sum+=A[i][j]-(i==B-1?0:A[i-B][j]);
                
            gsum=Math.max(gsum,sum);    
            for(int j=B;j<m;j++)
            {
                //i==B-1?0:A[i-B][j])  ==>upar kelie
                //(i==B-1?0:A[i-B][j-B]) ==>side kelie
                sum=sum+(A[i][j]-(i==B-1?0:A[i-B][j]))-(A[i][j-B]-(i==B-1?0:A[i-B][j-B]));
                gsum=Math.max(gsum,sum);
            }    
        }
        return gsum;
}

//152
int maxProduct(vector<int> &nums)
{
    int n = nums.size();
    int res = -1e8, left = 0, right = 0;  //left right ya 1
    for (int i = 0; i < n; i++)
    {
        left = (left == 0 ? 1 : left) * nums[i]; 
        //left=(left==0?1:left*nums[i]); cant write like this 
        //left = max(nums[i], left*nums[i]); jab 0 ho tbhi nums[i] kyunki -ve pr tou aage chlke positive bnne ka chance h uspr
        right = (right == 0 ? 1 : right) * nums[n - 1 - i];
        res = max(res, max(left, right));
    }
    return res;
}

//1191
int mod = 1e9 + 7;
int kadaneAlgoKconcat(vector<int> &nums) //for k concat question isme agr saare negative number h tou ans 0 return krne ko bolaa hai islie gmax ko 0 lo
{
    int gmax = 0, runningSum = 0;
    for (int ele : nums)
    {
        runningSum += ele;

        if (runningSum > gmax)
            gmax = runningSum;

        if (runningSum < 0)
            runningSum = 0;
    }
    return gmax;
}

int prefixSum(vector<int> &nums)
{
    int csum = 0;
    int gsum = -1e8;
    for (int ele : nums)
    {
        csum = csum + ele;
        gsum = max(gsum, csum);
    }
    return gsum;
}

int suffixSum(vector<int> &nums)
{
    int csum = 0;
    int gsum = -1e8;
    for (int i = nums.size() - 1; i >= 0; i--)
    {
        csum = csum + nums[i];
        gsum = max(gsum, csum);
    }
    return gsum;
}

int kConcatenationMaxSum(vector<int> &nums, int k) 
{
    long sumofArray = 0;
    int kadaneSum = kadaneAlgoKconcat(nums);
    if (k == 1)
        return (int)kadaneSum;

    for (int ele : nums)
        sumofArray += ele ;

    int pSum = prefixSum(nums);
    int sSum = suffixSum(nums);

    if (sumofArray > 0)
    {
        int apsum = (  (k - 2) * sumofArray) % mod + sSum + pSum) % mod;
        return apsum;
    }
    else
        return max(kadaneSum, (pSum + sSum)%mod);  //10^9+10^9 can be in int
}

//best method
int mod=1e9+7;
long kadanesSum(vector<int> &arr, int k)
{
    long gmax = 0, rsum = 0, n = arr.size();
    for (int i = 0; i < n * k; i++)
    {
        rsum = rsum + arr[i % n];
        if (rsum > gmax)
            gmax = rsum;
        if (rsum <= 0)
            rsum = 0;
    }
    return gmax;
}

int kConcatenationMaxSum(vector<int> &arr, int k)
{
    long prevSum = 0;
    for (int i = 1; i <= 3; i++)
    {
        long KSum = kadanesSum(arr, i)%mod;
        if (i == k)
            return (int)KSum;

        if (i == 3)
        {

            //a+(n-1)d
            return (int)((prevSum + ((k - 2)*(KSum - prevSum))%mod)%mod);
            //secondTerm+(k-2)*(thirdTerm-secondTerm) need to take 3 terms because just by 2 terms we cant say it is ap.
        }
        prevSum = KSum;
    }
    return 0;
}

// 363
public int maxSumSubmatrix(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++)
                matrix[i][j] += matrix[i - 1][j];
        }

        int maxsum = -(int) 1e9;
        for (int base = 0; base < n; base++) {
            for (int row = base; row < n; row++) {
                // yahan kadane lgake gsum calculate krskte for =k return anf for < k store in maxsum
                TreeSet<Integer> map = new TreeSet<>();
                map.add(0);
                int sum = 0;
                for (int j = 0; j < m; j++) {                    
                    sum += matrix[row][j] - (base != 0 ? matrix[base - 1][j] : 0);
                    Integer val1 = map.ceiling(sum - k);
                    if (val1 != null) {
                        maxsum = Math.max(maxsum, sum - val1);
                    }
                    map.add(sum);
                }                
            }
        }

        return maxsum == -(int) 1e9 ? -1 : maxsum;
    }
}

//https://www.interviewbit.com/problems/flip/
vector<int> flip(string A)
{
    int gsum = 0, gsi = -1, gei = -1, runningSum = 0, si = 0;
    for (int ei = 0; ei < A.length(); ei++)
    {
        if (A[ei] == '0')
            runningSum++;
        else
            runningSum--;

        if (runningSum > gsum)
        {
            gsum = runningSum;
            gsi = si + 1;
            gei = ei + 1; //1 indexed base
        }

        if (runningSum < 0)  //need of equal but for ib dont apply "1101010001"
        {
            runningSum = 0;
            si = ei + 1;
        }
    }

    if (gsi == -1)
        return {};
    return {gsi, gei};
}

// 485
public int findMaxConsecutiveOnes(int[] arr) {

    // int si=0,ei=0,n=arr.length,len=0;
    // while(ei<n)
    // {
    // while(ei<n && arr[ei] == 1)ei++;

    // len=Math.max(len,ei-si);

    // si=ei+1;
    // ei++;

    // while(ei<n && arr[ei]==0)
    // {
    // si++;ei++;
    // }

    // }

    // return len;

    int len = 0, count = 0;
    for (int i = 0; i < arr.length; i++) // slow code
    {
        if (arr[i] == 1)
            count++;
        else
            count = 0;

        len = Math.max(len, count);
    }

    return len;
}
