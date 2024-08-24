#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

string s1 = "send";
string s2 = "more";
string s3 = "money";
string s4 = s1 + s2 + s3;
vector<int> mapping(26, -1);

//1307
// vector<bool> canNumberbeUsed(10, false);
bool canNumberbeUsed(int value)
{
    for (int i = 0; i < mapping.size(); i++)
    {
        if (value == mapping[i])
            return false;
    }
    return true;
}

int decoding(string str)
{
    int num = 0;
    for (int i = 0; i < str.length(); i++)
    {
        num = num * 10 + mapping[str[i] - 'a'];
    }
    return num;
}

int Cryptoarithmetic(int idx)
{
    if (idx == s4.length())
    {
        int num1 = decoding(s1);
        int num2 = decoding(s2);
        int num3 = decoding(s3);
        // if (num1 + num2 == num3 && mapping[s1[0]-'a']!=0 && mapping[s2[0]-'a']!=0 && mapping[s3[0]-'a']!=0)
        if (num1 + num2 == num3)
        {
            cout << to_string(num1) << endl;
            cout << "+" + to_string(num2) << endl;
            cout << "-----" << endl;
            cout << num3 << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    for (int i = 0; i < 10; i++)
    {
        char ch = s4[idx];
        if ((ch == s1[0] || ch == s2[0] || ch == s3[0]) && i == 0)
            continue;
        // if (!canNumberbeUsed[i])

        if (canNumberbeUsed(i))
        {
            mapping[ch - 'a'] = i;
            // canNumberbeUsed[i] = true;   //chal ye canNumberbeused ka fn bnakr bhi jaayga but then time complexity compromise hogi aur agr canNumberbeused ka hum array bnaye tou space....agr hum bina duplicated htaye krte hai tou ek character ki mapping har baar change hoti jaaygi...we want to fix and then check if s1+S2==s3
            count += Cryptoarithmetic(idx + 1);
            mapping[ch - 'a'] = -1;
            // canNumberbeUsed[i] = false;
        }
    }
    return count;
}

void cryptoArith()
{
    int uniqueCharacter = 0;
    for (int i = 0; i < s4.length(); i++)
        uniqueCharacter |= (1 << (s4[i] - 'a'));  //on krdiya
    s4 = "";
    for (int i = 0; i < 26; i++)
    {
        int mask = (1 << i);
        if ((uniqueCharacter & mask) != 0)
            s4 += (char)(i + 'a');    //s4 string of unique char
    }
    cout << Cryptoarithmetic(0) << endl;
}

// nQueen Problem(4)
public static boolean isSafeToPlace(boolean[][] boxes, int n, int presentRow, int presentColumn, int dirs[][]) {
    for (int d = 0; d < dirs.length; d++) {
        for (int rad = 1; rad < n; rad++) {
            int r = presentRow + rad * dirs[d][0];
            int c = presentColumn + rad * dirs[d][1];
            if (r >= 0 && r < n && c >= 0 && c < n) {
                if (boxes[r][c] == true) {
                    return false;
                }
            } else
                break; // cant use break if we move breadth wise here we are moving depthWise.
        }
    }
    return true;
}

public static int nQueenProb(int queenPlaced, int totalQueen, String ans, int n, int idx, boolean[][] boxes,int[][] dirs) {
    if (queenPlaced == totalQueen) {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    for (int i = idx; i < n * n; i++) {
        int r = i / n;
        int c = i % n;
        if (isSafeToPlace(boxes, n, r, c, dirs)) {
            boxes[r][c] = true;
            count += nQueenProb(queenPlaced + 1, totalQueen, ans + "(" + r + "," + c + ")", n, i + 1, boxes, dirs);
            boxes[r][c] = false;
        }
    }
    return count;

    // int dirs[][] = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
    // System.out.println(nQueenProb(0, 4, "", 4, 0, visited,dirs));
}

public static int nQueenProbPermutation(int queenPlaced, int totalQueen, String ans, int n, boolean[][] boxes,
        int[][] dirs) {
    if (queenPlaced == totalQueen) {
        System.out.println(ans);
        return 1;
        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < n; j++) {
        // if (boxes[i][j] != '-')
        // System.out.print("q" + boxes[i][j] + "\t");
        // else
        // System.out.print("-\t");
        // }
        // System.out.println();
        // }
        // System.out.println();
    }
    int count = 0; 
    for (int i = 0; i < n * n; i++) {
        int r = i / n;
        int c = i % n;
        if (boxes[r][c] == false && isSafeToPlace(boxes, n, r, c, dirs)) {
            boxes[r][c] = true;
            // boxes[r][c] = (char) ('0' + queenPlaced);
            count += nQueenProbPermutation(queenPlaced + 1, totalQueen, ans + "(" + r + "," + c + ")", n, boxes,dirs);
            boxes[r][c] = false;
            // boxes[r][c] = '-';
        }
    }
    return count;
    // int dirs1[][] = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } , { 0, 1 }, {
    // 1, 1 }, { 1, 0 }, { 1, -1 }};
    // System.out.println(nQueenProbPermutation(0,4, "", 4,visited,dirs1));
}

static boolean rowArray[]; // yhan pe islie initialise nhi krrhe kyunki user se n ki value input lenge then initialse
static boolean columnArray[];
static boolean diagonalArray[];
static boolean antiDiagonalArray[];

public static void toggleArray(int r, int c, int n) {
    rowArray[r] = !rowArray[r];
    columnArray[c] = !columnArray[c];
    diagonalArray[r - c + n - 1] = !diagonalArray[r - c + n - 1];
    antiDiagonalArray[r + c] = !antiDiagonalArray[r + c];
}

//comb
public static int nQueenProb02(int queenPlaced, int totalQueen, String ans, int n, int idx) {
    if (queenPlaced == totalQueen) {
        System.out.println(ans);
        return 1;
        // System.out.println(ans + "."); //pep pr
    }
    int count = 0;
    for (int i = idx; i < n * n; i++) {
        int r = i / n;
        int c = i % n;
        if (!rowArray[r] && !columnArray[c] && !diagonalArray[r - c + n - 1] && !antiDiagonalArray[r + c]) {
            toggleArray(r, c, n);
            count += nQueenProb02(queenPlaced + 1, totalQueen, ans + "(" + r + "," + c + ")", n, i + 1);
            toggleArray(r, c, n);
        }
    }
    return count;
}

//perm
public static int nQueenProb03(int queenPlaced, int totalQueen, String ans, int n, int[][] visited) {
    if (queenPlaced == totalQueen) {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    for (int i = 0; i < n * n; i++) {
        int r = i / n;
        int c = i % n;
        if (visited[r][c] == 0 && !rowArray[r] && !columnArray[c] && !diagonalArray[r - c + n - 1]
                && !antiDiagonalArray[r + c]) {
            visited[r][c] = 1;
            toggleArray(r, c, n);
            count += nQueenProb03(queenPlaced + 1, totalQueen, ans + "(" + r + "," + c + ")", n, visited);
            toggleArray(r, c, n);
            visited[r][c] = 0;
        }
    }
    return count;
}

//using loop only on col
public static int nQueen04Optimization(int row, int totalQueen, int n, String res) {
    if (totalQueen == 0) {
        System.out.println(res);
        return 1;
    }
    int count = 0;
    for (int c = 0; c < n; c++) {
        if (!rowArray[row] && !columnArray[c] && !diagonalArray[row - c + n - 1] && !antiDiagonalArray[row + c]) // here rowArray not reqd because we move to next row in each iteration.
        {
            toggleArray(row, c, n);
            count += nQueen04Optimization(row + 1, totalQueen - 1, n, res + "(" + row + "," + c + ") ");
            toggleArray(row, c, n);
        }
    }
    return count;
}

// 0010110101101011010110101101-0100 right shift by 4
// 0000-0010110101101011010110101101

// The left shift and right shift operators should not be used for negative numbers.
static int column = 0;
static int diagonal = 0;
static int antiDiagonal = 0;

public static void toggleBits(int r, int c, int n) {
    column ^= (1 << c);
    diagonal ^= (1 << (r - c + n - 1));
    antiDiagonal ^= (1 << (r + c));
}

// we can use bit upto 64 bit because long is of 8 bytes.
// List<String> smallAns = new ArrayList<>(); //Q:51-distinct answer dene hai
// List<List<String>> res = new ArrayList<>();
// Q:52-count btana hai total answer ka
public static int nQueen05Bits(int row, int totalQueen, int n, String res) {
    if (totalQueen == 0) {
        System.out.println(res);
        return 1;
        // ArrayList<String> base = new ArrayList<>(smallAns);
        // res.add(base);
    }
    int count = 0;
    for (int c = 0; c < n; c++) {
        if ((column & (1 << c)) == 0 && (diagonal & (1 << (row - c + n - 1))) == 0
                && (antiDiagonal & (1 << (row + c))) == 0) {
            toggleBits(row, c, n);
            count += nQueen05Bits(row + 1, totalQueen - 1, n, res + "(" + row + "," + c + ") ");
            toggleBits(row, c, n);

            // String str = ""; //51
            // for (int i = 0; i < c; i++)
            // str += ".";
            // str += "Q";
            // for (int i = c + 1; i < n; i++)
            // str += ".";
            // smallAns.add(str);
            // toggleBits(row, c, n);
            // nQueen05Bits(row + 1, totalQueen - 1, n);
            // toggleBits(row, c, n);
            // smallAns.remove(smallAns.size() - 1);
        }
    }
    return count;

    // int n=4;
    // rowArray=new boolean[n];
    // columnArray=new boolean[n];
    // diagonalArray=new boolean[n+n-1];
    // antiDiagonalArray=new boolean[n+n-1];
    // System.out.println(nQueenProb02(0, 4, "", n,0));
    // int visited[][]=new int[n][n];
    // System.out.println(nQueenProb03(0, 4, "", n,visited));
    // System.out.println(nQueen04Optimization(0, 4,4, ""));
    // System.out.println(nQueen05Bits(0, 4,4, ""));
}

// 79
public boolean wordSearch(char[][] board, String word, int idx, int[][] dirs, int x, int y) {
    if (idx == word.length())
        return true;

    if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] == '#'
            || board[x][y] != word.charAt(idx))
        return false; // if(word.equals(ans)) this takes time

    char ch = board[x][y];
    board[x][y] = '#';
    for (int d = 0; d < dirs.length; d++) {
        int r = x + dirs[d][0];
        int c = y + dirs[d][1];

        boolean res = wordSearch(board, word, idx + 1, dirs, r, c);
        if (res)return res;
    }

    board[x][y] = ch;
    return false;
}

public boolean exist(char[][] board, String word) {
    int n = board.length;
    int m = board[0].length;
    int[][] dirs = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (board[i][j] == word.charAt(0)) {
                boolean ans = wordSearch(board, word, 0, dirs, i, j);
                if (ans)return ans;
            }
        }
    }
    return false;
}

