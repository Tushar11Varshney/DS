// Convert column name to number
// Convert number to excel column name


//171
int titleToNumber(string columnTitle)
{
    int colNum = 0;
    for (int i = 0; i < columnTitle.length(); i++)
        colNum = colNum * 26 + (columnTitle[i] - 'A' + 1);
    return colNum;
}

//168
string convertToTitle(int n)
{
    string str = "";
    while (n > 0)
    {
        n--;
        int ch = n % 26;
        n = n / 26;
        str += (char)('A' + ch);
    }
    reverse(str.begin(), str.end());

    return str;
}

void isPrime(int n)
{
    if (n == 0)
        return ;
    int flag=0, j;
    for (j = 2; j * j <= n; j++)
    {
        if (n % j == 0)
        {
            flag = 1;
            break;
        }
    }
    if (flag == 0)
        cout<<"prime"<<endl;
    else 
    cout<<"not prime"<<endl;
}

//319
public int bulbSwitch(int n) {
    // if(n==0)return 0;  //smbhal jaayga
    // perfect square ke odd number of factor and whi on rhenge
    int count=0;
    for(int i=1;i<=n;i++)
    {
        if(i*i<=n)  //on pep print i*i
            count++;
        else break;
    }
    
    return count;
}

// 7,9 
public int reverse(int x) {
    int num = Math.abs(x);
    int reversed = 0;
    int result = 0;
    while (num != 0) {
        int r = num % 10;
        reversed = result * 10 + r;
        if ((reversed - r) / 10 != result) // do only which can fall in integer range
            return 0;
        result = reversed;
        num = num / 10;
    }

    return x < 0 ? result * (-1) : result;
}

public boolean isPalindrome(int x) {
    if (x < 0)
        return false; // -11 ulta pdne pr 11-
    int revNum = reverse(x);
    if (revNum == x)
        return true;
    return false;
}

//1492
public int kthFactor(int n, int k) {
    int arr[]=new int[n+1];
    for(int i=1;i*i<=n;i++)
    {
        if(n%i==0)
        {
            arr[i]=i;
            arr[n/i]=n/i;
        }
    }

    int res=0;
    for(int i=1;i<n+1;i++)
    {
        if(arr[i]==i)
        {
            res=arr[i];
            k--;
            if(k==0)return res;
        }
    }

    return -1;
}


