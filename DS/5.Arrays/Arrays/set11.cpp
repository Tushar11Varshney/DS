// Maximum unsorted subarray-2  //581
// Maximum distance
// max sum triplet

// perfect peak
// Increasing Triplet Subsequence


// nc 581
//https://www.interviewbit.com/problems/maximum-unsorted-subarray/
vector<int> maximumUnsortedSubArray(vector<int> &A)
{ //memory limit exceeded

    int n = A.size();
    vector<int> leftMax(n);
    vector<int> rightMin(n);

    rightMin[n - 1] = A[n - 1];
    for (int i = n - 2; i >= 0; i--)
        rightMin[i] = min(rightMin[i + 1], A[i]);

    leftMax[0] = A[0];
    for (int i = 1; i < n; i++)
        leftMax[i] = max(leftMax[i - 1], A[i]);

    int j = n - 1;
    for (; j >= 0; j--)
    {
        if (leftMax[j] != rightMin[j])
            break;
    }

    int i = 0;
    for (; i < n; i++)
    {
        if (leftMax[i] != rightMin[i])
            break;
    }

    if (i == n && j == -1)
        return {-1};

    return {i, j};
}

vector<int> maximumUnsortedSubArray(vector<int> &A)
{
    int n = A.size();
    vector<int> sortedA;

    for (int ele : A)
        sortedA.push_back(ele);

    sort(sortedA.begin(), sortedA.end());
    if (sortedA == A)
        return {-1};

    int i = 0, j = n - 1;
    while (i < n && j >= 0)
    {
        if (sortedA[j] != A[j] && sortedA[i] != A[i])
            break;

        if (sortedA[i] == A[i])
            i++;

        if (sortedA[j] == A[j])
            j--;
    }

    return {i, j};
}

//https://www.interviewbit.com/problems/max-distance/
int maximumDistance(const vector<int> &A)
{
    int n = A.size();
    vector<int> maxSuffix(n);
    maxSuffix[n - 1] = A[n - 1];

    for (int i = n - 2; i >= 0; i--)
    {
        maxSuffix[i] = max(maxSuffix[i + 1], A[i]);
    }

    int ans = 0, i = 0, j = 0;
    while (i < n && j < n)
    {
        if (maxSuffix[j] >= A[i])
        {
            ans = max(ans, j - i); //length ni j-i btana h
            j++;
        }
        else
            i++;
    }
    return ans;
}

//https://www.interviewbit.com/problems/maximum-sum-triplet/
public static int maximumSumTriplet(int[] A) { 
    if (A.length < 3)
        return 0;
    int n = A.length;
    int maxSuffixArray[] = new int[n];
    maxSuffixArray[n - 1] = A[n - 1];
    int tripletSum = Integer.MIN_VALUE;
    TreeSet<Integer> set = new TreeSet<Integer>();
    set.add(A[0]);
    for (int i = n - 2; i >= 0; i--) {
        maxSuffixArray[i] = Math.max(maxSuffixArray[i + 1], A[i]);
    }
    for (int j = 1; j < n - 1; j++) {
        Integer firstValue = set.lower(A[j]); // lower(<),higher,ceiling,floor(<=)
        int lastValue = maxSuffixArray[j + 1];
        if (firstValue != null && lastValue > A[j])
            tripletSum = Math.max(tripletSum, firstValue + A[j] + lastValue);
        set.add(A[j]);
    }
    return tripletSum;
}


//ib https://www.interviewbit.com/problems/perfect-peak-of-array
//Do not consider the corner elements i.e A[0] and A[N-1] as the answer.
int perfectPeak(vector<int> &A)
{
    int maximum = A[0], n = A.size();
    for (int i = 1; i < n - 1; i++)
    {
        if (A[i] > maximum)
        {
            maximum = A[i];
            A[i] = A[i] * (-1);
        }
    }

    int minimum = A[n - 1];
    for (int i = n - 2; i >= 1; i--)
    {
        if (A[i] < 0 && abs(A[i]) < minimum)
            return 1;
        minimum = min(abs(A[i]), minimum);
    }

    return 0;
}

//334 bc
public boolean increasingTripletSubsequence(int[] nums) {
    int n=nums.length;
    if(n<3)return false;
    int i=Integer.MAX_VALUE,j=Integer.MAX_VALUE;
    for(int ele:nums)
    {
        if(ele<=i) //= for [1,1,1]
            i=ele;
        else if(ele<=j)
            j=ele;
        else return true;  
    }
    return false;
}

