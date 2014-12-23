package reifman.nytimes;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;


import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import com.google.gson.Gson;

public class NYTimes extends JFrame{
	
	private static int page = 0;
	
	public NYTimes() throws IOException{

		setSize(1000, 1000);
		setTitle("New York Times");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		Container container = getContentPane();
		
		BoxLayout box = new BoxLayout(container, BoxLayout.PAGE_AXIS);
		container.setLayout(box);
		container.setBackground(Color.WHITE);
		JLabel logo = new JLabel(new ImageIcon(new URL("https://www.factoryfive.com/wp-content/uploads/2012/06/nytimeslogo.jpg")));
		container.add(logo);
		
		JButton nextTen = new JButton("Older Posts");
		container.add(nextTen);

		
		ActionListener nextTenPosts = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				page++;
				try {
					NYTimes next = new NYTimes();
					next.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		nextTen.addActionListener(nextTenPosts);
		
		URL url = new URL("http://api.nytimes.com/svc/search/v2/articlesearch.json?page=" + page + "&api-key=75a5bf657bc8110fc76df31ffc88f8ff%3A6%3A70537592");
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
		News news = gson.fromJson(json, News.class);
		


		
		Document[] docs = news.getResponse().getDocs();
		
		for(int i =0; i< docs.length; i++){
			container.add(new JLabel(new ImageIcon(new URL("http://www.miamidadeculinary.com/images/dotted_line.png"))));
			JLabel headline = new JLabel(docs[i].getHeadline().getMain());
			headline.setForeground(Color.RED);
			headline.setFont(new Font("Serif", Font.BOLD, 20));
			container.add(headline);
			
			Multimedia[] pics = docs[i].getMultimedia();
			if(pics.length >0){
			String picUrl = "http://www.nytimes.com/" + pics[0].getUrl();
			
			container.add(new JLabel(new ImageIcon(new URL(picUrl))));
			}
			
			container.add(new JLabel(docs[i].getLead_paragraph()));
			JButton more = new JButton("Read More");
			container.add(more);
			
			final String web_url = docs[i].getWeb_url();
			ActionListener readMore = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					JButton more = (JButton) event.getSource();
					 try {
						Desktop.getDesktop().browse(new URL(web_url).toURI());
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
			};

			more.addActionListener(readMore);
			
		}
		
		
	}

	public static void main(String[] args) throws IOException {

		NYTimes times = new NYTimes();

		times.setVisible(true);
	}

}
