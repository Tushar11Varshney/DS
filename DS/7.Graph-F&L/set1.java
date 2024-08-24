// Path exist
// count/print all path
// get connected component
// Perfect friends
// Hamiltonian(every vertexp visited 1 time only)
// Number of island(no need of visited array)
// Max area of island
// Surrounded region
// Perimiter of 1 island/ multiple island(cant mark 0)


public static boolean hasPath(ArrayList<Edge>[] mygraph, int src, int dest, int visited[]) {
    if (src == dest)
        return true;

    visited[src] = 1;
    for (Edge edge : mygraph[src]) {
        if (visited[edge.nbr] == 0) {
            boolean hasRasta = hasPath(mygraph, edge.nbr, dest, visited);
            if (hasRasta == true)
                return true;
        }
    }
    return false;
} 

//1971
public boolean dfs(List<Integer>[]graph,int src,int dstn,boolean []visited)
{
    if(src==dstn)
        return true;
    
    boolean hasRasta=false;
    visited[src]=true;
    for(int vtx:graph[src])
    {
        if(!visited[vtx])
        {
            hasRasta=dfs(graph,vtx,dstn,visited);
            if(hasRasta==true)
                return true;
        }
    }
    return false;
}

public boolean validPath(int n, int[][] edges, int source, int destination) {
    List<Integer>[]graph=new ArrayList[n];
    for(int i=0;i<n;i++)
        graph[i]=new ArrayList<>();
    
    for(int []e:edges)
    {
        graph[e[0]].add(e[1]);
        graph[e[1]].add(e[0]);
    }
    
    boolean []visited=new boolean[n];
    return dfs(graph,source,destination,visited);
}

// count or print all path
public static int printAllPath(ArrayList<Edge>[] graph, int src, int dest, boolean visited[], String psf) {
    if (src == dest) {
        System.out.println(psf);
        return 1;
    }
    visited[src] = true;
    int count=0;
    for (Edge edge : graph[src]) {
        if (visited[edge.nbr] == false)
            count+=printAllPath(graph, edge.nbr, dest, visited, psf + edge.nbr);
    }
    visited[src] = false;
    return count;
    // boolean visited[]=new boolean[vtces]; 
    // printAllPath(graph,src,dest,visited,psf+ src);
}

public static void getConnectedComponent(ArrayList<Edge>[] graph, int visited[], int src, ArrayList<Integer> comp) {
    visited[src] = 1;
    comp.add(src);
    for (Edge e : graph[src]) {
        if (visited[e.nbr] == 0) {
            getConnectedComponent(graph, visited, e.nbr, comp);
        }
    }

    // int vtces=7;
    // ArrayList < ArrayList < Integer >> comps = new ArrayList < > ();
    // int visited[]=new int[vtces];
    // for(int i=0;i<vtces;i++)
    // {
    //     if(visited[i]==0)
    //     {
    //         ArrayList<Integer>comp=new ArrayList<>();
    //         getConnectedComponent(mygraph,visited,i,comp);
    //         comps.add(comp);
    //     }
    // }
    // System.out.println(comps);
    // System.out.println(comps.size()==1);
}   

public static void perfectFriends()
{
    int totalPair=0;    //sabse pehle connected component ki list bnao.
    for(int i=0;i<comps.size();i++)
    {
        for(int j=i+1;j<comps.size();j++)
            totalPair+=comps.get(i).size()*comps.get(j).size();
    }
    System.out.println(totalPair);
} 

public static void hamiltonianPathCycle(ArrayList<Edge>[] graph, HashSet<Integer> visited, int src, String psf,int osrc) {
    if (visited.size() == graph.length - 1) {  //visited psf se ek kadam piche chlta hai
        int flag = 0;
        for (Edge e : graph[src]) {
            if (e.nbr == osrc) {
                System.out.println(psf + "*");
                flag = 1;
                break;
            }
        }
        if (flag == 0)
            System.out.println(psf + ".");

        return;
    }

    visited.add(src);
    for (Edge e : graph[src]) {
        if (visited.contains(e.nbr) == false) {
            hamiltonianPathCycle(graph, visited, e.nbr, psf + e.nbr, osrc);
        }
    }
    visited.remove(src);

    // HashSet<Integer>visited=new HashSet<>();  //or take visited array
    // ArrayList<Integer>visited1=new ArrayList<>();//cant use this because when we
    // want to remove the src vertex in postorder then arraylist will remove that
    // index.
    // Base case mein loop lgakr check kro if the nbr has any edge to original source
    // hamiltonianPathCycle(graph,visited,src,""+src,src);.
} 

