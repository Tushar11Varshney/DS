 package Basic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class graph{

    public static class Edge{
        int v;
        int w;

        Edge(int v,int w)
        {
            this.v=v;
            this.w=w;
        }
    }

    static int N=9;
    @SuppressWarnings("unchecked")
    static ArrayList<Edge>[]graph=new ArrayList[N];

    public static void addEdge(int u,int v,int w)
    {
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static int findEdge(int u,int v)
    {
        int idx=-1;
        for(int i=0;i<graph[u].size();i++)
        {
            if(graph[u].get(i).v==v)
            {
                idx=i;
                break;
            }
        }
        return idx;
    }

    public static void removeEdge(int u,int v)
    {
        int idx1=findEdge(u, v);
        int idx2=findEdge(v, u);

        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVertex(int u)
    {
        // for(Edge e:graph[u]) //dont use forEach loop for modification
        // for(int i=0;i<graph[u].size();i++) //remove from piche because of change in indexing
        for(int i=graph[u].size()-1;i>=0;i--) 
            removeEdge(u, graph[u].get(i).v);
        // graph[u]=null;  //null pointer exception aayga fir display krte tym
    }

    public static void display()
    {
        for(int i=0;i<N;i++)
        {
            System.out.print(i+" -> ");
            for(Edge e:graph[i])
            {
                System.out.print(e.v+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void constructGraph()
    {
        for(int i=0;i<N;i++)
        graph[i]=new ArrayList<>();

        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        // addEdge(3, 4, 2);
        addEdge(4, 5, 3);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);
        addEdge(2, 7, 3);
        addEdge(2, 8, 13);
        addEdge(7, 8, 14);

        display();

        // removeEdge(0, 1);
        // display();

        // removeVertex(3);
        // display();

        // addEdge(0, 6, 20);

        // addEdge(7, 7, 10);  // 7 do baar add
    }


    static class Pair_ {
        int vertex;
        int time;

        Pair_(int vertex, int time) {
            this.vertex = vertex;
            this.time = time;
        }
    }

    public static int SpreadofInfection(ArrayList<Edge>[] graph, int visited[], int t, ArrayDeque<Pair_> dq) {
        int count = 0;
        while (dq.size() > 0) {
            Pair_ p = dq.pop();
            if (visited[p.vertex] == 1)
                continue;
            // count yahan mt bdaio nhi tou jab time 4 hoga aur condn andar check hogi usse
            // phle count increment ho chukega
            if (p.time == t + 1) {
                break;
            } else {
                visited[p.vertex] = 1;
                count++;
                for (Edge e : graph[p.vertex]) {
                    if (visited[e.nbr] == 0) {
                        dq.add(new Pair_(e.nbr, p.time + 1));
                    }
                }
            }
        }
        return count;

        // ArrayDeque<Pair>dq=new ArrayDeque<>();
        // int []visited=new int[vtces];
        // int infectedPerson=0;
        // dq.add(new Pair(src,1));
        // totalinfectedPersons=SpreadofInfection(graph,visited,t,dq);
    }

    //jis bus mein hum  phele baith chuke hain tou uske path explore krne ka koi fayda ni hai nhi tou cycle ban jaygi...islye make a array for all buses to check hum uspr phle baithe h ya ni....

    //also check the stations where we already visited before so no need to enter it again in queue.

    //815
    /*
    int numBusesToDestination(vector<vector<int>> &routes, int src, int dest)
    {
        if(src==dest)return 0;
        int n=routes.size();

        int busNumber=0;
        unordered_map<int,vector<int>>busMapping;  //busStand vs bus number going to the busStand
        for(vector<int>&route:routes)
        {
            for(int busStand:route)
            busMapping[busStand].push_back(busNumber);

            busNumber++;
        }

        vector<bool>isBusSeen(n,false);
        unordered_set<int>isBusStandSeen;

        queue<int>que;
        que.push(src);
        isBusStandSeen.insert(src);

        int level=0;
        while(que.size()>0)
        {
            int size=que.size();

            while(size-->0)
            {
                int bs=que.front();que.pop();

                vector<int>allbus=busMapping[bs];
                for(int busNo:allbus)
                {
                    if(isBusSeen[busNo])
                        continue;

                    for(int b:routes[busNo])
                    {
                        if(isBusStandSeen.find(b)==isBusStandSeen.end())
                        {
                            que.push(b);
                            isBusStandSeen.insert(b);

                            if(b==dest)
                            return level+1;
                        }
                    }
                    isBusSeen[busNo]=true;
                }
            }
            level++;
        }

        return -1;
    }
    */

}
