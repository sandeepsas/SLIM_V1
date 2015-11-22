import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import Graph.DirectedEdge;
import Graph.GraphNode;
import Graph.RoadGraph;


public class iGen {

	public static void osmToXml() throws Exception {

		System.out.println("Process started at"+ LocalDateTime.now() );
		RoadGraph g = new RoadGraph();

		//Import data and parse osm
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();
		xpp.setInput ( new FileReader ("Data/NYCRoadsF.osm"));
		//xpp.setInput ( new FileReader ("Data/NYC_sample.osm")); //Change Dia
		g.osmGraphParser(xpp);

		System.out.println("Parsing Completed at"+ LocalDateTime.now() );

		//Fetch the nodes and edges
		LinkedList<GraphNode> nodes = g.nodes;
		LinkedList<DirectedEdge> edges = g.edges;
		try{
			//Initialzie XML
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Graph");
			doc.appendChild(rootElement);

			Element rootElement1 = doc.createElement("GraphNodes");
			rootElement.appendChild(rootElement1);

			ListIterator<GraphNode> nodeIterator_t = nodes.listIterator();
			//Adding vertices
			while (nodeIterator_t.hasNext()) {
				GraphNode single_node= nodeIterator_t.next();

				// staff elements
				Element node = doc.createElement("Node");
				rootElement1.appendChild(node);

				// set attribute to staff element
				node.setAttribute("id", ""+single_node.getId());

				Element lat = doc.createElement("lat");
				lat.appendChild(doc.createTextNode(""+single_node.getLat()));
				node.appendChild(lat);

				Element lon = doc.createElement("lon");
				lon.appendChild(doc.createTextNode(""+single_node.getLon()));
				node.appendChild(lon);

			}

			Element rootElement2 = doc.createElement("GraphWays");
			rootElement.appendChild(rootElement2);

			ListIterator<DirectedEdge> listIterator_t = edges.listIterator();
			while (listIterator_t.hasNext()) {
				DirectedEdge single_edge = listIterator_t.next();

				Element way = doc.createElement("Way");
				rootElement2.appendChild(way);

				way.setAttribute("id", ""+single_edge.getWayId());

				Element source = doc.createElement("source");
				source.appendChild(doc.createTextNode(""+single_edge.from().getId()));
				way.appendChild(source);

				Element destination = doc.createElement("destination");
				destination.appendChild(doc.createTextNode(""+single_edge.to().getId()));
				way.appendChild(destination);

				Element weight = doc.createElement("weight");
				weight.appendChild(doc.createTextNode(""+single_edge.getWeight()));
				way.appendChild(weight);

				Element oneway = doc.createElement("oneway");
				oneway.appendChild(doc.createTextNode(""+single_edge.isOneway()));
				way.appendChild(oneway);

				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(""+single_edge.getName()));
				way.appendChild(name);


			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Data\\NYCRoadsF.xml"));
			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

}
