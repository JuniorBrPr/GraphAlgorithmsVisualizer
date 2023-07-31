package visualizer;

import javax.swing.*;
import java.awt.*;


public class Edge extends JComponent {
    private final Vertex v1;
    private final Vertex v2;
    private final int weight;

    public Edge(Vertex v1, Vertex v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
        setLayout(null);

        setBackground(Color.pink);
        setBounds(
                Math.min(v1.getX() + Vertex.SIZE / 2, v2.getX() + Vertex.SIZE / 2),
                Math.min(v1.getY() + Vertex.SIZE / 2, v2.getY() + Vertex.SIZE / 2),
                getComponentWidth(),
                getComponentHeight()
        );

        setName("Edge <" + v1.getId() + " -> " + v2.getId() + ">");
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawLine(v1.getX(), v1.getY(), v2.getX(), v2.getY());

        g.setColor(Color.RED);

        g.drawLine(
                v1.getX() < v2.getX() ? 0 : getComponentWidth(),
                v1.getY() < v2.getY() ? 0 : getComponentHeight(),
                v1.getX() < v2.getX() ? getComponentWidth() : 0,
                v1.getY() < v2.getY() ? getComponentHeight() : 0
        );

        g.setColor(Color.cyan);
        g.drawRect(
                0,
                0,
                getComponentWidth() - 1,
                getComponentHeight() - 1
        );

    }

    private int getComponentWidth() {
        return Math.max(v1.getX() - v2.getX(), v2.getX() - v1.getX()) - 1;
    }

    private int getComponentHeight() {
        return Math.max(v1.getY() - v2.getY(), v2.getY() - v1.getY()) - 1;
    }

    protected Vertex getV1() {
        return v1;
    }

    protected Vertex getV2() {
        return v2;
    }
}
