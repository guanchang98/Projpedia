import javax.swing.*;
import java.net.URL;

/**
 * This class creates a basic frame that is shared in every page of the client application.
 * It extends JFrame to make use of all methods provided by it.
 */
public class BasicFrame extends JFrame {
    /**
     * Constructor of the class.
     */
    public BasicFrame() {
        super();
        this.setTitle("ProjPedia");
        this.setBounds(100, 100, 700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
    }

    /**
     * This method is used to add background to every page.
     * This ensures the consistency of our application's background.
     */
    public void addBackground() {
        JLabel lblBackground = new JLabel(); // Initialize
        URL resource = this.getClass().getResource("/background.jpg"); // Image path
        ImageIcon icon = new ImageIcon(resource); // Create image object
        lblBackground.setIcon(icon); // Set image
        lblBackground.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // Position and size
        this.getContentPane().add(lblBackground); // Add components
    }
}
