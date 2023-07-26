package visualizer;

import javax.swing.*;

public class ApplicationRunner {

    public static void main(String[] args) throws Exception {
        Runnable initFrame = MainFrame::new;

        SwingUtilities.invokeAndWait(initFrame);
    }
}
