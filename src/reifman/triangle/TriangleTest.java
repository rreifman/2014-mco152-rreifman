package reifman.triangle;

import org.junit.Test;

import junit.framework.Assert;

public class TriangleTest {

	@Test
	public void testEqualsTriangle(){
		
		Triangle test = new Triangle(3);
		Assert.assertEquals("  *\n * *\n*****", test.toString());
	}
}
