#include <iostream>
#include <cmath>
#include <vector>
#include <string.h>
#include <algorithm>
#include <unordered_map>
#include <queue>
using namespace std;

// 31
public void reverse(int[] nums, int i, int j) {
    while (i < j)
        swap(nums, i++, j--);
}

public void nextPermutation(int[] nums) {
    int i = nums.length - 2;
    while (i >= 0 && nums[i] >= nums[i + 1])
        i--;
    if (i == -1)
        reverse(nums, 0, nums.length - 1); // i=-1 matlab descending
        // return -1;
    else {
        int val = nums[i];
        int j = nums.length - 1; // cant start from aage se
        while (j >= i + 1 && nums[j] <= val)
            j--;

        swap(nums, i, j);
        reverse(nums, i + 1, nums.length - 1);
    }

    // long ans = 0;
    // for (int j = 0; j < nums.length; j++) {
    //     ans = ans * 10 + nums[j];
    //     if (ans > Integer.MAX_VALUE)
    //         return -1;
    // }

    // return (int) ans;
}

// 60
public String getPermutation(int n, int k) {
    int[] nums = new int[n + 1];
    String str = "";
    for (int i = 1; i <= n; i++)
        nums[i] = i;

    for (int i = 1; i < k; i++)
        nextPermutation(nums);

    for (int i = 1; i < nums.length; i++)
        str += nums[i];

    return str;
}

String ans="";
public void getPermutation_helper(List<Integer>factorial,List<Integer>digits,int n,int k)
{
    if(n==1)
    {
        ans+=digits.get(0);
        return;
    }
    
    int index=k/(factorial.get(n-1));
    if(k%factorial.get(n-1)==0)
        index-=1;
    
    ans+=digits.get(index);
    
    digits.remove(index);
    k=k-(factorial.get(n-1)*index);
    getPermutation_helper(factorial,digits,n-1,k);
    
}

public String getPermutation60(int n, int k) {
    
    List<Integer>factorial=new ArrayList<>();
    List<Integer>digits=new ArrayList<>();
    
    for(int i=0;i<n;i++)
        digits.add(i+1);
    
    factorial.add(1);
    for(int i=1;i<=9;i++)
        factorial.add(factorial.get(i-1)*i);
    
    getPermutation_helper(factorial,digits,n,k);
    return ans;
}

//556
int nextGreaterElement(int n)
{
    auto str = to_string(n);
    next_permutation(begin(str), end(str));
    auto res = stoll(str);  //string to long int
    // the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.
    if (res > INT_MAX || res <= n)  //= for 1
        return -1; //21 kelie vrna aayga 12
    return res;
}

public int nextGreaterElement(int n) {
    List<Integer> li = new ArrayList<>();
    while (n != 0) {
        int r = n % 10;
        li.add(r);
        n = n / 10;
    }

    Collections.reverse(li);
    int[] nums = new int[li.size()];
    for (int i = 0; i < nums.length; i++)
        nums[i] = li.get(i);
    return nextPermutation(nums);
}

// 1850
public int getMinSwaps(String num, int k) {
    int[] nums = new int[num.length()];
    int[] kthSmallest = new int[num.length()];

    for (int i = 0; i < num.length(); i++) {
        nums[i] = num.charAt(i) - '0';
        kthSmallest[i] = nums[i];
    }

    for (int i = 0; i < k; i++)
        nextPermutation(kthSmallest);

    int swapCount = 0;
    for (int i = 0; i < nums.length; i++) {
        int j = i;
        while (nums[i] != kthSmallest[j])
            j++;

        while (i < j) {
            swap(kthSmallest, j, j - 1);
            j--;
            swapCount++;
        }
    }
    return swapCount;
}


