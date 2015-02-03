package reifman.iss;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class ISSOverhead {
	
	public static void main(String args[]) throws IOException{
		Scanner kybd = new Scanner(System.in);
		System.out.println("Enter Address");
		String address = kybd.nextLine();
		
		String encoded = URLEncoder.encode(address, "UTF-8");

		URL url = new URL(
				"https://maps.googleapis.com/maps/api/geocode/json?address="
						+ encoded + "&key="
						+ "AIzaSyAz0-JqMLJbcmnvYiXEe-6dg58xewydaKg");
		
		URLConnection connect = url.openConnection();
		InputStream in = connect.getInputStream();

		String json = IOUtils.toString(in);
		
		Gson gson = new Gson();
		LonLat location = gson.fromJson(json, LonLat.class);
		Result[] results = location.getResults();
		
		double lat=0;
		double lng = 0;
		
		for(int i=0 ; i<results.length; i++){
			lat = results[i].getGeometry().getLocation().getLat();
			lng = results[i].getGeometry().getLocation().getLng();
		}
		
		URL urlTwo = new URL("http://api.open-notify.org/iss-pass.json?lat=" + lat + "&lon=" + lng);
		
		URLConnection connectTwo = urlTwo.openConnection();
		InputStream inTwo = connectTwo.getInputStream();
		String jsonTwo = IOUtils.toString(inTwo);
		Gson gsonTwo = new Gson();
		
		Overhead overhead = gson.fromJson(jsonTwo, Overhead.class);
		Response[] responses = overhead.getResponse();
		String[] outputs = new String[responses.length];
		for(int i = 0; i<outputs.length; i++){
			long unixSeconds = responses[i].getRiseTime();
			Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
			sdf.setTimeZone(TimeZone.getTimeZone("EST")); // give a timezone reference for formating (see comment at the bottom
			String formattedDate = sdf.format(date);
			outputs[i] = formattedDate;

		}
		for(int i = 0; i<outputs.length; i++){
			System.out.println(outputs[i]);
		}
		
		
		
	}

}
