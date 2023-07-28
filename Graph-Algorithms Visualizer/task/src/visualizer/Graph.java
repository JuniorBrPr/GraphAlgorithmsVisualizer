package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JOptionPane.showInputDialog;

public class Graph {
    private final Map<Character, Vertex> vertices;
    private final Map<String, Edge> edges;
    private final int VERTEX_SIZE = 50;
    private final JPanel panel = createPanel();

    public Graph() {
        vertices = new HashMap<>();
        edges = new HashMap<>();

    }

    public void addVertex(MouseEvent e) {
        System.out.println("Adding vertex");

        String vertexId = showInputDialog(getPanel(), "Enter the vertexID (should be 1 char): ",
                "Vertex", JOptionPane.PLAIN_MESSAGE);
        if (vertexId == null) {
            return;
        } else if (vertexId.isBlank() || vertexId.length() != 1 || vertices.containsKey(vertexId.charAt(0))) {
            addVertex(e);
        } else {
            Vertex v = new Vertex(vertexId.charAt(0), e.getX(), e.getY(), VERTEX_SIZE);
            vertices.put(v.getId(), v);
            System.out.println(v);
        }
    }

    public void addEdge(Vertex v1, Vertex v2) {
        String weight = showInputDialog(getPanel(), "Enter the weight of the edge: ",
                "Edge", JOptionPane.PLAIN_MESSAGE);

        if (weight == null) {
            return;
        } else {
            if (weight.isBlank() || !weight.matches("[0-9]+") || Integer.parseInt(weight) < 0) {
                System.out.println("Is not a number, or is negative");
                addEdge(v1, v2);
            } else {
                System.out.println("Adding edge: " + v1.getId() + " -> " + v2.getId() + " with weight: " + weight);
                String edgeId = new String(new char[]{v1.getId(), v2.getId()});
                if (edges.containsKey(edgeId)) {
                    return;
                }
                Edge e = new Edge(v1, v2, Integer.parseInt(weight));
                edges.put(edgeId, e);
            }
        }
    }


    protected JPanel createPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.BLACK);
        panel.setName("Graph");
        return panel;
    }

    protected JPanel getPanel() {
        JPanel panel = createPanel();
        vertices.forEach((k, v) -> panel.add(v.getPanel()));
        edges.forEach((k, v) -> panel.add(v.getPanel()));

//        panel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                System.out.println("mouse");
//                addVertex();
//            }
//        });

        System.out.println("Added vertices and edges");

        return panel;
    }

    public Map<Character, Vertex> getVertices() {
        return vertices;
    }
}
