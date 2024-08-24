// Number of unique path in a grid
// Number of unique path in a grid with multiple jump
// Number of path if obstacle present
// Min Path sum
// Collect gold starting pt 1 column and move till cant move


//62
//yhaan dirn saari positive hain isliye no need to check >=0 agr ek bhi negative then check it.
int mazepath(int sr, int sc, int er, int ec, vector<vector<int>> &dp)
{
    if (dp[sr][sc] != -1)
        return dp[sr][sc];

    if (sr == er && sc == ec)
    {
        return dp[sr][sc] = 1; //jb bhi last waale cell ko reach krenge tab path count hoga...jb jb recurive call yahn pohchengi return 1
    }

    int count = 0;
    if (sc + 1 <= ec)
        count += mazepath(sr, sc + 1, er, ec, dp); //+ lgao ya na lgao mrzi
    if (sr + 1 <= er)
        count += mazepath(sr + 1, sc, er, ec, dp);
    if (sr + 1 <= er && sc + 1 <= ec)
        count += mazepath(sr + 1, sc + 1, er, ec, dp);

    return dp[sr][sc] = count;
}

int mazepathDIR(int sr, int sc, int er, int ec, vector<vector<int>> &dp, vector<vector<int>> &dir)
{
    if (sr == er && sc == ec)
        return dp[sr][sc] = 1;

    if (dp[sr][sc] != -1)
        return dp[sr][sc];

    int count = 0;
    for (int i = 0; i < dir.size(); i++)
    {
        if (sr + dir[i][0] <= er && sc + dir[i][1] <= ec)
            count += mazepathDIR(sr + dir[i][0], sc + dir[i][1], er, ec, dp, dir);
    }

    return dp[sr][sc] = count;
}

int mazepath_dp(int SR, int SC, int er, int ec, vector<vector<int>> &dp)
{
    for (int sr = er; sr >= 0; sr--) 
    {
        for (int sc = ec; sc >= 0; sc--)
        {
            if (sr == er && sc == ec)
            {
                dp[sr][sc] = 1;
                continue;
            }

            int count = 0;
            if (sr + 1 <= er)
                count += dp[sr + 1][sc];
            if (sc + 1 <= ec)
                count += dp[sr][sc + 1];
            if (sr + 1 <= er && sc + 1 <= ec)
                count += dp[sr + 1][sc + 1];
            dp[sr][sc] = count;
        }
    }
    return dp[SR][SC];
}

int mazepath_multiple(int sr, int sc, int er, int ec, vector<vector<int>> &dp)
{
    if (sr == er && sc == ec)
        return dp[sr][sc] = 1;

    if (dp[sr][sc] != -1)
        return dp[sr][sc];

    int count = 0;
    for (int jump = 1; sc + jump <= ec; jump++)
        count += mazepath_multiple(sr, sc + jump, er, ec, dp);
    for (int jump = 1; sr + jump <= er; jump++)
        count += mazepath_multiple(sr + jump, sc, er, ec, dp);
    for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
        count += mazepath_multiple(sr + jump, sc + jump, er, ec, dp);

    return dp[sr][sc] = count;
}

int mazepath_multipleDIR(int sr, int sc, int er, int ec, vector<vector<int>> &dp, vector<vector<int>> &dir, int totalJump)
{
    //totalJump = er > ec ? er - 1 : ec - 1;
    if (sr == er && sc == ec)
        return dp[sr][sc] = 1;

    if (dp[sr][sc] != -1)
        return dp[sr][sc];

    int count = 0;
    for (int i = 0; i < dir.size(); i++)
    {
        for (int jump = 1; jump <= totalJump; jump++)
        {
            int xjump = jump * dir[i][1];
            int yjump = jump * dir[i][0];
            if (sr + yjump <= er && sc + xjump <= ec)
                count += mazepath_multipleDIR(sr + yjump, sc + xjump, er, ec, dp, dir, totalJump);
            else break;
        }
    }
    return dp[sr][sc] = count;
}

int mazepath_multipleDP(int SR, int SC, int er, int ec, vector<vector<int>> &dp)
{
    for (int sr = er; sr >= 0; sr--)
    {
        for (int sc = ec; sc >= 0; sc--)
        {
            if (sr == er && sc == ec)
            {
                dp[sr][sc] = 1;
                continue;
            }
            int count = 0;
            for (int jump = 1; sc + jump <= ec; jump++)
                count += dp[sr][sc + jump];
            for (int jump = 1; sr + jump <= er; jump++)
                count += dp[sr + jump][sc];
            for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
                count += dp[sr + jump][sc + jump];
                
            dp[sr][sc] = count;
        }
    }
    return dp[SR][SC];
}