// knight Tour-https://www.pepcoding.com/resources/online-java-foundation/recursion-backtracking/knights-tour-official/ojquestion
static int directionKnight[][] = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 },{ -2, -1 } };

public static void displayBoard(int[][] chess) {
    for (int i = 0; i < chess.length; i++) {
        for (int j = 0; j < chess[0].length; j++) {
            System.out.print(chess[i][j] + " ");
        }
        System.out.println();
    }
    System.out.println();
}

public static void printKnightsTour(int col, int row, int n, int visited[][], int count) {
    if (count == n * n) {
        visited[row][col] = count;
        displayBoard(visited);
        visited[row][col] = 0;
    }

    visited[row][col] = count;
    for (int i = 0; i < directionKnight.length; i++) {
        int r = row + directionKnight[i][0];
        int c = col + directionKnight[i][1];

        if (r >= 0 && c >= 0 && r < n && c < n && visited[r][c] == 0) {
            // visited[row][col]=count;
            printKnightsTour(c,r,n,visited,count+1);
            // visited[row][col]=0;
        }
    }
    visited[row][col] = 0;

    // Scanner sc=new Scanner(System.in);
    // int n=sc.nextInt();
    // int row=sc.nextInt();
    // int col=sc.nextInt();
    // int visited[][]=new int[n][n];
    // printKnightsTour(col,row,n,visited,1);
}

