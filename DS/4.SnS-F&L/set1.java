public class set1 {
// 1. Binary Search, BS on sorted matrix
// 2. Search Range(2)  
// 3. Median 2 sorted array

// 2
// guess Number
// first bad version


// https://practice.geeksforgeeks.org/problems/who-will-win-1587115621/1# return 1 if present else -1
int binarySearch(vector<int> &nums, int target)
{
    int si = 0, ei = nums.size() - 1, mid = 0;
    while (si <= ei)
    {
        mid = (si + ei) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return -1;
}

// leetcode 74
bool searchMatrix(vector<vector<int>> &matrix, int target)
{
    if (matrix.size() == 0 || matrix[0].size() == 0)
        return false;
    int n = matrix.size(), m = matrix[0].size(), si = 0, ei = n * m - 1, mid = 0, val = 0;
    while (si <= ei) //= reason ye tou normal binary search hai
    {
        mid = (si + ei) / 2;
        val = matrix[mid / m][mid % m];
        if (val == target)
            return true;
        else if (val < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return false;
}

// 4
double findMedianSortedArrays(vector<int> &a, vector<int> &b)
{                                                          
    // h =n isliye taaki first array ke saare element consider krne ho tou krlein hum                                 
    int n = a.size(), m = b.size(), l = 0, h = n, te = n + m;
    if (n > m)
        return findMedianSortedArrays(b, a);
    // te:total elements
    while (l <= h)
    {
        int aleft = (l + h) / 2;
        int bleft = (te + 1) / 2 - aleft;

        int alm1 = (aleft == 0) ? INT_MIN : a[aleft - 1]; // aleft minus1
        int al = (aleft == n) ? INT_MAX : a[aleft];

        int blm1 = (bleft == 0) ? INT_MIN : b[bleft - 1];
        int bl = (bleft == m) ? INT_MAX : b[bleft];

        if (alm1 <= bl && blm1 <= al)
        {
            if (te % 2 == 0)
            {
                int leftValue = max(alm1, blm1);
                int rightValue = min(al, bl);

                return (leftValue + rightValue) / 2.0;
            }
            else
            {
                return max(alm1, blm1);
            }
        }
        else if (!(alm1 <= bl))
            h = aleft - 1;
        else if (!(blm1 <= al))
            l = aleft + 1;
    }

    return 0.0;
}


}
