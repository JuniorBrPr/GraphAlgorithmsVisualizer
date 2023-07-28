package visualizer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JOptionPane.showInputDialog;

public class Graph {
    private final JPanel panel;
    private final Map<Character, Vertex> vertices;
//    private final Map<String, Edge> edges;

    public Graph() {
        vertices = new HashMap<>();
//        edges = new HashMap<>();
        panel = new JPanel(null);
        panel.setBackground(Color.BLACK);
        panel.setName("Graph");
    }

    public void addVertex(int x, int y) {
        String vertexId = showInputDialog(panel, "Enter the vertexID (should be 1 char): ",
                "Vertex", JOptionPane.PLAIN_MESSAGE);

        if (vertexId == null) {
            return;
        } else if (vertexId.isBlank() || vertexId.length() != 1 || vertices.containsKey(vertexId.charAt(0))) {
            addVertex(x, y);
        } else {
            Vertex vertex = new Vertex(vertexId.charAt(0), x, y);
            vertices.put(vertexId.charAt(0), vertex);
            panel.add(vertex.getComponent());
            panel.repaint();
        }
    }

    public void addEdge(Vertex v1, Vertex v2, int weight) {
        Edge edge = new Edge(v1, v2, weight);
//        edges.put(v1 + "->" + v2, edge);
        panel.add(edge.getComponent());
        panel.repaint();
    }

    protected JPanel getPanel() {
        return panel;
    }

    public Map<Character, Vertex> getVertices() {
        return vertices;
    }
}
