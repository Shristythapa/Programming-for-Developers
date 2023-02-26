package Qn_9to12;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomeScreen extends JFrame {

    //ui components
    JPanel frame;
    JLabel title;

    JButton CreateJob;
    JButton addTask;
    JButton startJob;



    public HomeScreen() {
        initialize();
        ui();
    }


    public void initialize() {
        /*basic set up*/
        setTitle("Add Your Task");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(new Color(0x9F7298));
        getContentPane();

        frame = new JPanel();
        frame.setBounds(300, 50, 550, 550);
        frame.setBackground(Color.white);
        add(frame);
    }
    public void ui(){
        /*ui component set up*/
        title =new JLabel("Home Screen");
        title.setBounds(425,150,300,45);
        title.setForeground(new Color(0x574273));
        title.setFont(new Font("San Serif", Font.BOLD, 40));
        add(title);

        CreateJob = new JButton("Create Job");
        CreateJob.setBounds(425,250,300,45);
        CreateJob.setForeground(Color.white);
        CreateJob.setFocusPainted(false);
        CreateJob.setBackground(new Color(0x9494AE));
        CreateJob.setFont(new Font("San Serif", Font.PLAIN, 25));
        add(CreateJob);
        add(frame);

        CreateJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateJob createJobPage = new CreateJob();
                createJobPage.show();
                dispose();
            }
        });

        addTask = new JButton("Add Task");
        addTask.setBounds(425,350,300,45);
        addTask.setForeground(Color.white);
        addTask.setFocusPainted(false);
        addTask.setBackground(new Color(0x9494AE));
        addTask.setFont(new Font("San Serif", Font.PLAIN, 25));
        add(addTask);
        add(frame);

        addTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTaskPage addTaskPage = new AddTaskPage();
                addTaskPage.show();
                dispose();
            }
        });

        startJob = new JButton("Start Job");
        startJob.setBounds(425,450,300,45);
        startJob.setForeground(Color.white);
        startJob.setFocusPainted(false);
        startJob.setBackground(new Color(0x9494AE));
        startJob.setFont(new Font("San Serif", Font.PLAIN, 25));
        add(startJob);
        add(frame);

        startJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartJob startJobPage = new StartJob();
                startJobPage.show();
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        new HomeScreen().setVisible(true);
    }
}






