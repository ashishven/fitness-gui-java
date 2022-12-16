import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RegisterPanel extends JPanel {

    JLabel title, uname, fname,weight,pwd;
    JTextField unamefield, fnamefield, weightfield;
    JPasswordField pwdfield;
    JButton registerButton;

    public RegisterPanel(){
        setLayout(new FlowLayout(FlowLayout.CENTER,0,30));

        title = new JLabel("Register");


        uname = new JLabel("Username");
        fname = new JLabel("Full name");
        weight = new JLabel("Weight in Kg");
        pwd = new JLabel("Password");

        unamefield = new JTextField(10);
        fnamefield = new JTextField(10);
        weightfield= new JTextField(10);
        pwdfield = new JPasswordField(10);

        registerButton = new JButton("Register");

        JPanel titlepanel = new JPanel();
        JPanel fieldpanel = new JPanel();
        JPanel buttonpanel = new JPanel();

        titlepanel.add(title);
        title.setHorizontalAlignment(JLabel.CENTER);


        fieldpanel.setLayout(new GridLayout(4,2,20,25));
        fieldpanel.add(uname); fieldpanel.add(unamefield);
        fieldpanel.add(fname); fieldpanel.add(fnamefield);
        fieldpanel.add(weight); fieldpanel.add(weightfield);
        fieldpanel.add(pwd); fieldpanel.add(pwdfield);




        add(titlepanel);
        add(fieldpanel);
        add(registerButton);

        //styling
        titlepanel.setBackground(Color.decode("#FFFFFF"));
        fieldpanel.setBackground(Color.decode("#FFFFFF"));



        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        uname.setFont(new Font("SansSerif", Font.PLAIN, 19));
        fname.setFont(new Font("SansSerif", Font.PLAIN, 19));
        weight.setFont(new Font("SansSerif", Font.PLAIN, 19));
        pwd.setFont(new Font("SansSerif", Font.PLAIN, 19));

        unamefield.setFont(new Font("SansSerif", Font.PLAIN, 18));
        fnamefield.setFont(new Font("SansSerif", Font.PLAIN, 18));
        weightfield.setFont(new Font("SansSerif", Font.PLAIN, 18));
        pwdfield.setFont(new Font("SansSerif", Font.PLAIN, 18));

        unamefield.setBorder(BorderFactory.createLineBorder( new Color(55,148,233)));
        fnamefield.setBorder(BorderFactory.createLineBorder( new Color(55,148,233)));
        weightfield.setBorder(BorderFactory.createLineBorder( new Color(55,148,233)));
        pwdfield.setBorder(BorderFactory.createLineBorder( new Color(55,148,233)));

        registerButton.setBackground(new Color(217,217,217));
        registerButton.setForeground(Color.black);
        registerButton.setBorder(BorderFactory.createEmptyBorder());
        registerButton.setFont(new Font("SansSerif",Font.PLAIN,18));
        registerButton.setPreferredSize(new Dimension(100,40));
        registerButton.setFocusable(false);


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //get data from text fields
                String username = unamefield.getText();
                String fullname = fnamefield.getText();
                double weight =0;

                //check if weight contains only numbers
                try{
                   weight = Double.parseDouble(weightfield.getText());
                } catch (Exception exc)
                {
                    JOptionPane.showMessageDialog(null,"Invalid Weight","Error",JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String password= pwdfield.getText();
                FitnessDatabase.registerUser(username,fullname,weight,password);

            }
        });


        setPreferredSize(new Dimension(350,380));
        setBackground(Color.white);






    }


}
