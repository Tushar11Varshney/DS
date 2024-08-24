#include <iostream>
#include <vector>
#include <cmath>
#include <unordered_map>
#include <algorithm>
using namespace std;

// 374
int guessNumber(int n)
{
    int si = 1, ei = n;
    while (si <= ei)
    {
        int mid = (si + (ei - si) / 2);
        if (guess(mid) == 0)  //0 sahi guess ki
            return mid;
        else if (guess(mid) == -1) //-1 badi guess krli
            ei = mid - 1;
        else
            si = mid + 1;
    }
    return -1;
}

// 278
int firstBadVersion(int n)
{
    int si = 1, ei = n;
    while (si < ei)
    {
        int mid = (si + (ei - si) / 2);
        if (!isBadVersion(mid))
            si = mid + 1;
        else if (isBadVersion(mid))
            ei = mid;
    }
    return si;
}



// https://practice.geeksforgeeks.org/problems/find-transition-point/1#
int transitionPoint(int arr[], int n)
{
    int si = 0, ei = arr.length - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == 1)
        {
            if (mid - 1 >= 0 && arr[mid - 1] == 1)
                ei = mid - 1;
            else return mid;
        }
        else if (arr[mid] == 0)
            si = mid + 1;
    }
    return -1;
}


// =======
// https://practice.geeksforgeeks.org/problems/buildings-receiving-sunlight3032/1#
public static int longest(int arr[], int n)
{
    int max = -(int)1e9, count = 0;
    for (int ele : arr)
    {
        if (ele >= max)
        {
            count++;
            max = ele;
        }
    }
    return count;
}

// https://practice.geeksforgeeks.org/problems/chocolate-distribution-problem3825/1#
long long findMinDiff(vector<long long> arr, long long n, long long m)
{
    sort(arr.begin(), arr.end());
    int i = 0, j = m - 1;
    long long ans = 1e9;
    while (j < n)
    {
        ans = min(ans, arr[j] - arr[i]);
        i++;
        j++;
    }
    return ans;
}

// https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/count-the-triplets-official/ojquestion-sum of two ele equal 3rd
public static int countTriplets(int[] arr)
{
    Arrays.sort(arr);
    int count = 0;
    for (int i = arr.length - 1; i >= 2; i--)
    {
        int fixed = arr[i];
        int l = 0, r = i - 1;
        while (l < r)
        {
            int data = arr[l] + arr[r];
            if (data == fixed)
            {
                count++;  //bcz equal chahiey
                l++;
                r--;
            }
            else if (data > fixed)
                r--;
            else
                l++;
        }
    }
    return count;
}

// 611
int triangleNumber(vector<int> &nums)
{
    sort(nums.begin(), nums.end());
    int count = 0;
    for (int i = nums.size() - 1; i >= 2; i--)
    {
        int fixed = nums[i];
        int l = 0, r = i - 1;
        while (l < r)
        {
            if (nums[l] + nums[r] > fixed)
            {
                count += r - l;  //bcz gretaer than chahiye
                r--;
            }
            else
                l++;
        }
    }
    return count;
}

// https://practice.geeksforgeeks.org/problems/count-zeros-in-a-sorted-matrix/1
int countZeros(vector<vector<int>> A)
{
    int n = A.size(), i = 0, j = n - 1, count = 0;
    while (i < n && j >= 0)
    {
        if (A[i][j] == 1)
            j--;
        else if (A[i][j] == 0)
        {
            count += j + 1;
            i++;
        }
    }
    return count;
}

// https://practice.geeksforgeeks.org/problems/roof-top-1587115621/1#
static int maxStep(int A[], int N)
{
    int maxConsStep = 0, maxStepTillNow = 0;
    for (int i = 1; i < A.length; i++)
    {
        if (A[i] > A[i - 1])
        {
            maxStepTillNow++;
            maxConsStep = Math.max(maxConsStep, maxStepTillNow);
        }
        else
            maxStepTillNow = 0;
    }

    return maxConsStep;
}

