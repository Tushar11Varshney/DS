// Positive Negative(2)
// Move zeroes to end //283
// Binary Sort gfg
// Sort array by parity //905
// Sort array by parity //922
// Sort colors - 2 //75
// Rotate Array  //189
// Maximize sum of rotated array(2)
// Rearrange Array Elements by Sign 2149

void posNeg(vector<int> &arr) 
{
    int i = 0, j = arr.size() - 1;
    while (i < j)
    {
        while (arr[i] >= 0)  //use i<j
            i++;
        while (arr[j] < 0)
            j--;
        if(i<j)
        swap(arr[i++], arr[j--]);
    }
}

// Sir-jo left mein lejaani hai uspr swapping
void posNeg2(vector<int> &arr)
{
    int pivot = -1, idx = 0;
    while (idx < arr.size())
    {
        if (arr[idx] >= 0)
            swap(arr[++pivot], arr[idx]);
        idx++;
    }
}

// 283
void moveZeroes(vector<int> &arr)
{
    int pivot = -1, idx = 0; //0 se start kroge pivot tou post increment
    while (idx < arr.size())
    {
        if (arr[idx] != 0)
            swap(arr[++pivot], arr[idx]);
        idx++;
    }
}

// https://practice.geeksforgeeks.org/problems/binary-array-sorting-1587115620/1
void binSort(int arr[], int N)
{
    int pivot = -1, idx = 0;
    while (idx < N)
    {
        if (arr[idx] == 0)
            swap(arr[++pivot], arr[idx]);
        idx++;
    }
    // while (i < n) { if (arr[i] == 0) { swap(arr, i, j); i++; j++; } else { i++; }  //i 0 se initialse then post increment
}

//905
vector<int> sortArrayByParity(vector<int> &arr)
{
    int n = arr.size();
    int i = 0, j = n - 1;
    while (i < j)
    {
        while (i < j && arr[i] % 2 == 0)  //[0,2] use i<j..runtie err
            i++;
        while (i < j && arr[j] % 2 != 0)
            j--;
        if (i < j)
        {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    return arr;
}

//922 nc
vector<int> sortArrayByParityII(vector<int>& nums) {
    int i=0,j=1,n=nums.size(); //i->even j->odd
    while(i<n && j<n)
    {
        if(nums[i]%2!=0 && nums[j]%2!=1)
        {
            swap(nums[i],nums[j]);
            i+=2;
            j+=2;
        }
        else{
            if(nums[i]%2==0)
                i+=2;
            if(nums[j]%2==1)
                j+=2;
        }
    }
    return nums;
}

// 75
void sortColors(vector<int> &arr)
{
    int p1 = -1, p2 = arr.size() - 1, idx = 0;
    while (idx <= p2)
    {
        if (arr[idx] == 0)
            swap(arr[++p1], arr[idx++]);
        else if (arr[idx] == 2)
            swap(arr[idx], arr[p2--]);
        else
            idx++;
    }
    //  while (i <= k) // = pr chlane ki zrurat ni just bcz of pep portal.upr jaisa hi hai..bus i 0 se hai tou post incr
    //  {
    //   if (arr[i] == 1) i++;
    //   else if (arr[i] == 2) { swap(arr, i, k); k--; }
    //   else { swap(arr, i, j); i++; j++; } }
}

void sortColors(vector<int>& nums) {
    int p1=-1,p2=-1,idx=0;
    while(idx<nums.size())
    {
        if(nums[idx]==0)
        {
            swap(nums[++p1],nums[idx]);
            if(nums[idx]==1)swap(nums[++p2],nums[idx]);
            else ++p2;
            idx++;
        }
        else if(nums[idx]==1)
        {
            swap(nums[++p2],nums[idx++]);   
        }
        else idx++;
    }
}


// (k%length+length)%length 2 tarikke ya tou pehle puri reverse then beech ke part reverse ya vice versa
// 189
void reverse(vector<int> &nums, int i, int j)
{
    while (i < j)
    {
        swap(nums[i++], nums[j--]);
    }
}

void rotate(vector<int> &nums, int k)
{
    int m = nums.size();
    k = (k % m + m) % m;
    reverse(nums, 0, m - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, m - 1);
    for (int ele : nums)
        cout << ele << " ";
}

int calculate(vector<int> &nums)
{
    int sum = 0;
    for (int i = 0; i < nums.size(); i++)
        sum += nums[i] * i;
    return sum;
}

void maximizeSum(vector<int> &nums) //o(n^2)
{
    int m = nums.size();
    int maximum = 0;
    maximum = calculate(nums);
    // cout<<maximum<<" ";
    for (int k = 1; k < m; k++)
    {
        reverse(nums, 0, m - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, m - 1);
        // cout<<calculate(nums)<<" ";
        maximum = max(maximum, calculate(nums));
    }
    cout << maximum;
}

void maximizeSum2(vector<int> &nums) //o(n)
{
    int m = nums.size();
    int maximum = 0;
    int rotatedSum = 0;
    int sum = 0;
    for (int ele : nums)
        sum += ele;
    for (int i = 0; i < nums.size(); i++)
        rotatedSum += i * nums[i];
    maximum = rotatedSum;
    for (int i = 0; i < nums.size() - 1; i++) //ye loop rotation ke hisaaab se ni chla formulae ke hisaab se
    {
        maximum = max(maximum, rotatedSum = rotatedSum - sum + nums[i] * m);
    }
    cout << maximum;
}

// 2149 nc
public int[] rearrangeArray(int[] nums) {
    // nums consisting of an equal number of positive and negative integers.
        int ans[]=new int[nums.length];

        int oddIdx=1, evenIdx=0;
        for(int ele:nums)
        {
            if(ele>0)
            {  
                ans[evenIdx] = ele;
                evenIdx +=2;
            }
            else if(ele<0)
            {
                ans[oddIdx] = ele;
                oddIdx +=2;
            }
        }
        return ans;
}
