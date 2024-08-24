import java.util.Stack;

public class set3 {
    
// 1. NGOR(3),NSOR(2),NSOL(2),NGOL(2)
// 2. Maximum abs diff
// 3. Daily Temperatures
// 4. No of valid subarray  //nsr
// 5. Stock Span(2 varin)
// 6. Trapping rain water(storing vertically using min/max array, storing horizontally (ngl ngr), storing vertically without space) 
// 7.Largest Rectangle Area(2)  //nsl, nsr
// 8.Maximal Rectangle  
// 9.NGOR Circular
// 11.find ngr for nums2 and give ans for nums1 acc to map

void ngor(vector<int> &arr)
{
    int n = arr.size();
    vector<int> ngor(n, n);
    for (int i = 0; i < arr.size(); i++)
    {
        int val = arr[i];
        for (int j = i + 1; j < n; j++)
        {
            if (arr[j] > val)
            {
                ngor[i] = j;
                break;
            }
        }
    }
    for (int i = 0; i < n; i++)
        cout << ngor[i] << endl;
}

void ngorStack(vector<int> &arr) //seedha chalna if stream data
{
    //stack mein vo jo potn ng hoskte hain
    // nge[n - 1] = -1;

    // Stack<Integer> st = new Stack<>();
    // st.push(arr[n - 1]);
    // for (int i = n - 2; i >= 0; i--) {
    //   while (st.size() > 0 && st.peek() <= arr[i])
    //     st.pop();
    //   if (st.size() != 0)
    //     nge[i] = st.peek();
    //   else
    //     nge[i] = -1;

    //   st.push(arr[i]);
    // }
    // return nge;

    //jinka bdA hum dhud rhe hain right mein vo abhi stack mein hai
    stack<int> st;
    int n = arr.size(), i = 1;
    vector<int> ngrArray(n, n);
    st.push(0);    //i=0 and this not needed
    while (i < n)
    {
        if (st.size() != 0 && arr[i] > arr[st.top()])
        {
            ngrArray[st.top()] = i;
            st.pop();
        }
        else
        {
            st.push(i);
            i++;
        }
    }
    for (int i = 0; i < n; i++)
        cout << ngrArray[i] << endl;
}

// 739(ngor,stack->ll)
public int[] dailyTemperatures(int[] temperatures) {

    int n = temperatures.length;
    LinkedList<Integer> st = new LinkedList<>();
    int result[] = new int[n];

    st.addLast(0);
    for (int i = 1; i < n; i++) {
        while (st.size() != 0 && temperatures[st.getLast()] < temperatures[i]) {
            result[st.getLast()] = i - st.getLast();
            st.removeLast();
        }

        st.addLast(i);
    }
    return result;
}

void nsorStack(vector<int> &arr)
{
    stack<int> st;
    int n = arr.size(), i = 1;
    vector<int> nsrArray(n, n);
    st.push(0);
    while (i < n)
    {
        if (st.size() != 0 && arr[i] < arr[st.top()])
        {
            nsrArray[st.top()] = i;
            st.pop();
        }
        else
        {
            st.push(i);
            i++;
        }
    }
    for (int i = 0; i < n; i++)
        cout << nsrArray[i] << endl;
}

public int[] nearestSmallerRight(int a[], int n) {
    int[] nearestRightSmall = new int[n];
    Stack<Integer> st = new Stack<>();
    st.push(a[n - 1]);
    for (int i = n - 2; i >= 0; i--) {
        while (st.size() > 0 && st.peek()>=a[i])
            st.pop();
        if (st.size() == 0)
            nearestRightSmall[i] = 0;
        else
            nearestRightSmall[i] = st.peek();

        st.push(a[i]);
    }

    return nearestRightSmall;
}

void ngolStack(vector<int> &arr)
{
    stack<int> st;
    int n = arr.size(), i = n - 2;
    vector<int> nglArray(n, -1);
    st.push(n - 1);
    while (i >= 0)
    {
        if (st.size() != 0 && arr[i] > arr[st.top()])
        {
            nglArray[st.top()] = i;
            st.pop();
        }
        else
        {
            st.push(i);
            i--;
        }
    }
    for (int i = 0; i < n; i++)
        cout << nglArray[i] << endl;
}
 
void nsolStack(vector<int> &arr)
{
    stack<int> st;
    int n = arr.size(), i = n - 2;
    vector<int> nslArray(n, -1);
    st.push(n - 1);
    while (i >= 0)
    {
        if (st.size() != 0 && arr[i] < arr[st.top()])
        {
            nslArray[st.top()] = i;
            st.pop();
        }
        else
        {
            st.push(i);
            i--;
        }
    }
    for (int i = 0; i < n; i++)
    {
        cout << nslArray[i] << endl;
    }
}

// https://practice.geeksforgeeks.org/problems/maximum-difference/1#
public int[] nearestSmallerLeft(int a[], int n) {
    int[] nearestleftSmall = new int[n];
    Stack<Integer> st = new Stack<>();
    st.push(a[0]);
    for (int i = 1; i < n; i++) {
        while (st.size() > 0 && a[i] <= st.peek())
            st.pop();
        if (st.size() == 0)
            nearestleftSmall[i] = 0;
        else
            nearestleftSmall[i] = st.peek();

        st.push(a[i]);
    }

    return nearestleftSmall;
}

//Gfg-Maximum abs Difference btw nsol and nsor
int findMaxDiff(int a[], int n) {
    int[] nearestleftSmall = nearestSmallerLeft(a, n);
    int[] nearestRightSmall = nearestSmallerRight(a, n);
    int max = 0;
    for (int i = 0; i < n; i++) {
        max = Math.max(max, Math.abs(nearestleftSmall[i] - nearestRightSmall[i]));
    }
    return max;
}

//https://leetcode.ca/all/1063.html
// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/stacks/number-of-valid-subarrays-official/ojquestion
public static int validSubarrays(int[] nums) {
    int count = 0;
    Stack<Integer> st = new Stack<>();
    for (int i = 0; i < nums.length; i++) {
        while (st.size() > 0 && nums[i] < st.peek())  //nsr
            st.pop();
        st.push(nums[i]);

        count += st.size(); //jo jo stack me h us se lekr nums[i] ki subaaray
    }
    return count;
}

// https://pepcoding.com/resources/online-java-foundation/stacks-and-queues/stock-span-official/ojquestion
public static int[] stockSpan(int[] arr) {
    int[] span = new int[arr.length];
    span[0] = 1;   //span-higher than todays price.  //ngl

    Stack<Integer> st = new Stack<>();
    st.push(0);
    for (int i = 1; i < arr.length; i++) {
        while (st.size() > 0 && arr[st.peek()] < arr[i])
            st.pop();

        if (st.size() == 0)
            span[i] = i + 1;
        else
            span[i] = i - st.peek();

        st.push(i);
    }
    return span;
}

// stock span--fixed array
//901
class StockSpanner
{
public:
    stack<pair<int, int>> st;    //ngl
    //Stack<int[]>st=new Stack<>();  //max number of cons date for which price lower/equal todays price
    int idx = 0;
    StockSpanner()
    {
        st.push({-1, -1}); //{idx,value}
        //st.push(new int[]{-1,-1});
    }

