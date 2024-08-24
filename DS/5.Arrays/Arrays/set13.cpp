// Pair with sum in sorted matrix   
// Two sum unsorted/sorted/Target sum pair(cant use same element twice,print all ans)
// Three sum
// four sum(2)
// four sum count(2)

// 1
vector<int> twoSum(vector<int> &nums, int target)
{
    // vector<int> ans;
    unordered_map<int, int> map;
    for (int i = 0; i < nums.size(); i++)
    {
        if (map.find(target - nums[i]) != map.end())
        {
            // ans.push_back(map[target - nums[i]]);// ans.push_back(i);// return ans;
            return {map[target - nums[i]], i};
        }
        map[nums[i]] = i;
    }
    // ans.push_back(-1);// ans.push_back(-1);// return ans;
    return {-1, -1};
}

// leetcode 167
vector<int> twoSum(vector<int> &numbers, int target) // target sum pair mein sort then yhi technique
{
    int si = 0, ei = numbers.size() - 1, data = 0;
    while (si < ei) // cant use same element twice so here dont use =
    {
        data = numbers[si] + numbers[ei];
        //in qs-Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
        if (data == target)
            return {si + 1, ei + 1};
        if (data < target)
            si++;
        else
            ei--;
    }
    return {-1, -1};
}

public static void targetSumPair(int[] arr, int target)
{
    int i = 0, j = arr.length - 1;
    Arrays.sort(arr);
    while (i < j)
    {
        int sum = arr[i] + arr[j];
        if (sum == target)
        {
            System.out.println(arr[i] + ", " + arr[j]);
            i++;
            j--;
        }
        else if (sum < target)
            i++;
        else
            j--;
    }
}

// 15
vector<vector<int>> threeSum(vector<int> &arr)
{
    vector<vector<int>> ans;
    if (arr.size() < 3)
        return ans;
    sort(arr.begin(), arr.end());
    int target = 0, data = 0, n = arr.size();
    for (int i = 0; i < n; i++)
    {
        while (i != 0 && i < n && arr[i] == arr[i - 1])
            i++;
        int j = i + 1, k = n - 1;
        while (j < k)
        {
            data = arr[i] + arr[j] + arr[k];
            if (data == target) // cant do data+Arr[j]+arr[k] here then if false then how can it check data<target or not in next condn.
            {
                ans.push_back({arr[i], arr[j], arr[k]});
                j++;
                k--;
                while (j < k && arr[j] == arr[j - 1])
                    j++;
                while (j < k && arr[k] == arr[k + 1])
                    k--;
            }
            else if (data < target)
                j++;
            else
                k--;
        }
    }
    return ans;
}

// 18
vector<vector<int>> fourSum(vector<int> &nums, int target)
{
    sort(nums.begin(), nums.end());
    int n = nums.size();
    vector<vector<int>> ans;
    for (int i = 0; i < n; i++)
    {
        while (i != 0 && i < n && nums[i] == nums[i - 1])
            i++;
        for (int j = i + 1; j < n; j++)
        {
            while (j != i + 1 && j < n && nums[j] == nums[j - 1])
                j++;
            int k = j + 1, l = n - 1;
            while (k < l)
            {
                int subTarget = target - nums[i] - nums[j];
                //[1e9, 1e9, 1e9, 1e9] 0 isme overflow krjayga so target - use kiya
                int data = nums[k] + nums[l];
                if (data == subTarget)
                {
                    ans.push_back({nums[i], nums[j], nums[k], nums[l]});
                    k++;
                    l--;

                    while (k < l && nums[k] == nums[k - 1])
                        k++;
                    while (k < l && nums[l] == nums[l + 1])
                        l--;
                }
                else if (data < subTarget)
                    k++;
                else
                    l--;
            }
        }
    }
    return ans;
}


