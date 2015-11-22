package Osm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import Graph.DirectedEdge;
import Graph.GraphNode;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class UnusedFns {
	
	public void viewGraph()
	{
		// Initialize a Directed Graph
				DirectedSparseGraph<GraphNode, DirectedEdge> dgraph =
						new DirectedSparseGraph<GraphNode, DirectedEdge>();

		Layout<GraphNode, DirectedEdge> layout = new CircleLayout(dgraph);
		layout.setSize(new Dimension(1000,1000)); // sets the initial size of the space
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		BasicVisualizationServer<GraphNode, DirectedEdge> vv =
				new BasicVisualizationServer<GraphNode,DirectedEdge>(layout);
		vv.setPreferredSize(new Dimension(1000,1000)); //Sets the viewing area size

		Transformer<GraphNode,Paint> vertexPaint = new Transformer<GraphNode,Paint>() {
			public Paint transform(GraphNode i) {
				return Color.GREEN;
			}
		}; 
		float dash[] = {10.0f};
		final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
		Transformer<DirectedEdge, Stroke> edgeStrokeTransformer =
				new Transformer<DirectedEdge, Stroke>() {
			public Stroke transform(DirectedEdge s) {
				return edgeStroke;
			}
		};
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		 vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		 vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		 vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		 vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);



		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true); 
	}

}
/*		// Initialize a Directed Graph
DirectedSparseGraph<GraphNode, DirectedEdge> dgraph =
		new DirectedSparseGraph<GraphNode, DirectedEdge>();
//Iterate through the node list
ListIterator<GraphNode> nodeIterator = nodes.listIterator();
//Adding vertices
while (nodeIterator.hasNext()) {
	GraphNode single_node= nodeIterator.next();
	dgraph.addVertex(single_node);
}
//adding edges
ListIterator<DirectedEdge> listIterator = edges.listIterator();
while (listIterator.hasNext()) {
	DirectedEdge single_edge = listIterator.next();
	dgraph.addEdge(single_edge, single_edge.from(), single_edge.to(),EdgeType.DIRECTED);

}
//Adding weight
Transformer<DirectedEdge, Float> wtTransformer = new Transformer<DirectedEdge,Float>() {
	public Float transform(DirectedEdge link) {
		return link.getWeight();
	}
};

// Calculating Shortest path
Collection<GraphNode> vertices = dgraph.getVertices();
Iterator<GraphNode> iter=vertices.iterator();
GraphNode sampleNode = iter.next();
while(iter.hasNext()){
	GraphNode currentNode = iter.next();
	DijkstraShortestPath<GraphNode,DirectedEdge> alg = new DijkstraShortestPath(dgraph,wtTransformer);
	List<DirectedEdge> l = alg.getPath(sampleNode, currentNode);
	Number dist = alg.getDistance(sampleNode,currentNode);
	System.out.println("The shortest path from" + sampleNode +
			" to " + currentNode + " is:");
	System.out.println(l.toString());
	System.out.println("and the weight of the path is: " + dist+"\n");
}*/