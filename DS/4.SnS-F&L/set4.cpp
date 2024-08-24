// Concept: Rotated array,pivot
// search in rotated array 1/2 (= sign smartly, glt region mei na jaaye)
// find min in rotated array(2)/ kth rotation
// bitonic search


// 33
int search(vector<int> &arr, int target)
{
    int si = 0, ei = arr.size() - 1, mid = 0;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (arr[mid] == target)
            return mid;
        else if (arr[mid] < arr[ei])
        {
            if (target > arr[mid] && target <= arr[ei])
                si = mid + 1;
            else
                ei = mid - 1;
        }
        else
        {
            if (target >= arr[si] && target < arr[mid])
                ei = mid - 1;
            else
                si = mid + 1;
        }
    }
    return -1;
}

// 81
int search(vector<int> &arr, int target)
{
    int si = 0, ei = arr.size() - 1, mid = 0;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (arr[mid] == target || arr[si] == target) //[3 1 1/3] [1 3 1 1 1/3] why si  ...agr hi shift hoga tou arr[ei]==trgt
            return true;
        else if (arr[mid] < arr[ei]) //[1 1 3 1] 3 why no equal
        {
            if (target > arr[mid] && target <= arr[ei])
                si = mid + 1;
            else
                ei = mid - 1;
        }
        else if (arr[si] < arr[mid]) //[1 3 1 1 1] 3 why not equal
        {
            if (target >= arr[si] && target < arr[mid])
                ei = mid - 1;
            else
                si = mid + 1;
        }
        else
            si++; // ya ei--;
    }
    return false;
}

// 153
int findMin(vector<int> &arr)
{
    int lo = 0, hi = arr.size() - 1;

    while (lo < hi)
    {
        int mid = (lo + hi) / 2;
        if (arr[mid] < arr[hi])
            hi = mid;
        else if (arr[lo] <= arr[mid])
            lo = mid + 1; // check on 1 0(for = reason)
        else
            lo++; // for duplicate
    }
    return arr[lo]; // hi ya lo.
}

int findMin(vector<int> &arr)
{
    int lo = 0, hi = arr.size() - 1;
    if (arr[lo] <= arr[hi]) //[1] =
        return arr[lo];

    while (lo <= hi)
    {
        int mid = (lo + hi) / 2;
        if (arr[mid] > arr[mid + 1])
            return arr[mid + 1];
        else if (arr[mid] < arr[mid - 1])
            return arr[mid];
        else if (arr[lo] <= arr[mid]) // humein sorted region mein kabhi pivot nhi milne waala
            lo = mid + 1;
        else if (arr[mid] <= arr[hi])
            hi = mid - 1;
    }
    return -1;
}

// https://practice.geeksforgeeks.org/problems/rotation4723/1
int findKRotation(int arr[], int n)
{
    int lo = 0, hi = arr.length - 1;
    if (arr[lo] <= arr[hi])
        return 0;

    while (lo <= hi)
    {
        int mid = (lo + hi) / 2;
        if (arr[mid] > arr[mid + 1])  //{5, 1, 2, 3, 4} array rotated 1 time to right
            return mid + 1;
        else if (arr[mid] < arr[mid - 1])
            return mid;
        else if (arr[lo] <= arr[mid])
            lo = mid + 1;
        else if (arr[mid] <= arr[hi])
            hi = mid - 1;
    }
    return -1;
}

// ib
int binarySearch(vector<int> &nums, int target, int si, int ei)
{
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return -1;
}

int reverseBinarySearch(vector<int> &nums, int target, int si, int ei)
{
    while (si >= ei)
    {
        int mid = (si + ei) / 2;
        if (nums[mid] == target)
            return mid;
        else if (target < nums[mid])
            ei = mid + 1;
        else
            si = mid - 1;
    }
    return -1;
}

int searchBitonicArray(vector<int> &arr, int target)
{
    int si = 0, ei = arr.size() - 1, bitonicPt = 0;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1])
        {
            bitonicPt = mid;
            break;
        }
        else if (arr[mid] > arr[mid - 1])
            si = mid + 1;
        else
            ei = mid - 1;
    }
    int ans1 = binarySearch(arr, target, 0, bitonicPt);
    if (ans1 != -1)
        return ans1;
    return reverseBinarySearch(arr, target, arr.size() - 1, bitonicPt + 1);
}

