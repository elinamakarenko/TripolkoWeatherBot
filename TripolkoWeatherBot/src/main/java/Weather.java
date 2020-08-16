import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import com.google.gson.Gson;

public class Weather {
	static public Map<String, Object> jsonToMap(String str) {
		Map<String, Object> map = (Map<String, Object>) new Gson().fromJson(str, Object.class);
		return map;
	}

	static public String getWeather(String city) throws IOException {
		String apiKey = "81e1c137423f871931a6fe4276230ec9";
		String weather = "";
		String urlStr = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
		StringBuilder result = new StringBuilder();

		URL url = new URL(urlStr);

		URLConnection conn = url.openConnection();
	//	System.out.println("Connection done!");
		String line = "";
		BufferedReader buf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while ((line = buf.readLine()) != null) {
			result.append(line);
		}

		buf.close();
		
		Map<String, Object> resMap=jsonToMap(result.toString());
		Map<String, Object> mainMap=jsonToMap(resMap.get("main").toString());
		int temp = (int)((Double)mainMap.get("temp")-273);
		double humidity = (Double)mainMap.get("humidity");
		weather="Weather in "+city+": "+temp+", "+humidity+"%";
		
		
		return weather;

	}
}

