/**
 * @author Sandeep Sasidharan
 *
 */
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.ListIterator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import Graph.DirectedEdge;
import Graph.GraphNode;
import Graph.RoadGraph;


public class AlgorithmMain {
	
	public static void main(String[] args) throws Exception {
		PrintStream out = new PrintStream(new FileOutputStream("F:/NYRoadsF_Log2.txt"));
		System.setOut(out);
		System.out.println("Graphing Started");
		RoadGraph g = new RoadGraph();

		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();
		xpp.setInput ( new FileReader ("Data/NYCRoadsF.osm"));
		g.osmGraphParser(xpp);
		
		System.out.println("Graphing Done");
		
/*		LinkedList<GraphNode> nodes = g.nodes;
		LinkedList<DirectedEdge> edges = g.edges;
		
		ListIterator<DirectedEdge> listIterator = edges.listIterator();
		 while (listIterator.hasNext()) {
			 DirectedEdge single_edge = listIterator.next();
			 double weight = 60*(single_edge.getLength()/single_edge.speedMax());
			 System.out.println(single_edge.from().getId()+"-->"+single_edge.to().getId());
	         System.out.println(single_edge.getName()+"***"+single_edge.getType()+"***"+single_edge.speedMax()+"***"+single_edge.isOneway()+"***"+weight+"\n");
	        }*/
		
	}

}
