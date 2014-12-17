package reifman.morsecode;

import org.junit.Test;

import junit.framework.Assert;

public class MorseCodeTest {

	@Test
	public void testEqualsToMorseCode(){
		MorseCode code = new MorseCode();
		String morseCode = ".-. .. ...- -.- .-  / .-. --- -..- ";
		Assert.assertEquals(morseCode, code.toMorseCode("rivka rox"));
	}
	
	@Test
	public void testEqualsToPlainText(){
		MorseCode code = new MorseCode();
		String text = "RIVKA ROX";
		Assert.assertEquals(text, code.toPlainText(".-. .. ...- -.- .-  / .-. --- -..-"));
	}
}
