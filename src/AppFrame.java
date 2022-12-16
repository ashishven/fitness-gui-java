import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Main frame that changes between the dashboard panel and the view activities panel
public class AppFrame extends JFrame {
    DashboardPanel dashboardPanel;
    ViewActivityPanel viewActivityPanel;

    AddActivityFrame activityFrame;

   public AppFrame()
    {
        dashboardPanel = new DashboardPanel();
        viewActivityPanel = new ViewActivityPanel();

        dashboardPanel.viewActivitiesButton.addActionListener(new ButtonHandler());
        dashboardPanel.addActivityButton.addActionListener(new ButtonHandler());
        viewActivityPanel.backButton.addActionListener(new ButtonHandler());

        add(dashboardPanel);
        pack();
    }

    private class ButtonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // remove dashboard panel and load viewActivities panel
            if(e.getSource()==dashboardPanel.viewActivitiesButton)
            {
                remove(dashboardPanel);
                revalidate();
                repaint();
                add(viewActivityPanel);
                revalidate();
                repaint();
            }
            // remove viewActivities panel  and load dashboard panel
            if(e.getSource()==viewActivityPanel.backButton)
            {
                remove(viewActivityPanel);
                revalidate();
                repaint();
                add(dashboardPanel);
                revalidate();
                repaint();
            }
            //update activities in viewActivities upon adding new activity
            if(e.getSource()==dashboardPanel.addActivityButton)
            {
                activityFrame = new AddActivityFrame();
                activityFrame.setSize(500,500);
                activityFrame.setLocationRelativeTo(null); //display frame in center of screen
                activityFrame.setVisible(true);
                activityFrame.finishButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        activityFrame.dispose(); //close pop-up frame
                        //update activities
                        viewActivityPanel.loadActivitiesPanel("week");
                    }
                });
            }
        }
    }
}
