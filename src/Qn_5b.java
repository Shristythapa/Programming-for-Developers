import java.util.*;

/*
Assume an electric vehicle must go from source city s to destination city d. You can locate many service centers
along the journey that allow for the replacement of batteries; however, each service center provides batteries with a
specific capacity. You are given a 2D array in which serviceCenter[i]=[xi,yj] indicates that the ith service center is
xi miles from the source city and offers yj miles after the automobile can travel after replacing batteries at specific
service centers. Return the number of times the car's batteries need to be replaced before arriving at the destination.
Input: serviceCenters = [{10,60},{20,30},{30,30},{60,40}], targetMiles= 100, startChargeCapacity = 10
Output: 2
Explanation: The car can go 10 miles on its initial capacity; after 10 miles, the car replaces batteries with a
capacity of 60 miles; and after travelling 50 miles, at position 60 we change batteries with a capacity of 40 miles;
and ultimately, we may arrive at our destination.
*/
 class ServiceCenter {
    public int numBatteryReplacements(int[][] serviceCenters, int targetMiles, int startChargeCapacity) {
        int count = 0;// Keep track of number of battery replacements needed
        int currentMiles = startChargeCapacity;// Keep track of current miles the car can travel on one charge
        ArrayList<Integer> distances = new ArrayList<>(); // List to store the distances of the service centers
        ArrayList<Integer> capacities = new ArrayList<>();// List to store the battery capacities of the service centers

        // Loop through the service centers and add their distances and capacities to their respective lists
        for (int[] serviceCenter : serviceCenters) {
            distances.add(serviceCenter[0]);
            capacities.add(serviceCenter[1]);
        }

        // Loop through the distances list and check if the car can reach the next service center
        for (int i = 0; i < distances.size(); i++) {
            if (distances.get(i) > currentMiles) {
                currentMiles = capacities.get(i - 1);
                count++;// Increment count of battery replacements
            }
        }
        if (currentMiles < targetMiles) {
            count++;// If the final service center can't be reached, replace the battery one more time
        }
        return count;// Return the number of battery replacements needed
    }
    public static void main(String[] args) {

        // Initialize the service center list and create an instance of the class
        int [][] serviceCenterList={{10,60},{20,30},{30,30},{60,40}};
        ServiceCenter question1=new ServiceCenter();
        int finalAnswer=question1.numBatteryReplacements(serviceCenterList,100,10);
        System.out.println("Car's batteries are replaced "+finalAnswer +"times.");
    }
}