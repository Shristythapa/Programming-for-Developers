import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/* Implement multi-threaded algorithm to multiply n*n matrix*/
class MatrixMultiplication {
    private int[][] result;
    private int[][] matrix1;
    private int[][] matrix2;

     MatrixMultiplication(int[][] matrix1, int[][] matrix2) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        /*the result matrix will have size of
         * row length = of first matrix row
         * column length = of second matrix column*/
        result = new int[matrix1.length][matrix2[0].length];
    }

    public int[][] multiply() {
        /*The number of threads created is equal to the number of available processors on the machine.*/
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        /*executing thread*/
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                executorService.execute(new MultiplicationTask(i, j));
            }
        }

        executorService.shutdown();
        try {

            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {/*this exception might be thrown if the thread is interrupted while waiting*/
            e.printStackTrace();
        }

        return result;
    }


    private class MultiplicationTask implements Runnable {
        private int row;
        private int col;

        public MultiplicationTask(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void run() {
            /*calculation of the multiplication value */
            int sum = 0;
            for (int i = 0; i < matrix2.length; i++) {
                sum += matrix1[row][i] * matrix2[i][col];
            }
            result[row][col] = sum;
        }
    }

    public static void main(String[] args) {
        int[][] m1 = {{1,2,3},{4,5,6}};
        int[][] m2={{10,11},{20,21},{30,31}};
        MatrixMultiplication mm = new MatrixMultiplication(m1,m2);
        System.out.println(Arrays.deepToString(mm.multiply()));
    }

}
