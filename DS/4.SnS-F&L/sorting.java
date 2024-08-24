import java.util.*;

/*
                    
       
Transition pt    

// 9.Three sum multi 

Building receiving sunlight
chocolate distrbn
.Count triplets
.Triangle num
count 0 in matrix
roof top
.sqr root (2)
.Pow
Find pair with given diff



*/

// https://practice.geeksforgeeks.org/problems/median-in-a-row-wise-sorted-matrix1527/1#
int countSmallerthanMid(vector<int> &A, int val)
{
    int si = 0, ei = A.size() - 1, mid = 0;
    while (si <= ei)
    {
        int mid = (si + (ei - si) / 2);
        if (A[mid] <= val)
        {
            si = mid + 1;
        }
        else
            ei = mid - 1;
    }
    return si;
}

// The median is the middle number in a sorted, ascending or descending
int median(vector<vector<int>> &A, int r, int c)
{
    int low = 0, high = 1e9;
    while (low <= high)
    {
        int mid = (low + (high - low) / 2);
        int count = 0;
        for (int i = 0; i < r; i++)
            count += countSmallerthanMid(A[i], mid);

        if (count <= (r * c) / 2)
            low = mid + 1;
        else
            high = mid - 1;
    }

    return low;
}

//https://www.codingninjas.com/codestudio/problems/aggressive-cows_1082559?leftPanelTab=1
bool isPossibleToPlace(vector<int> &stalls, int k, int dist)
{
    int i=0,j=1,count=0;
    bool flag=false;
    while(j<stalls.size())
    {
        if(stalls[j]-stalls[i]>=dist)
        {
            flag=true;
            i=j;j++;
            count++;
        }
        else j++;
    }
    if(flag)return count+1>=k;
    return false;
}

int aggressiveCows(vector<int> &stalls, int k)
{
    sort(stalls.begin(),stalls.end());
    int n=stalls.size();
    if(k>n)return -1;
    int ans=0,si=0,ei=1e9;
    while(si<=ei)
    {
        int mid=(si+ei)/2;
        if(isPossibleToPlace(stalls,k,mid))
        {
            ans=mid;
            si=mid+1;
        }
        else ei=mid-1;
    }
    return ans;
}

void pattern()
{
    vector<int> arr = {2, 5, 7, -8, 3, -2};
    // vector<int>arr={2,5,7,8,3,2};
    int n = arr.size(), maximum = 0;
    for (int ele : arr)
        maximum = max(ele, maximum);

    for (int i = 0; i < maximum; i++)
    {
        for (int j = 0; j < n; j++)
        {
            int val = arr[j];
            if (val > 0 && (maximum - val) <= i)
                cout << "*\t";
            else
                cout << "\t";
        }
        cout << endl;
    }

    for (int ele : arr)
    {
        if (ele < 0)
            maximum = max(maximum, -ele);
    }

    for (int i = 0; i < maximum; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (arr[j] < 0)
            {
                int val = -arr[j];

                if (val > i)
                    cout << "*\t";
                else
                    cout << "\t";
            }
            else
                cout << "\t";
        }
        cout << endl;
    }
}

public class sorting {

    // pep pr isSmaller ,swap,isGreater fn derkhe the because portal pr inke hisaab se printing krai hai

    // used for swapping ith and jth elements of array
    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping " + arr[i] + " and " + arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    //*
    public static int quickSelect(int[] arr, int lo, int hi, int k) { // k 0 based 
        int pivot = arr[hi];
        int pivotIndex = partition(arr, pivot, lo, hi);

        if (pivotIndex < k)
            return quickSelect(arr, pivotIndex + 1, hi, k);
        else if (pivotIndex > k)
            return quickSelect(arr, lo, pivotIndex - 1, k);
        else
            return arr[k];
    }



    public static void countSortDate(String[] arr, int div, int mod, int range) {
        int psum[] = new int[range];
        String sortedArray[] = new String[arr.length];
        // for(int i=0;i<arr.length;i++)
        for (String ele : arr) {
            psum[(Integer.parseInt(ele, 10) / div) % mod]++;
        }
        psum[0]--;
        for (int i = 1; i < range; i++) {
            psum[i] = psum[i] + psum[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            String val = arr[i];
            int index = psum[(Integer.parseInt(val, 10) / div) % mod];
            sortedArray[index] = val;
            psum[(Integer.parseInt(val, 10) / div) % mod]--;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sortedArray[i];
        }
    }

    public static void sortDates(String[] arr) {

        countSortDate(arr, 1000000, 100, 32); // day
        countSortDate(arr, 10000, 100, 13); // month
        countSortDate(arr, 1, 10000, 2501); // year

        // int i=057;
        // System.out.println(i);  //print 47
    }

    public static void main(String[] args) {
        // int arr[]={5,9,8,1,2};
        // int arr1[]={2,9,5,1,3};
        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr1);

        // int arr[] = { 7, 9, 4, 8, 3, 6, 2, 1 };
        // partition(arr, 5);
        // for (int ele : arr)
        //     System.out.println(ele);
    }

    //https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/counting-elements-in-two-arrays-official/ojquestion

    //https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/count-zeros-xor-pairs-official/ojquestion

    //https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/distinct-absolute-array-elements-official/ojquestion

    //https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/find-the-element-that-appears-once-in-sorted-array--offcial/ojquestion

    //https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/maximum-number-of-1s-row-official/ojquestion

}
