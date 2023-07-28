package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private static final int PREFERRED_WIDTH = 800;
    private static final int PREFERRED_HEIGHT = 600;
    private Graph graph;
    private JLabel modeLabel;
    private JPanel graphPanel;

    public MainFrame() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graph-Algorithms Visualizer");
        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        setBackground(Color.black);
        pack();

        createModeLlb();
        setJMenuBar(createMenu());

        graph = new Graph();
        graphPanel = graph.getPanel();

        addVertexMode();

        add(modeLabel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);
        setVisible(true);
    }

//    private void loadPanel(){
//        if(this.getComponents().length > 1){
//            for(Component c : this.getComponents()){
//                if(c.getName().equals("Graph")){
//                    this.remove(c);
//                }
//            }
//        }
//        JPanel panel = graph.getPanel();
//        panel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                System.out.println("mouse");
//
//            }
//        });
//
//        this.add(graph.getPanel(), BorderLayout.CENTER);
//        System.out.println("Added panel");
//    }

    private JMenuBar createMenu() {
        JMenuItem addVertex = new JMenuItem("Add a Vertex");
        addVertex.addActionListener(e -> addVertexMode());
        JMenuItem addEdge = new JMenuItem("Add an Edge");
        addEdge.addActionListener(this::addEdgeMode);
        JMenuItem none = new JMenuItem("None");
        none.addActionListener(this::noneMode);
        JMenu menu = new JMenu("Menu");
        menu.setName("Menu");
        menu.add(addVertex);
        menu.add(addEdge);
        menu.add(none);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        return menuBar;
    }

    private void createModeLlb() {
        modeLabel = new JLabel("Add a Vertex");
        modeLabel.setName("ModeLabel");
        modeLabel.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 18));
        modeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        modeLabel.setOpaque(false);
    }

    private void noneMode(ActionEvent e) {
        modeLabel.setText("None");
        removeListeners();
    }

    private void addVertexMode() {
        modeLabel.setText("Add a Vertex");
        graphPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                graph.addVertex(e);
                graphPanel = graph.getPanel();
                add(graphPanel, BorderLayout.CENTER);
            }
        });
    }

    private void addEdgeMode(ActionEvent e) {
        modeLabel.setText("Add an Edge");
        removeListeners();

        Vertex[] vertices = new Vertex[2];
        graph.getPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Mouse clicked");
                graph.getVertices().forEach((k, v) -> {
                    if (v.getPanel().getBounds().contains(e.getPoint())) {
                        System.out.println("Vertex " + k + " was clicked");
                        if (vertices[0] == null) {
                            vertices[0] = v;
                        } else if (vertices[1] == null && vertices[0] != v) {
                            vertices[1] = v;
                            graph.addEdge(vertices[0], vertices[1]);
                            vertices[0] = null;
                            vertices[1] = null;
                        }
                    }
                });
            }
        });
    }

    private void removeListeners() {
        if (graph.getPanel().getMouseListeners().length > 0) {
            graph.getPanel().removeMouseListener(graph.getPanel().getMouseListeners()[0]);
        }
        if (!graph.getVertices().isEmpty()) {
            graph.getVertices().forEach((k, v) -> {
                if (v.getPanel().getMouseListeners().length > 0) {
                    v.getPanel().removeMouseListener(v.getPanel().getMouseListeners()[0]);
                }
            });
        }
    }
}