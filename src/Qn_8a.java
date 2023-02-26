import java.util.Stack;

/*Given 2D matrix of 1 and 0s. Using stack, find maximum area of square made by 0s.
INPUT: 1 0 1 0 0
0 1 1 1 1
0 0 0 0 1
0 0 0 1 1
0 1 0 1 1
OUTPUT: 4*/
class SquareArea {

    public static int maxSquareArea(int[][] matrix) {
        /*input: 2D matrix of 1 and 0
        * output: area of the largest square made by 0*/
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n + 1];//array to keep track of the rectangle
        int maxArea = 0;

        //loop through all rows of the matrix
        for (int i = 0; i < m; i++) {
            Stack<Integer> stack = new Stack<>();//stack store the index of the current rectangle

            //loop through all columns of the matrix
            for (int j = 0; j <= n; j++) {
                if (j < n) {
                    //if the current element is 0, increase the height of the rectangle
                    if (matrix[i][j] == 0) {
                        heights[j]++;
                    } else {
                        heights[j] = 0;
                    }
                }

                //pop rectangles from the stack until the height of the current rectangle is greater than or equal to the previous rectangle
                while (!stack.isEmpty() && heights[stack.peek()] > heights[j]) {
                    int height = heights[stack.pop()];
                    int width = stack.isEmpty() ? j : j - stack.peek() - 1;

                    // Update the max area if the area of the current rectangle is greater
                    maxArea = Math.max(maxArea, Math.min(height, width) * Math.min(height, width));
                }
                stack.push(j);// Push the index of the current rectangle onto the stack
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1}};
        System.out.println(maxSquareArea(matrix)); // Output: 4
    }
}
