package reifman.earthquakes;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.gson.Gson;

public class EarthquakeFrame extends JFrame{


	public EarthquakeFrame() throws IOException {
		
		
		setSize(400, 400);
		setTitle("Earthquakes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setBackground(Color.LIGHT_GRAY);

		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		container.add(Box.createRigidArea(new Dimension(5, 5)));
		container.add(new JLabel("Significant Earthquakes in the past 30 days"));
		
		
		URL url = new URL(
				"http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson");
		URLConnection connect = url.openConnection();
		InputStream in = connect.getInputStream();

		byte b[] = new byte[4096];
		int n = -1;
		StringBuilder build = new StringBuilder();
		while ((n = in.read(b)) != -1) {
			// when input Stream gets to the end it will return a -1
			build.append(new String(b, 0, n));
		}
		String json = build.toString();

		Gson gson = new Gson();
		FeatureCollection quakes = gson.fromJson(json, FeatureCollection.class);

		Feature[] features = quakes.getFeatures();
		
		for (int i = 0; i < features.length; i++) {
			container.add(Box.createRigidArea(new Dimension(5, 5)));
			Properties prop = features[i].getProperties();
			container.add(new JLabel((i+1)+": " + "Place: " + prop.getPlace()));
			container.add(new JLabel("Mag: " + prop.getMag()));
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		EarthquakeFrame earthquake = new EarthquakeFrame();
		earthquake.setVisible(true);
	}

}
