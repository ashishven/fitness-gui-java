import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

public class AddActivityFrame extends JFrame {

    private JButton nextButton;

    private  JButton backButton;

     JButton finishButton;
    private JLabel steps;

    private Formatter outfile;

    private static final String[] activities = {"Running","Walking","Dancing","Bicycling","Swimming"};

    private JList activityList;

    private String currentActivity;

    private JPanel chooseTypePanel;
    private  JPanel chooseDurationPanel ;

    private JPanel chooseDistancePanel;

    private JLabel currentActivityLabel;



    private JLabel activityValueLabel;

    private JLabel durationValueLabel;

    private JLabel distanceValueLabel;

    private JLabel caloriesBurntValueLabel;

    private JPanel durationTitlePanel;

    private JTextField durationText;

    private JTextField distanceText;
    private int currentStep=0;

    private JComboBox durationCombo;

    private JComboBox distanceCombo;

    private static final String[] durationOptions ={"  Hours","  Minutes"};

    private static final String[] distanceOptions ={"  Km","  Meters"};
    private  ImageIcon[] stepPictures;

    private Font subtitleFont;

    private JButton completeButton;

    private JPanel[] stepPanels;

    private JPanel titlePanel;

    private JPanel activityInfoPanel;

    private  JPanel activityInfoBorder;
    AddActivityFrame()  {

      stepPictures = new ImageIcon[3];
      ImageIcon step1 = new ImageIcon("step1.1.png");
      ImageIcon step2 = new ImageIcon("step2.2.png");
      ImageIcon step3 = new ImageIcon("step3.3.png");

       stepPictures[0]= step1;
       stepPictures[1]= step2;
       stepPictures[2]= step3;

       subtitleFont=new Font("Inter",Font.BOLD,15);

       Font normalTextPlainFont = new Font("SansSerif",Font.PLAIN,17);


        chooseTypePanel = new JPanel();  //-----------------------------------------------------------------------



        titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        titlePanel.setPreferredSize(new Dimension(300,40));

        JLabel titleLabel = new JLabel("Add Activity");
        titleLabel.setFont(new Font("Inter",Font.BOLD,25));

        titlePanel.add(titleLabel);


        chooseTypePanel.setBackground(Color.white);


        JLabel typeTitle = new JLabel("Choose type of activity");
        typeTitle.setFont(new Font("SansSerif",Font.BOLD,16));
        typeTitle.setHorizontalAlignment(SwingConstants.CENTER);
        typeTitle.setPreferredSize(new Dimension(350,50));


        chooseTypePanel.add(typeTitle);
        chooseTypePanel.setPreferredSize(new Dimension(400,200));

        activityList = new JList(activities);
        activityList.setFont(new Font("SansSerif",Font.PLAIN,15));
        activityList.setBorder(BorderFactory.createLineBorder(new Color(55,148,233)));
        activityList.setFixedCellWidth(100);activityList.setSelectedIndex(0);
        activityList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        activityList.setSelectionBackground(new Color(55,148,233));
        activityList.setSelectionForeground(Color.white);

        activityList.setVisibleRowCount(4);
        chooseTypePanel.add(activityList);










        chooseDurationPanel = new JPanel( new FlowLayout(FlowLayout.CENTER,5,5)); //-------------------------------------
        chooseDurationPanel.setPreferredSize(new Dimension(400,200));



        currentActivityLabel = new JLabel("");
        currentActivityLabel.setFont(new Font("SansSerif",Font.BOLD,16));
        currentActivityLabel.setForeground(Color.red);


        JLabel durationTitle = new JLabel("Enter duration for activity:");
        durationTitle.setFont(new Font("SansSerif",Font.BOLD,16));

        durationTitlePanel = new JPanel();

        durationTitlePanel.setPreferredSize(new Dimension(350,60));

        durationTitlePanel.add(durationTitle);
        durationTitlePanel.add(currentActivityLabel);

        chooseDurationPanel.add(durationTitlePanel);

        durationText = new JTextField();
        durationText.setPreferredSize(new Dimension(75,30));
        durationText.setHorizontalAlignment(SwingConstants.CENTER);
        durationText.setBorder(BorderFactory.createLineBorder(new Color(55,148,233)));


        durationCombo = new JComboBox(durationOptions);
        durationCombo.setBackground(Color.white);
        durationCombo.setPreferredSize(new Dimension(80,30));
        durationCombo.setBorder(BorderFactory.createEmptyBorder());


        chooseDurationPanel.add(durationText);
        chooseDurationPanel.add(durationCombo);
        chooseDurationPanel.setBackground(Color.white);
        durationTitlePanel.setBackground(Color.white);




        chooseDistancePanel = new JPanel(); //------------------------------------------------------------------------------------------------
        chooseDistancePanel.setPreferredSize(new Dimension(400,200));

        JPanel distanceTitlePanel = new JPanel();
        distanceTitlePanel.setPreferredSize(new Dimension(350,60));
        JLabel optionalLabel = new JLabel("Optional");
        optionalLabel.setFont(subtitleFont);
        optionalLabel.setForeground(Color.red);

        JLabel distanceTitleLabel = new JLabel(": Enter distance covered");
        distanceTitleLabel.setFont(subtitleFont);

        distanceTitlePanel.add(optionalLabel);
        distanceTitlePanel.add(distanceTitleLabel);

        distanceTitlePanel.setBackground(Color.white);

        chooseDistancePanel.add(distanceTitlePanel);


        distanceText = new JTextField();
        distanceText.setPreferredSize(new Dimension(75,30));
        distanceText.setHorizontalAlignment(SwingConstants.CENTER);
        distanceText.setBorder(BorderFactory.createLineBorder(new Color(55,148,233)));


        distanceCombo = new JComboBox(distanceOptions);
        distanceCombo.setBackground(Color.white);
        distanceCombo.setPreferredSize(new Dimension(80,30));
        distanceCombo.setBorder(BorderFactory.createEmptyBorder());


        chooseDistancePanel.add(distanceText);
        chooseDistancePanel.add(distanceCombo);
        chooseDistancePanel.setBackground(Color.white);



        activityInfoPanel = new JPanel(new GridLayout(4,2,0,10)); //--------------------------------------------------------------
        activityInfoPanel.setBackground(Color.white);


        JLabel activityLabel = new JLabel("Activity");
        JLabel distanceLabel = new JLabel("Distance");
        JLabel durationLabel = new JLabel("Duration");
        JLabel caloriesBurntLabel = new JLabel("Calories Burnt");

        activityLabel.setFont(normalTextPlainFont);
        distanceLabel.setFont(normalTextPlainFont);
        durationLabel.setFont(normalTextPlainFont);
        caloriesBurntLabel.setFont(subtitleFont);

        activityValueLabel = new JLabel();
        distanceValueLabel = new JLabel();
        durationValueLabel = new JLabel();
        caloriesBurntValueLabel = new JLabel();

        activityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        distanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        durationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        caloriesBurntLabel.setHorizontalAlignment(SwingConstants.CENTER);

        activityValueLabel.setFont(subtitleFont);
        activityValueLabel.setForeground(new Color(55,148,233));

        durationValueLabel.setFont(normalTextPlainFont);
        distanceValueLabel.setFont(normalTextPlainFont);
        caloriesBurntValueLabel.setFont(subtitleFont);

        activityValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        distanceValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        durationValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        caloriesBurntValueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        activityInfoPanel.add(activityLabel);
        activityInfoPanel.add(activityValueLabel);

        activityInfoPanel.add(durationLabel);
        activityInfoPanel.add(durationValueLabel);

        activityInfoPanel.add(distanceLabel);
        activityInfoPanel.add(distanceValueLabel);

        activityInfoPanel.add(caloriesBurntLabel);
        activityInfoPanel.add(caloriesBurntValueLabel);

        activityInfoBorder = new JPanel();
        activityInfoBorder.add(activityInfoPanel);
        activityInfoBorder.setBorder(BorderFactory.createLineBorder(new Color(55,148,233)));
        activityInfoBorder.setBackground(Color.white);
        activityInfoPanel.setPreferredSize(new Dimension(350,180));
        activityInfoBorder.setPreferredSize(new Dimension(380,200));



        stepPanels = new JPanel[3];

        stepPanels[0]= chooseTypePanel;
        stepPanels[1]= chooseDurationPanel;
        stepPanels[2]= chooseDistancePanel;



        steps = new JLabel(step1);
        setLayout(new FlowLayout(FlowLayout.CENTER,20,20));

        add(titlePanel);
        add(steps);


        backButton = new JButton("Back");

        completeButton = new JButton("Complete");

        nextButton = new JButton("Next");

        finishButton = new JButton("Finish");


        ButtonHandler buttonHandler = new ButtonHandler();
        nextButton.addActionListener(buttonHandler);
        backButton.addActionListener(buttonHandler);
        completeButton.addActionListener(buttonHandler);
        finishButton.addActionListener(buttonHandler);


        nextButton.setBackground(new Color(217,217,217));
        nextButton.setForeground(Color.black);
        nextButton.setPreferredSize(new Dimension(80,30));
        nextButton.setBorder(BorderFactory.createEmptyBorder());
        nextButton.setFont(new Font("SansSerif",Font.PLAIN,18));
        nextButton.setFocusable(false);


        backButton.setBackground(new Color(217,217,217));
        backButton.setForeground(Color.black);
        backButton.setPreferredSize(new Dimension(80,30));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setFont(new Font("SansSerif",Font.PLAIN,18));
        backButton.setFocusable(false);

        completeButton.setBackground(new Color(217,217,217));
        completeButton.setForeground(Color.black);
        completeButton.setPreferredSize(new Dimension(100,30));
        completeButton.setBorder(BorderFactory.createEmptyBorder());
        completeButton.setFont(new Font("SansSerif",Font.PLAIN,18));
        completeButton.setFocusable(false);

        finishButton.setBackground(new Color(217,217,217));
        finishButton.setForeground(Color.black);
        finishButton.setPreferredSize(new Dimension(80,30));
        finishButton.setBorder(BorderFactory.createEmptyBorder());
        finishButton.setFont(new Font("SansSerif",Font.PLAIN,18));
        finishButton.setFocusable(false);

        add(chooseTypePanel);
        add(nextButton);
        getContentPane().setBackground(Color.white);
    }

