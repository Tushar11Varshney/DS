// Majority Element n/2 //169
// Majority element n/3 //229
// Majority element general n/k //gfg     


// 169-Moore voting algo
//Assumes majority element always exist so no need to check if freq greater than n/2;
int majorityElement(vector<int> &nums)
{
    int major = nums[0], count = 1;
    for (int i = 1; i < nums.size(); i++)
    {
        if (count == 0)
        {
            major = nums[i];
            count++;
        }
        else if (major == nums[i])
            count++;
        else
            count--;
    }
    return major;
}

// 229
void isGreaternby3(vector<int> &ans, int val, vector<int> &nums)
{
    int count = 0;
    for (int i = 0; i < nums.size(); i++)
    {
        if (val == nums[i])
            count++;
    }

    if (count > (nums.size() / 3))
        ans.push_back(val);
}

vector<int> majorityElement_(vector<int> &nums)
{
    vector<int> ans;
    int val1 = nums[0], count1 = 1, val2 = nums[0], count2 = 0;

    for (int i = 1; i < nums.size(); i++)
    {
        if (val1 == nums[i])
            count1++;
        else if (val2 == nums[i])
            count2++;
        else
        {
            if (count1 == 0)
            {
                val1 = nums[i];
                count1 = 1;
            }
            else if (count2 == 0)
            {
                val2 = nums[i];
                count2 = 1;
            }
            else
            {
                count1--;
                count2--;
            }
        }
    }

    isGreaternby3(ans, val1, nums);
    if (val1 != val2)
        isGreaternby3(ans, val2, nums); //1111 pr val1=1 val2=1 so added twice

    return ans;
}

// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/arrays-and-strings/majority-element-general/ojquestion
public static List<Integer> majorityElement(int[] arr, int k, HashMap<Integer, Integer> map) {

    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
        map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
    }

    int n = arr.length;
    for (int key : map.keySet()) {
        if (map.get(key) > (n / k))
            ans.add(key);
    }

    return ans;
}

