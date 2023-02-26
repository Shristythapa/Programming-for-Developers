import java.util.ArrayList;
import java.util.List;

/*Given an array of even numbers sorted in ascending order and an integer k,
Find the k^th missing even number from provided array
Input a[] ={0, 2, 6, 18, 22} k=6
Output: 16 examples:
Explanation: Missing even numbers on the list are 4, 8, 10, 12, 14, 16, 20 and so on and kth
missing number is on 6th place of the list i.e. 16*/
 class kthMissing {

    public static int findKthMissingEvenNumber(int[] a, int k) {
        List<Integer> missingValues = new ArrayList<>();

        /*j keeps track of element in array*/
        int j = 0;

        //for loop "i" iterates through all the even number including the missing once
        for (int i = a[0]; i < a[a.length - 1]; i += 2) {
            //if the value of i is present in the array in j index then value is not missing
            //and increment j
            if (j < a.length && a[j] == i) {
                j++;
                continue;
            }
            //else add the value of i to missing values list
            missingValues.add(i);

            //if the missing values list reaches the size of k
            // that is the term of missing value returned by the function
            if (missingValues.size() == k) {
                return i;
            }
        }
        return a[a.length - 1] + 2 * k;
    }
    public static void main(String[] args) {
        int[] a = {0, 2, 6, 18, 22};
        int k = 6;
        System.out.println(findKthMissingEvenNumber(a, k));
    }

}
