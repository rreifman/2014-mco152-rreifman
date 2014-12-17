package reifman.triangle;

import java.util.Scanner;

public class Triangle {
	
	private int number;
	
	public Triangle(int number){
		this.number = number;
	}
	
	public String toString(){
		
		StringBuilder output = new StringBuilder();
		//first line
		for(int i = 0; i<number-1; i++){
			output.append(" ");
		}
		output.append("*");
		output.append("\n");
		
		//body
		int newNumber = number -2;
		int spaces = 1;
		
		for(int i =0; i<number-2; i++){
			for(int j = 0; j<newNumber; j++){
			output.append(" ");
		}
		output.append("*");
		for(int h = 0; h<spaces; h++){
			output.append(" ");
		}
		output.append("*");
		output.append("\n");
		newNumber--;
		spaces+=2;

		}
		
		//last line
		for (int i = 0; i<(number*2)-1; i++){
			output.append("*");
		}
		
		return output.toString();
	}


}
