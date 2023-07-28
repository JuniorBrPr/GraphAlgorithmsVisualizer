package visualizer;

import javax.swing.*;
import java.awt.*;

public class Vertex {
    private final char id;
    private final int x;
    private final int y;
    private final int SIZE = 50;

    public Vertex(char id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    protected JComponent getComponent() {
        JComponent component = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillOval(0, 0, SIZE, SIZE);
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(id), SIZE / 2, SIZE / 2);
            }
        };
        component.setBounds(x, y, SIZE, SIZE);
        return component;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}