#include<iostream>
#include<vector>
using namespace std;
class Edge{
    int src;
    int nbr;
    int wt;

    public:
    Edge(int src,int nbr,int wt){
        this->src=src;
        this->nbr=nbr;
        this->wt=wt;
    }
};

int main()
{
    int n=7;
    vector<vector<Edge>>graph(n,vector<Edge>());
    graph[0].push_back(Edge(0,3,40));
    graph[0].push_back(Edge(0,1,10));

    graph[1].push_back(Edge(1,0,10));
    graph[1].push_back(Edge(1,2,10));

    graph[2].push_back(Edge(2,3,10));
    graph[2].push_back(Edge(2,1,10));

    graph[3].push_back(Edge(3,4,2));
    graph[3].push_back(Edge(3,0,40));
    graph[3].push_back(Edge(3,2,10));

    graph[4].push_back(Edge(4,3,2));
    graph[4].push_back(Edge(4,5,3));
    graph[4].push_back(Edge(4,6,8));

    graph[5].push_back(Edge(5,4,3));
    graph[5].push_back(Edge(5,6,3));

    graph[6].push_back(Edge(6,4,8));
    graph[6].push_back(Edge(6,5,3));

    // cout<<"tushar"+to_string(5)<<endl;   //ya tou "tushar"<<5 ya "tushar"+to_string(5)..if we do "tushar"+5 then ajeeb behaviour..in java integer string mein automatically typecast 

    //Array of vector
    vector<int> v[5];
    void insertionInArrayOfVectors()
    {
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                v[i].push_back(j);
            }
        }
    }

    void printElements()
    {
        for (int i = 0; i < 5; i++) {
            cout << "Elements at index "<< i << ": ";
            for (auto it = v[i].begin();it != v[i].end(); it++)
                cout << *it << ' ';
            cout << endl;
        }
    }

}