// 1.missing number  //268(2)
// 2.Find disappearing number //448
// 3.Find all duplicate in array //442  
// 4.Find duplicate number(2) //287
// 5.first missing positive //41

//268 gc
// 1 <= n <= 10^4 if you use n=0 and set for each value in array then check which bit will be off so it cant do for more than 32 if int taken 
public int missingNumber(int[] nums) {
    int n=nums.length;
    int sumShouldBe=(n*(n+1))/2;
    int sum=0;
    for(int val:nums)
        sum+=val;
    return sumShouldBe-sum;
}

public int missingNumber_(int[] nums) {
    int result=nums.length;
    for(int i=0;i<nums.length;i++)
        result^=i^nums[i];
    return result;
}

//448 gc
public List<Integer> findDisappearedNumbers(int[] nums) {
    
    List<Integer>ans=new ArrayList<>();
    for(int i=0;i<nums.length;i++)
    {
        int idx=Math.abs(nums[i]);
        if(nums[idx-1]<0)continue;
        else nums[idx-1]=-nums[idx-1];
    }
    
    for(int i=0;i<nums.length;i++)
    {
        if(nums[i]>0)
            ans.add(i+1);
    }
    return ans;
}

// 442 yc
public List<Integer> findDuplicates(int[] nums) {
    List<Integer>ans=new ArrayList<>();
    for(int i=0;i<nums.length;i++)
    {
        int idx=Math.abs(nums[i]);
        if(nums[idx-1]<0){
            ans.add(idx);
        }
        else nums[idx-1]=-nums[idx-1];
    }
    return ans;
}

// 287 yc
public int findDuplicate(int[] nums) {
        for(int i=0;i<nums.length;i++)
        {
            int idx=Math.abs(nums[i]);
            if(nums[idx-1]<0){
                return idx;
            }
            else nums[idx-1]=-nums[idx-1];
        }
        return -1;
}

public int findDuplicate2(int[] nums) {
    int fast = nums[0], slow = nums[0];
    do {
        slow = nums[slow];
        fast = nums[nums[fast]];
    } while (fast != slow);

    slow = nums[0];
    while (fast != slow) {
        slow = nums[slow];
        fast = nums[fast];
    }

    return slow;
}

//41
public int firstMissingPositive(int[] nums) {
    boolean presentOne=false;
    for(int val:nums)
    {
        if(val==1)
        {
            presentOne=true;
            break;
        }
    }
    if(!presentOne)
        return 1; 
    
    int n=nums.length;
    if(n==1)return 2;
    
    for(int i=0;i<n;i++)
    {
        if(nums[i]<=0 || nums[i]>n)
            nums[i]=1;
    }
    
    for(int i=0;i<n;i++)
    {
        int idx=Math.abs(nums[i]);
        if(nums[idx-1]>0)nums[idx-1]=nums[idx-1]*(-1);
    }
    
    for(int i=0;i<n;i++)
        if(nums[i]>0)return i+1;
    
    return n+1;
}