// https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/find_pair_with_given_difference/ojquestion
public static void findPairWithGivenDiffernce(int[] arr, int target)
{
    Arrays.sort(arr);
    int i = 0, j = 1, flag = 0;
    while (j < arr.length)
    {
        int diff = arr[j] - arr[i];
        if (diff == target)
        {
            flag = 1;

            System.out.println(arr[i] + " " + arr[j]);
            //skip duplicates
            int valJ = arr[j++];
            while (j < arr.length && arr[j] == valJ)
                j++;

            if (j < arr.length)
            {
                int valI = arr[i++];
                while (i < arr.length && arr[i] == valI)
                    i++;
            }
        }
        else if (diff < target)
            j++;
        else
            i++;
    }

    if (flag == 0)
        System.out.println(-1);
}

// 69
public int mySqrt(int x)
{
    if (x == 0)
        return 0;
    long ans = 1;
    for (long i = 2; i * i <= x; i++)
        ans = i;

    return (int)ans;
}

int mySqrt_(int x)
{
    int start = 1, end = x, ans = 0;
    while (start <= end)
    {
        int mid = (start + (end - start) / 2);
        if (mid <= (x / mid))
        {
            ans = mid;
            start = mid + 1;
        }
        else
            end = mid - 1;
    }
    return ans;
}

// 50
public double myPow(double x, int n)    
{
    if (n == 0)
        return 1.0;
    else if (n < 0)
    {
        x = 1 / x;
        double ans = myPow(x, -(n / 2));
        // for n=-2147483648 if we write -n/2 then -n=2147483638 which is higher than
        // int_max so it will overflow so phle divide then positive bnao.
        return n % 2 == 0 ? ans * ans : ans * ans * x;
    }
    else
    {
        double ans = myPow(x, n / 2);
        return n % 2 == 0 ? ans * ans : ans * ans * x;
    }
}

// ===========
//923
int mod=(int)1e9+7; 
public int threeSumMulti(int[] arr, int target) {
    Arrays.sort(arr);
    int ans=0,n=arr.length;
    for(int i=0;i<arr.length;i++)
    {   
        int j=i+1,k=n-1;
        while(j<k)
        {
            int data=arr[i]+arr[j]+arr[k];
            if(data==target)
            {   
                if(arr[j]==arr[k])
                {
                    int r=k-j+1;
                    //for this we calculate the number of all possible combinations we can make to pick 2 elements i.e. nC2
                    ans=(ans%mod+(r*(r-1)/2)%mod)%mod;
                    break;
                }
                int c_j=1,c_k=1;
                while(j+1<k && arr[j]==arr[j+1]){
                    j++;
                    c_j++;
                }
                while(j+1<k && arr[k]==arr[k-1]){
                    k--;
                    c_k++;
                }
                ans=(ans%mod+((c_j)*(c_k))%mod)%mod;
                j++;k--;
            }
            else if(data<target)
                j++;
            else k--;
        }
    }
    return ans%mod;
}
    

//==========


//493
public int reversePairCount(int []arr,int si,int mid,int ei,int []snums)
{
    int i=si,j=mid+1,k=si,count=0;
    
    while(i<=mid && j<=ei)
    {
        long val1=arr[i];
        long val2=(long)arr[j]*2;
        if(val1>val2)
        {
            count+=mid+1-i;
            j++;
        }else i++;
    }
    
    i=si;j=mid+1;
    while(i<=mid && j<=ei)
    {
        if(arr[i]<=arr[j]){
            snums[k++]=arr[i++];
        }
        else{
            snums[k++]=arr[j++];
        }
    }
    
    while(i<=mid)
        snums[k++]=arr[i++];
    
    while(j<=ei)
        snums[k++]=arr[j++];
    
    for(int idx=si;idx<=ei;idx++)
        arr[idx]=snums[idx];

    return count;
}


public int reversePairs_helper(int []nums,int si,int ei,int []snums)
{
    if(si==ei)
        return 0;
    
    int count=0;
    int mid=(si+ei)/2;
    count+=reversePairs_helper(nums,si,mid,snums);
    count+=reversePairs_helper(nums,mid+1,ei,snums);
    
    count+=reversePairCount(nums,si,mid,ei,snums);
    return count;
}

public int reversePairs(int[] nums) {
    int n=nums.length;
    int []snums=new int[n];
    return reversePairs_helper(nums,0,n-1,snums);
}


//1985
bool static compare(const string &s1,const string &s2)
{
    if(s2.size()==s1.size())
        return s1<s2;  //cant do in java
    return s1.size()<s2.size();
}

string kthLargestNumber(vector<string>& nums, int k) {
    sort(nums.begin(),nums.end(),compare);
    return nums[nums.size()-k];
}