//63-1 obstacle
int mazepath_obstacle(int sr, int sc, int er, int ec, vector<vector<int>> &dp,vector<vector<int>> &obstacleGrid)
{
    if (sr == er && sc == ec)
        return 1;

    if (dp[sr][sc] != -1)
        return dp[sr][sc];

    int count = 0;
    if (sc + 1 <= ec && obstacleGrid[sr][sc+1]!=1)
        count += mazepath_obstacle(sr, sc + 1, er, ec, dp,obstacleGrid);
    if (sr + 1 <= er && obstacleGrid[sr+1][sc]!=1)
        count += mazepath_obstacle(sr + 1, sc, er, ec, dp,obstacleGrid);

    return dp[sr][sc] = count;
}

int mazepath_obstacleDP(int SR, int SC, int er, int ec, vector<vector<long>> &dp, vector<vector<int>> &obstacleGrid)
{
    for (int sr = er; sr >= 0; sr--)
    {
        for (int sc = ec; sc >= 0; sc--)
        {
            if (sr == er && sc == ec)
            {
                    dp[sr][sc] = 1;
                    continue;
            }

            long count = 0;
            if (sc + 1 <= ec && obstacleGrid[sr][sc + 1] != 1)
                count += dp[sr][sc + 1];
            if (sr + 1 <= er && obstacleGrid[sr + 1][sc] != 1)
                count += dp[sr + 1][sc];

            dp[sr][sc] = count;
        }
    }
    return (int)dp[SR][SC];
}

int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
    
    int sr = 0,sc = 0, er = obstacleGrid.size(),ec = obstacleGrid[0].size();
    if(obstacleGrid[er-1][ec-1]==1 || obstacleGrid[0][0]==1 )return 0;
    vector<vector<int>> dp(er, vector<int>(ec, -1));
    return mazepath_obstacle(sr, sc, er - 1, ec - 1, dp, obstacleGrid);
}

//64
int minpathsum(int sr, int sc, int er, int ec, vector<vector<int>> &grid, vector<vector<int>> &dp)
{
    if (sr == er && sc == ec)
        return dp[sr][sc] = grid[sr][sc];

    if (dp[sr][sc] != 1e8)
        return dp[sr][sc];

    if (sc + 1 <= ec)
        dp[sr][sc] = min(dp[sr][sc], minpathsum(sr, sc + 1, er, ec, grid, dp));
    if (sr + 1 <= er)
        dp[sr][sc] = min(dp[sr][sc], minpathsum(sr + 1, sc, er, ec, grid, dp));

    return dp[sr][sc] += grid[sr][sc];
}

int minpathsumDP(int SR, int SC, int er, int ec, vector<vector<int>> &grid, vector<vector<int>> &dp)
{
    for (int sr = er; sr >= 0; sr--)
    {
        for (int sc = ec; sc >= 0; sc--)
        {
            if (sr == er && sc == ec)
            {
                dp[sr][sc] = grid[sr][sc];
                continue;
            }

            if (sc + 1 <= ec)
                dp[sr][sc] = min(dp[sr][sc], dp[sr][sc + 1]);
            if (sr + 1 <= er)
                dp[sr][sc] = min(dp[sr][sc], dp[sr + 1][sc]);

            dp[sr][sc] += grid[sr][sc];
        }
    }
    return dp[SR][SC];
}

