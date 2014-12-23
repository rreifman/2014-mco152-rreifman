package reifman.weather;

import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.google.gson.Gson;

public class WeatherFrame extends JFrame {

	public WeatherFrame() throws IOException {

		setSize(300, 300);
		setTitle("Current Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();

		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

//		URL url = new URL(
//				"http://api.openweathermap.org/data/2.5/weather?q=Brooklyn&units=imperial");
//		URLConnection connection = url.openConnection();
//		InputStream in = connection.getInputStream();
//
//		byte b[] = new byte[4096];
//		int n = -1;
//		StringBuilder build = new StringBuilder();
//		while ((n = in.read(b)) != -1) {
//			// when input Stream gets to the end it will return a -1
//			build.append(new String(b, 0, n));
//		}
//		String json = build.toString();
//		Gson gson = new Gson();
//		WeatherNow now = gson.fromJson(json, WeatherNow.class);
		
		Weather[] descs = now.getWeather();

		container.add(Box.createRigidArea(new Dimension(5, 5)));
		JLabel name = new JLabel(now.getName());
		container.add(name);
		container.add(Box.createRigidArea(new Dimension(5, 5)));

		// Container forPics = new Container();
		// FlowLayout pics = new FlowLayout();
		// forPics.setLayout(pics);
		// container.add(forPics);

		for (int i = 0; i < descs.length; i++) {
			String icon = descs[i].getIcon();
			String picUrl = "http://openweathermap.org/img/w/" + icon + ".png";
			ImageIcon img = new ImageIcon(new URL(picUrl));
			container.add(new JLabel(img));
			container.add(new JLabel(descs[i].getMain() + ": "
					+ descs[i].getDescription()));
		}

		container.add(Box.createRigidArea(new Dimension(5, 10)));
		container.add(new JLabel("Temp: "
				+ String.valueOf(now.getMain().getTemp())));
		container.add(new JLabel("Min: "
				+ String.valueOf(now.getMain().getTemp_min()) + " Max: "
				+ String.valueOf(now.getMain().getTemp_max())));
		
		WeatherDownloadThread thread = new WeatherDownloadThread();
		thread.start();
	}

	public static void main(String args[]) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, IOException {
		UIManager.setLookAndFeel(UIManager
				.getCrossPlatformLookAndFeelClassName());
		WeatherFrame weather = new WeatherFrame();
		weather.setVisible(true);
	}

}
