package a9;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class BoardPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -8258561942096130007L;
	private LightButton[][] board = new LightButton[5][5];
	private NotificationPanel notifier;
	private boolean gameOver = false;

	public BoardPanel(NotificationPanel notifier) {
		super();
		this.setLayout(new GridLayout(5, 5));
		this.notifier = notifier;

		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				LightButton tile = new LightButton(row, col);
				tile.addActionListener(this);
				board[row][col] = tile;
				this.add(tile);
			}
		}

		randomizeBoard();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!gameOver) {
			MenuPanel.updateClicks();
			LightButton tile = (LightButton) e.getSource();
			toggle(tile);
		}
	}

	public void randomizeBoard() {
		clearBoard();
		gameOver = false;
		for (int i = 0; i < 10; i++) {
			int row = (int) (Math.random() * 4);
			int col = (int) (Math.random() * 4);
			toggle(board[row][col]);
		}
	}

	private void clearBoard() {
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				board[row][col].turnOff();
			}
		}
	}

	public void flashHint(boolean solve) {
		if (calculateLit() == 0) {
			gameOver = true;
			return;
		}
		// Chase the lights
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 5; col++) {
				if (board[row][col].isOn()) {
					LightButton tile = board[row + 1][col];
					if (solve) {
						solve(tile);
					} else {
						tile.flash();
					}
					return;
				}
			}
		}
		// Last row algorithm
		ArrayList<LightButton> hints = new ArrayList<LightButton>();
		LightButton B1 = board[0][1];
		LightButton D1 = board[0][3];
		LightButton E1 = board[0][4];

		if (board[4][0].isOn()) {
			hints.add(D1);
			hints.add(E1);
		}
		if (board[4][1].isOn()) {
			hints.add(B1);
			hints.add(E1);
		}
		if (board[4][2].isOn()) {
			hints.add(D1);
		}

		if (Collections.frequency(hints, B1) == 2) {
			hints.remove(B1);
			hints.remove(B1);
		}
		if (Collections.frequency(hints, D1) == 2) {
			hints.remove(D1);
			hints.remove(D1);
		}
		if (Collections.frequency(hints, E1) == 2) {
			hints.remove(E1);
			hints.remove(E1);
		}

		for (LightButton tile : hints) {
			if (solve) {
				tile.flash();
				toggle(tile);
			} else {
				tile.flash();
				return;
			}
		}
		if (solve)
			flashHint(true);
	}

	private void solve(LightButton tile) {
		tile.flash();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				toggle(tile);
				flashHint(true);
			}
		}, 750);
	}

	private void toggle(LightButton tile) {
		tile.toggleState();

		int row = tile.getRow();
		int col = tile.getCol();
		if (row + 1 < 5) {
			board[row + 1][col].toggleState();
		}
		if (row - 1 >= 0) {
			board[row - 1][col].toggleState();
		}
		if (col + 1 < 5) {
			board[row][col + 1].toggleState();
		}
		if (col - 1 >= 0) {
			board[row][col - 1].toggleState();
		}

		if (calculateLit() == 0) {
			notifier.notify("You won!");
		}
	}

	private int calculateLit() {
		int lit = 0;
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if (board[row][col].isOn()) {
					lit++;
				}
			}
		}
		return lit;
	}
}
