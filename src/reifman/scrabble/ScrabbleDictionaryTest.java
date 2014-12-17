package reifman.scrabble;

import org.junit.Assert;
import org.junit.Test;

public class ScrabbleDictionaryTest {

	@Test
	public void testContainsNull() {
		ScrabbleDictionary dictionary = new ScrabbleDictionary();
		Assert.assertFalse(dictionary.contains(null));
	}
	
	@Test
	public void testContainsFalse() {
		ScrabbleDictionary dictionary = new ScrabbleDictionary();
		Assert.assertFalse(dictionary.contains("ljlkafj"));
	}
	
	@Test
	public void testContainsTrue() {
		ScrabbleDictionary dictionary = new ScrabbleDictionary();
		Assert.assertTrue(dictionary.contains("Hello"));
	}

}
