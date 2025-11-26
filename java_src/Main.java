import gui.MainFrame;

/**
 * Main Entry Point
 * Launches the Event Management System application
 */
public class Main {
    
    public static void main(String[] args) {
        // Start the GUI application
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
