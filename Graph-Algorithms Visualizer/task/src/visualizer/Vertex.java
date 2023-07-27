package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vertex {
    private final char id;
    private final JPanel panel;
    private final JLabel label;
    private final int x;
    private final int y;
    private final int size;

    public Vertex(char id, int x, int y, int size) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.size = size;

        panel = createPanel();
        label = createLabel();

        panel.add(label, BorderLayout.CENTER);
        panel.repaint();
    }

    public char getId() {
        return id;
    }

    protected JPanel getPanel() {
        return panel;
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillOval(0, 0, size, size);
            }
        };

        panel.setName("Vertex " + id);
        panel.setBounds(x, y, size, size);
        panel.setBackground(null);
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
}