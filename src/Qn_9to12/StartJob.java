package Qn_9to12;

import Qn_9to12.JobSheduling.SequencingJob;
import Qn_9to12.JobSheduling.SchedulingJobModel;
import Qn_9to12.controller.Controller;
import Qn_9to12.model.JobModel;
import Qn_9to12.model.JobTaskModel;
import Qn_9to12.model.TaskModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.List;
import java.util.Timer;
import java.util.*;

public class StartJob  extends JFrame {

    int count = 0;
    Controller taskController = new Controller();
    JButton back;
    JPanel frame;
    JLabel jobs;
    JLabel selectJob;
    JButton jButton;
    JScrollPane jtf;
    String text="";
    ScrollableLabel t;
    JButton returnHome;
    StartJob(){
        setTitle("Start Job");
        setSize(1280, 720 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(new Color(0x9F7298));
        getContentPane();

        frame =new JPanel();
        frame.setBounds(300,50,550,550);
        frame.setBackground(Color.white);
        add(frame);

        initilize();
    }
    void initilize(){


        returnHome = new JButton("Home");
        returnHome.setBounds(10, 10, 100, 30);
        returnHome.setFont(new Font("Roboto", Font.BOLD, 20));
        returnHome.setFocusPainted(false);
        add(returnHome);
        returnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeScreen home = new HomeScreen();
                home.show();
                dispose();
            }
        });


        selectJob = new JLabel("Select Job");
        selectJob.setFont(new Font("San Serif", Font.BOLD, 20));
        selectJob.setBounds(450, 100, 200, 30);
        selectJob.setBackground(new Color(0x9494AE));
        selectJob.setForeground(Color.white);
        add(selectJob);
        add(frame);

        jobs = new JLabel("Selected job will be displayed here");
        jobs.setFont(new Font("Roboto", Font.PLAIN, 20));
        jobs.setBounds(450,200,500,30);
        add(jobs);
        add(frame);
        t=new ScrollableLabel(text);
        t.setFont(new Font("San Serif", Font.PLAIN, 15));
        jtf=new JScrollPane(t);
        jtf.setBounds(400,300,400,55);
        jtf.setVisible(true);
        add(jtf);

        jButton = new JButton("Start Job");
        jButton.setBounds(450,400,300,45);
        jButton.setForeground(Color.white);
        jButton.setFocusPainted(false);
        jButton.setBackground(new Color(0x9494AE));
        jButton.setFont(new Font("San Serif", Font.PLAIN, 25));
        add(jButton);
        add(frame);



        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "job started");
                try {
                    jobShedule();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });



    }
    public void topoSort(int jobId){
//        int jobId = Integer.parseInt(String.valueOf(jobs.getSelectedItem()));

        JobModel jobModel = taskController.fetchJobBYId(jobId);
        int vertixes = jobModel.getNumOfTask();
        Graph graph = new Graph(vertixes);

        ArrayList<JobTaskModel> jtm = taskController.fetchTaskModels(jobId);
        for(int i=0; i<jtm.size()-1; i++){
            graph.addEdge(jtm.get(i).getSource(),jtm.get(i).getDestination());
        }

        int[] tasks=graph.topologicalSort();

        for (int i=0; i<tasks.length; i++){
            TaskModel tm =new TaskModel();
            tm=taskController.getTaskById(tasks[i]);
            System.out.println(tm.getTask());
            text+= tm.getTask()+"->";
            t.setText(text);

        }



    }

    public void jobShedule() throws InterruptedException {

        ArrayList<JobModel> jobModels = taskController.fetchJob();
        SequencingJob sequencingJob = new SequencingJob();

        ArrayList<SchedulingJobModel> jminsc = new ArrayList<>();

        //creating job model from the job model retreved from database
        for(int i=0; i<jobModels.size(); i++){
            SchedulingJobModel jobm = new SchedulingJobModel(jobModels.get(i).getJobId(),jobModels.get(i).getProfit(),jobModels.get(i).getTime());
            jminsc.add(jobm);
        }


        //calling the sheduled job algorithm
        List<Integer> scheduledJobs = new ArrayList<Integer>();
        scheduledJobs=sequencingJob.scheduling(jminsc);
        int[] allTask =  new int[scheduledJobs.size()];
        int[] allTime = new int[scheduledJobs.size()];


        //fetching all the job data from the job database of the selected task
        for(int i=0; i<scheduledJobs.size(); i++){
            JobModel JM = taskController.fetchJobBYId(scheduledJobs.get(i));
            allTime[i]=JM.getTime();
            allTask[i]=JM.getJobId();
        }

        //created list of executables tasks
        Runnable[] TASKS = new Runnable[allTask.length];
        for(int tim = 0; tim<allTime.length;tim++) {

            int finalTim = tim;
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    text="";
                    t.setText(text);
                    JobModel job = taskController.fetchJobBYId(allTask[finalTim]);

                    String jobName = job.getJobName();
                    jobs.setText(jobName);
                    topoSort(allTask[finalTim]);
                }
            };
            TASKS[tim]=task;
        }
        for(int i=0; i<allTime.length; i++){

        }

        //retrieving the current hour
        String currtime = LocalTime.now().toString();

        String hrs="";
        for(int i=0; i<=1; i++){
            hrs+=currtime.charAt(i);
        }
        int intHrs=Integer.parseInt(hrs);

        //list of date object of when task will execute
        ArrayList<Calendar> TIMES = new ArrayList<>();
        for(int timeTo=0; timeTo<allTime.length;timeTo++){
            Calendar date = Calendar.getInstance();
            date.set(Calendar.HOUR_OF_DAY,intHrs);
            date.set(Calendar.MINUTE,allTime[timeTo]);
            date.set(Calendar.SECOND,0);
            TIMES.add(date);

        }


        //executing tasks in the given time
        //here the given time will be considered as current minute
        //i.e. if the job is schedule at 3 then job will execute at current hour when it is 3 minutes eg 4:03
        Timer timer = new Timer();
        for(int i=0; i<TASKS.length; i++){
            final int index=i;
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    TASKS[index].run();;
                }
            };
            timer.schedule(task,TIMES.get(i).getTime());
        }


    }


}
