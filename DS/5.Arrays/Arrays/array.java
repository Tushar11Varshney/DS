import java.util.*;

public class array {

//605
public boolean canPlaceFlowers(int[] flowerbed, int n) {
    if(n==0)return true;
    for(int i=0;i<flowerbed.length;i++)
    {
        if(flowerbed[i]==0)
        {
            if((i-1<0 || flowerbed[i-1]==0) && (i+1>=flowerbed.length || flowerbed[i+1]==0))
            {
                flowerbed[i]=1;
                n--;
            }
            if(n==0)return true;
        }
    }
    return false;
}

int balanceArray(vector<int> &A)
{ //ib
    int sumEven = 0, sumOdd = 0, n = A.size();
    for (int i = 0; i < n; i++)
    {
        if (i % 2 == 0)
            sumEven += A[i];
        else
            sumOdd += A[i];
    }

    int count = 0;
    for (int i = n - 1; i >= 0; i--)
    {
        if (i % 2 == 0)
        {
            sumEven = sumEven - A[i];
            if (sumEven == sumOdd)
                count++;
            sumOdd = sumOdd + A[i];
        }
        else
        {
            sumOdd = sumOdd - A[i];
            if (sumEven == sumOdd)
                count++;
            sumEven = sumEven + A[i];
        }
    }
    return count;
}

int nobleInteger(vector<int> &A) //ib
{
    sort(A.begin(), A.end());

    if (A[A.size() - 1] == 0)
        return 1;
    for (int i = 0; i < A.size(); i++)
    {
        if (A[i] < 0)
            continue;
        else
        {
            // int val=A[i];
            // int count=0;  //tle
            // for(int j=i+1;j<A.size();j++)
            // {
            //     if(A[j]==val)A[j]= -val;
            //     else if(A[j]>val)count++;
            // }

            // if(count==val)return 1;

            if (i + 1 < A.size() && A[i + 1] != A[i] && A[i] == (A.size() - (i + 1)))
                return 1;
        }
    }

    return -1;
}

int nobleInteger(vector<int> &A)
    {

        int n = A.size();
        vector<int> countArray(n + 1);

        for (int i = 0; i < n; i++)
        {
            if (A[i] >= n)
                countArray[n]++;

            else if (A[i] >= 0)
                countArray[A[i]]++;
        }

        int totalGreater = countArray[n];

        for (int i = n - 1; i >= 0; i--)
        {

            if (totalGreater == i && countArray[i] > 0)
                return 1;

            if (totalGreater > i)
                return -1;

            totalGreater += countArray[i];
        }

        return -1;
    }

//406
public int[][] reconstructQueue(int[][] people) {
        
    Arrays.sort(people,(int []a,int []b)->{
        if(a[0]==b[0])return b[1]-a[1];
        return a[0]-b[0]; 
    });
    
    int n=people.length;
    
    int [][]ans=new int[n][2];
    
    for(int i=0;i<n;i++)
        ans[i][0]=-1;
    
    for(int i=0;i<n;i++)
    {
        int height=people[i][0];
        int count=people[i][1];
        
        int posn=-1;
        for(int j=0;j<n;j++)
        {
            if(count!=0)
            {
                if(ans[j][0]==-1)count--;
                else if(ans[j][0]>=height)count--;
            }
            else if(ans[j][0]==-1){
                posn=j;
                break;
            }
        }
        
        ans[posn][0]=height;ans[posn][1]=people[i][1];
    }
    
    return ans;
}


//1031
void maxSum_helper(vector<int>&left,vector<int>&right,vector<int>&arr,int firstLen,int secondLen)
{
    int n=arr.size();
    int sum=0;
    for(int i=0;i<n-secondLen;i++) //calculating till here bcz baaki ke element second part dega
    {
        if(i<firstLen)
        {
            sum+=arr[i];
            left[i]=sum;
        }
        else{
            sum=sum+arr[i]-arr[i-firstLen];
            left[i]=max(left[i-1],sum);
        }
    }
    
    sum=0;
    for(int i=n-1;i>=firstLen;i--)
    {
        if(i>=n-secondLen)
        {
            sum+=arr[i];
            right[i]=sum;
        }
        else{
            sum=sum+arr[i]-arr[i+secondLen];
            right[i]=max(right[i+1],sum);
        }
    }    
}

int maxSumTwoNoOverlap(vector<int>& arr, int firstLen, int secondLen) {
    int n=arr.size();
    vector<int>left(n);
    vector<int>right(n);
    
    maxSum_helper(left,right,arr,firstLen,secondLen);
    int ans=0,j=firstLen;
    for(int i=firstLen-1;i<n-secondLen;i++)
    {
        ans=max(ans,left[i]+right[j]);
        j++;
    }
    
    for(int i=0;i<n;i++)
    {
        left[i]=0;
        right[i]=0;
    }
    maxSum_helper(left,right,arr,secondLen,firstLen);
    j=secondLen;    //can make a fn and pass firstLen,secondLen in 1st call and vice versa in second.
    for(int i=secondLen-1;i<n-firstLen;i++)
    {
        ans=max(ans,left[i]+right[j]);
        j++;
    }
    return ans;
}

// wiggle sort 2 optimized soln left

//689
public int[] maxSumOfThreeSubarrays(int[] arr, int k) {
        
    int n=arr.length;
    int []dp1=new int[n];
    int []dp2=new int[n];
    int prefixSum[]=new int[n];
    
    int sum=0,sum1=0;
    for(int i=0;i<n;i++)
    {
        sum1+=arr[i];
        if(i<k)
        {
            sum+=arr[i];
            dp1[i]=sum;
        }
        else{
            sum=sum+arr[i]-arr[i-k];
            dp1[i]=Math.max(dp1[i-1],sum);
        }
        prefixSum[i]=sum1;
    }
    
    sum=0;
    for(int i=n-1;i>=0;i--)
    {
        if(i>=n-k)
        {
            sum+=arr[i];
            dp2[i]=sum;
        }
        else{
            sum=sum+arr[i]-arr[i+k];
            dp2[i]=Math.max(dp2[i+1],sum);
        }
    }
    
    int []ans=new int[3];
    int maximum=0;
    int l=k-1,r=2*k,lval=-1,idx2=-1,rval=-1,mval=-1;
    for(int i=k;i<n-k;i++)
    {
         int val=dp1[l]+prefixSum[i+k-1]-prefixSum[i-1]+dp2[r];
         if(val>maximum)
         {
             maximum=dp1[l]+prefixSum[i+k-1]-prefixSum[i-1]+dp2[r];
             idx2=i;
             lval=dp1[l];rval=dp2[r];
             mval=prefixSum[i+k-1]-prefixSum[i-1];
         }
        l++;r++; 
    }
    
    for(int i=0;i<idx2;i++)   //search where(idx) we go leftsum first
    {
        if(prefixSum[i+k-1]-(i-1==-1?0:prefixSum[i-1])==lval)
        {
            ans[0]=i;
            break;
        }
    }
    ans[1]=idx2;
    for(int i=idx2+k;i<=n-k;i++)  //search after second array finish
    {
        if(prefixSum[i+k-1]-prefixSum[i-1]==rval)
        {
            ans[2]=i;
            break;
        }
    }
    return ans;
}

