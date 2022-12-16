import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewActivityPanel extends JPanel {

    JPanel activitiesPanel;
    JButton weekFilterButton;
    JButton monthFilterButton;

    JButton backButton;
    Color themeColor;


    public ViewActivityPanel() {


        Font headingFont = new Font("SansSerif",Font.PLAIN,17);

        Font normalTextPlainFont = new Font("SansSerif",Font.PLAIN,17);

        themeColor = new Color(55,148,233);

        setLayout(new FlowLayout(FlowLayout.CENTER,15,20));
        JLabel title = new JLabel("View Activities");
        title.setFont(new Font("Inter",Font.BOLD,25));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(200,60));




        JPanel borderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));

        activitiesPanel = new JPanel();

        JPanel headingPanel = new JPanel(new GridLayout(1,5));

        JLabel dateHeading = new JLabel("Date");
        JLabel activityHeading = new JLabel("Activity");
        JLabel durationHeading = new JLabel("Duration");
        JLabel distanceHeading = new JLabel("Distance");
        JLabel caloriesHeading = new JLabel("Calories Burnt");

        dateHeading.setFont(headingFont);
        activityHeading.setFont(headingFont);
        durationHeading.setFont(headingFont);
        distanceHeading.setFont(headingFont);
        caloriesHeading.setFont( new Font("Open sans",Font.BOLD,17));

        dateHeading.setHorizontalAlignment(SwingConstants.CENTER);
        activityHeading.setHorizontalAlignment(SwingConstants.CENTER);
        durationHeading.setHorizontalAlignment(SwingConstants.CENTER);
        distanceHeading.setHorizontalAlignment(SwingConstants.CENTER);
        caloriesHeading.setHorizontalAlignment(SwingConstants.CENTER);

        headingPanel.add(dateHeading);
        headingPanel.add(activityHeading);
        headingPanel.add(durationHeading);
        headingPanel.add(distanceHeading);
        headingPanel.add(caloriesHeading);

        headingPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, themeColor));


        borderPanel = new JPanel();
        borderPanel.setBorder(BorderFactory.createLineBorder(themeColor,1));

        JLabel filterText = new JLabel("Filter by: ");
        filterText.setPreferredSize(new Dimension(100,50));
        filterText.setFont(new Font("SansSerif",Font.PLAIN,18));


        weekFilterButton = new JButton("This week");
        monthFilterButton = new JButton("This month");


        weekFilterButton.setBackground(themeColor);
        weekFilterButton.setForeground(Color.white);
        weekFilterButton.setPreferredSize(new Dimension(100,40));
        weekFilterButton.setFont(new Font("SansSerif",Font.PLAIN,15));
        weekFilterButton.setBorder(BorderFactory.createEmptyBorder());

        monthFilterButton.setBackground(new Color(217,217,217));
        monthFilterButton.setForeground(Color.black);
        monthFilterButton.setPreferredSize(new Dimension(100,40));
        monthFilterButton.setFont(new Font("SansSerif",Font.PLAIN,15));
        monthFilterButton.setBorder(BorderFactory.createEmptyBorder());

        ImageIcon backArrowIcon = new ImageIcon("Arrow 1.png");
        backButton = new JButton(backArrowIcon);
        backButton.setBackground(new Color(217,217,217));
        backButton.setPreferredSize(new Dimension(50,40));
        backButton.setFocusable(false);
        backButton.setBorder(BorderFactory.createEmptyBorder());

        ButtonHandler buttonHandler = new ButtonHandler();

        weekFilterButton.addActionListener(buttonHandler);
        monthFilterButton.addActionListener(buttonHandler);

        weekFilterButton.setFocusable(false);
        monthFilterButton.setFocusable(false);


        borderPanel.setBackground(Color.white);
        headingPanel.setBackground(Color.white);
        activitiesPanel.setBackground(Color.white);



        borderPanel.add(headingPanel);
        borderPanel.setPreferredSize(new Dimension(725,380));
        headingPanel.setPreferredSize(new Dimension(700,50));

        loadActivitiesPanel("week");

        JScrollPane scrollPane = new JScrollPane(activitiesPanel);
        scrollPane.setPreferredSize(new Dimension(700,300));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);



        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,30));
        JPanel buttonsPanel = new JPanel(new GridLayout(1,2,20,0));

        filterPanel.setBackground(Color.white);
        buttonsPanel.setBackground(Color.white);


        buttonsPanel.add(weekFilterButton);
        buttonsPanel.add(monthFilterButton);

        filterPanel.add(filterText);
        filterPanel.add(buttonsPanel);


        borderPanel.add(scrollPane);
        add(backButton);
        add(title);
        add(borderPanel);
        add(filterPanel);
        setBackground(Color.white);
        setPreferredSize(new Dimension(850,650));

    }


    public void loadActivitiesPanel(String filter) {
        //get activities from database and store in a list
        ArrayList<Activity> activityList = FitnessDatabase.getActivities(filter);

        activitiesPanel.removeAll(); //clear activity panel
        revalidate();
        repaint();
        if(activityList.size()==0) //if no activities show text
        {
            JLabel noActivitiesLabel = new JLabel("No Activities");
            noActivitiesLabel.setFont(new Font("SansSerif",Font.BOLD,20));
            activitiesPanel.add(noActivitiesLabel);
        } else{
            //create grid layout with rows = list size
            activitiesPanel.setLayout(new GridLayout(activityList.size(),5));
            //each row has a height of 50px
            activitiesPanel.setPreferredSize(new Dimension(700,50*activityList.size()));



            //convert date format from database to more readable format
            SimpleDateFormat dateRead = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateWrite = new SimpleDateFormat("E, dd MMM");
            for(Activity activity: activityList)
            {
                String newDate="";
                try{
                    Date date = dateRead.parse(activity.getDate());
                     newDate = dateWrite.format(date);
                } catch (Exception e)
                {
                    System.out.println(e);
                }

                //set values in labels
                JLabel dateValue = new JLabel(newDate);
                JLabel activityValue = new JLabel(activity.getType());
                JLabel durationValue = new JLabel(activity.getDuration());
                JLabel distanceValue = new JLabel(activity.getDistance());
                JLabel caloriesValue = new JLabel(String.valueOf(activity.getCaloriesBurnt()));

                dateValue.setFont( new Font("SansSerif",Font.PLAIN,16));
                activityValue.setFont( new Font("SansSerif",Font.PLAIN,16));
                durationValue.setFont( new Font("SansSerif",Font.PLAIN,16));
                distanceValue.setFont( new Font("SansSerif",Font.PLAIN,16));
                caloriesValue.setFont( new Font("SansSerif",Font.BOLD,16));

                dateValue.setHorizontalAlignment(SwingConstants.CENTER);
                activityValue.setHorizontalAlignment(SwingConstants.CENTER);
                durationValue.setHorizontalAlignment(SwingConstants.CENTER);
                distanceValue.setHorizontalAlignment(SwingConstants.CENTER);
                caloriesValue.setHorizontalAlignment(SwingConstants.CENTER);

                //add labels
                activitiesPanel.add(dateValue);
                activitiesPanel.add(activityValue);
                activitiesPanel.add(durationValue);
                activitiesPanel.add(distanceValue);
                activitiesPanel.add(caloriesValue);

            }
        }

        revalidate();
        repaint();
    }

    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==weekFilterButton)
            {
                weekFilterButton.setBackground(themeColor);
                weekFilterButton.setForeground(Color.white);
                monthFilterButton.setBackground(new Color(217,217,217));
                monthFilterButton.setForeground(Color.black);
                loadActivitiesPanel("week");

            }

            if(e.getSource()==monthFilterButton)
            {
                monthFilterButton.setBackground(themeColor);
                monthFilterButton.setForeground(Color.white);
                weekFilterButton.setBackground(new Color(217,217,217));
                weekFilterButton.setForeground(Color.black);
                loadActivitiesPanel("month");
            }
        }
    }
}
