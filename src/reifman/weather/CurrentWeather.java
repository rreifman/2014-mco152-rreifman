package reifman.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class CurrentWeather {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		URL url = new URL(
				"http://api.openweathermap.org/data/2.5/weather?q=Brooklyn&units=imperial");
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();

		byte b[] = new byte[4096];
		int n = -1;
		StringBuilder build = new StringBuilder();
		while ((n = in.read(b)) != -1) {
			// when input Stream gets to the end it will return a -1
			build.append(new String(b, 0, n));
		}
		String json = build.toString();
		Gson gson = new Gson();
		WeatherNow now = gson.fromJson(json, WeatherNow.class);
		System.out.println(now.getName());
		Weather[] descs= now.getWeather();
		for(int i = 0; i<descs.length; i++){
			System.out.print(descs[i].getDescription() + ", ");
		}
		System.out.println(now.getMain().getTemp());
		
	}

}