////////=================================
//https://www.interviewbit.com/problems/triplets-with-sum-between-given-range/
public int tripletWithSumbtwGivenRange(ArrayList<String> A) { // ib
    int n = A.size();
    Double[] values = new Double[n];
    for (int i = 0; i < n; i++) {
        values[i] = Double.parseDouble(A.get(i));
    }

    double a = values[0], b = values[1], c = values[2];

    for (int i = 3; i < n; i++) {
        if (a + b + c > 1 && a + b + c < 2)
            return 1;
        else if (a + b + c > 2) {
            if (a > b && a > c)
                a = values[i];
            else if (b > c)
                b = values[i];
            else
                c = values[i];
        } else {
            if (a < b && a < c)
                a = values[i];
            else if (b < c)
                b = values[i];
            else
                c = values[i];
        }
    }

    if (a + b + c > 1 && a + b + c < 2)
        return 1;
    return 0;
}

//https://www.interviewbit.com/problems/minimum-lights-to-activate/
int minimumLightToActivate(vector<int> &A, int B)
{
    int i = 0, n = A.size(), count = 0;
    while (i < n)
    {
        int bulbFound = -1;
        int j = min(i + B - 1, n - 1);

        while (j >= i - B + 1)
        {
            if (A[j] == 1)
            {
                bulbFound = j;
                i = j + B;
                count++;
                break;
            }
            j--;
        }

        if (bulbFound == -1)
            return -1;
    }
    return count;
}

//179
public String largestNumber(int[] nums) {
    String []arr=new String[nums.length];
    for(int i=0;i<nums.length;i++)
        arr[i]=nums[i]+"";
    
    Arrays.sort(arr,(a,b)->{
        long n1=Long.parseLong(a+b);
        long n2=Long.parseLong(b+a);
        
        if(n2>n1)return -1;
        else if(n1>n2)return 1;
        return 0;
    });
    
    String ans="";
    for(int i=arr.length-1;i>=0;i--)
        ans+=arr[i];
    return ans.charAt(0)=='0'?"0":ans;  [0,0]
}



//754
public int reachNumber(int target) {
    target=Math.abs(target);

    long N=1;
    int parity=0;
    while(!(target<=N*(N+1)/2 && ((target&1)==(N*(N+1)/2 & 1))))
    {
        N++;
        //parity=(parity+1)%2;  //parity se ni krskte because odd even aese alternative ni h
    }
    return (int)N;
}

//977
vector<int> sortedSquares(vector<int> &nums)
{
    vector<int> ans(nums.size());
    int i = 0, j = nums.size() - 1, k = nums.size() - 1;
    while (i <= j)
    {
        if (abs(nums[i]) > abs(nums[j]))
        {
            ans[k--] = abs(nums[i]) * abs(nums[i]);
            i++;
        }
        else
        {
            ans[k--] = abs(nums[j]) * abs(nums[j]);
            j--;
        }
    }
    return ans;
}

//66
//Do inplace without making ans array..
vector<int> plusOne(vector<int> &digits)
{
    int carry = 1, n = digits.size();
    for (int i = n - 1; i >= 0; i--)
    {
        int sum = digits[i] + carry;
        digits[i] = sum % 10;
        carry = sum / 10;
    }

    if (carry > 0)
        digits.insert(digits.begin(), 1);
    return digits;
}

//1200
vector<vector<int>> minimumAbsDifference(vector<int> &arr)
{
    sort(arr.begin(), arr.end());
    int minabsDiff = 1e9;
    vector<vector<int>> ans;
    for (int i = 0; i < n - 1; i++)
    {
        if (abs(arr[i] - arr[i + 1]) < minabsDiff)
        {
            minabsDiff = abs(arr[i] - arr[i + 1]);
            ans.clear();
            ans.push_back({arr[i], arr[i + 1]});
        }
        else if (abs(arr[i] - arr[i + 1]) == minabsDiff)
            ans.push_back({arr[i], arr[i + 1]});
    }

    return ans;
}

//https://www.interviewbit.com/problems/min-steps-in-infinite-grid/
int minStepinInfiniteGrid(vector<int> &A, vector<int> &B)
{

    int steps = 0;
    int n = A.size();
    for (int i = 0; i < n - 1; i++)
    {
        steps += max(abs(A[i] - A[i + 1]), abs(B[i] - B[i + 1]));
    }
    return steps;
}

