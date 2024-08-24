// Remove elements: Remove inplace from sorted(2)  
// Remove duplicates inplace from sorted(2) 
// Remove duplicates atmost time inplace from sorted

//27
public int removeElement(int[] nums, int val) {
    int len=0;
    for(int i=0;i<nums.length;i++)
    {
        if(nums[i]!=val)
        {
            nums[len]=nums[i];
            len++;
        }
    }
    return len;
}

public int removeElement_(int[] nums, int val) {  //my
    int i=0,n=nums.length,j=n-1,len=0;
    while(i<j)
    {
        while(i<n && nums[i]!=val)i++;
        while(j>=0 && nums[j]==val)j--;
        
        if(i<j)
        {
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
        }
    } 
    
    for(int value:nums)
    {
        if(value==val)
            return len;
        len++;
    }
    
    return len;
}

//26
public int removeDuplicates(int[] nums) {  //my
    //no need to resize or erase as it doesn't matter what you leave beyond the new length.
    int len=0,i=0;
    while(i<nums.length)
    {
        nums[len++]=nums[i];
        int val=nums[i];
        
        while(i+1<nums.length && nums[i+1]==val)i++; //array sorted
        i++;
    }
    return len;
}

public int removeDuplicates_(int[] A) {
    int len=1;
    for(int i=1;i<A.length;i++)
    {
        if(A[i]!=A[i-1])
            A[len++]=A[i];
    }
    return len;
}

//80
public int removeDuplicatesII(int[] nums) {
    int len=0,si=0,ei=0,n=nums.length;
    while(ei<n)
    {
        int val=nums[si];
        ei=si+1;
        while(ei<n && nums[ei]==val)ei++;
        if(ei-si==1)
        {
            nums[len++]=nums[si];
        }
        else if(ei-si>=2)
        {
            nums[len++]=nums[si];
            nums[len++]=nums[si];
        }
        
        si=ei;
    }
    return len;
}
  