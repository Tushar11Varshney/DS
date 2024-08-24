// sum of diagonals
// sort all diagonal(Diagonal-Gap strategy)
// store anti diagonal(Anti diagonal-Think pattern..gap strategy dont work)

// all rows of pascal triangle(nCr formula)
// specific row of pascal triangle




//1572 nc
int diagonalSum(vector<vector<int>>& mat)
{
    int sum=0,n=mat.size(),i=0;
    
    int r1=0,c1=0,r2=0,c2=n-1;
    while(i<n)
    {
        sum+=mat[r1][c1]+mat[r2][c2];
        r1++;c1++;r2++;c2--;i++;
    }
    
    r1=n/2,c1=n/2;
    if(n%2!=0)
        sum-=mat[r1][c1];
    return sum;
}


//1329 nc
public int[][] diagonalSort(int[][] mat) {
    int n=mat.length,m=mat[0].length;
    for(int gap=0;gap<m;gap++)
    {
        List<Integer>l=new ArrayList<>(); 
        for(int i=0,j=gap;i<n && j<m;j++)
        {
            l.add(mat[i][j]);
            i++;
        }
        
        Collections.sort(l);
        int k=0;
        for(int i=0,j=gap;i<n && j<m;j++)
        {
            mat[i][j]=l.get(k);
            k++;i++;
        }
    }
    
    for(int gap=1;gap<n;gap++)
    {
        List<Integer>l=new ArrayList<>(); 
        for(int i=gap,j=0;i<n && j<m;i++)
        {
            l.add(mat[i][j]);
            j++;
        }
        
        Collections.sort(l);
        int k=0;
        for(int i=gap,j=0;i<n && j<m;i++)
        {
            mat[i][j]=l.get(k);
            k++;j++;
        }
    }
    
    return mat;
}

//ib gc
vector<vector<int>> antiDiagonal(vector<vector<int>> &A)
{
    int n = A.size();
    vector<vector<int>> result;
    for (int s = 0; s < n; s++) //s:sum is always constant for all list element
    {
        vector<int> subres;
        for (int i = 0, j = s; i <= s; i++, j--)
        {
            subres.push_back(A[i][j]);
        }

        result.push_back(subres);
    }

    for (int s = 1; s < n; s++)
    {
        vector<int> subres;
        for (int i = s, j = n - 1; j >= s; i++, j--)
        {
            subres.push_back(A[i][j]);
        }

        result.push_back(subres);
    }
    return result;
} 

//119
long nCr(long n, long r)
{
    if (n < r)   //no need here
        return 0L;

    long result = 1L;
    for (long i = 0; i < r; i++)
    {
        result *= (n - i);
        result /= (i + 1);
    }
    return result;
}

vector<int> getRow(int A)  //A-->rowindex       
{
    vector<int> result;
    result.push_back(1);
    if (A == 0)
        return result;
    for (int i = 1; i < A; i++)
    {
        result.push_back(nCr(A, i));
    }
    result.push_back(1);
    return result;
}

//118
vector<vector<int>> generate(int A)
{
    vector<vector<int>> result;
    if (A == 0)
        return result;
    result.push_back({1});
    for (int i = 1; i < A; i++)
    {
        vector<int> subRes;
        subRes.push_back(1);
        for (int j = 1; j < i; j++)
        {
            subRes.push_back(result[i - 1][j - 1] + result[i - 1][j]);
        }
        subRes.push_back(1);
        result.push_back(subRes);
    }
    return result;
}

