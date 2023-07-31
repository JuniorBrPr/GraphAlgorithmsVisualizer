package visualizer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JOptionPane.showInputDialog;

public class Graph extends JPanel {
    private final Map<Character, Vertex> vertices;
    private final Map<String, Edge> edges;

    public Graph() {
        vertices = new HashMap<>();
        edges = new HashMap<>();
        setBackground(Color.BLACK);
        setName("Graph");
        setLayout(null);
    }

    public void addVertex(int x, int y) {
        String vertexId = showInputDialog(this, "Enter the vertexID (should be 1 char): ",
                "Vertex", JOptionPane.PLAIN_MESSAGE);

        if (vertexId == null) {
            return;
        } else if (vertexId.isBlank() || vertexId.length() != 1 || vertices.containsKey(vertexId.charAt(0))) {
            addVertex(x, y);
        } else {
            Vertex vertex = new Vertex(vertexId.charAt(0),x - Vertex.SIZE / 2, y - Vertex.SIZE / 2);
            vertices.put(vertexId.charAt(0), vertex);
            add(vertex);
            repaint();
        }
    }

    public void addEdge(Vertex v1, Vertex v2, int weight) {
        Edge edge = new Edge(v1, v2, weight);
        Edge edge2 = new Edge(v2, v1, weight);
        System.out.println(edge.getName());
        System.out.println(edge2.getName());
        JLabel label = new JLabel(String.valueOf(weight));
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setBounds((v1.getX() + v2.getX()) / 2, (v1.getY() + v2.getY()) / 2, 50, 50);
        label.setName("EdgeLabel <" + v1.getId() + " -> " + v2.getId() + ">");

        edges.put(edge.getName(), edge);
        edges.put(edge2.getName(), edge2);
        add(edge);
        add(edge2);
        add(label);
        repaint();
    }

    public void removeVertex(Vertex vertex) {
        vertices.remove(vertex.getId().charAt(0));
        remove(vertex);
        repaint();
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge.getName());
        remove(edge);
        for (Component component : getComponents()) {
            if (component.getName().equals("EdgeLabel <" + edge.getV1().getId() + " -> " +edge.getV2().getId() + ">")) {
                remove(component);
            }
        }
        repaint();
    }

    public void clear() {
        for (Component component : getComponents()) {
            if (component instanceof Vertex) {
                removeVertex((Vertex) component);
            } else if (component instanceof Edge) {
                removeEdge((Edge) component);
            }
        }
    }

    public Map<Character, Vertex> getVertices() {
        return vertices;
    }

    public Map<String, Edge> getEdges() {
        return edges;
    }
}
