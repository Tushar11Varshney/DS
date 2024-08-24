// Concept: Data outside range, within range

// Search Range
// Binary search nearest index
// Search Insert (2)(Whenever in doubt for equal to check for corner number), LIS
// Find k closest
// BS on row/colm sorted(2)
// Inversion count

// leetcode 34
vector<int> searchRange(vector<int>& nums, int target) {
    int mid = 0, si = 0, ei = nums.size() - 1, idx1 = -1, idx2 = -1, midCopy = mid;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (nums[mid] == target)
        {
            idx1 = idx2 = midCopy = mid;
            while (--mid >= 0 && nums[mid] == target)
                idx1 = mid;
            while (++midCopy < nums.size() && nums[midCopy] == target)
                idx2 = midCopy;
            return {idx1,idx2};
        }
        else if (nums[mid] > target)
            ei = mid - 1;
        else
            si = mid + 1;
    }
    return {-1, -1};  
}

// sir
int binarySearchFirstIndex(vector<int> &nums, int target)
{
    int si = 0, ei = nums.size() - 1, mid = 0;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (nums[mid] == target)
        {
            if (mid - 1 >= 0 && nums[mid - 1] == target) 
                ei = mid - 1;
            else return mid;
        }
        else if (nums[mid] < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return -1;
}

int binarySearchLastIndex(vector<int> &nums, int target)
{
    int si = 0, ei = nums.size() - 1, mid = 0;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (nums[mid] == target)
        {
            if (mid + 1 < nums.size() && nums[mid + 1] == target)
                si = mid + 1;
            else
                return mid;
        }
        else if (nums[mid] < target)
            si = mid + 1;
        else ei = mid - 1;
    }
    return -1;
}

vector<int> searchRange(vector<int> &nums, int target)
{
    return  {binarySearchFirstIndex(nums, target), binarySearchLastIndex(nums, target)};
}

int binarySearchNearestIndex(vector<int> &nums, int target)
{
    int size = nums.size();
    if (size == 0)
        return 0;
    if (target <= nums[0] || target >= nums[size - 1])
        return target <= nums[0] ? 0 : size - 1; 

    int si = 0, ei = size - 1, mid = 0;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] < target)
            si = mid + 1; // ei = floor, si = ceil.
        else
            ei = mid - 1;
    }
    return target - nums[ei] < nums[si] - target ? ei : si;
}

// leetcode 35
int searchInsert(vector<int> &nums, int target)
{
    int size = nums.size();
    if (size == 0)
        return -1;
    if (target <= nums[0] || target > nums[size - 1]) 
        return target <= nums[0] ? 0 : size;   
    int si = 0, ei = nums.size() - 1, mid = 0;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (nums[mid] == target)
            return mid;  //return index if target found
        else if (nums[mid] < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return si; // si answer btata h
}

int searchInsert(vector<int> &nums,int target)  
{
    int si = 0, ei = nums.size(), mid = 0; // in binary srch it is not fixed that ei=mid-1..it depend on observation
    while (si < ei)                        //= pr chlne pr loop firse while loop infinite
    {
        mid = (si + ei) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] < target)
            si = mid + 1;
        else ei = mid;
    }
    return ei; // ya si dono answer btate hain not mid

    // ArrayList<Integer>al=new ArrayList<>();
    // al.add(10);al.add(18);al.add(13);al.add(19);al.add(20);

    // int idx=Collections.binarySearch(al,11);  //works on both sorted and unsorted
    // System.out.println(idx);

    // vector<int>arr={10,11,12,165,98};  //works on sorted
    // cout<<binary_search(arr.begin(),arr.end(),98);  //return true or false
    // cout<<*lower_bound(arr.begin(),arr.end(),100);  function returns the index of the next smallest number just greater than or equal to that number.
    /*
    Input: 10 20 30 40 50
    Output: lower_bound for element 30 at index 2

    Input: 10 20 30 40 50
    Output: lower_bound for element 35 at index 3

    Input: 10 20 30 40 50
    Output: lower_bound for element 55 at index 5 (Basically, 55 is not present, so it returns end() iterator)

    Input: 10 20 30 30 30 40 50
    Output: lower_bound for element 30 at index 2
    */
}

