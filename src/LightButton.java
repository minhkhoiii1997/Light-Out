package a9;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class LightButton extends JButton {
	private static final long serialVersionUID = 7538783041509331531L;

	private boolean on = false;
	private int row;
	private int col;

	public LightButton(int row, int col) {
		super();
		this.setPreferredSize(new Dimension(50, 50));
		this.setFocusable(false);
		this.setBackground(Color.BLACK);

		this.row = row;
		this.col = col;
	}

	public boolean isOn() {
		return on;
	}

	public void toggleState() {
		on = !on;
		if (on) {
			this.setBackground(Color.WHITE);
		} else {
			this.setBackground(Color.BLACK);
		}
	}
	
	public void turnOff() {
		on = false;
		this.setBackground(Color.BLACK);
	}
	
	public void flash() {
		this.setBackground(Color.YELLOW);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				toggleState();
				toggleState();
			}
		}, 500);
	}
	
	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}
}