public static boolean IsKnightSafe(boolean[][] chess, int i, int j, int[][] dirn) {
    // if(chess[i][j]==true)return false;

    // boolean res=true;
    // for(int d=0;d<dirn.length;d++)
    // {
    // int x=i+dirn[d][0];
    // int y=j+dirn[d][1];
    // if(x>=0 && x<chess.length && y>=0 && y<chess.length)
    // res=IsKnightSafe(chess,x, y,dirn);
    // if(res==false)return res;
    // }

    // return true;

    if (i - 1 >= 0 && j - 2 >= 0 && chess[i - 1][j - 2])  //only check 4 dirn
        return false;
    if (i - 2 >= 0 && j - 1 >= 0 && chess[i - 2][j - 1])
        return false;
    if (i - 2 >= 0 && j + 1 < chess.length && chess[i - 2][j + 1])
        return false;
    if (i - 1 >= 0 && j + 2 < chess.length && chess[i - 1][j + 2])
        return false;

    return true;
}

public static void nknights(int kpsf, int tk, boolean[][] chess, int lcno, int[][] dirn) {
    if (kpsf == tk) {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess.length; j++) {
                if (chess[i][j] == true)
                    System.out.print("k	");
                else
                    System.out.print("-	");
            }
            System.out.println();
        }
        System.out.println();
        return;
    }

    for (int i = lcno; i < chess.length * chess.length; i++) {
        int r = i / chess.length;
        int c = i % chess.length;

        if (IsKnightSafe(chess, r, c, dirn)) {
            chess[r][c] = true;
            nknights(kpsf + 1, tk, chess, i + 1, dirn);
            chess[r][c] = false;
        }
    }
}

