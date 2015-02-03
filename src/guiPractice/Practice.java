package guiPractice;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Practice extends JFrame{

	public Practice() throws MalformedURLException{
		
		setSize(450,450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		JPanel panelOne = new JPanel();
		BoxLayout box = new BoxLayout(panelOne, BoxLayout.PAGE_AXIS);
		panelOne.setLayout(box);
		
		container.add(panelOne, BorderLayout.NORTH);
		ImageIcon obama = new ImageIcon(new URL("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xap1/v/t1.0-1/p50x50/1376580_10151878817796749_162570700_n.png?oh=9dc2a5afaa6bad9327a65b5c650f965b&oe=553DF12B&__gda__=1425691132_a31cf0117b80132037eb1ae67a181691"));
		panelOne.add(new JLabel(obama));
		panelOne.add(new JLabel("President Obama"));
		
		JPanel panelTwo = new JPanel();
		panelTwo.setLayout(new FlowLayout(FlowLayout.RIGHT));
		container.add(panelTwo, BorderLayout.EAST);
		ImageIcon biden = new ImageIcon(new URL("http://www.gq.com/images/news-and-politics/2010/12/joe-biden/joe-biden_96.jpg"));
		panelTwo.add(new JLabel(biden));
		panelTwo.add(new JLabel("Vice President Joe Biden"));
		
		JPanel panelThree = new JPanel();
		panelThree.setLayout(new GridLayout());
		container.add(panelThree, BorderLayout.SOUTH);
		ImageIcon kerry = new ImageIcon(new URL("http://ww3.hdnux.com/photos/33/60/54/7277314/5/gallery_thumb.jpg"));
		panelThree.add(new JLabel(kerry));
		panelThree.add(new JLabel("Secretary of State John Kerry"));
		
	}
	
	public static void main(String args[]) throws MalformedURLException{
		Practice practice = new Practice();
		practice.setVisible(true);
	}
}
