package visualizer;

import javax.swing.*;
import java.awt.*;

public class Vertex extends JPanel {
    private final char id;
    private final int x;
    private final int y;
    public static final int SIZE = 50;

    public Vertex(char id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        setLayout(null);
        setBackground(Color.black);
        setName("Vertex " + id);
        JLabel label = new JLabel(String.valueOf(id));
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setName("VertexLabel " + id);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setBounds(0, 0, SIZE, SIZE);
        setBounds(x , y , SIZE, SIZE);
        add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, SIZE, SIZE);
        g.setColor(Color.CYAN);
        g.drawRect(0, 0, SIZE - 1, SIZE - 1);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getId() {
        return String.valueOf(id);
    }
}