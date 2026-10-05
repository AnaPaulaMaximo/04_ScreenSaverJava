import javax.swing.SwingUtilities;

import view.ScreenSaver;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            ScreenSaver saver = new ScreenSaver();
            saver.start();
        });
    }
}