//CrossWord
public static void print(char[][] arr) {
    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr[0].length; j++) {
            System.out.print(arr[i][j]);
        }
        System.out.println();
    }
}

public static boolean canWePlaceHorizontally(char[][] arr, int i, int j, String word) {
        if(j-1>=0 && arr[i][j-1]!='+') //hum jahan place krrhe h uske saath mein empty space nhi chahiye aur koi character bhi ni chahiye...+ chahiye...2 case handle hue ya tou left side ho hi naa aur agr hai tou + ho. 
        return false;
        if(j+word.length()<arr[0].length && arr[i][j+word.length()]!='+') //agar right side h tou total column se km and + hona chahiye 
        return false;  

    for (int jj = 0; jj < word.length(); jj++) // jj ko j se ni chla skte because agr maanlo j ki value 4 h aur word ki length 5 h tou loop ekbaar chlega bs jbki chalna puri length tak tha.
    {
        if (j + jj >= arr[0].length)
            return false; // character daalrhe h pr daalte daalte bahaar na nikl jaaye
        // if(arr[i][jj+j]!='-' && arr[i][jj+j]!=word.charAt(jj))
        // return false;
        if (arr[i][jj + j] == '-' || arr[i][jj + j] == word.charAt(jj))
            continue;
        else return false;
    }
    return true;
}

public static boolean canWePlaceVertically(char[][] arr, int i, int j, String word) {
    
    if(i-1>=0 && arr[i-1][j]!='+') return false; 
    if(i+word.length()<arr.length && arr[i+word.length()][j]!='+') return false;
        
    for (int ii = 0; ii < word.length(); ii++) {
        if (i + ii >= arr.length)
            return false;
        // if(arr[ii+i][j]!='-' && arr[ii+i][j]!=word.charAt(ii))
        if (arr[ii + i][j] == '-' || arr[ii + i][j] == word.charAt(ii))
            continue;
        return false;
    }
    return true;
}