//https://www.interviewbit.com/problems/xor-ing-the-subarrays/
int xoringSubArray(vector<int> &A)
{ //ib

    int size = A.size();
    if (size % 2 == 0)
        return 0;

    int res = 0;
    for (int i = 0; i < size; i += 2)
        res = res ^ A[i];

    return res;
}

bool hotelBookingPossible(vector<int> &arrive, vector<int> &depart, int K)
{
    sort(arrive.begin(), arrive.end());
    sort(depart.begin(), depart.end());

    for (int i = K; i < arrive.size(); i++)
    {
        if (arrive[i] < depart[i - K])
            return false;
    }

    return true;
}

bool hotelBookingPossible(vector<int> &arrive, vector<int> &depart, int K)
{
    sort(arrive.begin(), arrive.end());
    sort(depart.begin(), depart.end());

    int i = 0, j = 0, rooms = 0, n = arrive.size();

    while (i < n && j < n)
    {
        if (arrive[i] < depart[j])
        {
            rooms++;
            if (rooms > K)
                return false;
            i++;
        }
        else if (arrive[i] > depart[j])
        {
            rooms--;
            j++;
        }
        else
        { //usdin aaya usdin hi chlagya
            i++;
            j++;
        }
    }

    return true;
}

//https://practice.geeksforgeeks.org/problems/leaders-in-an-array-1587115620/1#
vector<int> leaders(int a[], int n)          //2nd approach:right se max print krate jao
{
    vector<int> answer;
    stack<int> st;
    st.push(a[n - 1]);
    answer.push_back(a[n - 1]);
    for (int i = n - 2; i >= 0; i--)
    {
        while (st.size() != 0 && st.top() <= a[i])
            st.pop();

        if (st.size() == 0)
            answer.push_back(a[i]);
        st.push(a[i]);
    }

    reverse(answer.begin(), answer.end());
    return answer;
}

//https://www.interviewbit.com/problems/maximum-absolute-difference/
int maxAbsoluteDiff(vector<int> &arr)
{

    int maximum1 = -1e9;
    int maximum2 = -1e9;
    int minimum1 = 1e9;
    int minimum2 = 1e9;

    for (int i = 0; i < arr.size(); i++)
    {
        maximum1 = max(maximum1, arr[i] + i);
        maximum2 = max(maximum2, arr[i] - i);

        minimum1 = min(minimum1, arr[i] + i);
        minimum2 = min(minimum2, arr[i] - i);
    }

    return max(maximum1 - minimum1, maximum2 - minimum2);
}                

//min xor value --ib
//https://www.interviewbit.com/problems/min-xor-value/
int findMinXor(vector<int> &arr)
{
    sort(arr.begin(), arr.end());

    int min = (int)1e9;
    for (int i = 0; i < arr.size() - 1; i++)
    {
        int val = arr[i] ^ arr[i + 1];
        if (val < min)
        {
            min = val;
        }
    }
    return min;
}

//https://www.interviewbit.com/problems/max-min-05542f2f-69aa-4253-9cc7-84eb7bf739c4/
int maximumMinimum(vector<int> &A)
{

    int n = A.size();
    int max = A[0], min = A[0];

    for (int i = 1; i < n; i++)
    {
        if (A[i] > max)
            max = A[i];
        else if (A[i] < min)
            min = A[i];
    }
    //return Math.max(…A) + Math.min(…A);
    return max + min;
}

//1855
public int maxDistance(int[] num1, int[] num2) {
    int i=0,j=0,n1=num1.length,n2=num2.length,ans=0;
    while(i<n1 && j<n2)
    {
        if(num1[i]<=num2[j])
            j++;
        else i++;
        
        ans=Math.max(ans,j-i);
    }
    return ans==0?ans:ans-1;
}

