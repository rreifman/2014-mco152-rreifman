package reifman.test1;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;



public class AirplaneTest {

	@Test
	public void testToStringWithEmptyPlane() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Assert.assertEquals(
				"    AB_CD_EF\n" +
				"001 .._.._..\n" + 
				"002 .._.._..\n" + 
				"003 .._.._..\n", plane.toString());
	}
	@Test
	public void testToString() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy("1A");
		Assert.assertEquals(
				"    AB_CD_EF\n" +
				"001 O._.._..\n" + 
				"002 .._.._..\n" + 
				"003 .._.._..\n", plane.toString());
	}
	
	@Test
	public void testToStringWithFullPlane() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy(
				"1A", "1B", "1C", "1D", "1E", "1F",
				"2A", "2B", "2C", "2D", "2E", "2F",
				"3A", "3B", "3C", "3D", "3E", "3F");
		Assert.assertEquals(
				"    AB_CD_EF\n" +
				"001 OO_OO_OO\n" + 
				"002 OO_OO_OO\n" + 
				"003 OO_OO_OO\n", plane.toString());
	}
	
	
	@Test
	public void testGetNumSeats() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Assert.assertEquals(18, plane.getNumSeats());
	}
	
	@Test
	public void testGetNumEmptySeats() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);

		Assert.assertEquals(18, plane.getNumSeats());
		plane.occupy("1A", "1B");
		Assert.assertEquals(16, plane.getNumEmptySeats());
	}

	@Test
	public void testIsFull() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy(
				"1A", "1B", "1C", "1D", "1E", "1F",
				"2A", "2B", "2C", "2D", "2E", "2F",
				"3A", "3B", "3C", "3D", "3E", "3F");
		Assert.assertTrue(plane.isFull());
	}
	
	@Test
	public void testGetSeatThrowsUnknownSeatException() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		try{
			plane.occupy("1AA");
			Assert.fail("UnknownSeatException should be thrown here");
			}catch(UnknownSeatException e){
				e.printStackTrace();
			}
	}
	
	@Test
	public void testOccupySeats() 
			throws UnknownSeatException, FullPlaneException, NotEnoughSeatsTogeatherException {
		Airplane plane = new Airplane("ABC_DEFG", 3);
		List<Seat>seats = plane.occupySeats(3);
		Assert.assertEquals(
				"    ABC_DEFG\n" +
				"001 OOO_....\n" + 
				"002 ..._....\n" + 
				"003 ..._....\n", plane.toString());
		Assert.assertTrue(plane.getSeat("1A").isOccupied());
		Assert.assertFalse(plane.getSeat("1G").isOccupied());
	}
	
	@Test
	public void testOccupySeatsThrowsNotEnoughSeatsTogeatherException() 
			throws FullPlaneException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		try{
			plane.occupySeats(6);
			Assert.fail("UnknownSeatException should be thrown here");
			}catch(NotEnoughSeatsTogeatherException e){
				e.printStackTrace();
			}
	}
	
	@Test
	public void testOccupySeatsThrowsFullPlaneException() throws NotEnoughSeatsTogeatherException, UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy(
				"1A", "1B", "1C", "1D", "1E", "1F",
				"2A", "2B", "2C", "2D", "2E", "2F",
				"3A", "3B", "3C", "3D", "3E", "3F");
		try{
			plane.occupySeats(2);
			Assert.fail("UnknownSeatException should be thrown here");
			}catch(FullPlaneException e){
				e.printStackTrace();
			}
	}
	

}
