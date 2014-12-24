package reifman.weather;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class WeatherDownloadImageThread extends Thread {

	private URL url;
	private JLabel label;

	public WeatherDownloadImageThread(URL url, JLabel label) {
		this.url = url;
		this.label = label;

	}

	public void run() {

		ImageIcon image = new ImageIcon(url);
		label.setIcon(image);

	}
}
