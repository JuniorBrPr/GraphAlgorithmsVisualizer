package visualizer;

import javax.swing.*;
import java.awt.*;

public class Edge {
    private final Vertex v1;
    private final Vertex v2;
    private final int weight;

    public Edge(Vertex v1, Vertex v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }
    public JComponent getComponent() {
        JComponent component = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.drawLine(v1.getX() + 25, v1.getY() + 25, v2.getX() + 25, v2.getY() + 25);
                g.drawString(String.valueOf(weight), (v1.getX() + v2.getX()) / 2, (v1.getY() + v2.getY()) / 2);
            }
        };
        component.setBounds(0, 0, 1000, 1000);
        System.out.println("Edge panel created");
        return component;
    }
}
