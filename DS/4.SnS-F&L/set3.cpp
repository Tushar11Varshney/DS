// Concept - Use array in order and find max/min

// Can ship within d days(2)
// minimum #of page
// psble to paint
// split array 
// smallest divisor threshold - si=1 
// Koko eating banana slowly- si=1 
// wood cutting (cant do si=mid becase lo never become greather than high)
// Serving area cake
// Minimize max dist to station

// 1011
bool canWeShipWithIn_DDays(int leastWgt, vector<int> &weights, int days)
{
    int count = 0, sum = 0;
    for (int i = 0; i < weights.size(); i++)
    {
        sum += weights[i];
        if (sum > leastWgt)  //jab cap < maxwt hogi tb ye apne aap count>days hone ki wjh se return false kredega
        {
            count++;
            if (count > days)
                return false;
            sum = 0;
            i = i - 1;
        }
    }
    count++;
    return count > days ? false : true;
}

int shipWithinDays(vector<int> &weights, int days)
{
    int si = 0, ei = 0;
    for (int ele : weights)
        ei += ele;

    while (si < ei)
    {
        int mid = (si + (ei - si) / 2);
        if (canWeShipWithIn_DDays(mid, weights, days))
            ei = mid;
        else
            si = mid + 1;
    }
    return si; // ya ei
}

bool canWeShipWithIn_DDays(int leastWgt, vector<int> &weights, int days)
{
    int count = 1, sum = 0;
    for (int i = 0; i < weights.size(); i++)
    {
        sum += weights[i];
        if (sum > leastWgt)
        {
            count++;
            if (count > days)
                return false;
            sum = weights[i];
        }
    }
    return count <= days;  //true
}

int shipWithinDays(vector<int> &weights, int days)
{
    int si = 0, ei = 0;
    for (int ele : weights)
    {
        si = max(si, ele);
        ei += ele;
    }

    //Each day, we load the ship with packages on the conveyor belt
    if (weights.size() == days)
        return si; // agr 10 weights hain 10 hi din hai tou koi bhi din khaali ni hoskta tou least weight capacity should be maximum.
    while (si < ei)
    {
        int mid = (si + (ei - si) / 2);
        if (canWeShipWithIn_DDays(mid, weights, days))
            ei = mid;
        else si = mid + 1;
    }
    return ei;
}

// https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/searching-and-sorting/allocate_minimum_number_of_pages/ojquestion
public static boolean isPossible(int[] arr, int load, int m)
{
    int student = 1, sum = 0;
    for (int ele : arr)
    {
        sum += ele;
        if (sum > load)
        {
            student++;
            if (student > m)
                return false;
            sum = ele;
        }
    }
    return student <= m;
}

public static int minPages(int[] arr, int m)
{
    int sum = 0, max = 0;
    for (int ele : arr)
    {
        sum += ele;
        max = Math.max(max, ele);
    }

    int lo = max, hi = sum;
    while (lo < hi)
    {
        int mid = (lo + (hi - lo) / 2);
        if (isPossible(arr, mid, m))
            hi = mid;
        else
            lo = mid + 1;
    }
    return lo;
}

// https://practice.geeksforgeeks.org/problems/the-painters-partition-problem1535/1#
//  each painter takes 1 unit time to paint 1 unit of the board. 
static boolean Possible_ToPaint(int arr[], long paintLength, int noOfPainters)
{
    int painters = 1;
    long sum = 0;
    for (int ele : arr)
    {
        sum += ele;
        if (sum > paintLength)
        {
            painters++;
            if (painters > noOfPainters)
                return false;
            sum = ele;
        }
    }
    return painters <= noOfPainters; //true // yahan return true ni hoga vo tb jab si=max pr set na hoke 0 pr set ho
}

static long minTime(int[] arr, int n, int k)
{
    long sum = 0L;
    int max = 0;
    for (int ele : arr)
    {
        max = Math.max(max, ele);
        sum += ele;
    }

    long si = max, ei = sum; // si ko max set krna pdega vrna aur min chle jaega jo ki galat answer hoga
    while (si < ei)
    {
        long mid = si + (ei - si) / 2;
        if (Possible_ToPaint(arr, mid, k))
            ei = mid;
        else
            si = mid + 1;
    }

    return si;
}

// 410
public static boolean isPossible_ToSplit(int[] arr, int minSum, int m)
{
    int partition = 1, sum = 0;
    for (int ele : arr)
    {
        sum += ele;
        if (sum > minSum)
        {
            partition++;
            if (partition > m)
                return false;
            sum = ele;
        }
    }
    return partition <= m;
}

public int splitArray(int[] arr, int m)
{
    int sum = 0, max = 0;
    for (int ele : arr)
    {
        sum += ele;
        max = Math.max(max, ele);
    }

    int lo = max, hi = sum;
    while (lo < hi)
    {
        int mid = (lo + (hi - lo) / 2);
        if (isPossible_ToSplit(arr, mid, m)) // kitne subarray ki zrurat pdegi ki maximum mid ho
            hi = mid;
        else
            lo = mid + 1;
    }
    return lo;
}

// 1283
public boolean divisor_LessThanThreshold(int[] nums, int threshold, int divisor)
{
    int sum = 0;
    for (int ele : nums)
    {
        // System.out.println(Math.ceil((ele*1.0)/divisor));
        sum += Math.ceil((ele * 1.0) / divisor);
        if (sum > threshold)
            return  false;
    }
    return true;
}

