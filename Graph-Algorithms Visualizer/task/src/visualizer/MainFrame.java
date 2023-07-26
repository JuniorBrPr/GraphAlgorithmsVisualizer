package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame {
    private static final int PREFERRED_WIDTH = 800;
    private static final int PREFERRED_HEIGHT = 600;


    private final Graph graph;

    public MainFrame() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graph-Algorithms Visualizer");
        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        setBackground(Color.black);
        pack();

        int width = getContentPane().getWidth();
        int height = getContentPane().getHeight();

        graph = new Graph();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new VertexAdder(graph,e.getX(), e.getY());
            }
        });

        add(graph.getPanel(), BorderLayout.CENTER);
        setVisible(true);
    }
}