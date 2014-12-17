package reifman.test2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Datebook holds Events
 * 
 * 
 * You can obtain the day of week, day of month and day of year for a particular
 * Date by using the following code.
 * 
 * Date date = ... ; Calendar calendar = Calendar.getInstance();
 * calendar.setTime(date); int dayOf = calendar.get(field);
 * 
 * Where field is one of Calendar.DAY_OF_YEAR, Calendar.DAY_OF_MONTH,
 * Calendar.DAY_OF_WEEK
 * 
 * Refer to the code in DatebookTest on how to construct a Date object.
 * 
 * Refer to documentation on the Calendar class
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 * 
 */

public class Datebook {

	private Map<Date, List<Event>> eventsOnADay;
	private List<Event> dailyEvents;
	private List<Event> allEvents;
	private Map<Integer, List<Event>> weeklyEvents;
	private Map<Integer, List<Event>> monthlyEvents;
	private Map<Integer, List<Event>> yearlyEvents;

	public Datebook() {
		eventsOnADay = new HashMap<Date, List<Event>>();
		allEvents = new ArrayList<Event>();
		dailyEvents = new ArrayList();
		weeklyEvents = new HashMap<Integer, List<Event>>();
		monthlyEvents = new HashMap<Integer, List<Event>>();
		yearlyEvents = new HashMap<Integer, List<Event>>();
	}

	/**
	 * Add a single Event to the Datebook for a particular date. This is a
	 * non-recurring event.
	 * 
	 * @param event
	 * @param date
	 */
	public void addSingleEvent(Event event, Date date) {
		allEvents.add(event);
		List<Event> events = new ArrayList<Event>();
		if (eventsOnADay.get(date) != null) {
			events.addAll(eventsOnADay.get(date));
		}
		events.add(event);

		eventsOnADay.put(date, events);

	}

	/**
	 * Adds an Event to a Datebook that is recurring every day. For instance, a
	 * wake up alarm.
	 */
	public void addDailyEvent(Event event) {
		dailyEvents.add(event);

	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every week.
	 * For instance, a class starts at the same time once a week.
	 * 
	 * @param dayOfWeek
	 *            This is a constant from the Calendar class. (ex.
	 *            Calendar.MONDAY, Calendar.TUESDAY...)
	 * 
	 */
	public void addWeeklyEvent(Event event, int dayOfWeek) {
		if (weeklyEvents.containsKey(dayOfWeek)) {
			weeklyEvents.get(dayOfWeek).add(event);
		} else {
			List<Event> events = new ArrayList<Event>();
			events.add(event);
			weeklyEvents.put(dayOfWeek, events);
		}
	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every month.
	 * For instance, you always get paid on the 1st and the 15th of the month.
	 * 
	 * @param dayOfMonth
	 *            this is the day of the month starting with 1
	 */
	public void addMonthlyEvent(Event event, int dayOfMonth) {
		if (monthlyEvents.containsKey(dayOfMonth)) {
			monthlyEvents.get(dayOfMonth).add(event);
		} else {
			List<Event> events = new ArrayList<Event>();
			events.add(event);
			monthlyEvents.put(dayOfMonth, events);
		}
	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every year.
	 * For instance, a birthday.
	 * 
	 * @param dayOfYear
	 *            this is the day of the year starting with 1 and ending with
	 *            365
	 */
	public void addYearlyEvent(Event event, int dayOfYear) {
		if (yearlyEvents.containsKey(dayOfYear)) {
			yearlyEvents.get(dayOfYear).add(event);
		} else {
			List<Event> events = new ArrayList<Event>();
			events.add(event);
			yearlyEvents.put(dayOfYear, events);
		}
	}

	/**
	 * 
	 * @return a List of Events for the specified date. The Events should be
	 *         sorted by their timeOfDay. If no events occur on that day then an
	 *         empty List should be returned.
	 */
	public List<Event> getEvents(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		List<Event> events = new ArrayList<Event>();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

		if (weeklyEvents.get(dayOfWeek) != null) {
			events.addAll(weeklyEvents.get(dayOfWeek));
		}

		if (monthlyEvents.get(dayOfMonth) != null) {
			events.addAll(monthlyEvents.get(dayOfMonth));
		}
		
		if (yearlyEvents.get(dayOfYear) != null) {
			events.addAll(yearlyEvents.get(dayOfYear));
		}

		if (dailyEvents != null) {
			events.addAll(dailyEvents);
		}

		if (eventsOnADay.get(date) != null) {
			events.addAll(eventsOnADay.get(date));
		}
		
		Collections.sort(events);

		return events;

	}

}