public int smallestDivisor(int[] nums, int threshold)
{
    int max = 0;
    for (int ele : nums)
        max = Math.max(max, ele);

    int lo = 1, hi = max;
    while (lo < hi)
    {
        int mid = (lo + (hi - lo) / 2);
        if (divisor_LessThanThreshold(nums, threshold, mid))
            hi = mid;
        else
            lo = mid + 1;
    }
    return lo;
}

// 875
int isPossibletoEat(vector<int> &piles, int eatingSpeed, int H) // array mein we dont write & because pointer passes but in vector & is ram ka hi ek pointer so that copy constructor dont fire.
{
    int totalHour = 0;
    for (int i = piles.size() - 1; i >= 0; i--) // sort krne se ye faeda jab kabhi H exceed hoga tou hum beech mein hi ruk jaenge break lgakr peeche se chalakr
    {
        totalHour += ceil(piles[i] / (eatingSpeed * 1.0)); // ceil returns integer..in a/b one value should be float
        if (totalHour > H)
            return false;
    }
    return true;
}

int minEatingSpeed(vector<int> &piles, int H)
{
    //minspeed cannot be maximum value  bcz koko like to eat slowly.and should be atleast 1
    // sort(piles.begin(),piles.end());
    int n = piles.size();
    // int minSpeed=1,maxSpeed=piles[n-1],eatingSpeed=0;
    int minSpeed = 1, maxSpeed = 1e9, eatingSpeed = 0; 
    while (minSpeed < maxSpeed)
    {
        // 1 <= piles[i] <= 10^9
        eatingSpeed = (minSpeed) + (maxSpeed - minSpeed) / 2; // here though maxSpeed can be 1e9(given) in constraint but if Int_max then eatingspeed overflow krskti hai
        if (isPossibletoEat(piles, eatingSpeed, H))
            maxSpeed = eatingSpeed;
        else
            minSpeed = eatingSpeed + 1;
    }
    return maxSpeed; // ya minspeed ,not eating speed
}

// ib-wood cutting made easy
int isPossibleTo_Cut(vector<int> &A, int heightBlade, int B)
{
    int sum = 0;
    for (int i = 0; i < A.size(); i++)
    {
        if (A[i] > heightBlade)
            sum += A[i] - heightBlade;

        if (sum >= B)
            return true;
    }
    return false;
} 

int woodCutting(vector<int> &A, int B) 
{
    int lo = 0, hi = 1e9, ans = 0;
    while (lo <= hi)
    {
        int mid = (lo + (hi - lo) / 2);
        if (isPossibleTo_Cut(A, mid, B))
        {
            ans = mid;  //isme ni chlega lo=mid
            lo = mid + 1;
        }
        else
            hi = mid - 1;
    }
    return ans;
}

// https://leetcode.com/discuss/interview-question/348510/Google-or-Online-Assessment-or-Maximum-Area-Serving-Cake
bool isPossibletoEat(vector<double> &area, double piecearea, int k)
{
    int count = 0;
    for (double ele : area)
    {
        count += ele / piecearea;  //not taken ceil because cant give bacha hua tukda to someone
        if (count >= k)
            return true;
    }
    return false;
}

double servingAreaCake(vector<int> radii, int k)
{
    vector<double> area;
    double si = 0.0, ei = 0.0, mid = 0.0;
    for (int i = 0; i < radii.size(); i++)
    {
        area.push_back(radii[i] * radii[i] * 2 * acos(0.0));
        ei = max(ei, area[i]);
    }
    while (ei - si > 1e-5) // 4 decimal point tk answer maanga h jaise hi differnce 4 decimal se chhota hua loop stop 
    {
        mid = (ei + si) / 2;
        if (isPossibletoEat(area, mid, k))
            si = mid;
        // else ei=mid-1e-5;  //no farak even if we dont subtract (1e-5 is safe can also use 1e-4)
        else 
            ei = mid; // cant do -1.0 because answer is accepted till 4 decimal place.
    }
    return mid; // si ya ei ya mid(bcz kyunki differnce bahut kam h)
}

bool isValid(vector<int> &stations, int k, double mid) // binary search on difference
{
    int count = 0;
    for (int i = 0; i < stations.size() - 1; i++)
    {
        count += (int)((stations[i + 1] - stations[i]) / mid);
        if (count > k)
            return true;
    }
    return false; // agar 3 pump lgane the aur 2 lga paaye then km kro apna distance also if 3 lga paye then also kam kro your distance
}

// https://www.lintcode.com/problem/minimize-max-distance-to-gas-station/description
double minmaxGasDist(vector<int> &stations, int k)
{
    double si = 0.0, ei = 1e9, mid = 0.0; // agr ei calculate by o(n) ka loop chalakr then it takes more time than log(1e9)
    while ((ei - si) > 1e-7)
    {
        mid = si + (ei - si) / 2.0;
        if (isValid(stations, k, mid))
            si = mid;
        else
            ei = mid - 10e-6; // ya ei = mid and if answer is accepted till 10e-6 then should sub 10e-7 but can also 10e-6
    }
    return mid; // si//ei
}

int main()
{
    // vector<int> nums = {-2, 0, 2, 3, 5, 15, 17, 19, 29, 48, 68, 78, 98};
    // int target = 67;
    // cout << binarySearchNearestIndex(nums, target) << endl;
    vector<int> radii = {1, 1, 1, 2, 2, 3};
    // vector<int> radii = {4,3,3};
    int k = 6;
    cout << servingAreaCake(radii, k);
    // vector<int> stations = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    // int k = 10;
    // cout << minmaxGasDist(stations, k) << endl;
    return 0;
}

