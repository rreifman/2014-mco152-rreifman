package reifman.opportunity;

import java.awt.BorderLayout;
import java.awt.Container;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class NasaFrame extends JFrame {

	private Container window;
	private JPanel panel;
	private JLabel lable;
	//private JSlider slider;



	public NasaFrame() throws MalformedURLException {

		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("MERA Opportunity Rover");

		window = getContentPane();
		window.setLayout(new BorderLayout());
		
		NasaFrameRequestThread thread = new NasaFrameRequestThread(this, window);
		thread.start();
		panel = new JPanel();
		lable = new JLabel();
		panel.add(lable);
		window.add(panel);

		

	}
	
	public void displayImage(Opportunity image, int pictureNum){
		NasaFrameRequestPhotoThread photoThread = new NasaFrameRequestPhotoThread(panel, pictureNum, image, lable);
		photoThread.start();
		
	}

	public static void main(String[] args) throws MalformedURLException {
		NasaFrame nasa = new NasaFrame();
		nasa.setVisible(true);

	}

}