vector<vector<int>> twoSum(vector<int> &arr, int si, int ei, int target)
{
    int data, i = si, j = ei;
    vector<vector<int>> smallAns;
    while (i < j)
    {
        data = arr[i] + arr[j];
        if (data == target)
        {
            smallAns.push_back({arr[i], arr[j]});
            i++;
            j--;

            while (i < j && arr[i] == arr[i - 1])
                i++;
            while (i < j && arr[j] == arr[j + 1])
                j--;
        }
        else if (data < target)
            i++;
        else
            j--;
    }
    return smallAns;
}

vector<vector<int>> threeSum(vector<int> &arr, int si, int ei, int target)
{
    vector<vector<int>> res;
    for (int i = si; i <= ei; i++)
    {
        if (i != si && i <= ei && arr[i] == arr[i - 1])
            continue;
        vector<vector<int>> smallAns = twoSum(arr, i + 1, ei, target - arr[i]);
        if (smallAns.size() > 0)
        {
            for (vector<int> ar : smallAns)
            {
                ar.push_back(arr[i]);
                res.push_back(ar);
            }
        }
    }
    return res;
}

vector<vector<int>> fourSumGeneric(vector<int> &arr, int si, int ei, int target)
{
    vector<vector<int>> res;
    for (int i = si; i <= ei; i++)
    {
        if (i != si && i <= ei && arr[i] == arr[i - 1])
            continue; // while lgake overflow hone ke chance rhenge kyunki aage chlega fn but in continue it skips iteration
        vector<vector<int>> smallAns = threeSum(arr, i + 1, ei, target - arr[i]); 
        //yahan overflow [0,0,0,0] 0
        if (smallAns.size() > 0)
        {
            for (vector<int> ar : smallAns)
            {
                ar.push_back(arr[i]);
                res.push_back(ar);
            }
        }
    }
    return res;
}

vector<vector<int>> fourSum(vector<int> &nums, int target)
{
    vector<vector<int>> ans;
    if (nums.size() < 4)
        return ans;
    sort(nums.begin(), nums.end());
    ans = fourSumGeneric(nums, 0, nums.size() - 1, target);
    return ans;
}

// 454
int fourSumCount(vector<int> &A, vector<int> &B, vector<int> &C, vector<int> &D)
{
    unordered_map<int, int> map;
    for (int ele1 : A)
    {
        for (int ele2 : B)
        {
            map[ele1 + ele2]++;
        }
    }

    int count = 0;
    for (int ele1 : C)
    {
        for (int ele2 : D)
        {
            // if(map[-(ele1+ele2)])
            if (map.find(-(ele1 + ele2)) != map.end()) // ye zyada tez chlta hai because agr element nhi milta tou upr waala jo if hai wo us element ke across 0 bhi daalta h
                count += map[-(ele1 + ele2)];
        }
    }
    return count;
}

int twoSumCount(vector<int> &arr1, vector<int> &arr2) // array mein fast
{
    sort(arr1.begin(), arr1.end());
    sort(arr2.begin(), arr2.end());

    int i = 0, n = arr1.size(), j = n - 1, count = 0, ABCount = 0, CDCount = 0;
    while (i < n && j >= 0)
    {
        int data = arr1[i] + arr2[j];
        if (data == 0)
        {
            ABCount = 1;
            CDCount = 1;
            while (++i < n && arr1[i] == arr1[i - 1])
                ABCount++;
            while (--j >= 0 && arr2[j] == arr2[j + 1])
                CDCount++;

            count += ABCount * CDCount;
        }
        else if (data < 0)
            i++;
        else
            j--;
    }
    return count;
}

int fourSumCount(vector<int> &A, vector<int> &B, vector<int> &C, vector<int> &D)
{
    vector<int> ABCombination;
    vector<int> CDCombination;  // all array of same len
    int n = A.size();
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            ABCombination.push_back(A[i] + B[j]);
            CDCombination.push_back(C[i] + D[j]);
        }
    }
    return twoSumCount(ABCombination, CDCombination);
}


