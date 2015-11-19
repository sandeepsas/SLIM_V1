import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.TimerTask;

import org.json.JSONObject;


public class JSONParser {

		static InputStream is = null;
		static String json = "";
		JSONObject jObj = null;

		// constructor
		public JSONParser() {
		}
		
		public String getJSONFromUrl(String url) {

			// Making HTTP request
			try {
				
				HttpURLConnection  connection = (HttpURLConnection) new URL(url).openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept-Charset", "UTF-8");
				connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setRequestProperty("Content-length", "1000");
				connection.connect();  
				InputStream is = connection.getInputStream();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				json = sb.toString();
				is.close();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}catch (Exception e) {
				System.out.println("Buffer Error -> Error converting result " + e.toString());
			}
			return json;

		}

	}