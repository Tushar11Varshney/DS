//Sudoku: brute force, call only for reqd box, bits, using hashmap
// Subsequence(3), all subsets
// all subsets with array having duplicate
// KPC (2-9/1-9/1-11)

vector<vector<int>> boxes = {{3, 0, 0, 0, 0, 0, 0, 0, 0},
                             {5, 2, 0, 0, 0, 0, 0, 0, 0},
                             {0, 8, 7, 0, 0, 0, 0, 3, 1},
                             {0, 0, 3, 0, 1, 0, 0, 8, 0},
                             {9, 0, 0, 8, 6, 3, 0, 0, 5},
                             {0, 5, 0, 0, 9, 0, 6, 0, 0},
                             {1, 3, 0, 0, 0, 0, 2, 5, 0},
                             {0, 0, 0, 0, 0, 0, 0, 7, 4},
                             {0, 0, 5, 2, 0, 6, 3, 0, 0}};

void display()
{
    for (vector<int> row : boxes)
    {
        for (int x : row)
        {
            cout << x << " ";
        }
        cout << endl;
    }
}

bool isSafetoPlaceNumber(int r, int c, int num)  
// bool isSafetoPlaceNumber(int r, int c, char charNum, vector<vector<char>> &boxes)
{
    //column
    for (int i = 0; i < boxes[0].size(); i++)
    {
        if (num == boxes[r][i])
            return false;
    }

    //row
    for (int i = 0; i < boxes[0].size(); i++)
    {
        if (num == boxes[i][c])
            return false;
    }

    r = (r / 3) * 3;
    c = (c / 3) * 3;

    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (boxes[r + i][c + j] == num)
                return false;
        }
    }
    return true;
}

int sudokuSolver(int idx)
{
    if (idx == boxes[0].size() * boxes[0].size())
    {
        display();
        cout << endl;
        return 1;
    }

    int r = idx / boxes[0].size();
    int c = idx % boxes[0].size();
    int count = 0;
    if (boxes[r][c] != 0)
    {
        return sudokuSolver(idx + 1);
    }
    for (int i = 1; i < 10; i++)
    {
        if (isSafetoPlaceNumber(r, c, i))
        {
            boxes[r][c] = i;
            count += sudokuSolver(idx + 1);
            boxes[r][c] = 0;
        }   
    }
    return count;
}

// void sudokoquestion()[]
// {
//     for (int i = 0; i < boxes[0].size(); i++) //this approach of two for loop will not work because suppose in a matrix while filling the sudoku we get to the first row last element but in this no value can be filled so it has to backtrack but what it do in this is it increment j due to which it reaches to the next row as j is also on last column so row increases.
//     {
//         for (int j = 0; j < boxes[0].size(); j++)
//         {
//             if (boxes[i][j] == '.')
//             {
//                 for (int k = 0; k <= 9; k++)
//                 {
//                     if (isSafetoPlace())
//                     {
//                     }
//                 }
//             }
//         }
//     }
// }

//37
int sudoKuSolverOpti(vector<int> &indexList, int idx)
// bool sudoKuSolverOpti(vector<int>&indexList,int idx)
{
    if (idx == indexList.size())
    {
        display();
        cout << endl;
        return 1;
        // return true;
    }

    int r = indexList[idx] / boxes[0].size();
    int c = indexList[idx] % boxes[0].size();
    // bool res = false;
    int count = 0;
    for (int i = 1; i < 10; i++)
    {
        // char charNum = (char)('0' + i);
        if (isSafetoPlaceNumber(r, c, i))
        {
            // board[r][c] = charNum;
            boxes[r][c] = i;
            count += sudoKuSolverOpti(indexList, idx + 1);
            // res=res||sudoKuSolverOpti(indexList,idx+1);
            boxes[r][c] = 0;
            // board[r][c] = '.';
        }
    }
    // return res;
    return count;

    // vector<int> indexList;
    // for (int i = 0; i < boxes[0].size(); i++)
    // {
    //     for (int j = 0; j < boxes[0].size(); j++)
    //     {
    //         if (boxes[i][j] == 0)
    //         {
    //             indexList.push_back(i * boxes.size() + j);
    //         }
    //     }
    // }
    // cout << sudoKuSolverOpti(indexList, 0);
}

//Expected member declarator-The error reason is that the compiler cannot distinguish whether the statement is a member variable declaration or a member function declaration.//kyunki hume class mein dena hota h solution so intialise in a fn or constructor.
vector<int> row(9, 0);
vector<int> column(9, 0);
vector<vector<int>> compressedMatrix(3, vector<int>(3, 0));
void toggleBits(int r, int c, int n)
{
    int mask = 1 << n;
    row[r] ^= mask;
    column[c] ^= mask;
    compressedMatrix[r / 3][c / 3] ^= mask;
}

