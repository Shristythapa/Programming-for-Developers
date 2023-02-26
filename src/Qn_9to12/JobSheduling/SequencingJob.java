package Qn_9to12.JobSheduling;



import java.util.*;

public class SequencingJob {

    public List<Integer> scheduling(ArrayList<SchedulingJobModel> js){


        Compare cs = new Compare();
        Collections.sort(js,cs);
        // Create list of scheduled jobs and set of used deadlines
        List<Integer> scheduledJobs = new ArrayList<Integer>();
        Set<Integer> usedDeadlines = new HashSet<>();

        // Iterate over jobs and select jobs with available deadlines
        for (SchedulingJobModel job : js) {
            for (int i = job.getDeadline(); i >= 1; i--) {
                if (!usedDeadlines.contains(i)) {
                    scheduledJobs.add(job.getJobId());
                    usedDeadlines.add(i);
                    break;
                }
            }
        }

        // Print scheduled jobs
        System.out.println("Scheduled jobs: " + scheduledJobs);
        return scheduledJobs;


    }

    public static void main(String[] args) {
        SchedulingJobModel p1 = new SchedulingJobModel(11,70,2);
        SchedulingJobModel p2 = new SchedulingJobModel(12,100,1);
        SchedulingJobModel p3 = new SchedulingJobModel(13,20,3);
        SchedulingJobModel p4 = new SchedulingJobModel(14,40,2);
        SchedulingJobModel p5 = new SchedulingJobModel(15,20,1);

        ArrayList<SchedulingJobModel> input = new ArrayList<SchedulingJobModel>();
        input.add(p1);
        input.add(p2);
        input.add(p3);
        input.add(p4);
        input.add(p5);
        SequencingJob s = new SequencingJob();
        s.scheduling(input);
    }
}
