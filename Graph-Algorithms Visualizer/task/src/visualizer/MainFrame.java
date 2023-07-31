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
    private final JLabel modeLabel;

    public MainFrame() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graph-Algorithms Visualizer");
        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        setBackground(Color.black);
        pack();

        setJMenuBar(createMenu());

        modeLabel = new JLabel("Add a Vertex");
        modeLabel.setName("Mode");
        modeLabel.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 18));
        modeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        modeLabel.setOpaque(false);

        graph = new Graph();

        add(modeLabel, BorderLayout.NORTH);

        graph.addMouseListener(handleAddVertex());

        add(graph, BorderLayout.CENTER);
        setVisible(true);
    }

    private JMenuBar createMenu() {
        JMenuItem addVertex = new JMenuItem("Add a Vertex");
        addVertex.addActionListener(e -> {
            modeLabel.setText("Add a Vertex");
            removeActionListeners();
            graph.addMouseListener(handleAddVertex());
        });
        addVertex.setName("Add a Vertex");

        JMenuItem addEdge = new JMenuItem("Add an Edge");
        addEdge.addActionListener(e -> {
            modeLabel.setText("Add an Edge");
            removeActionListeners();
            handleAddEdge();
        });
        addEdge.setName("Add an Edge");

        JMenuItem removeVertex = getRemoveVertexMenuItem();

        JMenuItem removeEdge = getRemoveEdgeMenuItem();

        JMenuItem none = new JMenuItem("None");
        none.addActionListener(e -> {
            modeLabel.setText("None");
            removeActionListeners();
        });
        none.setName("None");

        JMenu menu = new JMenu("Menu");
        menu.setName("Menu");
        menu.add(addVertex);
        menu.add(addEdge);
        menu.add(removeVertex);
        menu.add(removeEdge);
        menu.add(none);

        JMenuItem newGraph = new JMenuItem("New");
        newGraph.addActionListener(e -> {
            modeLabel.setText("Add a Vertex");
            removeActionListeners();
            graph.clear();
            graph.addMouseListener(handleAddVertex());
        });
        newGraph.setName("New");

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> {
            removeActionListeners();
            System.exit(0);
        });
        exit.setName("Exit");


        JMenu file = new JMenu("File");
        file.setName("File");
        file.add(newGraph);
        file.add(exit);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        menuBar.add(menu);
        return menuBar;
    }

    private JMenuItem getRemoveEdgeMenuItem() {
        JMenuItem removeEdge = new JMenuItem("Remove an Edge");
        removeEdge.addActionListener(e -> {
            modeLabel.setText("Remove an Edge");
            removeActionListeners();
            graph.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    graph.getEdges().forEach((k, v) -> {
                        if (v.getBounds().contains(e.getPoint())) {
                            System.out.println("Edge " + k + " was clicked");
                            graph.removeEdge(v);
                        }
                    });
                }
            });
        });
        removeEdge.setName("Remove an Edge");
        return removeEdge;
    }

    private JMenuItem getRemoveVertexMenuItem() {
        JMenuItem removeVertex = new JMenuItem("Remove a Vertex");
        removeVertex.addActionListener(e -> {
            modeLabel.setText("Remove a Vertex");
            removeActionListeners();
            graph.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    graph.getVertices().forEach((k, v) -> {
                        if (v.getBounds().contains(e.getPoint())) {
                            System.out.println("Vertex " + k + " was clicked");
                            graph.removeVertex(v);
                            graph.getEdges().forEach((k1, v1) -> {
                                if (v1.getV1() == v || v1.getV2() == v) {
                                    graph.removeEdge(v1);
                                }
                            });
                        }
                    });
                }
            });
        });
        removeVertex.setName("Remove a Vertex");
        return removeVertex;
    }

    private void removeActionListeners() {
        if (graph.getMouseListeners().length > 0) {
            for (int i = 0; i < graph.getMouseListeners().length; i++) {
                graph.removeMouseListener(graph.getMouseListeners()[i]);
            }
        }
    }

    private MouseListener handleAddVertex() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Mouse clicked at " + e.getX() + ", " + e.getY());
                for (Component c : graph.getComponents()) {
                    if (c.getBounds().contains(e.getPoint())) {
                        System.out.println("Collision detected");
                        return;
                    }
                }
                graph.addVertex(e.getX(), e.getY());
            }
        };
    }

    private void handleAddEdge() {
        Vertex[] vertices = new Vertex[2];
        graph.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                graph.getVertices().forEach((k, v) -> {
                    if (v.getBounds().contains(e.getPoint())) {
                        System.out.println("Vertex " + k + " was clicked");
                        if (vertices[0] == null) {
                            vertices[0] = v;
                        } else if (vertices[1] == null && vertices[0] != v) {
                            vertices[1] = v;
                            String weight = JOptionPane.showInputDialog("Enter the weight of the edge");
                            boolean isWeightValid = false;
                            while (!isWeightValid) {
                                if (weight == null) {
                                    vertices[0] = null;
                                    vertices[1] = null;
                                    return;
                                }
                                if (weight.matches("-?[0-9]+")) {
                                    isWeightValid = true;
                                } else {
                                    weight = JOptionPane.showInputDialog("Enter a valid weight");
                                }
                            }
                            graph.addEdge(vertices[0], vertices[1], Integer.parseInt(weight));
                            vertices[0] = null;
                            vertices[1] = null;
                        }
                    }
                });
            }
        });
    }

}