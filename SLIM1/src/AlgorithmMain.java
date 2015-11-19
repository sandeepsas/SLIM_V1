/**
 * @author Sandeep Sasidharan
 *
 */
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import Graph.DirectedEdge;
import Graph.GraphNode;
import Graph.RoadGraph;


public class AlgorithmMain {

	public static void main(String[] args) throws Exception {
		PrintStream out = new PrintStream(new FileOutputStream("F:/TravelTimeEst.txt"));
		System.setOut(out);
		RoadGraph g = new RoadGraph();

		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();
		xpp.setInput ( new FileReader ("Data/NYCRoadsF.osm"));
		g.osmGraphParser(xpp);

		System.out.println("Start Node, End Node -->  Travel Time");


		LinkedList<GraphNode> nodes = g.nodes;
		LinkedList<DirectedEdge> edges = g.edges;

		// Fill weights
		ListIterator<GraphNode> nodeIterator = nodes.listIterator();


		while (nodeIterator.hasNext()) {
			GraphNode single_node= nodeIterator.next();
			float travel_time_LGA =0;
			Thread.sleep(1000);
			travel_time_LGA = NetFns.travelTimeLGA(single_node);
			single_node.setDrivingTime(travel_time_LGA);
			nodeIterator.set(single_node);

			System.out.println("LGA to "+single_node.getId()+" "+single_node.toString()+ " --> "+single_node.getDrivingTime()+" min");
		}
		System.out.println("Start Node, End Node, Street Name, Road Type, Speed Max, Oneway, Travel Time");
		ListIterator<DirectedEdge> listIterator = edges.listIterator();
		while (listIterator.hasNext()) {
			DirectedEdge single_edge = listIterator.next();
			float weight = single_edge.getWeight();
			System.out.println(single_edge.from().getId()+","+single_edge.to().getId()+","+single_edge.getName()+","+single_edge.getType()+","+single_edge.speedMax()+","+single_edge.isOneway()+","+weight+"\n");
		}

	}

}

