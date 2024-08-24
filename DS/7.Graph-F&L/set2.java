// bfs-cycle+level printing/Level printing + shortest path/path printing
// get connected component count
// Shortest path binary 
// Isbipartite 
// Oranges rottning
// Wall and gates 
// Ladder length

//====================
//1.cycle detection
//2.shortest path
//3.shortest path print kelie 
public static void BFS_Cycle(int src,boolean []visited)t
{
    int dest=6;
    int level=0;
    int atLevel=-1;
    boolean isCycle=false;

    LinkedList<Integer>que=new LinkedList<>();
    que.addLast(src);

    while(que.size()>0)
    {
        int size=que.size();
        System.out.print("Level "+level+" -> ");
        while(size-- > 0)
        {
            int rvtx=que.removeFirst();
            if(!visited[rvtx])System.out.print(rvtx+" ");

            if(visited[rvtx])
            {
                isCycle=true;
                continue;
            }
            visited[rvtx]=true;

            if(rvtx==dest)
                atLevel=level;
            
            for(Edge e:graph[rvtx])
            {
                if(!visited[e.v])
                que.addLast(e.v);
            }
        }
        System.out.println();
        level++;
    }

    System.out.println("isCycle: "+isCycle);
    System.out.println("level: "+ atLevel);
}

public static void BFS_ShortestPath(int src,boolean []visited)
{
    int dest=6;
    int level=0;
    int atLevel=-1;

    LinkedList<Integer>que=new LinkedList<>();
    que.addLast(src);
    visited[src]=true;
    while(que.size()>0)
    {
        int size=que.size();
        System.out.print("Level "+level+" -> ");
        while(size-- > 0)
        {
            int rvtx=que.removeFirst();
            System.out.print(rvtx+" ");

            if(rvtx==dest)
                atLevel=level;

            for(Edge e:graph[rvtx])
            {
                if(!visited[e.v])
                {
                    visited[e.v]=true;
                    que.addLast(e.v);;
                }
            }
        }
        System.out.println();
        level++;
    }
    System.out.println("level: "+ atLevel);
}

public static void printShortestPath(int src,boolean []visited)
{
    int dest=6;
    int level=0;
    int atLevel=-1;
    int parent[]=new int[N];
    parent[src]=-1;

    LinkedList<Integer>que=new LinkedList<>();
    que.addLast(src);
    visited[src]=true;
    while(que.size()>0)
    {
        int size=que.size();
        while(size-- > 0)
        {
            int rvtx=que.removeFirst();

            if(rvtx==dest)
                atLevel=level;

            for(Edge e:graph[rvtx])
            {
                if(!visited[e.v])
                {
                    visited[e.v]=true;
                    que.addLast(e.v);
                    parent[e.v]=rvtx;
                }
            }

        }
        System.out.println();
        level++;
    }

    int idx=dest;
    while(idx!=-1)
    {
        System.out.print(idx+" -> ");
        idx=parent[idx];
    }
}

public static int BFS_gcc()
{
    boolean visited[]=new boolean[N];
    int components=0;
    for(int i=0;i<N;i++)
    {
        if(!visited[i])
        {   
            BFS_Cycle(i,visited);
            components++;
        }
    }
    return components;
}

