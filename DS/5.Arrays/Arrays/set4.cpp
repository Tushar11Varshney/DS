// Merge Interval  //56 gc
// Meeting room I //920  lintcode gc
// Interval intersection //986
// Insert Interval //57

//56
vector<vector<int>> merge(vector<vector<int>> &intervals)
{
    int n = intervals.size();
    if (n == 1)
        return intervals;

    //[[1,4],[0,0]] sort intervals else answer wrong
    sort(intervals.begin(), intervals.end(), [](vector<int> &a, vector<int> &b)
         { return a[0] < b[0]; });

    vector<vector<int>> ans;
    ans.push_back(intervals[0]);

    for (int i = 1; i < n; i++)
    {
        int li = ans.size();
        if (ans[li - 1][1] >= intervals[i][0])
            ans[li - 1][1] = max(intervals[i][1], ans[li - 1][1]);
        else
            ans.push_back(intervals[i]);
    }
    return ans;
}

//https://www.lintcode.com/problem/920/discuss
bool canAttendMeetings(vector<Interval> &intervals)
{
    int n = intervals.size();
    if (n == 1)
        return true;

    sort(intervals.begin(), intervals.end(), [](Interval &a, Interval &b)
         { return a.start < b.start; });

    for (int i = 1; i < n; i++)
    {
        if (intervals[i - 1].end > intervals[i].start)
            return false;
    }
    return true;
}

//986
void mergeInterval(int a, int b, int c, vector<vector<int>> &ans)
{
    vector<int> smallAns;
    if (a >= b)
        smallAns = {a, c};
    else
        smallAns = {b, c};

    ans.push_back(smallAns);
}

vector<vector<int>> intervalIntersection(vector<vector<int>> &firstList, vector<vector<int>> &secondList)
{
    vector<vector<int>> ans;
    if (firstList.size() == 0 || secondList.size() == 0)
        return ans;

    int i = 0, j = 0, n = firstList.size(), m = secondList.size();
    while (i < n && j < m)
    {
        int iendPt = firstList[i][1];
        int jendPt = secondList[j][1];

        int istPt = firstList[i][0];
        int jstPt = secondList[j][0];

        if (jendPt < istPt)
            j++;
        else if (iendPt < jstPt)
            i++;
        else if (iendPt == jendPt)
        {
            mergeInterval(istPt, jstPt, iendPt, ans);
            i++;
            j++;
        }
        else if (jendPt < iendPt)
        {
            mergeInterval(istPt, jstPt, jendPt, ans);
            j++;
        }
        else
        {
            mergeInterval(istPt, jstPt, iendPt, ans);
            i++;
        }
    }
    return ans;
}

//57
// in place soln mein o(n) ki jaan to delete so it become o(n^2)
vector<vector<int>> insert(vector<vector<int>> &intervals, vector<int> &newInterval)
{
    vector<vector<int>> ans;
    int i = 0;
    if (intervals.size() == 0)
    {
        ans.push_back(newInterval);
        return ans;
    } 

    for (int i = 0; i < intervals.size(); i++)
    {
        if (newInterval[1] < intervals[i][0]) //jaise hi range se bahar add krdo aur change kro apna newInterval
        {
            ans.push_back(newInterval);
            newInterval = intervals[i];
        }
        else if (intervals[i][1] < newInterval[0]) //end point chotta start point se..range ke andar aaya bhi ni new interval ki
        {
            ans.push_back(intervals[i]);
        }
        else
        {
            newInterval[0] = min(intervals[i][0], newInterval[0]);
            newInterval[1] = max(intervals[i][1], newInterval[1]);
        }
    }
    ans.push_back(newInterval);
    return ans;
}

