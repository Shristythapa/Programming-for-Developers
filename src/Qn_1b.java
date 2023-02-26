import java.util.*;
/*Assume you were hired to create an application for an ISP, and there is n number of network devices, such as
routers, that are linked together to provides internet access to home user users. You are given a 2D array that
represents network connections between these network devices such that a[i]=[xi,yi] where xi is connected to yi
device. Suppose there is a power outage on a certain device provided as int n represents id of the device on which
power failure occurred)), Write an algorithm to return impacted network devices due to breakage of the link
between network devices. These impacted device list assists you notify linked consumers that there is a power
outage and it will take some time to rectify an issue. Note that: node 0 will always represent a source of internet or
gateway to international network..
Input: edges= {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}}
Target Device (On which power Failure occurred): 4
Output (Impacted Device List) = {5,7}
Explanation: power failure on network device 4 will disconnect 5 and 7 from internet*/
class AdjMatrix {
    int vertices;
    int matrix[][];

    AdjMatrix(int vertices) {
        this.vertices = vertices;
        matrix = new int[vertices][vertices];//already populated with 0 value
    }

    public void addEdge(int source, int destination) {//take int wight as input in case of widhted matrix
        //source initial point - U , destination final point -V
        matrix[source][destination] = 1;//assign weight instead of  in case of weighted graph
        matrix[destination][source] = 1;//only in case of undirected graph
    }


    public List getAdjecentNode(int vertex) {
        /*get node connected by edge with the input vertex*/
        ArrayList ans = new ArrayList();
        for (int i = 0; i < vertices; i++) {
            if (matrix[vertex][i] != 0) {
                ans.add(i);

            }
        }
        return ans;
    }
}


 class FindDefectedDevice {
    public void DFS(AdjMatrix matrix,int defectedAdj,int defected){
        /*initialize a boolean array to keep track of visited vertex while dfs the graph*/
        boolean[] visited =new boolean[matrix.vertices];
        visited[defected]=true;
        find(visited, matrix,defectedAdj);
    }

    public void dfs(int source, boolean[] visited,AdjMatrix matrix,Stack<Integer> s){
        /*perform depth first traversal if and add the vertex in the path to stack "s"*/
        if(source==0){
            return;
        }
        visited[source]=true;

        Iterator<Integer> iterator=matrix.getAdjecentNode(source).iterator();//inbuild function in java to itterate over list ---you can also user for loop
        while (iterator.hasNext()){
            int adjval=iterator.next();
            if(!visited[adjval]){
                s.push(adjval);
                dfs(adjval,visited,matrix,s);
            }
        }
    }
    public void find(boolean[] visited,AdjMatrix matrix,int adj){

        ///main algorithm
        /*finds the damaged node*/

        /*stack to keep track of the route vertex*/
        Stack<Integer> s = new Stack();
        s.push(adj);
        visited[adj]=true;

        Iterator<Integer> iterator=matrix.getAdjecentNode(adj).iterator();//inbuild function in java to itterate over list ---you can also user for loop

        /*if 0 is found in the path*/
        boolean found = false;

        /*dfs through all the adj nodes of */
        while (iterator.hasNext()) {

            int adjval = iterator.next();

            if (!visited[adjval]) {
                s.push(adjval);
                dfs(adjval,visited,matrix,s);
            }

        }

        int[] ans1 = new int[s.size()];

        /*pop the values from the stack if 0 is found then "found" updated to true else add it to array*/
        for (int i = 0; i <=s.size(); i++) {
            int val = s.pop();
            if (val == 0) {
                found=true;
            }else{
                ans1[i]=val;
            }
        }

        /*if 0 is found in the array i.e. all the routes aren't traversed then set found true*/
        for(int i=0; i<ans1.length; i++){
            if(ans1[i]==0){
                found=true;
            }
        }

        /*if found is true return*/
        if(found){
            return ;

            /*else 0 can't be reached from that node if the given defected node is not traversed
             * so the damaged nodes will be printed*/
        }else{
            System.out.println(Arrays.toString(ans1));
        }

    }
    public void findDefectedNode(int defected,AdjMatrix matrix) {

        Iterator<Integer> iterator=matrix.getAdjecentNode(defected).iterator();
        /*calls DFS on each adj vertex of defected*/
        while (iterator.hasNext()){
            DFS(matrix,iterator.next(),defected);

        }
    }


    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix(8);
        adjMatrix.addEdge(0,1);
        adjMatrix.addEdge(0,2);
        adjMatrix.addEdge(1,3);
        adjMatrix.addEdge(1,6);
        adjMatrix.addEdge(4,5);
        adjMatrix.addEdge(2,4);
        adjMatrix.addEdge(4,6);
        adjMatrix.addEdge(5,7);
        FindDefectedDevice d = new FindDefectedDevice();
        d.findDefectedNode(1,adjMatrix);
    }
}
