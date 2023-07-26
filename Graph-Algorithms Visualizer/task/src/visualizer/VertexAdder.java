package visualizer;

import javax.swing.*;

public class VertexAdder extends JOptionPane {

    public VertexAdder(Graph graph, int x, int y){

        String vertexId = showInputDialog(graph.getPanel(), "Enter the vertexID (should be 1 char): ",
                "Vertex", JOptionPane.PLAIN_MESSAGE);

        if(vertexId == null){
            return;
        } else if (vertexId.isBlank() || vertexId.length() != 1 || graph.getVertices().containsKey(vertexId.charAt(0))) {
            new VertexAdder(graph, x, y);
        } else {
            graph.addVertex(x - 33 , y - 56 , 50, vertexId.charAt(0));
        }
    }
}
