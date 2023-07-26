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

//        panel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                new VertexAdder(Graph.this,e.getX(), e.getY());
//            }
//        });
    }

    public void addVertex(int x, int y, int size, char id) {
        Vertex v = new Vertex(id, x, y, size);
        vertices.put(id, v);
        panel.add(v.getPanel());
        panel.repaint();
    }

    protected JPanel getPanel() {
        return panel;
    }

    public Map<Character, Vertex> getVertices() {
        return vertices;
    }
}
