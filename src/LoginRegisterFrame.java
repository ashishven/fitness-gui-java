import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegisterFrame extends JFrame {
    JPanel contentPanel;
   static JButton alreadyHaveButton;

    JButton createAccountButton;
    RegisterPanel registerPanel;
    LoginPanel loginPanel;
    ImageIcon registerImage;
    ImageIcon loginImage;
    JLabel imageLabel;

   public LoginRegisterFrame ()
    {
        setLayout(new FlowLayout(FlowLayout.CENTER,0,15));

        JLabel appTitle = new JLabel("myFitness App");
        appTitle.setFont(new Font("Inter",Font.BOLD,40));
        appTitle.setHorizontalAlignment(SwingConstants.CENTER);
        appTitle.setPreferredSize(new Dimension(800,60));

        registerImage = new ImageIcon("registerImage.png");
        loginImage = new ImageIcon("loginImage.png");

        imageLabel = new JLabel(loginImage);
        registerPanel = new RegisterPanel();



        loginPanel = new LoginPanel();
        loginPanel.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username =  loginPanel.unamefield.getText();
                String password =  loginPanel.pwdfield.getText();

                String msg = FitnessDatabase.loginUser(username,password);

                if(msg.equals("sucess"))
                {
                    dispose();
                }

            }
        });


        contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,30,0));
        contentPanel.setBackground(Color.white);
        contentPanel.add(imageLabel);
        contentPanel.add(loginPanel); //add login panel


        alreadyHaveButton = new JButton("Already have an account");
        alreadyHaveButton .setBackground(new Color(217,217,217));
        alreadyHaveButton .setForeground(Color.black);
        alreadyHaveButton .setBorder(BorderFactory.createEmptyBorder());
        alreadyHaveButton .setFont(new Font("SansSerif",Font.PLAIN,19));
        alreadyHaveButton .setPreferredSize(new Dimension(230,40));
        alreadyHaveButton.setFocusable(false);

        createAccountButton = new JButton("Create account");
        createAccountButton .setBackground(new Color(217,217,217));
        createAccountButton .setForeground(Color.black);
        createAccountButton .setBorder(BorderFactory.createEmptyBorder());
        createAccountButton .setFont(new Font("SansSerif",Font.PLAIN,19));
        createAccountButton .setPreferredSize(new Dimension(200,40));
        createAccountButton.setFocusable(false);


        ButtonHandler buttonHandler = new ButtonHandler();

        alreadyHaveButton.addActionListener(buttonHandler);
        createAccountButton.addActionListener(buttonHandler);

        add(appTitle);
        add(contentPanel);
        add(createAccountButton);



        getContentPane().setBackground(Color.white);



    }



    private class ButtonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //remove login panel and switch to register panel
            if(e.getSource()==createAccountButton)
            {
                contentPanel.remove(loginPanel);
                imageLabel.setIcon(null);
                remove(createAccountButton);
                revalidate();
                repaint();
                imageLabel.setIcon(registerImage);
                contentPanel.add(registerPanel);
                add(alreadyHaveButton);
                revalidate();
                repaint();
            }
            //remove register panel and add login panel
            if(e.getSource()==alreadyHaveButton)
            {
                contentPanel.remove(registerPanel);
                imageLabel.setIcon(null);
                remove(alreadyHaveButton);
                revalidate();
                repaint();
                imageLabel.setIcon(loginImage);
                contentPanel.add(loginPanel);
                add(createAccountButton);
                revalidate();
                repaint();
            }
        }
    }
}
