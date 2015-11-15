package Graph;

/**
 * 
 * @author Sandeep Sasidharan
 *
 */
public class GraphNode {
	private double lon;
	private double lat;
	private String name;
	private long id;
	

	public GraphNode() {
		this.lon = 0.0;
		this.lat = 0.0;
		this.name = null;
	}
	

	public GraphNode(double lon, double lat, String name) {
		this.lon = lon;
		this.lat = lat;
		this.name = name;
	}
	

	public double getLon() {
		return lon;
	}
	
	public double getLat() {
		return lat;
	}
	
	public String getName() {
		return name;
	}
	
	public long getId() {
		return id;
	}
	
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	public void setId(long l){
		this.id = l;
	}
	
	public boolean equals(GraphNode node){
		if(node == null)
			return false;
		return this.getId()==node.getId();
	}
}
