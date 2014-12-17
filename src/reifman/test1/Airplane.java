package reifman.test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import reifman.test1.Seat;
import reifman.test1.UnknownSeatException;

public class Airplane {
	private Map<String, Seat> planeSeats;
	private int numSeats;
	private char[] alphabet;
	private ArrayList<Seat> seatsList;
	private int numRows;
	private String configuration;
	private int numSeatsPerRow;
	private int numAisles;

	// private Seat[][] seats2DArray;

	/**
	 * Construct a new Airplane with the specified configuration and number of
	 * rows. The configuration is a String with letters specifying a seat's
	 * position in the row and a "_" for the aisle.
	 * 
	 * For instance, an Airplane with configuration, ABC_DEFGH_JKL would be
	 * three seats, then an aisle, then 5 seats, then an aisle, then 3 seats.
	 * 
	 * @param configuration
	 * @param numRows
	 */
	public Airplane(String configuration, int numRows) {
		this.configuration = configuration;
		this.numRows = numRows;
		numAisles = 0;
		numSeatsPerRow = 0;
		planeSeats = new HashMap<String, Seat>();
		seatsList = new ArrayList<Seat>();

		alphabet = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
				'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
				'V', 'W', 'X', 'Y', 'Z' };
		for (int i = 0; i < configuration.length(); i++) {
			if (configuration.charAt(i) == '_') {
				numAisles++;
			} else {
				numSeatsPerRow++;
			}
		}
		this.numSeats = numSeatsPerRow * numRows;
		// seats2DArray = new Seat[numRows][numSeatsPerRow];
		String seatCode;
		int rowNumber = 0;
		char seatLetter = ' ';

		for (int i = 0; i < numRows; i++) {
			rowNumber++;

			for (int j = 0; j < numSeatsPerRow; j++) {
				seatLetter = alphabet[j];
				seatCode = Integer.toString(rowNumber) + seatLetter;
				Seat seat = new Seat(rowNumber, seatLetter);
				seatsList.add(seat);
				planeSeats.put(seatCode, seat);
				// seats2DArray[i][j] = seat;
			}
		}
	}

	/**
	 * @return the total number of EMPTy seats on the plane.
	 */
	public int getNumEmptySeats() {
		int numEmptySeats = 0;
		for (Seat seat : seatsList) {
			if (!seat.isOccupied()) {
				numEmptySeats++;
			}
		}
		return numEmptySeats;
	}

	/**
	 * @return true if the plane is full, otherwise false.
	 */
	public boolean isFull() {
		int numEmptySeats = 0;
		for (Seat seat : seatsList) {
			if (!seat.isOccupied()) {
				numEmptySeats++;
			}
		}
		return numEmptySeats == 0;
	}

	/**
	 * @param code
	 * @return true if the seat is occupied, otherwise false.
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public boolean isOccupied(String code) throws UnknownSeatException {
		return planeSeats.get(code).isOccupied();
	}

	/**
	 * Sets the seat as occupied/unoccupied
	 * 
	 * @param code
	 * @param occupied
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public void setOccupied(String code, boolean occupied)
			throws UnknownSeatException {
		for (Seat seat : seatsList) {
			if (code.equals(seat.getCode())) {
				seat.setOccupied(occupied);
				planeSeats.put(code, seat);
			}
		}

	}

	/**
	 * Set all seats by their codes as occupied
	 * 
	 * @param codes
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public void occupy(String... codes) throws UnknownSeatException {

		for (String code : codes) {
			if (planeSeats.containsKey(code)) {
				Seat seat1 = planeSeats.get(code);
				seat1.setOccupied(true);
			} else {
				throw new UnknownSeatException();
			}
		}

	}

	/**
	 * Sets all seats as occupied
	 * 
	 * @param seats
	 */
	public void occupy(List<Seat> seats) {
		for (Seat seat : seats) {
			seat.setOccupied(true);
			planeSeats.put(seat.getCode(), seat);
		}
	}

	/**
	 * Returns the seat specified by it's code
	 * 
	 * @param code
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public Seat getSeat(String code) throws UnknownSeatException {
		if (planeSeats.containsKey(code)) {
			return planeSeats.get(code);
		} else {
			throw new UnknownSeatException();
		}

	}

	/**
	 * @return total number of seats on the plane
	 */
	public int getNumSeats() {
		return numSeats;
	}

	/**
	 * Returns the Airplane specified in text format.
	 * 
	 * The first line should be the configuration, prepended by 4 spaces Each
	 * row in the plane gets a line which starts with The row number, padded
	 * with leading zeros so that is is always 3 digits. A space Then for each
	 * seat, either a "." for an empty seat, "O" for an occupied seat and "_"
	 * for an aisle.
	 * 
	 * Example. AB_CD_EF\n 001 .._.._..\n 002 .._.._..\n 003 .._.._..\n
	 * 
	 * 
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("    ");
		builder.append(configuration);
		builder.append("\n");
		int rowNumber = 1;
		int counter = 0;

		for (int i = 0; i < numRows; i++) {

			builder.append(String.format("%03d", rowNumber));

			builder.append(" ");

			for (int j = 0; j < numSeatsPerRow + numAisles; j++) {

				if (configuration.charAt(j) == '_') {
					builder.append("_");
				} else {
					Seat seat = seatsList.get(counter++);
					if (seat.isOccupied()) {
						builder.append("O");
					} else {
						builder.append(".");
					}
				}

			}
			builder.append("\n");
			rowNumber++;
		}
		return builder.toString();
	}

	/**
	 * 
	 * @param numSeatsTogeather
	 *            the number of seats to occupy.
	 * @return A list of occupied seats.
	 * @throws FullPlaneException
	 *             if the plane is full
	 * @throws NotEnoughSeatsTogeatherException
	 *             if there are not enough seats next to each other.
	 */
	public List<Seat> occupySeats(int numSeatsTogeather)
			throws FullPlaneException, NotEnoughSeatsTogeatherException {

		if (isFull()) {
			throw new FullPlaneException();
		}

		else {
			int seatsAvailable = 0;
			List<Seat> seatsToOccupy = new ArrayList<Seat>();
			int counter = 0;
			for (int i = 0; i < numRows; i++) {
				seatsAvailable = 0;
				seatsToOccupy = new ArrayList<Seat>();
				for (int j = 0; j < numSeatsPerRow + numAisles; j++) {

					Seat nextSeat = seatsList.get(counter);
					if (configuration.charAt(j) == '_') {
						seatsAvailable = 0;
						seatsToOccupy = new ArrayList<Seat>();
					} else if (!nextSeat.isOccupied()) {
						seatsToOccupy.add(nextSeat);
						seatsAvailable++;
						if (seatsAvailable == numSeatsTogeather) {
							break;
						}
						counter++;
					}

				}
				if (seatsAvailable == numSeatsTogeather) {
					break;
				}

			}
			if (seatsAvailable == numSeatsTogeather) {
				occupy(seatsToOccupy);
				return seatsToOccupy;
			} else {
				throw new NotEnoughSeatsTogeatherException();
			}
		}

	}

}
