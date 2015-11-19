package Graph;

import java.util.Set;

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
	private Set<GraphNode> walking_nodes;
	private float driving_time;
	

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
	public void setWalkingNodes(Set<GraphNode> wn){
		this.walking_nodes = wn;
	}
	public Set<GraphNode>  getWalkingNodes(){
		return walking_nodes;
	}
	public void setDrivingTime(float dt){
		this.driving_time= dt;
	}
	public float getDrivingTime(){
		return driving_time;
	}

	
	public boolean equals(GraphNode node){
		if(node == null)
			return false;
		return this.getId()==node.getId();
	}
	
	public String toString(){
		String rt_str;
		rt_str = "("+this.lat+ "," + this.lon+")";
		return	rt_str;
	}
	
}
