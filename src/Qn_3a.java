import java.util.Arrays;
/*
* You are given an even length array; divide it in half and return possible minimum product difference of any two
subarrays.
Note that the minimal product difference is the smallest absolute difference between any two arrays a and b, which
is computed by calculating the difference after multiplying each element of the arrays a and b.
Input: {5,2,4,11}
Output: 2
{5,4} {2,11} result into minimum product difference.
*/
class MinDiff {

    static int minDiff(int arr[]){

        //minimum product difference
        int minDiff = Integer.MAX_VALUE;
        int length=arr.length;
        int subLength=length/2;

        Arrays.sort(arr);

        //second index will start from the middle of the array
        int last=subLength;

        /*"i" will be the first index*/
        for(int i=0; i<length-1;i++){
            int prod1=1;
            int prod2=1;

            /*creating two sub arrays and calculating their respective product*/
            for(int j=i; j<subLength+i; j++){

                /*move circular through array*/
                prod1*=arr[j%length];
            }
            for (int k=last; k<subLength+last; k++){

                /*move circular through array*/
                prod2*=arr[k%length];
            }

            /*calculating the product difference*/
            int diff = Math.abs(prod1-prod2);


            /*updating the product difference*/
            if(diff<minDiff){
                minDiff=diff;
            }
            last=last%length+1;
        }
        return minDiff;
    }

    public static void main(String[] args) {
        int arr[]={5,2,4,11};
        System.out.println(minDiff(arr));
    }

}