int sudoKuSolverBits(vector<int> &indexList, int idx)
{
    if (idx == indexList.size())
    {
        display();
        cout << endl;
        return 1;
    }

    int r = indexList[idx] / boxes[0].size();
    int c = indexList[idx] % boxes[0].size();
    int count = 0;
    for (int i = 1; i < 10; i++)
    {
        int mask = 1 << i;
        if ((row[r] & mask) == 0 && (column[c] & mask) == 0 && (compressedMatrix[r / 3][c / 3] & mask) == 0)
        {
            // boxes[r][c] = (char)('0' + i);
            boxes[r][c] = i;
            toggleBits(r, c, i);
            count += sudoKuSolverBits(indexList, idx + 1);
            boxes[r][c] = 0;
            // boxes[r][c] = '.';
            toggleBits(r, c, i);
        }
    }
    return count;
}

void sudokoBits()
{
    // row.resize(9, 0); 
    // column.resize(9, 0);
    // compressedMatrix.resize(3, vector<int>(3, 0));
    vector<int> indexList;
    for (int i = 0; i < boxes[0].size(); i++)
    {
        for (int j = 0; j < boxes[0].size(); j++)
        {
            if (boxes[i][j] == 0)
                indexList.push_back(i * boxes.size() + j);
            else
                toggleBits(i, j, boxes[i][j]); //yahan pe hum bina check kiye ki jis number ke liye hum set krrhe h vo already present h ya ni isliye krskte h kyunki question mein given h ki guaranteed answer hoga so no chance of repetition.
        }
    }
    cout << sudoKuSolverBits(indexList, 0);
}

//leetcode 36-valid sudoku
vector<int> row;
vector<int> column;
vector<vector<int>> compressedMatrix;
void toggleBits(int r, int c, int n)
{
    int mask = (1 << n);
    row[r] ^= mask;
    column[c] ^= mask;
    compressedMatrix[r / 3][c / 3] ^= mask;
}
bool sudokoBits(vector<vector<char>> &boxes)
{
    row.resize(9, 0);
    column.resize(9, 0);
    compressedMatrix.resize(3, vector<int>(3, 0));
    for (int r = 0; r < boxes[0].size(); r++)
    {
        for (int c = 0; c < boxes[0].size(); c++)
        {
            if (boxes[r][c] != '.')
            {
                int val = (char)(boxes[r][c] - '0');
                int mask = (1 << val);
                if ((row[r] & mask) == 0 && (column[c] & mask) == 0 && (compressedMatrix[r / 3][c / 3] & mask) == 0)
                    toggleBits(r, c, boxes[r][c] - '0');
                else
                    return false;
            }
        }
    }
    return true;
}

bool sudokoBitsHashMap(vector<vector<char>> &boxes)
{
    vector<unordered_set<int>> row(9);
    vector<unordered_set<int>> col(9);
    vector<vector<unordered_set<int>>> compressedMatrix(3, vector<unordered_set<int>>(3));
    for (int r = 0; r < boxes[0].size(); r++)
    {
        for (int c = 0; c < boxes[0].size(); c++)
        {
            if (boxes[r][c] != '.')
            {
                int val = boxes[r][c] - '0';
                if (row[r].find(val) == row[r].end() && col[c].find(val) == col[c].end() && compressedMatrix[r / 3][c / 3].find(val) == compressedMatrix[r / 3][c / 3].end())
                {
                    row[r].insert(val);
                    col[c].insert(val);
                    compressedMatrix[r / 3][c / 3].insert(val);
                }
                else
                    return false;
            }
        }
    }
    return true;
}

bool isValidSudoku(vector<vector<char>> &board)
{
    return sudokoBits(board);
}

// return type recursion
public static ArrayList<String> printSS(String str, int idx) { 
    if (idx == str.length()) {
        ArrayList<String> base = new ArrayList<>();
        base.add("");
        return base;
    }

    char ch = str.charAt(idx);
    ArrayList<String> smallAns = printSS(str, idx + 1);
    ArrayList<String> ans = new ArrayList<>();

    for (String str1 : smallAns) {
        ans.add(str1);
        ans.add(ch + str1);
    }
    return ans;
}

// void type recursion
public static int printSS_SubstringKaatkr(String ques, String ans) {
    if (ques.length() == 0) {
        System.out.println(ans);
        return 1;
    }

    int count = 0;
    count += printSS_SubstringKaatkr(ques.substring(1), ans + ques.charAt(0)); // substring kaatne mein o(n) ki jaan instead use index.agar string mein sirf ek hi character hai..and substring kaatni h 1 se then empty string is passed.
    count += printSS_SubstringKaatkr(ques.substring(1), ans);
    return count;
}

