package reifman.gameoflife;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GameOfLife extends JFrame {

	private static final int ROWS = 40;
	private static final int COLS = 40;
	private Cell cells[][];
	private JButton next;

	public GameOfLife() {

		setSize(800, 600);
		setTitle("Game Of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		next = new JButton("Next");
		container.add(next, BorderLayout.WEST);

		Container container2 = new Container();
		container.add(container2);
		container2.setLayout(new GridLayout(ROWS, COLS));

		ActionListener nextGen = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JButton next = (JButton) event.getSource();
				nextGeneration();
				setColors();
			}
		};

		next.addActionListener(nextGen);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button = (JButton) event.getSource();

				button.setBackground(Color.GREEN);
				button.setBorderPainted(false);
			}
		};

		Random random = new Random();
		cells = new Cell[COLS][ROWS];

		for (int i = 0; i < COLS; i++) {
			for (int j = 0; j < ROWS; j++) {
				final Cell button = new Cell();
				cells[i][j] = button;
				button.setOpaque(true);
				container2.add(button);

				button.addActionListener(listener);

				int n = random.nextInt(100);
				if (n < 30) {
					button.setAlive();
	
				} 
			}
		}
		setColors();
	}

	public void nextGeneration() {
		for (int i = 0; i < COLS; i++) {
			for (int j = 0; j < ROWS; j++) {
				int neighbors = getNumAliveNeighbors(i, j);
				switch (neighbors) {
				case 0:
				case 1:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
					cells[i][j].setDead();
					break;
				case 2:
					break;
				case 3:
					cells[i][j].setAlive();
					break;
				}
			}
		}

	}

	public void setColors() {
		for (int i = 0; i < COLS; i++) {
			for (int j = 0; j < ROWS; j++) {
				cells[i][j].setColor();
			}
		}
	}

	public int getNumAliveNeighbors(int i, int j) {
		int numAlive = 0;

		if (isAlive(i - 1, j - 1)) {
			numAlive++;
		}
		if (isAlive(i, j - 1)) {
			numAlive++;
		}
		if (isAlive(i + 1, j - 1)) {
			numAlive++;
		}
		if (isAlive(i - 1, j)) {
			numAlive++;
		}
		if (isAlive(i + 1, j)) {
			numAlive++;
		}
		if (isAlive(i - 1, j + 1)) {
			numAlive++;
		}
		if (isAlive(i, j + 1)) {
			numAlive++;
		}
		if (isAlive(i + 1, j + 1)) {
			numAlive++;
		}
		return numAlive;

	}

	private boolean isAlive(int i, int j) {
		try {
			return cells[i][j].getBackground() == Color.GREEN;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		GameOfLife game = new GameOfLife();
		game.setVisible(true);
	}

}
