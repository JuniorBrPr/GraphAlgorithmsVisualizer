package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private static final int PREFERRED_WIDTH = 800;
    private static final int PREFERRED_HEIGHT = 600;
    private final Graph graph;
    private final JLabel modeLabel;

    protected MainFrame() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graph-Algorithms Visualizer");
        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        setBackground(Color.black);
        pack();

        setJMenuBar(createMenu());

        modeLabel = new JLabel("Add a Vertex");
        modeLabel.setName("ModeLabel");
        modeLabel.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 18));
        modeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        modeLabel.setOpaque(false);

        graph = new Graph();
        handleAddVertex();

        add(modeLabel, BorderLayout.NORTH);
        add(graph.getPanel(), BorderLayout.CENTER);
        setVisible(true);
    }

    private JMenuBar createMenu() {
        JMenu menu = new JMenu("Menu");
        menu.setName("Menu");

        JMenuItem addVertex = new JMenuItem("Add a Vertex");
        JMenuItem addEdge = new JMenuItem("Add an Edge");
        JMenuItem none = new JMenuItem("None");

        addVertex.addActionListener(e -> {
            modeLabel.setText("Add a Vertex");
            graph.getPanel().removeMouseListener(graph.getPanel().getMouseListeners()[0]);
            handleAddVertex();
        });

        addEdge.addActionListener(e -> {
            modeLabel.setText("Add an Edge");
            graph.getPanel().removeMouseListener(graph.getPanel().getMouseListeners()[0]);
            handleAddEdge();
        });

        none.addActionListener(e -> {
            modeLabel.setText("None");
            graph.getPanel().removeMouseListener(graph.getPanel().getMouseListeners()[0]);
        });

        menu.add(addVertex);
        menu.add(addEdge);
        menu.add(none);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        return menuBar;
    }

    private void handleAddEdge(){
        Vertex[] vertices = new Vertex[2];
        graph.getPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                graph.getVertices().forEach((k, v) -> {
                    if (v.getComponent().getBounds().contains(e.getPoint())) {
                        System.out.println("Vertex " + k + " was clicked");
                        if (vertices[0] == null) {
                            vertices[0] = v;
                        } else if (vertices[1] == null && vertices[0] != v) {
                            vertices[1] = v;
                            String weight = JOptionPane.showInputDialog("Enter the weight of the edge");
                            if (weight == null) {
                                vertices[0] = null;
                                vertices[1] = null;
                                return;
                            }
                            if (weight.isEmpty()) {
                                weight = "1";
                            }
                            if (weight.matches("[0-9]+")) {
                                graph.addEdge(vertices[0], vertices[1], Integer.parseInt(weight));
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid weight");
                            }
                            vertices[0] = null;
                            vertices[1] = null;
                        }
                    }
                });
            }
        });
    }

    private void handleAddVertex(){
        graph.getPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Mouse clicked at " + e.getX() + ", " + e.getY());
                graph.addVertex(e.getX(), e.getY());
            }
        });
    }
}