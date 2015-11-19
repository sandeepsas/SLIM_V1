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
		PrintStream out = new PrintStream(new FileOutputStream("F:/NYRoadsF_JSON1.txt"));
		System.setOut(out);
		RoadGraph g = new RoadGraph();

		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();
		xpp.setInput ( new FileReader ("Data/NYC_sample.osm"));
		g.osmGraphParser(xpp);
		
		 System.out.println("Start Node, End Node, Street Name, Road Type, Speed Max, Oneway, Travel Time");
	       
		
		LinkedList<GraphNode> nodes = g.nodes;
		LinkedList<DirectedEdge> edges = g.edges;
		
		// Fill weights
		ListIterator<GraphNode> nodeIterator = nodes.listIterator();
		Timer timer = new Timer();

  		 while (nodeIterator.hasNext()) {
			 GraphNode single_node= nodeIterator.next();
			 TimerTask timerTask = new TimerTask() {

		            @Override
		            public void run() {
		            	float travel_time_LGA = NetFns.travelTimeLGA(single_node);

		            }
		        };
			 
			 single_node.setDrivingTime(travel_time_LGA);
			 nodeIterator.set(single_node);
			 
			 System.out.println("LGA to "+single_node.getId()+" --> "+single_node.getDrivingTime());
	        }
		
		

        timer.scheduleAtFixedRate(timerTask, 0, 10*1000);
		 
		
/*		ListIterator<DirectedEdge> listIterator = edges.listIterator();
		 while (listIterator.hasNext()) {
			 DirectedEdge single_edge = listIterator.next();
			 float weight = single_edge.getWeight();
			 System.out.println(single_edge.from().getId()+","+single_edge.to().getId()+","+single_edge.getName()+","+single_edge.getType()+","+single_edge.speedMax()+","+single_edge.isOneway()+","+weight+"\n");
	        }*/
		
	}

}
