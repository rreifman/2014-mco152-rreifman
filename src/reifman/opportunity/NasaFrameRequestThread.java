package reifman.opportunity;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class NasaFrameRequestThread extends Thread {
	
	private NasaFrame frame;
	private JSlider slider;
	private Opportunity spacePic;
	private Container window;
	
	public NasaFrameRequestThread(NasaFrame frame, Container window){
		this.frame = frame;
		this.window = window;
	}

	public void run(){
		try {
		
			
		URL url = new URL("http://merpublic.s3.amazonaws.com/oss/mera/images/images_sol13.json");
		URLConnection connect = url.openConnection();
		InputStream in = connect.getInputStream();

		String json = IOUtils.toString(in);
	
		
		Gson gson = new Gson();
		 spacePic = gson.fromJson(json, Opportunity.class);
		
		frame.displayImage(spacePic, 0);
		
		
		slider = new JSlider(0, spacePic.getNumPics()-1, 0);
		slider.setOrientation(JSlider.HORIZONTAL);
		slider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent event) {
				int pictureNum = slider.getValue();
				frame.displayImage(spacePic, pictureNum);
				
			}

		});
		
		window.add(slider, BorderLayout.SOUTH);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	
}
