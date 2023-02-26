import java.util.*;
/*There are n nations linked by train routes. You are given a 2D array indicating routes between countries and the
time required to reach the target country, such that E[i]=[xi,yi,ki], where xi represents the source country, yi
represents the destination country, and ki represents the time required to go from xi to yi. If you are also given
information on the charges, you must pay while entering any country. Create an algorithm that returns the cheapest
route from county A to county B with a time constraint.
Input: edge= {{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}}
Charges = {10,2,3,25,25,4}
Source: 0
Destination: 5
Output: 64
Time Constraint=14 min
Note: the path 0, 3, 4, 5 will take minimum time i.e., 13 minutes and which costs around $64. We cannot take path
0,1,2,5 as it takes 15 min and violates time constraint which in 14 min.*/
 class CheapestRoute {
    class Country{
        int name;
        int time;
        int cost;
        public Country(int name, int time, int cost){
            this.name=name;
            this.time=time;
            this.cost=cost;
        }
    }


    public int findCheapestRoute(int[][] edges, int[] charges, int source, int destination, int timeConstraint){
          /*inputs:
        edges -> array of lists of source destination and distance
        charges are the cost of entering each node
        source is the source country to travel from to the destination country
        timeConstraint is the time limit within which we should travel the nodes
        output:
        the minimum cost to reach the destination from source within the given timeConstraint
        * */

        // Create a graph represented as an adjacency list
        Map<Integer, List<Country>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int sources = edge[0];
            int destinations = edge[1];
            int time = edge[2];
            int cost = charges[destinations];
            List<Country> list = graph.getOrDefault(sources, new ArrayList<>());
            list.add(new Country(destinations, time, cost));
            graph.put(sources, list);
        }

        // Initialize the distances and visited flags
        int[] distances = new int[charges.length];
        boolean[] visited = new boolean[charges.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        // Use a priority queue to select the node with the smallest time
        PriorityQueue<Country> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));

        //as we enqueue the element will be sorted in ascending order according to time
        queue.offer(new Country(source, 0, charges[source]));//

        // Dijkstra's algorithm with a time constraint
        while (!queue.isEmpty()) {
            Country current = queue.poll();

            //if the current node is the destination then the cost is the current node only
            if (current.name == destination) {
                return current.cost;
            }
            //do not visit one node more than once in a path
            if (visited[current.name]) {
                continue;
            }
            visited[current.name] = true;
            for (Country neighbor : graph.getOrDefault(current.name, new ArrayList<>())) {
                int newTime = current.time + neighbor.time;
                int newCost = current.cost + charges[neighbor.name];

                //select the current node only if its time added to old time will not excide the time constrant and the
                if (newTime <= timeConstraint && newCost < distances[neighbor.name]) {
                    distances[neighbor.name] = newCost;
                    queue.offer(new Country(neighbor.name, newTime, newCost));
                }
            }
        }

        return -1; // No path found

    }

    public static void main(String[] args) {
        CheapestRoute cr = new CheapestRoute();
        int[][] arr={{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}};
        System.out.println(cr.findCheapestRoute(arr, new int[]{10, 2, 3, 25, 25, 4},0,5,14));
    }

}
