package reifman.opportunity;

public class Opportunity {
	
	private MiImages[] mi_images;
	private PCamImages[] pcam_images;
	private NCamImages[] ncam_images;
	private FCamImages[] fcam_images;

	public MiImages[] getMi_images(){
		return mi_images;
	}
	
	public PCamImages[] getPcam_images() {
		return pcam_images;
	}



	public NCamImages[] getNcam_images() {
		return ncam_images;
	}



	public FCamImages[] getFcam_images() {
		return fcam_images;
	}
	
	public int getNumPics(){
		return mi_images.length + pcam_images.length + ncam_images.length + fcam_images.length;
	}
	
	public String getAPic(int n){
		Images[] array = new Images[255];
		int counter = 0;
		for(int i = 0; i< mi_images.length; i ++){
			array[counter++] = mi_images[i].getImages()[0];
		}
		for(int i = 0; i< pcam_images.length; i ++){
			array[counter++] = pcam_images[i].getImages()[0];
		}
		for(int i = 0; i< ncam_images.length; i ++){
			array[counter++] = ncam_images[i].getImages()[0];
		}
		for(int i = 0; i< fcam_images.length; i ++){
			array[counter++] = fcam_images[i].getImages()[0];
		}
		return array[n].getUrl();
	}

}
