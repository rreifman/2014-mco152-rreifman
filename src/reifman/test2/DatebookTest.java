package reifman.test2;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DatebookTest {

	/**
	 * 
	 * @param year
	 *            4 digit year
	 * @param month
	 *            Calendar.JANUARY, Calendar.FEBRUARY...
	 * @param dayOfMonth
	 *            starting from 1
	 * @return A Date from the specified parameters
	 */
	private Date getDate(int year, int month, int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month, dayOfMonth, 0, 0, 0);
		return calendar.getTime();
	}

	@Test
	/**
	 * After calling addSingleEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddSingleEvent() {
		Datebook datebook = new Datebook();
		
		// given an event
		Event event = new Event("EVENT 1", 1200);
		Date today = getDate(2014, Calendar.NOVEMBER, 25);
		
		// when the event is added today
		datebook.addSingleEvent(event, today);

		// then the event is returned for today
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

		// then the event is not returned tomorrow
		Date tomorrow = getDate(2014, Calendar.NOVEMBER, 26);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	/**
	 * After calling addYearlyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddYearlyEvent() {
			Datebook datebook = new Datebook();
			Event event = new Event("EVENT 1", 1200);

			datebook.addYearlyEvent(event,1);
			Date day1 = getDate(2014, Calendar.JANUARY, 1);
			List<Event> list = datebook.getEvents(day1);
			Assert.assertNotNull(list);
			Assert.assertEquals(1, list.size());
			Assert.assertSame(event, list.get(0));
			
			Date day2 = getDate(2015, Calendar.JANUARY, 1);
			List<Event> list2 = datebook.getEvents(day2);
			Assert.assertNotNull(list2);
			Assert.assertEquals(1, list2.size());
			Assert.assertSame(event, list2.get(0));
	}

	@Test
	/**
	 * After calling addMonthlyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddMonthlyEvent() {
		Datebook datebook = new Datebook();
		Event event = new Event("EVENT 1", 1200);

		datebook.addMonthlyEvent(event,1);
		Date day1 = getDate(2014, Calendar.JANUARY, 1);
		List<Event> list = datebook.getEvents(day1);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));
		
		Date day2 = getDate(2014, Calendar.DECEMBER, 1);
		List<Event> list2 = datebook.getEvents(day2);
		Assert.assertNotNull(list2);
		Assert.assertEquals(1, list2.size());
		Assert.assertSame(event, list2.get(0));
	}

	@Test
	/**
	 * After calling addWeeklyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddWeeklyEvent() {
		Datebook datebook = new Datebook();
		Event event = new Event("EVENT 1", 1200);

		datebook.addWeeklyEvent(event,1);
		Date day1 = getDate(2014, Calendar.NOVEMBER, 30);
		List<Event> list = datebook.getEvents(day1);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));
		
		Date day2 = getDate(2014, Calendar.DECEMBER, 7);
		List<Event> list2 = datebook.getEvents(day2);
		Assert.assertNotNull(list2);
		Assert.assertEquals(1, list2.size());
		Assert.assertSame(event, list2.get(0));
	}

	@Test
	/**
	 * After calling addDailyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddDailyEvent() {
		Datebook datebook = new Datebook();
		Event event = new Event("EVENT 1", 1200);

		datebook.addDailyEvent(event);
		Date today = getDate(2014, Calendar.NOVEMBER, 25);
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));
		
		Date tomorrow = getDate(2014, Calendar.NOVEMBER, 26);
		List<Event> list2 = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list2);
		Assert.assertEquals(1, list2.size());
		Assert.assertSame(event, list2.get(0));
		
		
	}

	@Test
	/**
	 * After adding multiple Events, verify that they are all returned from getEvents() in the correct order.
	 */
	public void testGetEventsReturnsSortedList() {
		Datebook datebook = new Datebook();
		Event event1 = new Event("EVENT 1", 1400);
		Event event2 = new Event("EVENT 2", 1200);
		datebook.addDailyEvent(event1);
		datebook.addDailyEvent(event2);
		datebook.addWeeklyEvent(event1, 4);
		datebook.addMonthlyEvent(event2, 26);
		datebook.addYearlyEvent(event1, 330);
		Date tomorrow = getDate(2014, Calendar.NOVEMBER, 26);
		List<Event> list2 = datebook.getEvents(tomorrow);
		Assert.assertEquals(5, list2.size());
		
		Datebook datebook2 = new Datebook();
		Event event3 = new Event("EVENT 1", 1400);
		Event event4 = new Event("EVENT 2", 1200);

		datebook2.addDailyEvent(event3);
		datebook2.addDailyEvent(event4);
		List<Event> list4 = datebook2.getEvents(tomorrow);
		Assert.assertEquals(event4, list4.get(0));
		Assert.assertEquals(event3, list4.get(1));
		
	}

}