public static boolean[] placeWordHorizonatally(char[][] arr, int i, int j, String word) {
    boolean[] wePlaced = new boolean[word.length()];
    for (int jj = 0; jj < word.length(); jj++) {
        if (arr[i][jj + j] == '-') {
            arr[i][jj + j] = word.charAt(jj);
            wePlaced[jj] = true;
        }
    }
    return wePlaced;
}

public static boolean[] placeWordVertically(char[][] arr, int i, int j, String word) {
    boolean[] wePlaced = new boolean[word.length()];
    for (int ii = 0; ii < word.length(); ii++) {
        if (arr[ii + i][j] == '-') // yaha pr ye isliye lagana pdrha hai kyunki hoskta hai jo hum key daalna chah rhe hain wo dusre word ne dali hui ho
        {
            arr[ii + i][j] = word.charAt(ii);
            wePlaced[ii] = true;
        }
    }
    return wePlaced;
}

public static void unplaceWordHorizontally(char[][] arr, int i, int j, boolean[] wePlaced) {
    for (int jj = 0; jj < wePlaced.length; jj++) {
        if (wePlaced[jj] == true) {
            arr[i][jj + j] = '-';
        }
    }
}

public static void unplaceWordVertically(char[][] arr, int i, int j, boolean[] wePlaced) {
    for (int ii = 0; ii < wePlaced.length; ii++) {
        if (wePlaced[ii] == true) {
            arr[ii + i][j] = '-';
        }
    }
}

public static void solution(char[][] arr, String[] words, int vidx) {  //this program print all psble soln
    if (vidx == words.length) {
        print(arr);
        return;
    }
    String word = words[vidx];
    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr[0].length; j++) {
            if (arr[i][j] == '-' || arr[i][j] == word.charAt(0)) {
                if (canWePlaceHorizontally(arr, i, j, word)) {
                    boolean wePlaced[] = placeWordHorizonatally(arr, i, j, word);
                    solution(arr, words, vidx + 1);
                    unplaceWordHorizontally(arr, i, j, wePlaced);
                }

                if (canWePlaceVertically(arr, i, j, word)) {
                    boolean wePlaced[] = placeWordVertically(arr, i, j, word);
                    solution(arr, words, vidx + 1);
                    unplaceWordVertically(arr, i, j, wePlaced);
                }
            }
        }
    }
    // char[][] arr = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            //         { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            //         { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            //         { '+', '-', '-', '-', '-', '-', '+', '+', '+', '+' },
            //         { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
            //         { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
            //         { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
            //         { '+', '+', '-', '-', '-', '-', '-', '-', '+', '+' },
            //         { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
            //         { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' } };

    // String[] words = { "LONDON", "DELHI", "ICELAND", "ANKARA" };
    // solution(arr, words, 0);
}

//301
public int getMin(String s)
{
    Stack<Character>st=new Stack<>();
    int count=0;
    for(int i=0;i<s.length();i++)
    {
        char ch=s.charAt(i);
        if(ch=='(')
            st.push(ch);
        else if(ch==')'){
            if(st.size()>0)
                st.pop();
            else count++;
        }
    }
    count+=st.size();
    return count;
}

public void removeInvalid_helper(String s,int minremoval,HashSet<String>hs,HashSet<String>visited)
{
    if(minremoval==0)
    {
        if(!hs.contains(s) && getMin(s)==0)
            hs.add(s);
        return;
    }
    
    if(visited.contains(s))
        return;
    visited.add(s);
    for(int i=0;i<s.length();i++)
    {
        if(s.charAt(i)=='(' || s.charAt(i)==')')
        {
            String str=s.substring(0,i)+s.substring(i+1);
            removeInvalid_helper(str,minremoval-1,hs,visited);
        }
    }
}