    private class ButtonHandler implements ActionListener{

        @Override
                public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==nextButton)
            {
                //check if at last step
                if(currentStep<2)
                {
                    //check if duration field is left blank
                    if(currentStep==1 && durationText.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null,"Please Enter Duration of Activity",
                                "Invalid Input",JOptionPane.WARNING_MESSAGE);
                    } else {
                        //go to next step
                        changePanels("next");
                    }

                }
            }

            if(e.getSource()==backButton)
            {
                //if not at step 1
                if(currentStep>0)
                {
                    changePanels("back");
                }
            }


            if(e.getSource()==completeButton)
            {
                //get duration
                double duration = Double.parseDouble(durationText.getText());

                double distance = 0;
                //check if distance inserted and read text
                if(!distanceText.getText().equals(""))
                {
                    distance = Double.parseDouble(distanceText.getText());
                }

                //get activity selected
                String activity = currentActivityLabel.getText();
                String durationUnit = durationOptions[durationCombo.getSelectedIndex()].strip(); //get duration unit
                String distanceUnit = distanceOptions[distanceCombo.getSelectedIndex()].strip(); //get distance unit
                int caloriesBurnt = calculateCaloriesBurnt(activity,duration,distance,durationUnit,distanceUnit);

                //display values in JLabels
                activityValueLabel.setText(activity); //set activity
                durationValueLabel.setText(duration+ " "+durationUnit.strip()); //set duration
                //if distance not provided
                if(distance==0)
                {
                    distanceValueLabel.setText("Not Provided");
                } else {
                    distanceValueLabel.setText(distance+ " "+distanceUnit.strip()); //set distance
                }
                caloriesBurntValueLabel.setText(caloriesBurnt +"kcal");  // set calories burnt

                //add new values to frame and remove previous items
                remove(titlePanel);
                steps.setIcon(null);
                steps.setText("Activity Added!");
                steps.setPreferredSize(new Dimension(350,70));
                steps.setFont(new Font("Inter",Font.BOLD,30));
                remove(stepPanels[2]);
                remove(completeButton);
                remove(backButton);
                revalidate();
                repaint();
                add(activityInfoBorder);
                JPanel spacePanel = new JPanel();
                spacePanel.setPreferredSize(new Dimension(400,40));
                spacePanel.setBackground(Color.white);
                add(spacePanel);
                add(finishButton);
                revalidate();
                repaint();

                //insert activity in database
                FitnessDatabase.saveActivity(activity,duration,distance,durationUnit,distanceUnit,caloriesBurnt);



            }


        }


        private void writeIntoFile(String activity, double duration, double distance, String durationUnit, String distanceUnit,int caloriesBurnt)
        {
            String currentDate = String.valueOf(java.time.LocalDate.now());

            try {
                File file = new File(currentDate + ".txt");
                if(file.exists())
                {
                    FileWriter f = new FileWriter(currentDate + ".txt", true);
                }

                outfile = new Formatter(currentDate + ".txt");

            } catch (FileNotFoundException fnfe) {
                System.out.println(fnfe);
            } catch (SecurityException | IOException se)
            {
                System.out.println(se);
            }


            outfile.format("%s\t%.1f %s\t%.1f %s\t%d\n",activity,duration,durationUnit,distance,distance,caloriesBurnt);


            outfile.close();



        }

        private int calculateCaloriesBurnt(String activity, double duration, double distance, String durationUnits, String distanceUnits)
        {

            double weight = FitnessDatabase.getUserWeight();  //get user weight
            int caloriesBurnt= 0;
            double MET = 0;

            //convert duration into minutes
            if(durationUnits.equals("Hours"))
            {
                duration=duration*60;
            }

            //convert meters to kilometers

            if(distanceUnits.equals("Meters"))
            {
                distance = distance/1000;
            }




            if(activity.equals("Running"))
            {

                if(distance==0)
                {
                   MET = 7.0; // use general MET

                } else {
                    double speed = distance/(duration/60); //calculate km/hr

                    if(speed<=6)
                    {
                        MET=6;
                    } else if(speed>6 && speed<=11)
                    {
                        MET= 10;
                    } else{
                        MET = 15;
                    }

                }

            }


            if(activity.equals("Walking"))
            {

                if(distance==0)
                {
                    MET = 3.5;  //General

                } else {
                    double speed = distance/(duration/60); //calculate km/hr

                    if(speed<=3)
                    {
                        MET=2;
                    } else if(speed>3 && speed<=6)
                    {
                        MET= 5;
                    } else{
                        MET = 8;
                    }

                }

            }

            if(activity.equals("Bicycling"))
            {
                if(distance==0)
                {
                    MET = 7.5;
                } else{
                    double speed = distance/(duration/60); //calculate km/hr

                    if(speed<16)
                    {
                        MET = 4.0;
                    } else if (speed>16 && speed<22) {
                        MET =7;
                    } else {
                        MET = 10;
                    }
                }
            }


            if(activity.equals("Swimming"))
            {
                MET = 13.8;
            }




            if(activity.equals("Dancing"))
            {
                MET = 5;
            }



            caloriesBurnt= (int) (duration*(MET*3.5*weight)/200);
            return caloriesBurnt;
        }


        private void changePanels(String command)
        {

            if(command.equals("next"))
            {

                if(currentStep == 0)
                {
                    //set current activity selected text
                    currentActivityLabel.setText(activities[activityList.getSelectedIndex()]);

                }

                currentStep++;
                steps.setIcon(stepPictures[currentStep]); // change step icon

                //removing and re-adding buttons after removing to maintain Flow layout

                remove(stepPanels[currentStep-1]); //remove appropriate panel
                remove(nextButton);

                revalidate();
                repaint();

                add(stepPanels[currentStep]);
                add(backButton);

                //add complete button if at final step else add next button
                if(currentStep==2)
                {
                    add(completeButton);
                } else {
                    add(nextButton);
                }

                revalidate();
                repaint();


            } else if(command.equals("back")) {

                //remove back button if at step 1
                if(currentStep == 1)
                {
                    remove(backButton);
                }

                //remove complete button if not in step 2

                if(currentStep ==2)
                {
                    remove(completeButton);
                }

                currentStep--;
                steps.setIcon(stepPictures[currentStep]); //display appropriate step icon

                remove(stepPanels[currentStep+1]);
                remove(nextButton);

                revalidate();
                repaint();

                add(stepPanels[currentStep]);

                if(currentStep != 0)
                {
                    add(backButton);
                }

                add(nextButton);
                revalidate();
                repaint();

            }
        }




    }
}
