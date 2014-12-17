package reifman.gameoflife;

import java.awt.Color;

import javax.swing.JButton;


public class Cell extends JButton {
	private boolean isAlive;
	private Color color;
	
	public Cell(){ //default constructed is dead cell
		isAlive = false;
		
	}
	
	public Cell(boolean isAlive){
		this.isAlive = isAlive;

	}
	
	public void setAlive(){
		isAlive = true;
		
	}
	
	public boolean getIfAlive(){
		return isAlive;
	}
	
	public void setDead(){
		isAlive = false;
	
	}
	
	public void setColor(){
		if(isAlive){
			color = Color.GREEN;
			setBackground(Color.GREEN);
			setBorderPainted(false);
		}
		else{
			color = Color.BLACK;
			setBackground(Color.BLACK);
			setBorderPainted(false);
		}
	}
	

}
