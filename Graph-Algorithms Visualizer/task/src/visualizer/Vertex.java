package visualizer;

import javax.swing.*;
import java.awt.*;

public class Vertex {
    private final char id;
    private final int x;
    private final int y;
    private final int size;

    public Vertex(char id, int x, int y, int size) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public char getId() {
        return id;
    }

    protected JPanel getPanel() {
        JPanel panel = createPanel();
        panel.add(createLabel(), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillOval(x, y, size, size);
            }
        };
        panel.setBounds(x, y, size, size);
        panel.setBackground(Color.cyan);
        panel.setName("Vertex " + id);
        System.out.println(panel.getName());
        return panel;
    }

    private JLabel createLabel() {
        JLabel label = new JLabel(String.valueOf(id));
        label.setName("VertexLabel " + id);
        label.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 25));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        return label;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", size=" + size +
                '}';
    }
}