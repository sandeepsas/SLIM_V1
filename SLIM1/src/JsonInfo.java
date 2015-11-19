
public class JsonInfo {
	double end_lat;
	double end_long;
	String html_instruction;
	String total_dist;
	String part_dist;
	String turn_info;
	double CurrentDist;
	double Cost=0;
	
	
	public JsonInfo(double end_lat, double end_long, String html_inst,String total_dist,String part_dist, String turn_info )
	{
		this.end_lat = end_lat;
		this.end_long = end_long;
		this.html_instruction = html_inst;
		this.total_dist = total_dist;
		this.part_dist = part_dist;
		this.turn_info = turn_info;
	}
	
	

	public double getEnd_lat() {
		return end_lat;
	}
	public void setEnd_lat(double end_lat) {
		this.end_lat = end_lat;
	}
	public String getTurn_info() {
		return turn_info;
	}



	public void setTurn_info(String turn_info) {
		this.turn_info = turn_info;
	}



	public double getEnd_long() {
		return end_long;
	}
	public void setEnd_long(double end_long) {
		this.end_long = end_long;
	}
	public String getHtml_instruction() {
		return html_instruction;
	}
	public void setHtml_instruction(String html_instruction) {
		this.html_instruction = html_instruction;
	}
	public String getTotal_dist() {
		return total_dist;
	}
	public void setTotal_dist(String total_dist) {
		this.total_dist = total_dist;
	}
	public String getPart_dist() {
		return part_dist;
	}
	public void setPart_dist(String part_dist) {
		this.part_dist = part_dist;
	}
	

}
