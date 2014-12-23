package reifman.nytimes;

public class Document {
	
	private String web_url;

	private String lead_paragraph;
	private Headline headline; 
	private Multimedia[] multimedia;
	

	public Multimedia[] getMultimedia() {
		return multimedia;
	}
	public String getWeb_url() {
		return web_url;
	}
	public String getLead_paragraph() {
		return lead_paragraph;
	}
	public Headline getHeadline() {
		return headline;
	}
}