//1091
public int shortestPathBinaryMatrix(int[][] grid) {
    if(grid.length==0 || grid[0].length==0)return -1;
    
    int n=grid.length;
    if(grid[0][0]==1 || grid[n-1][n-1]==1)return -1;
            
    LinkedList<Integer>que=new LinkedList<>();
    que.addLast(0*n+0);
    grid[0][0]=1;
    int level=1;
    int[][]dirs={{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};

    while(que.size()>0)
    {
        int size=que.size();
        while(size-- > 0)
        {
            int idx=que.removeFirst();
            int r=idx/n;
            int c=idx%n; 

            if(r==n-1 && c==n-1)
                return level;  //counting on basis of nodes

            for(int i=0;i<dirs.length;i++)
            {
                int x=r+dirs[i][0];
                int y=c+dirs[i][1];

                if(x>=0 && y>=0 && x<n && y<n && grid[x][y]==0)
                {
                    grid[x][y]=1;
                    que.addLast(x*n+y);
                }
            }
        }
        level++;
    }

    return -1;
}

// 994
public int orangesRotting(int[][] grid) {

    int n = grid.length;
    int m = grid[0].length;

    LinkedList<Integer> que = new LinkedList<>();
    int freshOranges = 0, time = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (grid[i][j] == 2)
            que.addLast(i * m + j);

            if (grid[i][j] == 1)
            freshOranges++;
        }
    }

    if (freshOranges == 0)
    return 0;
    int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    while (que.size() > 0) {
        int size = que.size();
        while (size-- > 0) {
            int idx = que.removeFirst();
            int r = idx / m;
            int c = idx % m;

            for (int i = 0; i < dirs.length; i++) {
                int x = r + dirs[i][0];
                int y = c + dirs[i][1];

                if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1) {
                    freshOranges--;
                    grid[x][y] = 2;
                    que.addLast(x * m + y);
                    if (freshOranges == 0)
                    return time + 1;
                }
            }
        }
        time++;
    }
    return -1;
}

// 286
//https://www.lintcode.com/problem/663/
public void wallsAndGates(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;

    LinkedList<Integer> que = new LinkedList<>();
    int emptyRooms = 0, distance = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (grid[i][j] == 0)
            que.addLast(i * m + j);

            if (grid[i][j] == 2147483647)
            emptyRooms++;
        } 
        //-1 - A wall or an obstacle.
        // 0 - A gate.
        // INF - Infinity means an empty room
    }

    if (emptyRooms == 0)
    return;
    int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    while (que.size() > 0) {
        int size = que.size();
        while (size-- > 0) {
            int idx = que.removeFirst();
            int r = idx / m;
            int c = idx % m;

            for (int i = 0; i < dirs.length; i++) {
                int x = r + dirs[i][0];
                int y = c + dirs[i][1];

                if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 2147483647) {
                    emptyRooms--;
                    grid[x][y] = distance + 1;
                    que.addLast(x * m + y);
                    if (emptyRooms == 0)
                    return;
                }
            }
        }
        distance++;
    }
}

//785
public boolean isBipartite(int[][] graph,int []visited,int src) {
    LinkedList<Integer>que=new LinkedList<>();
    que.addLast(src);
    int color=0;
    boolean isCycle=false;

    while(que.size()>0)
    {
        int size=que.size();
        while(size-- > 0)
        {
            int rvtx=que.removeFirst();

            if(visited[rvtx]!=-1)
            {
                    isCycle=true;
                    if(visited[rvtx]!=color)
                    return false;

                    continue;
            }

            visited[rvtx]=color;
            for(int v:graph[rvtx])
            {
                if(visited[v]==-1)
                    que.addLast(v);
            }
        }
        color=(color+1)%2;
    }
    return true;
}    

public boolean isBipartite(int[][] graph) {
    
    int n=graph.length;
    int []visited=new int[n];
    Arrays.fill(visited, -1);

    // boolean res=true;
    for(int i=0;i<n;i++)
    {
        // if(visited[i]==-1)
        // res=res&&isBipartite(graph,visited,i);
        if(visited[i]==-1 && !isBipartite(graph,visited,i))
            return false;
    }
    // return res;
    return true;
}

//127 nc
int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
    unordered_set<string>hs;
    bool isPresent=false;
    for(string ele:wordList)
    {
       hs.insert(ele);
       if(ele.compare(endWord)==0)
           isPresent=true;
    }
    if(!isPresent)return 0;
    
    queue<string>que;
    int level=0;
    que.push(beginWord);
    
    while(que.size()>0)
    {
        int size=que.size();
        while(size-->0)
        {
            string w=que.front();
            que.pop();
            
            for(int i=0;i<w.length();i++)
            {
                string temp=w;        //w=hit
                for(int j=0;j<26;j++)
                {
                    temp[i]=(char)(j+'a');
                    if(w.compare(temp)==0)          //temp=hit then continue             
                        continue;
                    if(w.compare(endWord)==0)
                        return level+1;
                    if(hs.find(temp)!=hs.end())
                    {
                        hs.erase(temp);
                        que.push(temp);
                    }
                }
            }
        }
        level++;
    }
    
    return 0;
}
