/**
 * @author Sandeep Sasidharan
 *
 */
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import Graph.DirectedEdge;
import Graph.DropoffStorage;
import Graph.GraphNode;
import Graph.RoadGraph;
import Osm.OsmConstants;


public class AlgorithmMain {

	public static void main(String[] args) throws Exception {
		
		PrintWriter djk_writer = new PrintWriter("G:/NYCF_Djk_clean1.txt", "UTF-8");
		PrintWriter con_writer = new PrintWriter("G:/NYCF_ConsistancyCheckers_clean1.txt", "UTF-8");
		PrintWriter fw_writer = new PrintWriter("G:/NYCF_FW_clean1.txt", "UTF-8");
		PrintStream out = new PrintStream(new FileOutputStream("G:/NYCF_fullRunLOG_clean1.txt"));
		System.setOut(out);
		System.out.println("Process started at"+ LocalDateTime.now() );
		RoadGraph g = new RoadGraph();

		//Import data and parse osm
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();
		xpp.setInput ( new FileReader ("Data/NYCRoadsF.osm"));
		//xpp.setInput ( new FileReader ("Data/NYC_sample.osm")); //Chenge Dia
		g.osmGraphParser(xpp);

		System.out.println("Parsing Completed at"+ LocalDateTime.now() );

		//Fetch the nodes and edges
		LinkedList<GraphNode> nodes = g.nodes;
		LinkedList<DirectedEdge> edges = g.edges;
		GraphNode hub_node = g.getLgaNode();
		System.out.println(hub_node.toString());
		//Consistancy checker for edge
		ListIterator<DirectedEdge> temp_itr = edges.listIterator();
		con_writer.println("WAY_ID, START_NODE, END_NODE, NAME, WEIGHT, LENGTH, SPEED, TYPE, ONEWAY");
		while(temp_itr.hasNext()){
			DirectedEdge temp_edge = temp_itr.next();
			con_writer.println(temp_edge.getWayId()+", "+temp_edge.from()+", "+temp_edge.to()+", "+temp_edge.getName()+", "+temp_edge.getWeight()
					+", "+temp_edge.getLength()+", "+temp_edge.speedMax()+", "+temp_edge.getType()+", "+temp_edge.isOneway());
		}
		
		//JGRAPHT IMPLEMETATION FOR FW
		DefaultDirectedWeightedGraph <GraphNode,DefaultWeightedEdge> gr_t = new  
				DefaultDirectedWeightedGraph <GraphNode,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		ListIterator<GraphNode> nodeIterator_t = nodes.listIterator();
		//Adding vertices
		while (nodeIterator_t.hasNext()) {
			GraphNode single_node= nodeIterator_t.next();
			gr_t.addVertex(single_node);
		}

		//adding edges
		int loop_ctr = 0;
		int multi_edge_ctr = 0;
		ListIterator<DirectedEdge> listIterator_t = edges.listIterator();
		while (listIterator_t.hasNext()) {
			DirectedEdge single_edge = listIterator_t.next();
			float weight = single_edge.getWeight();
			// Loop detection
			
			if(single_edge.from().getId() == single_edge.to().getId()){
				loop_ctr++;
				continue;
			}
			if(weight<=0){
				loop_ctr++;
				continue;
			}

			//Add single edge for Oneway, Two for others
			if(single_edge.isOneway()){
				//check if edge already present
				DefaultWeightedEdge temp_e = gr_t.getEdge(single_edge.from(),single_edge.to());
				if(temp_e != null){
					multi_edge_ctr++;
					continue;
				}
				else{
					
					DefaultWeightedEdge e1 = gr_t.addEdge(single_edge.from(),single_edge.to()); 
					gr_t.setEdgeWeight(e1,weight);
				}
				 
			}else{
				//check if edge already present
				DefaultWeightedEdge temp_i = gr_t.getEdge(single_edge.from(),single_edge.to());
				if(temp_i != null){
					multi_edge_ctr++;
					continue;
				}
				else{
					DefaultWeightedEdge e1 = gr_t.addEdge(single_edge.from(),single_edge.to()); 
					gr_t.setEdgeWeight(e1, weight); 
				}
				DefaultWeightedEdge temp_o = gr_t.getEdge(single_edge.to(),single_edge.from());
				if(temp_o != null){
					continue;
				}else{
					DefaultWeightedEdge e2 = gr_t.addEdge(single_edge.to(),single_edge.from()); 
					gr_t.setEdgeWeight(e2, weight); 
				}
			}
		}
		System.out.println("Graph Construction completed at"+ LocalDateTime.now() );
		System.out.println("SKIPPED EDGES DUE TO LOOPS = "+loop_ctr);
		System.out.println("Total Multiple edge Detected = "+multi_edge_ctr);
		
		// Calculating Shortest path
		/*System.out.println("Dijkstra SPA started at"+ LocalDateTime.now() );
		
		Set<GraphNode> vertices_t = gr_t.vertexSet();
		Iterator<GraphNode> iter_t=vertices_t.iterator();
		while(iter_t.hasNext()){
			GraphNode currentNode = iter_t.next();

			DijkstraShortestPath<GraphNode, DefaultWeightedEdge> dsp = new DijkstraShortestPath<GraphNode, DefaultWeightedEdge>(gr_t,hub_node,currentNode);
			//List shortest_path =   DijkstraShortestPath.findPathBetween(gr_t, sampleNode_t, currentNode);
			//List<DefaultWeightedEdge> l = dsp.getPathEdgeList();
			
			Number dist = dsp.getPathLength();
			djk_writer.println("The travel time from LGA to " + currentNode + " is:"+dist+"\n");
		}
		System.out.println("Dijkstra SPA completed at"+ LocalDateTime.now() );*/
		System.out.println("FW FWA started at"+ LocalDateTime.now() );
		
		FloydWarshallShortestPaths<GraphNode, DefaultWeightedEdge> fw = new FloydWarshallShortestPaths<GraphNode, DefaultWeightedEdge>(gr_t);
		System.out.println("FW APA Initialized at"+ LocalDateTime.now() );
		long no_paths = fw.getShortestPathsCount();
		fw_writer.println("Total no of paths = "+no_paths);
		
		
		// Loop through all vertices and find the all-pairs SP
		DropoffStorage node_DS = new DropoffStorage();
		Set<GraphNode> allVertices = gr_t.vertexSet();
		Iterator<GraphNode> allVertItr = allVertices.iterator();
		int all_vertices_no = allVertices.size();
		System.out.println("Total no of vertices to be processed by FW = "+all_vertices_no);
		int vn =0;
		while(allVertItr.hasNext()){
			vn++;
			System.out.println("Vertex no proceesed =" +vn +" out of "+all_vertices_no);
			//Compute all-pairs with Graph
			GraphNode single_vertex = allVertItr.next();
			StringBuilder all_pair = new StringBuilder();
			all_pair.append(single_vertex.getId()+"-->");
			node_DS.setNode(single_vertex);
			List<GraphNode> temp_node_set = new ArrayList<GraphNode>();
			Collection<GraphPath<GraphNode, DefaultWeightedEdge>> SPtoVertices =  fw.getShortestPaths();
			//Iterate through the paths
			Iterator<GraphPath<GraphNode,DefaultWeightedEdge>> sp_itr = SPtoVertices.iterator();
			while(sp_itr.hasNext()){
				GraphPath<GraphNode, DefaultWeightedEdge> g_path = sp_itr.next();
				double total_travel_time = g_path.getWeight();
				//Check if the travel_time is less than specified time 15 mins
				
				if(total_travel_time<5){
					GraphNode end_Vertex = (GraphNode) g_path.getEndVertex();
					temp_node_set.add(end_Vertex);
					all_pair.append(end_Vertex.getId()+",");
				}
			}
			node_DS.setDrivable_nodes(temp_node_set);
			fw_writer.println(all_pair);
		}
		System.out.println("FW APA Completed at"+ LocalDateTime.now() );

	}

}

