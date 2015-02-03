package reifman.iss;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class ISSOverheadFrame extends JFrame {

	private String address;
	private JTextField textField;
	private Container window;
	private JPanel output;
	private JList<String> list;


	public ISSOverheadFrame() {

		setSize(600, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		window = getContentPane();
		window.setLayout(new BorderLayout());

		JPanel input = new JPanel();
		input.setLayout(new BorderLayout());
		window.add(input, BorderLayout.NORTH);

		textField = new JTextField("1716 Avenue I Brooklyn, NY 11230");
		input.add(textField, BorderLayout.CENTER);
		textField.setSize(150, 8);

		JButton getTimes = new JButton("Get Times");
		input.add(getTimes, BorderLayout.EAST);
		
		list = new JList<String>();
		output = new JPanel();
		output.add(list);
		window.add(output, BorderLayout.WEST);		

		ActionListener getTheTimes = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				IssOverheadReuestThread t = new IssOverheadReuestThread(list, textField.getText());
				t.start();
				/*address = textField.getText();
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
				
				for(int i=0 ; i<results.length; i++){
					lat = results[i].getGeometry().getLocation().getLat();
					lng = results[i].getGeometry().getLocation().getLng();
				}
				
				URL urlTwo = new URL("http://api.open-notify.org/iss-pass.json?lat=" + lat + "&lon=" + lng);
				
				URLConnection connectTwo = urlTwo.openConnection();
				InputStream inTwo = connectTwo.getInputStream();
				String jsonTwo = IOUtils.toString(inTwo);
				
				Overhead overhead = gson.fromJson(jsonTwo, Overhead.class);
				Response[] responses = overhead.getResponse();
				outputs = new String[responses.length];
				for(int i = 0; i<outputs.length; i++){
					long unixSeconds = responses[i].getRiseTime();
					Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
					sdf.setTimeZone(TimeZone.getTimeZone("EST")); // give a timezone reference for formating (see comment at the bottom
					String formattedDate = sdf.format(date);
					outputs[i] = formattedDate;
				}
				
				list.setListData(outputs);;*/

			}
			
		};

		getTimes.addActionListener(getTheTimes);
		

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ISSOverheadFrame main = new ISSOverheadFrame();
		main.setVisible(true);

	}

}