    int next(int price)
    {
        while (st.top().second != -1 && st.top().second <= price)
            st.pop();
        int span = idx - st.top().first;
        st.push({idx, price});
        idx++;

        //while (st.peek()[0]!=-1 && st.peek()[1] <= price)
        //        st.pop();

        //int span=idx-st.peek()[0];
        //st.push(new int[]{idx++,price});
        return span;
    }
};

//42
int trap(vector<int> &height)
{
    int n = height.size();
    vector<int> left(n, 0);
    vector<int> right(n, 0);

    int prev = 0;
    for (int i = 0; i < n; i++)
    {
        left[i] = max(height[i], prev);
        prev = left[i];
    }

    prev = 0;
    for (int i = n - 1; i >= 0; i--)
    {
        right[i] = max(height[i], prev);
        prev = right[i];
    }

    int water = 0;
    for (int i = 0; i < n; i++)
    {
        water += min(left[i], right[i]) - height[i];
    }

    return water;
}

int trap2(vector<int> &height)
{ 
    stack<int> st;
    int n = height.size();

    int water = 0;
    for (int i = 0; i < n; i++)
    {
        while (st.size() != 0 && height[st.top()] <= height[i])  //ngr
        {
            int h = st.top(); //jo nikla h uskelie calculate hoga
            st.pop();

            if (st.size() == 0)
                break;
            int w = i - st.top() - 1;
            water += w * (min(height[i], height[st.top()]) - height[h]);
        }

        st.push(i);
    }
    return water;
}

int trap3(vector<int> &height)
{
    int water = 0, lmax = 0, rmax = 0;
    int i = 0, j = height.size() - 1;
    while (i < j)
    {
        lmax = max(height[i], lmax);
        rmax = max(height[j], rmax);

        if (lmax <= rmax)
            water += lmax - height[i++];
        else
            water += rmax - height[j--];
    }

    return water;
}

//84-1st method in java
public int largestRectangleArea(int[] arr) {
    int[] nsLeft = nseLeft(arr);
    int[] nsRight = nseRight(arr);

    int maxArea = 0;
    for (int i = 0; i < arr.length; i++) {
        int width = nsRight[i] - nsLeft[i] - 1;
        int area = arr[i] * width;
        System.out.println("Area " + i + "can make-->" + area);
        maxArea = Math.max(maxArea, area);
    }

    return maxArea;
}

int largestRectangleArea(vector<int> &heights)
{
    int n = heights.size();
    stack<int> st;
    st.push(-1);

    int area = 0;
    for (int i = 0; i < n; i++)
    {
        while (st.top() != -1 && heights[i] <= heights[st.top()])  //nsr
        {
            int height = heights[st.top()];
            st.pop();

            int w = i - st.top() - 1;
            area = max(area, height * w);
        }
        st.push(i);
    }

    while (st.size() != 1)
    {
        int height = heights[st.top()];
        st.pop();

        int w = n - st.top() - 1;
        area = max(area, height * w);
    }
    return area;
}

//85
int maximalRectangle(vector<vector<char>> &matrix)
{
    if (matrix.size() == 0 || matrix[0].size() == 0)
        return 0;

    int n = matrix.size();
    int m = matrix[0].size();
    vector<int> arr(m, 0);
    int area = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            arr[j] = matrix[i][j] == '1' ? arr[j] + 1 : 0;
        }
        area = max(area, largestRectangleArea(arr));
    }
    return area;
}

