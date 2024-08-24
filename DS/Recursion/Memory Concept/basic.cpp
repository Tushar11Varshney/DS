#include <iostream>
#include <vector>
using namespace std;

//https://classroom.pepcoding.com/data-structures-and-algorithms---levelup-pitampura-jun-01-2020/54/classvideos/726
void fun(int a, int b)
{
    if (a == 5)
    {
        b = 5;
        return;
    }

    a++;
    fun(a, b);
    cout << b << endl;
}

void copyConstructorFire(int *ans,vector<int> arr)
{
    ans[0]=10;
    arr[0] = 5;
}

//Syntax
void vectorMadeonHeap(vector<int> *arr)
{
    arr->at(0) = 5;
}

void whyCppnotsafe_KAAEK_Example()
{
    vector<int> arr = {1, 2, 3, 4};
    for (int i = 0; i < 100; i++) //humne memory allocate kri sirf 4*4 16 byte but usne dikhaya 100 elements kelie answer so ye hogya ki vo aage jaake jo memory pr garbage value pdi h vo use krli aur ...agr aesa ho ki kisine class ka ek object bnaya aur vahan data pdaa h pr usse deallocate krna bhul gya so it can read that data while overflowing.
        cout << arr[i] << " ";
}

int main()
{
    // fun(1,1);

    vector<int>arr={100,2,3};
    int *ans=new int[10];
    ans[0]=100;
    copyConstructorFire(ans,arr);
    cout<<arr[0];  //100
    cout<<ans[0]; //10

    // vector<int> *arr = new vector<int>({1, 2, 3, 45});  //Syntax
    // vectorMadeonHeap(arr);
    // cout << arr->at(0) << endl;  //5

    // whyCppnotsafe_KAAEK_Example();
    return 0;
}

/*//leetcode 39
vector<vector<int>> result;
vector<int> smallAns;
void combinationSum(vector<int> &candidates, int target, int idx) 
{
    if (target == 0 || idx == candidates.size())
    {
        if (target == 0)
        {
            // vector<int> base = smallAns;
            // result.push_back(base);

            result.push_back(smallAns); //no need ye stack pr bnaa hai.yahan copy constructor fire hoke deepcopy
        }
        return;
    }

    if (target - candidates[idx] >= 0)
    {
        smallAns.push_back(candidates[idx]);
        combinationSum(candidates, target - candidates[idx], idx);
        smallAns.pop_back(); //agar popback ni krenge tou ye smallans argument mein pass krna then copy constructur fire hota..copy constructor se bachne kelie hume & lgana pdta ...& se bdiya fir global hi bnado recursive call lgane mein bhi asaani hogi because argument will be less.
    }
    combinationSum(candidates, target, idx + 1);
}
vector<vector<int>> combinationSum(vector<int> &candidates, int target)
{
    combinationSum(candidates, target, 0);
    return result;
} */