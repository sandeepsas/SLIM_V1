
import Graph.GraphNode;
import Osm.OsmConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class NetFns {

	public static float travelTimeLGA(GraphNode single_node)
	{
		float travel_time = 0;
		String url = makeURL (OsmConstants.LaG_lat, OsmConstants.LaG_lng, single_node.getLat(), single_node.getLon());
		JSONParser jParser = new JSONParser();
		String json = jParser.getJSONFromUrl(url);
		try {
			JSONObject jObj = new JSONObject(json);
			
			JSONObject objRoute = jObj.getJSONArray("routes").getJSONObject(0);
		    JSONObject objLegs = objRoute.getJSONArray("legs").getJSONObject(0);
			JSONObject objDistance = objLegs.getJSONObject("distance");
			JSONObject objDuration = objLegs.getJSONObject("duration");
			String text_distance = objDistance.getString("text");
			String[] text_duration = objDuration.getString("text").split("\\s+");
			travel_time = Float.parseFloat(text_duration[0]);
	
		} catch(JSONException e) {
			e.printStackTrace();
		}
		
		return travel_time;

	}



	public static String makeURL (double sourcelat, double sourcelog, double destlat, double destlog ){
		try{
			StringBuilder urlString = new StringBuilder();
			urlString.append("http://maps.googleapis.com/maps/api/directions/json");
			urlString.append("?origin=");// from
			urlString.append(Double.toString(sourcelat));
			urlString.append(",");
			urlString
			.append(Double.toString( sourcelog));
			urlString.append("&destination=");// to
			urlString
			.append(Double.toString( destlat));
			urlString.append(",");
			urlString.append(Double.toString( destlog));
			urlString.append("&sensor=false&mode=driving&alternatives=true");
			urlString.append("&key="/*+OsmConstants.API_KEY*/);
			return urlString.toString();
		}
		catch(Exception e)
		{
			System.out.println("\n ERROR ->makeURL");
		}
		return null;
	}

}