// 496->find nge for nums2 and give ans for nums1 acc to map
public static HashMap<Integer, Integer> solve(int[] arr) {
    int n = arr.length;
    int[] nge = new int[n];
    nge[n - 1] = -1;

    Stack<Integer> st = new Stack<>();
    HashMap<Integer, Integer> map = new HashMap<>();
    st.push(arr[n - 1]);
    map.put(arr[n - 1], -1);
    for (int i = n - 2; i >= 0; i--) {
        while (st.size() > 0 && st.peek() < arr[i])
            st.pop();
        if (st.size() != 0)
            nge[i] = st.peek();
        else
            nge[i] = -1;
        map.put(arr[i], nge[i]);

        st.push(arr[i]);
    }
    return map;
}

public int[] nextGreaterElement(int[] nums1, int[] nums2) {

    int ans[] = new int[nums1.length];
    int i = 0;
    HashMap<Integer, Integer> map = solve(nums2);
    for (int ele : nums1) {
        ans[i] = map.get(ele);
        i++;
    }

    return ans;
}

//503
void nextGreaterElements2(vector<int> &arr)
{
    stack<int> st;
    int n = arr.size(), i = 1;
    vector<int> ngrArray(n, -1);
    //if(n==1)return ngrArray;
    st.push(0);
    while (i < 2 * n)
    {
        if (st.size() != 0 && arr[i % n] > arr[st.top()])
        {
            ngrArray[st.top()] = arr[i % n];
            st.pop();
        }
        else if (i < n)
        {
            st.push(i);
            i = i + 1;
        }
        else
            i = i + 1;
    }
}


}