// 200-no need of visited array
public void dfsIsland(int i, int j, int n, int m, char[][] grid, int[][] dirArray) {
    grid[i][j] = '0';
    for (int k = 0; k < dirArray.length; k++) {
    int r = i + dirArray[k][0];
    int c = j + dirArray[k][1];

    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1') 
        dfsIsland(r, c, n, m, grid, dirArray);
    }
}

public int numIslands(char[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) // grid.length [] grid[0].length [[]]
    return 0;

    int n = grid.length;
    int m = grid[0].length;
    int[][] dirArray = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    int noOfIsland = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                noOfIsland++;
                dfsIsland(i, j, n, m, grid, dirArray);
            }
        }
    }
    return noOfIsland;
}

// 695
public int dfsIslandArea(int i, int j, int n, int m, int[][] grid, int[][] dirArray) {
    grid[i][j] = 0;
    int area = 1;
    for (int k = 0; k < dirArray.length; k++) {
    int r = i + dirArray[k][0];
    int c = j + dirArray[k][1];

    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) 
        area += dfsIslandArea(r, c, n, m, grid, dirArray);
    }
    return area;
} 

public int maxAreaOfIsland(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0)
    return 0;

    int n = grid.length;
    int m = grid[0].length;
    int[][] dirArray = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    int maxArea = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (grid[i][j] == 1) {
                maxArea = Math.max(maxArea, dfsIslandArea(i, j, n, m, grid, dirArray));
            }
        }
    }
    return maxArea;
}

// 463-exactly 1 island and no lake within one island so no dfs calculate ngbr
public int islandPerimeter(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0)
    return 0;

    int n = grid.length;
    int m = grid[0].length;
    int[][] dirArray = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    int land = 0, neigbours = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (grid[i][j] == 1) {
                land++;
                for (int k = 0; k < dirArray.length; k++) {
                    int r = i + dirArray[k][0];
                    int c = j + dirArray[k][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                        neigbours++;
                    }
                }
            }
        }
    }
    return land * 4 - neigbours;
}

// 130
public void surroundedIsland(char[][] grid, int i, int j, int n, int m, int[][] dirArray) {
    grid[i][j] = '$';
    for (int k = 0; k < dirArray.length; k++) {
        int r = i + dirArray[k][0];
        int c = j + dirArray[k][1];

        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 'O') {
            surroundedIsland(grid, r, c, n, m, dirArray);
        }
    }   
}

public void surroundedRegion(char[][] grid) {
    if (grid.length == 0 || grid[0].length == 0)
    return;

    int n = grid.length;
    int m = grid[0].length;
    int[][] dirArray = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    for (int j = 0; j < m; j++) {
        if (grid[0][j] == 'O')
            surroundedIsland(grid, 0, j, n, m, dirArray);

        if (grid[n - 1][j] == 'O')
            surroundedIsland(grid, n - 1, j, n, m, dirArray);
    }

    for (int i = 1; i < n - 1; i++) {
        if (grid[i][0] == 'O')
            surroundedIsland(grid, i, 0, n, m, dirArray);

        if (grid[i][m - 1] == 'O')
            surroundedIsland(grid, i, m - 1, n, m, dirArray);
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (grid[i][j] == 'O')
            grid[i][j] = 'X';
            else if (grid[i][j] == '$')
            grid[i][j] = 'O';
        }
    }
}


public static int get_perm__more_than_1_island(int [][]grid,int [][]dir,int u,int v)
{
        if(grid[u][v]==2)return 0;  //this will be shared boundary dont increase sides on this.
        grid[u][v]=2;
        
        int sides=0;
        for(int i=0;i<dir.length;i++)
        {
            int r=u+dir[i][0];
            int c=v+dir[i][1];
            if(r>=0 && c>=0 && r<grid.length && c<grid[0].length && grid[r][c]!=0)
                sides+=get_perm__more_than_1_island(grid,dir,r,c);
            else sides++;
        }
        return sides;
}
    
public static void main(String[] args) {
    int [][]grid = {
        {0,1,0,1},
        {1,1,1,0},
        {0,1,0,1},
        {1,1,0,1}
    };
    int dir[][]={{0,1},{0,-1},{1,0},{-1,0}};
    ArrayList<Integer>ans=new ArrayList<>();
    for(int i=0;i<grid.length;i++)
        for(int j=0;j<grid[0].length;j++)
            if(grid[i][j]!=0 && grid[i][j]!=2)
                {
                    int res=get_perm__more_than_1_island(grid,dir,i,j);
                    ans.add(res);
                }   
    System.out.println(ans);
}

