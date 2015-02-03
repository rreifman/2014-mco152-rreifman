package reifman.opportunity;

import java.awt.BorderLayout;
import java.awt.Container;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class NasaFrameRequestPhotoThread extends Thread {

	private Container window;
	private int pictureNum;
	private JPanel panel;
	private Opportunity spacePic;
	private JLabel lable;

	public NasaFrameRequestPhotoThread(JPanel panel, int pictureNum,
			Opportunity spacePic, JLabel lable) {
		this.panel = panel;
		this.pictureNum = pictureNum;
		this.spacePic = spacePic;
		this.lable = lable;
	}

	public void run() {
		try {

			//String imageUrl = spacePic.getMi_images()[pictureNum].getImages()[0].getUrl();
			String imageUrl = spacePic.getAPic(pictureNum);
			ImageIcon image = new ImageIcon(new URL(imageUrl));

			lable.setIcon(image);

			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
