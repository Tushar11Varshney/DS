#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

// ArrayList<String>res=new ArrayList<>(map.keySet());
// for(String s:res)  //better because if we write(String s:map.keySet()) then baar baar ek naya keyset mangwataa hai
// {
// }
void set1()
{
    unordered_map<string, int> map;
    map["IND"] = 10000;
    map["USA"] = 100;
    map["UK"] = 990;
    map["NEP"] = 1009;
    map["GER"] = 1899;
    map["UK"] = 10078;

    for (pair<string, int> key : map)//or use auto
    {
        cout << key.first << " : " << key.second << endl;
    }
    cout << endl;

    if (map.find("INd") != map.end())
        cout << map["INd"] << endl;
    else
        cout << "Not Present" << endl;

    map.erase("GER");
    for (auto key : map)                      
    {
        cout << key.first << " : " << key.second << endl;
    }
}

void freqMap(string str)
{
    unordered_map<char, int> map;
    for (char ch : str)
        map[ch]++;
    for (pair<char, int> key : map)
    {
        cout << key.first << " : " << key.second << endl;
    }
}

void freqMap2(string str)
{
    unordered_map<char, vector<int>> map;
    for (int i = 0; i < str.length(); i++)
        map[str[i]].push_back(i);
    for (pair<char, vector<int>> key : map)
    {
        cout << key.first<<":";
        for(int ele:key.second)
        {
            cout<<ele<<" ";
        }
        cout<<endl;
    }
}

//java
public static void fun1() throws Exception {
    HashMap<String, Integer> mp = new HashMap<>();
    mp.put("india", 135);
    mp.put("china", 200);
    mp.put("england", 89);
    mp.put("uk", 5);
    mp.put("us", 13);

    mp.put("india", 90);
    mp.put("aus", 20);

    // System.out.println(mp); //{india=90,aus=20..}

    System.out.println(mp.get("india"));
    System.out.println(mp.get("utopia"));

    System.out.println(mp.containsKey("india"));
    System.out.println(mp.containsKey("utopia"));

    ArrayList<String> keySet = mp.keyset();
    for (String str : keySet) {
        Integer i = mp.get(str);
        System.out.println(str + " " + i);
    }
}

int main()
{
    // set1();
    // freqMap("jskcsmjsASDaassacsacascsvsacasacasc");
    freqMap2("jskcsmjsAAASSSDaassacsmmacascsSSvsacasacccasc");
    return 0;
}
