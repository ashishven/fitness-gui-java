import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    JLabel title, uname, fname,weight,pwd;
    JTextField unamefield;
    JPasswordField pwdfield;
    JButton loginButton;

    public LoginPanel(){
        setLayout(new FlowLayout(FlowLayout.CENTER,0,40));

        title = new JLabel("Login");

        uname = new JLabel("Username");
        pwd = new JLabel("Password");

        unamefield = new JTextField(10);

        pwdfield = new JPasswordField(10);

        loginButton = new JButton("Login");

        JPanel titlepanel = new JPanel();
        JPanel fieldpanel = new JPanel();
        JPanel buttonpanel = new JPanel();

        titlepanel.add(title);
        title.setHorizontalAlignment(JLabel.CENTER);


        fieldpanel.setLayout(new GridLayout(2,2,0,25));
        fieldpanel.add(uname); fieldpanel.add(unamefield);
        fieldpanel.add(pwd); fieldpanel.add(pwdfield);



        add(titlepanel);
        add(fieldpanel);
        add(loginButton);

        //styling
        titlepanel.setBackground(Color.decode("#FFFFFF"));
        fieldpanel.setBackground(Color.decode("#FFFFFF"));



        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        uname.setFont(new Font("SansSerif", Font.PLAIN, 19));
        uname.setHorizontalAlignment(SwingConstants.CENTER);
        pwd.setHorizontalAlignment(SwingConstants.CENTER);

        pwd.setFont(new Font("SansSerif", Font.PLAIN, 19));

        unamefield.setFont(new Font("SansSerif", Font.PLAIN, 18));

        pwdfield.setFont(new Font("SansSerif", Font.PLAIN, 18));

        unamefield.setBorder(BorderFactory.createLineBorder( new Color(55,148,233)));

        pwdfield.setBorder(BorderFactory.createLineBorder( new Color(55,148,233)));

        loginButton.setBackground(new Color(217,217,217));
        loginButton.setForeground(Color.black);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setFont(new Font("SansSerif",Font.PLAIN,18));
        loginButton.setPreferredSize(new Dimension(100,40));
        loginButton.setFocusable(false);




        setPreferredSize(new Dimension(350,380));
        setBackground(Color.white);
        





    }


}