public List<String> removeInvalidParentheses(String s) {
    HashSet<String>hs=new HashSet<>();
    HashSet<String>visited=new HashSet<>();
    int minremoval=getMin(s);
    removeInvalid_helper(s,minremoval,hs,visited);
    
    List<String>ans=new ArrayList<>();
    for(String str:hs)
        ans.add(str);
    return ans;
}

// search krne me problem aaegi array mein…. ..hashset me to map.contains() krke ho jaataa h easily
public static int wordBreak(String str, String ans, HashSet<String> dict, int idx) { 
    if (idx == str.length()) {
        System.out.println(ans);
        return 1;
    }

    int count = 0;
    for (int i = idx + 1; i <= str.length(); i++) {
        String word = str.substring(idx, i);
        if (dict.contains(word)) 
            count += wordBreak(str, ans + word + " ", dict, i);
    }
    return count;

    // HashSet<String>dict=new HashSet<>();
    // dict.add("i");dict.add("like");dict.add("sam");dict.add("sung");dict.add("samsung");dict.add("mobile");dict.add("ice");dict.add("cream");dict.add("icecream");dict.add("man");dict.add("go");dict.add("mango");dict.add("ilike");
    // wordBreak("ilikesamsung", "", dict, 0);
}

// https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/largest-number-at-most-k-swaps-official/ojquestion]
static String max;
public static String swap(String str, int i, int j) {
    char ith = str.charAt(i);
    char jth = str.charAt(j);
    String left = str.substring(0, i);
    String middle = str.substring(i + 1, j);
    String right = str.substring(j + 1);

    return left + jth + middle + ith + right;
}

public static void findMaximum(String str, int k) {
    if (Integer.parseInt(str) > Integer.parseInt(max)) {
        max = str;
    }
    if (k == 0) {
        return;
    }

    for (int i = 0; i < str.length() - 1; i++) {
        for (int j = i + 1; j < str.length(); j++) {
            str = swap(str, i, j);
            findMaximum(str, k - 1);
            str = swap(str, i, j);
        }
    }
    // max = str;
    // findMaximum(str, k);
}

//https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/all-palindromic-partitions-official/ojquestion
public static boolean isPalindrome(String str) {
    int i = 0, j = str.length() - 1;
    while (i < j) {
        char left = str.charAt(i);
        char right = str.charAt(j);

        if (left != right)
            return false;
        i++;
        j--;
    }

    return true;
}

public static void palindromicPartition(String str, String asf) {

    if (str.length() == 0) {
        System.out.println(asf);
        return;
    }

    for (int i = 0; i < str.length(); i++) {
        String cut = str.substring(0, i + 1);
        String ros = str.substring(i + 1);
        if (isPalindrome(cut)) {
            palindromicPartition(ros, asf + "(" + cut + ")" + " ");
        }
    }
}

//22
public void generateParenthesis_helper(int n,int open,int close,StringBuilder sb,List<String>ans)
{
    if(open+close==2*n)
    {
        ans.add(sb.toString());
        return;
    }
        
    if(open<n)  //at any time open are always more than close
    {
        sb.append("(");
        generateParenthesis_helper(n,open+1,close,sb,ans);
        sb.deleteCharAt(sb.length()-1);
    }

    if(close<open)
    {
        sb.append(")");
        generateParenthesis_helper(n,open,close+1,sb,ans);
        sb.deleteCharAt(sb.length()-1);
    }
}

public List<String> generateParenthesis(int n) {
    List<String>ans=new ArrayList<>();
    generateParenthesis_helper(n,0,0,new StringBuilder(),ans);
    return ans;
}

// 386
public static void dfs(int i, int n) {
    if (i > n)
        return;
    System.out.println(i);
    for (int j = 0; j < 10; j++) {
        dfs(i * 10 + j, n);
    }
}

public static void lexicographical_order(int n) {
    for (int i = 1; i < 10; i++)
        dfs(i, n);
}


