import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardPanel extends JPanel {
    JButton addActivityButton;
    JButton viewActivitiesButton;
    JButton dailySummaryButton;

    AddActivityFrame activityFrame;


    public DashboardPanel() {

        setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        JLabel dashBoardTitle = new JLabel("myFitness App");
        dashBoardTitle.setFont(new Font("Inter",Font.BOLD,40));
        JLabel trackText = new JLabel("Start Tracking");
        JLabel exerciseText = new JLabel("Your Exercise");

        dashBoardTitle.setHorizontalAlignment(SwingConstants.CENTER);
        dashBoardTitle.setPreferredSize(new Dimension(600,80));
        trackText.setFont(new Font("Open Sans",Font.PLAIN,35));
        exerciseText.setFont(new Font("Open Sans",Font.PLAIN,35));

        trackText.setHorizontalAlignment(SwingConstants.CENTER);
        exerciseText.setHorizontalAlignment(SwingConstants.CENTER);

        trackText.setPreferredSize(new Dimension(250,50));
        exerciseText.setPreferredSize(new Dimension(250,50));

        JPanel trackExerciseTextPanel = new JPanel();
        trackExerciseTextPanel.setPreferredSize(new Dimension(250,130));
        trackExerciseTextPanel.add(trackText);
        trackExerciseTextPanel.add(exerciseText);

        trackExerciseTextPanel.setBackground(Color.white);





        JPanel contentPanel = new JPanel();

        JPanel contentDivPanel = new JPanel();

        contentDivPanel.setBackground(Color.white);
        contentPanel.setBackground(Color.white);

        contentDivPanel.setPreferredSize(new Dimension(300,300));
        contentDivPanel.setBackground(Color.white);


        ImageIcon fitnessImage = new ImageIcon("fit3.png");
        JLabel fitnessImageLabel = new JLabel(fitnessImage);
        //fitnessImageLabel.setPreferredSize(new Dimension(400,400));


        addActivityButton = new JButton("Add Activity");
        viewActivitiesButton = new JButton("View Activities");
        dailySummaryButton = new JButton("Daily Summary");

        addActivityButton.setBackground(new Color(217,217,217));
        addActivityButton.setForeground(Color.black);
        addActivityButton.setBorder(BorderFactory.createEmptyBorder());
        addActivityButton.setFont(new Font("SansSerif",Font.PLAIN,17));
        addActivityButton.setFocusable(false);

        viewActivitiesButton.setBackground(new Color(217,217,217));
        viewActivitiesButton.setForeground(Color.black);
/*        viewActivitiesButton.setHorizontalAlignment(SwingConstants.CENTER);
        viewActivitiesButton.setVerticalTextPosition(SwingConstants.CENTER);
        viewActivitiesButton.setPreferredSize(new Dimension(150,60));*/
        viewActivitiesButton.setBorder(BorderFactory.createEmptyBorder());
        viewActivitiesButton.setFont(new Font("SansSerif",Font.PLAIN,17));
        viewActivitiesButton.setFocusable(false);

        dailySummaryButton.setBackground(new Color(217,217,217));
        dailySummaryButton.setForeground(Color.black);
        dailySummaryButton.setPreferredSize(new Dimension(200,50));
        dailySummaryButton.setBorder(BorderFactory.createEmptyBorder());
        dailySummaryButton.setFont(new Font("SansSerif",Font.PLAIN,17));
        dailySummaryButton.setFocusable(false);

        JPanel buttonPanel = new JPanel(new GridLayout(2,1,0,20));
        buttonPanel.setBackground(Color.white);

        buttonPanel.setPreferredSize(new Dimension(150,100));





        buttonPanel.add(addActivityButton);
        buttonPanel.add(viewActivitiesButton);



        dailySummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    FitnessDatabase.getDailySummary();

            }
        });


        add(dashBoardTitle);
        contentDivPanel.add(trackExerciseTextPanel);
        contentDivPanel.add(buttonPanel);
        contentPanel.add(contentDivPanel);
        contentPanel.add(fitnessImageLabel);
        add(contentPanel);
        add(dailySummaryButton);


       setPreferredSize(new Dimension(800,650));
       setBackground(Color.white);



    }


}