// 300
int lengthOfLIS(vector<int> &nums)
{
    int n = nums.size();
    if (n <= 1)
        return n;
    vector<int> ans;
    for (int ele : nums)
    {
        int idx = searchInsert(ans, ele);
        if (idx == ans.size())
            ans.push_back(ele);
        else
            ans[idx] = ele;
    }
    return ans.size();
}

// 658
vector<int> findClosestElements(vector<int> &arr, int k, int x)
{
    int n = arr.size();
    if (x <= arr[0])
    {
        vector<int> ans(arr.begin(), arr.begin() + k);  
        return ans;
    }
    else if (x >= arr[n - 1])
    {
        vector<int> ans(arr.begin() + n - k, arr.end()); 
        return ans;
    }
    else
    {
        int idx = searchInsert(arr, x); // sir wala
        // int idx = (lower_bound(list.begin(), list.end(), ele) - list.begin());  //the lower bound function gives address so if address is 12 and adrress of begin is 4 so it gives index (12-4)/4 (automatically divides)
        int si = max(0, idx - k);
        int ei = min(idx + k, n - 1); // can be idx+k-1

        while (ei - si >= k) // !=k-1
        {
            if ((x - arr[si]) > (arr[ei] - x))
                si++;
            else
                ei--;
        }
        vector<int> ans(arr.begin() + si, arr.begin() + ei + 1); // not included thats why +1
        return ans;
    }
}

// leetcode 240
bool searchMatrix(vector<vector<int>> &matrix, int target)
{
    int i = matrix.size() - 1, j = 0, n = matrix[0].size();
    while (i >= 0 && j < n)
    {
        if (matrix[i][j] == target)
            return true;
        else if (matrix[i][j] > target)
            i--;
        else
            j++;
    }
    return false;
}

bool searchMatrix(vector<vector<int>> &matrix, int target)
{
    if (matrix.size() == 0 || matrix[0].size() == 0)
        return false;
    int n = matrix.size(), m = matrix[0].size(), si = 0, ei = n - 1, mid = 0, val = 0, row;

    while (si <= ei)
    {
        mid = (si + ei) / 2;
        val = matrix[mid][0];

        if (val == target)
            return true;
        else if (val < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    if (ei >= 0 && ei < n)
        row = ei;
    else return false;

    si = 0;
    ei = m - 1;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        val = matrix[row][mid];

        if (val == target)
            return true;
        else if (val < target)
            si = mid + 1;
        else ei = mid - 1;
    }
    return false;
}

// https://practice.geeksforgeeks.org/problems/inversion-of-array-1587115620/1
//For an array, inversion count indicates how far (or close) the array is from being sorted. If array is already sorted then the inversion count is 0. If an array is sorted in the reverse order then the inversion count is the maximum. 
// Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j.
long long inversionCount2(long long arr[], int si, int ei, vector<long long> &sortedArray)
{
    long long count = 0;
    long long i = si, mid = (si + ei) / 2, j = mid + 1, k = si;
    while (i <= mid && j <= ei)
    {
        if (arr[i] <= arr[j])
        {
            sortedArray[k++] = arr[i];
            i++;
            // arr[i] < arr[j] and i < j; count += ei - j + 1;
        }
        else
        {
            sortedArray[k++] = arr[j];
            count += mid - i + 1;
            j++;
        }
    }
    while (i <= mid)
        sortedArray[k++] = arr[i++];
    while (j <= ei)
        sortedArray[k++] = arr[j++];

    while (si <= ei)
        arr[si] = sortedArray[si++];
    return count;
}

long long inversionCount1(long long arr[], int si, int ei, vector<long long> &sortedArray)
{
    if (si == ei)
        return 0;
    long long mid = (si + ei) / 2, count = 0;
    count += inversionCount1(arr, si, mid, sortedArray);
    count += inversionCount1(arr, mid + 1, ei, sortedArray);

    count += inversionCount2(arr, si, ei, sortedArray);
    return count;
}

long long int inversionCount(long long arr[], long long N) // long long int=long long=long int=long
{
    if (N == 0)
        return 0;
    vector<long long> sortedArray(N, 0);
    return inversionCount1(arr, 0, N - 1, sortedArray);
}
