import javax.swing.*;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Formatter;

public class FitnessDatabase {

    private static Connection myConnection;
    private static String uname; //store the current user's username
    private static double userWeightInKg;  // store the current user's weight


    //save activity to database
    public static void saveActivity(String activity, double duration, double distance, String durationUnit, String distanceUnit, int caloriesBurnt) {

        String finalDuration = duration + " " + durationUnit;
        String finalDistance = "";
        if (distance == 0) {
            finalDistance = "Not Provided";
        } else {
            finalDistance = distance + " " + distanceUnit;
        }

        String currentDate = String.valueOf(java.time.LocalDate.now());

        createConnection();
        try {
            Statement s = myConnection.createStatement();
            s.execute("INSERT INTO activities(date,uname,activity,duration,distance,caloriesBurnt)" +
                    " VALUES('" + currentDate + "','" + uname + "','" + activity + "','" + finalDuration + "','" + finalDistance + "','" + caloriesBurnt + "')");
            s.close();
            closeConnection();

        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }

    }

    /*public String deleteAddress(String name)
    {
        try {
            Statement s = myConnection.createStatement();
            s.execute("DELETE FROM Address WHERE name='"+name+"'");
            s.close();
        } catch (SQLException sqlException)
        {
            return "failed";
        }
        return "success";
    }*/


    public static ArrayList<Activity> getActivities(String filter) {
        ArrayList<Activity> activityList = new ArrayList<>();
        createConnection();
        try {
            Statement s = myConnection.createStatement();
            if (filter.equals("week")) {
                s.execute("SELECT date,activity,duration,distance,caloriesBurnt" +
                        " FROM activities WHERE week(date) = week(now()) AND uname='" + uname + "' ORDER BY date ");

            } else if (filter.equals("month")) {
                s.execute("SELECT date,activity,duration,distance,caloriesBurnt " +
                        "FROM activities WHERE month(date) = month(now()) AND uname='" + uname + "' ORDER BY date");
            } else {

                s.execute("SELECT date,activity,duration,distance,caloriesBurnt " +
                        "FROM activities WHERE day(date) = day(now()) AND uname='" + uname + "' ORDER BY date");
            }

            ResultSet rs = s.getResultSet();

            if (rs != null) {
                while (rs.next()) {
                    Activity activity = new Activity(rs.getString(1), rs.getString(2)
                            , rs.getString(3), rs.getString(4), rs.getInt(5));
                    activityList.add(activity);
                }
            }

        } catch (SQLException sqle) {
            System.out.println(sqle);
        }

        closeConnection();
        return activityList;
    }

    public static void getDailySummary() {
        String currentDate = String.valueOf(java.time.LocalDate.now());
        Formatter outfile = null;
        try {
            outfile = new Formatter(currentDate + ".txt");
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe);
        }

        ArrayList<Activity> activities = getActivities("day");

        int count = 0;
        int sum = 0;

        for (Activity activity : activities) {
            sum += activity.getCaloriesBurnt();
        }

        outfile.format("Summary for: %s\n", currentDate);
        outfile.format("Total Calories Burnt: %d\n\n", sum);
        outfile.format("%18s\t%15s\t%15s\t%15s\n\n", "Activity", "Duration", "Distance", "Calories Burnt");

        for (Activity activity : activities) {
            count++;
            outfile.format("%d.%16s\t%15s\t%15s\t%15d\n", count, activity.getType(), activity.getDuration(), activity.getDistance(), activity.getCaloriesBurnt());
        }

        try {
            ProcessBuilder pb = new ProcessBuilder("Notepad.exe", currentDate + ".txt");
            pb.start();
        } catch (Exception e) {

        }

        outfile.close();
    }

    public static void createConnection() {

        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost/fitness", "root", "");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void closeConnection() {

        try {
            myConnection.close();
        } catch (SQLException sqlException) {

        }

    }

    public static void registerUser(String username, String fullName, double weight, String password) {
        createConnection();

        try {
            Statement s = myConnection.createStatement();
            s.execute("SELECT * FROM users WHERE uname ='" + username + "'");
            ResultSet rs = s.getResultSet();

            //check if user already exists
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Username already exists!", "Invalid username", JOptionPane.WARNING_MESSAGE);
            } else {
                s.execute("INSERT INTO users VALUES('" + username + "'," + weight + ",'" + fullName + "','" + password + "')");
                JOptionPane.showMessageDialog(null, "Account successfully created!", "Success", JOptionPane.PLAIN_MESSAGE);
                LoginRegisterFrame.alreadyHaveButton.doClick(); //go to login screen
            }

            s.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        closeConnection();

    }


    public static String loginUser(String username, String password) {

        createConnection();

        try {
            Statement s = myConnection.createStatement();
            s.execute("SELECT * FROM users WHERE password='" + password + "' AND uname='" + username + "'");
            ResultSet rs = s.getResultSet();

            //verify if correct combination
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password!", "Invalid credentials", JOptionPane.WARNING_MESSAGE);
                return "";

            } else {
                //set username and weight of current user
                uname = rs.getString(1);
                userWeightInKg = rs.getDouble(2);

                //open main app (dashboard)
                AppFrame appFrame = new AppFrame();
                appFrame.setLocationRelativeTo(null);
                appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                appFrame.setVisible(true);
            }

            s.close();

        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        closeConnection();

        return "sucess";

    }

    public static String getUname() {
        return uname;
    }

    public static double getUserWeight() {
        return userWeightInKg;
    }


    /*public Address searchAddress(String name)
    {
        Address address = null;
        try{
            Statement s = myConnection.createStatement();
            s.execute("SELECT address,phone,email FROM Address WHERE name='"+name+"'");

            ResultSet rs = s.getResultSet();


            while(rs.next())
            {
              address = new Address(name,rs.getString(1),rs.getString(2),rs.getString(3));
            }

        } catch (SQLException sqlException)
        {
            return null;
        }

        return address;
    }*/

    /*public String updateAddress(String name,String address,String phone,String email)
    {
        try {
            Statement s = myConnection.createStatement();



            s.execute("UPDATE Address SET address='"+address+"' WHERE name='"+name+"'");

            s.close();


        } catch (SQLException sqlException)
        {
            return "failed";
        }

        return "success";
    }*/

}
