// Merge sort //ib
// Merge sort  //88

//88
void merge(vector<int> &nums1, int m, vector<int> &nums2, int n)
{
    int i = m - 1, j = n - 1, k = nums1.size() - 1;
    while (i >= 0 && j >= 0)
    {
        if (nums1[i] >= nums2[j])
            nums1[k--] = nums1[i--];
        else
            nums1[k--] = nums2[j--];
    }
    while (j >= 0)
        nums1[k--] = nums2[j--];
}

//ib gc
public void merge(ArrayList<Integer> a, ArrayList<Integer> b) { 
    
    int i=0,j=0,n=a.size(),m=b.size();  
    
    while(j<m)
    {
        int iVal=i == a.size()?Integer.MAX_VALUE:a.get(i);  //a ka size increase horha h baar baar cant write n
        int jVal=b.get(j);
        
        if(iVal<=jVal)
        i++;
        else{
            a.add(i,jVal);
            i++;
            j++;
        }
    }
}