void mp()
{
    // int sr = 0, sc = 0, er = 3, ec = 7;
    // vector<vector<int>> dp(er, vector<int>(ec, -1));
    // vector<vector<int>> dir{{0, 1}, {1, 1}, {1, 0}};
    // cout << mazepath(sr, sc, er - 1, ec - 1, dp) << endl;
    // cout << mazepathDIR(sr, sc, er - 1, ec - 1, dp, dir) << endl;
    // cout << mazepath_dp(sr, sc, er - 1, ec - 1, dp) << endl;
    // cout << mazepath_multiple(sr, sc, er - 1, ec - 1, dp) << endl;
    
    // *****int totalJump = er > ec ? er - 1 : ec - 1;

    // cout << mazepath_multipleDIR(sr, sc, er - 1, ec - 1, dp, dir, totalJump) << endl;
    // print2d(dp);
    // cout << mazepath_multipleDP(sr, sc, er - 1, ec - 1, dp) << endl;

    // vector<vector<int>> obstacleGrid = {{0, 0, 0},
    //                                     {0, 1, 0},
    //                                     {0, 0, 0}};
    // vector<vector<int>> obstacleGrid = {{1}};
    // vector<vector<int>> obstacleGrid = {{1, 0}};
    // int sr = 0, sc = 0, er = obstacleGrid.size(), ec = obstacleGrid[0].size();
    // vector<vector<int>> dp(er, vector<int>(ec, -1));
    // cout << mazepath_obstacle(sr, sc, er - 1, ec - 1, dp, obstacleGrid) << endl;
    // cout << mazepath_obstacleDP(sr, sc, er - 1, ec - 1, dp, obstacleGrid) << endl;
    // print2d(dp);

    // vector<vector<int>> grid = {{1, 3, 0},
    //                             {1, 5, 1},
    //                             {4, 2, 1}};
    // int sr = 0, sc = 0, er = grid.size(), ec = grid[0].size();  
    
    //*******if grid.size()==0 || grid[0].size() return 0;

    // vector<vector<int>> dp(er, vector<int>(ec, 1e8));
    // cout << minpathsum(0, 0, er - 1, ec - 1, grid, dp) << endl;
    // print2d(dp);
    // cout << minpathsumDP(0, 0, er - 1, ec - 1, grid, dp) << endl;
}

//https://practice.geeksforgeeks.org/problems/gold-mine-problem2608/1#
//agr sr==er && sc==ec tou base case end mein bnega...blki base case bnna whin chahiye jahan col khtm
int goldmine(int sr, int sc, int er, int ec, vector<vector<int>> &grid, vector<vector<int>> &dp)
{
    if (sc == ec) 
        return dp[sr][sc] = grid[sr][sc];

    if (dp[sr][sc] != 0)
        return dp[sr][sc];

    if (sc + 1 <= ec)
        dp[sr][sc] = max(dp[sr][sc], goldmine(sr, sc + 1, er, ec, grid, dp)); 

    if (sr - 1 <= er && sc + 1 <= ec && sr - 1 >= 0)
        dp[sr][sc] = max(dp[sr][sc], goldmine(sr - 1, sc + 1, er, ec, grid, dp));

    if (sr + 1 <= er && sc + 1 <= ec)
        dp[sr][sc] = max(dp[sr][sc], goldmine(sr + 1, sc + 1, er, ec, grid, dp));

    return dp[sr][sc] += grid[sr][sc];
}

int goldmineDP(int er, int ec, vector<vector<int>> &grid, vector<vector<int>> &dp)
{
    for (int sc = ec; sc >= 0; sc--)
    {
        for (int sr = er; sr >= 0; sr--)
        {
            if (sc == ec) 
            {
                dp[sr][sc] = grid[sr][sc];
                continue;
            }

            if (sc + 1 <= ec)
                dp[sr][sc] = max(dp[sr][sc], dp[sr][sc + 1]);

            if (sr - 1 <= er && sc + 1 <= ec && sr - 1 >= 0)
                dp[sr][sc] = max(dp[sr][sc], dp[sr - 1][sc + 1]);

            if (sr + 1 <= er && sc + 1 <= ec)
                dp[sr][sc] = max(dp[sr][sc], dp[sr + 1][sc + 1]);

            dp[sr][sc] += grid[sr][sc];
        }
    }
    
    int maxVal = -1e9;
    for (int r = 0; r <= er; r++)
    {
        maxVal = max(maxVal, dp[r][0]);
    }
    return maxVal;
}

void goldmineproblem()
{
    // vector<vector<int>> grid = {{10, 33, 13, 15},
    //                             {22, 21, 04, 1},
    //                             {5, 0, 2, 3},
    //                             {0, 6, 14, 2}};
    // vector<vector<int>> grid = {{1, 3, 1, 5},
    //                             {2, 2, 4, 1},
    //                             {5, 0, 2, 3},
    //                             {0, 6, 1, 2}};
    vector<vector<int>> grid = {{1,2},{3,4}};
    int sc = 0, er = grid.size(), ec = grid[0].size();
    vector<vector<int>> dp(er, vector<int>(ec, 0));
    // int res=-1e9;
    // for (int sr = 0; sr <= er - 1; sr++)
    //     res = max(res,goldmine(sr, sc, er - 1, ec - 1, grid, dp));
    // cout<<res;


    cout<<goldmineDP(er-1,ec-1,grid,dp);cout<<endl; //tabulation mein tou har point kelie calculate hoga but recursion mein agr first row first column kelie calculate horha hoga tou second row first column kbhi pahunch ni skta
    print2d(dp);
}
