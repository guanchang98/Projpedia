import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * This class creates the login page of the client application.
 * It is opened when the user clicks on the login button in the welcome page.
 * It implements ActionListener interface to support onclick() functionality.
 */
public class LoginPage implements ActionListener {
    BasicFrame frame = new BasicFrame();
    JPanel panel = new JPanel();

    JLabel welcomeLabel = new JLabel("Welcome to ProjectPedia - Login");
    JLabel userLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel portLabel = new JLabel("Server Port:");

    JTextField userText = new JTextField(20);
    JPasswordField passwordText = new JPasswordField(20);
    JTextField portText = new JTextField(20);

    JButton loginButton = new JButton("submit");
    JButton backButton = new JButton("back");
    public LoginPage() {

        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 30));
        welcomeLabel.setBounds(150, 120, 500, 40);
        welcomeLabel.setForeground(Color.white);
        userLabel.setFont(new Font(userLabel.getFont().getName(), Font.BOLD, 13));
        userLabel.setBounds(230,190,80,25);
        userLabel.setForeground(Color.white);
        userText.setBounds(320,190,165,25);
        passwordLabel.setFont(new Font(passwordLabel.getFont().getName(), Font.BOLD, 13));
        passwordLabel.setBounds(230,220,80,25);
        passwordLabel.setForeground(Color.white);
        passwordText.setBounds(320,220,165,25);
        portLabel.setFont(new Font(portLabel.getFont().getName(), Font.BOLD, 13));
        portLabel.setBounds(230,250,80,25);
        portLabel.setForeground(Color.white);
        portText.setBounds(320,250,165,25);
        loginButton.setBounds(400, 300, 80, 25);
        backButton.setBounds(230, 300, 80, 25);

        loginButton.addActionListener(this);
        backButton.addActionListener(this);
        panel.setLayout(null);
        panel.add(welcomeLabel);
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(portLabel);
        panel.add(portText);
        panel.add(loginButton);
        panel.add(backButton);
        frame.setContentPane(panel);

        frame.addBackground();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {


            if (userText.getText() == null || passwordText.getPassword() == null || portText.getText() == null) {
                JOptionPane.showMessageDialog(null, "Field should not be empty", "alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int port = -1;
            try {
                port = Integer.parseInt(portText.getText());
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Please enter correct port number", "alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (port < 0) {
                JOptionPane.showMessageDialog(null, "Please enter correct port number", "alert", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String hostname = "127.0.0.1";
            Registry registry = null;
            RemoteUserInterface stub = null;
            String username = userText.getText();
            String password = String.valueOf(passwordText.getPassword());
            String loginResponse = null;
            try {
                registry = LocateRegistry.getRegistry(hostname, port);
                stub = (RemoteUserInterface) registry.lookup("PROJPEDIA");
                loginResponse = stub.login(username, password);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Server connection failed", "alert", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (loginResponse.equals("Already login as " + username + ". Please logout first")) {
                JOptionPane.showMessageDialog(null, "Already logged in as " + username + ". Please logout first", "alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else if (loginResponse.equals("Login successfully")) {
                JOptionPane.showInternalMessageDialog(null, "[" + System.currentTimeMillis() + "] " + "Login successfully!", "information", JOptionPane.INFORMATION_MESSAGE);
                new MainPage(username, stub);
                frame.dispose();
            }
            else {
                JOptionPane.showMessageDialog(null, "[" + System.currentTimeMillis() + "] " + "Wrong username or password. Please enter and try again", "alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (e.getSource() == backButton) {
            frame.dispose();
            new WelcomePage();
        }


    }
}