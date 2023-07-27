package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    private final JPanel panel;
    private final Map<Character, Vertex> vertices;

    public Graph() {
        vertices = new HashMap<>();
        panel = new JPanel(null);
        panel.setBackground(Color.BLACK);
        panel.setName("Graph");

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new VertexAdder(Graph.this,e.getX(), e.getY());
            }
        });
    }

    public void addVertex(int x, int y, int size, char id) {
        Vertex v = new Vertex(id, x, y, size);
        vertices.put(id, v);
        panel.add(v.getPanel());
        panel.repaint();
    }

    public void addEdge(Vertex v1, Vertex v2, int weight){
        JComponent edge = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.drawLine(v1.getX() + 25, v1.getY() + 25, v2.getX() + 25, v2.getY() + 25);
                g.drawString(String.valueOf(weight), (v1.getX() + v2.getX()) / 2, (v1.getY() + v2.getY()) / 2);
            }
        };
//        edge.setBackground(Color.cyan);
        edge.setBounds(0, 0, 1000, 1000);
        edge.setBackground(null);
        panel.add(edge);
        panel.repaint();
    }

//    public void addEdge(Vertex v1, Vertex v2, int weight){
//        Edge edge = new Edge(v1, v2, weight);
//        panel.add(edge);
//        panel.repaint();
//    }

    protected JPanel getPanel() {
        return panel;
    }

    public Map<Character, Vertex> getVertices() {
        return vertices;
    }
}