//https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/k-partitions-official/ojquestion
// rssf:resultant set so far
public static void kPartition(int i, int n, int k, int rssf, ArrayList<ArrayList<Integer>> ans) {
    if (i > n) {
        if (rssf == k) {
            count++;
            System.out.print(count + ". ");
            for (int j = 0; j < ans.size(); j++) {
                System.out.print(ans.get(j) + " ");
            }
            System.out.println();
        }
        return;
    }

    for (int j = 0; j < ans.size(); j++) {
        if (ans.get(j).size() > 0) {
            ans.get(j).add(i);
            kPartition(i + 1, n, k, rssf, ans);
            ans.get(j).remove(ans.get(j).size() - 1);
        } else {
            ans.get(j).add(i);
            kPartition(i + 1, n, k, rssf + 1, ans);
            ans.get(j).remove(ans.get(j).size() - 1);
            break;
        }
    }

    // ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    // for(int i = 0; i < k; i++) {
    // ans.add(new ArrayList<>());
    // }
    // kPartition(1, n, k, 0, ans);
}

static int count = 0;
public static void kSubsetEqualSum(int[] arr, int idx, int n, int k, int[] subsetSum, int rssf,
        ArrayList<ArrayList<Integer>> ans) {
    if (idx == n) {
        if (rssf == k) {
            boolean flag = false;
            for (int j = 1; j < ans.size(); j++) {
                if (subsetSum[j] != subsetSum[j - 1]) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                for (int i = 0; i < ans.size(); i++)
                    System.out.print(ans.get(i) + " ");
                System.out.println();
            }
        }
        return;
    }

    for (int j = 0; j < ans.size(); j++) {
        if (ans.get(j).size() > 0) {
            ans.get(j).add(arr[idx]);
            subsetSum[j] += arr[idx];
            kSubsetEqualSum(arr, idx + 1, n, k, subsetSum, rssf, ans);
            subsetSum[j] -= arr[idx];
            ans.get(j).remove(ans.get(j).size() - 1);
        } else {
            ans.get(j).add(arr[idx]);
            subsetSum[j] += arr[idx];
            kSubsetEqualSum(arr, idx + 1, n, k, subsetSum, rssf + 1, ans);
            subsetSum[j] -= arr[idx];
            ans.get(j).remove(ans.get(j).size() - 1);
            break;
        }
    }

    // if k is equal to 1, then whole array is your answer
    // if(k == 1) {
    // System.out.print("[");
    // for(int i = 0 ; i < arr.length; i++) {
    // System.out.print(arr[i] + ", ");
    // }
    // System.out.println("]");
    // return;
    // }

    // //if there are more subsets than no. of elements in array or sum of all
    // elements is not divisible by k
    // if(k > n || sum % k != 0) {
    // System.out.println("-1");
    // return;
    // }

    // int[] subsetSum = new int[k]; //0th index 1st set ka sum contain kregi
    // ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    // for(int i = 0; i < k; i++) {
    // ans.add(new ArrayList<>());
    // }
}

static int mindiff = Integer.MAX_VALUE;
static String ans = "";
public static void tugOfWar(int[] arr, int vidx, ArrayList<Integer> set1, ArrayList<Integer> set2, int soset1,int soset2) {

    if (vidx == arr.length) {
        int delta = Math.abs(soset1 - soset2);
        if (delta < mindiff) {
            mindiff = delta;
            ans = set1 + " " + set2;
        }
        return;
    }

    if (set1.size() < (arr.length + 1) / 2) {
        set1.add(arr[vidx]);
        tugOfWar(arr, vidx + 1, set1, set2, soset1 + arr[vidx], soset2);
        set1.remove(set1.size() - 1);
    }

    if (set2.size() < (arr.length + 1) / 2) {
        set2.add(arr[vidx]);
        tugOfWar(arr, vidx + 1, set1, set2, soset1, soset2 + arr[vidx]);
        set2.remove(set2.size() - 1);
    }
}

//https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/magnets-official/ojquestion

//https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/words-kselection-3-official/ojquestion

//https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/words-kselection-4-official/ojquestion

//https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/wors-klength-words-3-official/ojquestion

//https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/wors-klength-words-3-official/ojquestion