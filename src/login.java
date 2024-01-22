import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JFrame implements ActionListener
{
	private JLabel userLabel, passwordLabel, backgroundLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, cancelButton;
    private JPanel inputPanel, buttonPanel;

    public login() {
        super("Login Page");

        userLabel = new JLabel("User Name");
        passwordLabel = new JLabel("Password");
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = createStyledButton("Login", "images/login.jpg");
        cancelButton = createStyledButton("Cancel", "images/cancel.png");

        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);

        backgroundLabel = new JLabel(new ImageIcon(getScaledImage("images/pop.jpg", 340, 340)));

        setLayout(new BorderLayout());

        inputPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        
        int topPadding = 60; // You can adjust this value according to your needs
        EmptyBorder emptyBorder = new EmptyBorder(topPadding, 0, 0, 0);
        
        // Set the EmptyBorder to the inputPanel
        inputPanel.setBorder(emptyBorder);
//        buttonPanel = new JPanel();

        inputPanel.add(userLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(loginButton);
        inputPanel.add(cancelButton);

//        buttonPanel.add(loginButton);
//        buttonPanel.add(cancelButton);

        inputPanel.setBackground(Color.WHITE);
//        buttonPanel.setBackground(Color.WHITE);

        add(backgroundLabel, BorderLayout.WEST);
        add(inputPanel, BorderLayout.CENTER);
//        add(buttonPanel, BorderLayout.SOUTH);

        setSize(700, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    private JButton createStyledButton(String text, String imagePath) {
        ImageIcon icon = new ImageIcon(getScaledImage(imagePath, 30, 30));
        JButton button = new JButton(text, icon);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    // Helper method to scale images
    private Image getScaledImage(String imagePath, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource(imagePath));
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(image).getImage();
    }

    public void actionPerformed(ActionEvent ae){
        try {
            if (ae.getSource() == loginButton) {
                conn c1 = new conn();
                String a = usernameField.getText();
                String b = new String(passwordField.getPassword());
                String q = "select * from login where username = '" + a + "' and password = '" + b + "'";
                ResultSet rs = c1.s.executeQuery(q);
                if (rs.next()) {
                    new Project().setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login");
                    setVisible(false);
                }
            } else if (ae.getSource() == cancelButton) {
                // Implement cancel functionality
                // For now, exit the application
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error: " + e);
        }
    }


    public static void main(String[] args){
        new login().setVisible(true);
    }

}