public static int printSS_SubstringKaatnaNotAllowed(String ques, int idx, String ans) {
    if (idx == ques.length()) {
        System.out.println(ans);
        return 1;
    }

    int count = 0;
    count += printSS_SubstringKaatnaNotAllowed(ques, idx + 1, ans + ques.charAt(idx));
    count += printSS_SubstringKaatnaNotAllowed(ques, idx + 1, ans);
    return count;
}

// 78
public void subSet(int[] nums, int idx, List<Integer> smallAns, List<List<Integer>> result) {
    if (idx == nums.length) {
        List<Integer> base = new ArrayList<>(smallAns);
        result.add(base);
        return;
    }

    smallAns.add(nums[idx]);
    subSet(nums, idx + 1, smallAns, result);
    smallAns.remove(smallAns.size() - 1);

    subSet(nums, idx + 1, smallAns, result);
}

public List<List<Integer>> subsets(int[] nums) { // subset also called power set
    List<List<Integer>> result = new ArrayList<>();
    subSet(nums, 0, new ArrayList<>(), result);
    return result;
}

//90
public void subsetsWithDup_helper(int []nums,List<Integer>smallAns,List<List<Integer>>ans,int idx)
{
    if(idx==nums.length)
        return;
    
    for(int i=idx;i<nums.length;i++)
    {
        if(i>idx && nums[i]==nums[i-1])continue;
        smallAns.add(nums[i]);

        List<Integer>smallAns_c=new ArrayList<>(smallAns);
        ans.add(smallAns_c);

        subsetsWithDup_helper(nums,smallAns,ans,i+1);
        smallAns.remove(smallAns.size()-1);
    }
    
}

public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>>ans=new ArrayList<>();
    ans.add(new ArrayList<>());  //empty subset
    subsetsWithDup_helper(nums,new ArrayList<>(),ans,0);
    return ans;
}

static String arr[] = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };
public static ArrayList<String> printKPC(String str, int idx) {
    if (idx == str.length()) {
        ArrayList<String> base = new ArrayList<>();
        base.add("");
        return base;
    }

    int digit = str.charAt(idx) - '0';
    ArrayList<String> smallAns = printKPC(str, idx + 1);
    ArrayList<String> ans = new ArrayList<>();

    String code = arr[digit];
    for (int i = 0; i < code.length(); i++)
    // for(char ch:code)// syntax-ye iterable nhi hai // ans.add(ch+str1);
    // String str="abcd";// // char ch=str[2];(not allowed)// char ch=str.charAt(2);
    {
        for (String str1 : smallAns)
            ans.add(code.charAt(i) + str1);
    }
    return ans;
}

// leetcode 17
static String arr2[] = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
public static ArrayList<String> printKPC_(String str, int idx) {
    if (idx == str.length()) {
        ArrayList<String> base = new ArrayList<>();
        base.add("");
        return base;
    }

    int digit = str.charAt(idx) - '0';
    ArrayList<String> smallAns = printKPC_(str, idx + 1);
    ArrayList<String> ans = new ArrayList<>();

    String code = arr2[digit];
    for (int i = 0; i < code.length(); i++) {
        for (String str1 : smallAns)
            ans.add(code.charAt(i) + str1);
    }
    return ans;
}

public List<String> letterCombinations(String digits) {
    ArrayList<String> res = new ArrayList<>();
    if (digits.length() == 0)
        return res;
    return printKPC(digits, 0);
}

static String codes1[] = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz", "+-*", "/%^" };
public static int KpcExtension(String str, int idx, String ans) {
    if (str.length() == idx) {
        System.out.println(ans);
        return 1;
    }
    int num = str.charAt(idx) - '0';
    String code = codes1[num];
    int count = 0;
    for (int i = 0; i < code.length(); i++)
        count += KpcExtension(str, idx + 1, ans + code.charAt(i));
    if (idx < str.length() - 1) {
        num = (str.charAt(idx) - '0') * 10 + (str.charAt(idx + 1) - '0');
        if (num == 10 || num == 11) {
            code = codes1[num];
            for (int i = 0; i < code.length(); i++)
                count += KpcExtension(str, idx + 2, ans + code.charAt(i));
        }
    }
    return count;
}


public static int JosephusProb(int n, int k) {
    if (n == 1)
        return 0;
    int x = JosephusProb(n - 1, k);
    int y = (x + k) % n;
    return y;
}

// 1823
public int findTheWinner_helper(int n, int k) {
    if (n == 1)
        return 0;
    int x = findTheWinner_helper(n - 1, k);
    int y = (x + k) % n;
    return y;
}

public int findTheWinner(int n, int k) {
    return findTheWinner_helper(n, k) + 1;
}

