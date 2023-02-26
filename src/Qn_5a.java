import java.util.*;
/*You are given a 2D array containing coordinates and height of rectangle such that height[i]=[xi,yi,hi], where xi the
x coordinate of left edge, yi represents x coordinate of right edge of rectangle and hi represents the height of the
peaks of each rectangle. If you want to construct a border line over the peaks of rectangle represented in bar chart,
return the key coordinates required to build a border line that contacts the peaks of the given chart.
Note: key points are the left coordinates of shape representing peaks where you need to draw boarder line.
Input: height={{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}}
Output: {{1,10},{2,15},{5,12},{8,0},{9,1},{11,15},{13,0}}
*/

class BorderLines {
    public List<List<Integer>> getBorders(int[][] buildings) {
        /*input: the 2d array each row containing right x coordinate and left x coordinate
         and height of each rectangle*/

        /*output: key coordinates required to build a borderline that contacts
         the peaks of the given chart
        * */


        List<List<Integer>> res=new ArrayList<>();
        List<int[]> heights=new ArrayList<>();

        /*   // transform the 2D array into a list of 2-element arrays,
        // where the first element is the x-coordinate, and the second is the height
        // (with a negative height indicating the start of a building,
        // and a positive height indicating the end of a building).*/
        transformBuilding(buildings,heights);


/* sort the list of heights based on the x-coordinates.
         If the x-coordinates are the same, sort by the height (ascending).*/
        Collections.sort(heights,(a,b)->(a[0]==b[0]) ? a[1]-b[1] : a[0]-b[0]);// TC->O(nlog n)
        // Create a priority queue to store heights in descending orde
        PriorityQueue<Integer> pq=new PriorityQueue<Integer>((a,b)->(b-a));

        /* Initialize the priority queue with 0 (the ground level).*/
        pq.add(0);
        int prevMax=0;

        /*  Iterate through the sorted list of heights.*/
        for(int[] height:heights){ //O(n)

            /*  If the height has a negative value, add its absolute value to the priority queue.*/
            if(height[1]<0){

                pq.add(-height[1]);
            }
            /*  If the height has a positive value, remove its absolute value from the priority queue.*/
            else{
                pq.remove(height[1]);
            }

            /*Get the current maximum height from the priority queue.*/
            int currentMax=pq.peek();
            /*  If the current maximum height is different from the previous maximum height,
             add the x-coordinate and the current maximum height to the result list.*/
            if(currentMax!=prevMax)
            {
                List<Integer> subResult=new ArrayList<>();
                subResult.add(height[0]);
                subResult.add(currentMax);

                res.add(subResult);
                prevMax=currentMax;
            }
        }

        return res;
    }
    // transform the 2D array of buildings into a list of 2-element arrays.
    // For each building, add two 2-element arrays to the list:
    // one with the start x-coordinate and negative height, and
    // one with the end x-coordinate and positive height
    private void transformBuilding(int[][] buildings,List<int[]> heights)
    {
        for(int[] building:buildings)
        {
            heights.add(new int[]{building[0],-building[2]});
            heights.add(new int[]{building[1],building[2]});
        }



    }

    public static void main(String[] args) {
        int[][] rec = {{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}};
        BorderLines solution = new BorderLines();
        List<List<Integer>> ans = solution.getBorders(rec);
        System.out.println(ans);

    }



}