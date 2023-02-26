package Qn_9to12.JobSheduling;

import java.util.Comparator;

public class Compare implements Comparator<SchedulingJobModel> {

    @Override
    public int compare(SchedulingJobModel o1, SchedulingJobModel o2) {
        if(o1.getProfit()<o2.getProfit()){//if ending value of first object is higher than second value then swap those two value
            return 1;
        }
        else if(o1.getProfit()>o2.getProfit()){
            return -1;
        }
        return 0;
    }
}
