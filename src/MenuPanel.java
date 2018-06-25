package a9;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 6955801778811333203L;
	private MenuButton reset, hint, solve, quit;
	private BoardPanel board;
	private NotificationPanel notifier;
	private static JLabel clickCounter = new JLabel(" Clicks: 0");
	private static int clicks;

	public MenuPanel(BoardPanel board, NotificationPanel notifier) {
		super();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.board = board;
		this.notifier = notifier;

		clickCounter.setFont(new Font("Helvetica", 0, 16));

		reset = new MenuButton("Reset", this);
		hint = new MenuButton("Hint", this);
		solve = new MenuButton("Solve", this);
		quit = new MenuButton("Quit", this);

		JPanel wrapper = new JPanel(new GridLayout(5, 1, 0, 10));
		wrapper.add(clickCounter);
		wrapper.add(reset);
		wrapper.add(hint);
		wrapper.add(solve);
		wrapper.add(quit);
		this.add(wrapper);
	}

	public static void updateClicks() {
		clicks++;
		clickCounter.setText(" Clicks: " + clicks);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuButton src = (MenuButton) e.getSource();
		if (src == quit) {
			System.exit(0);
		} else if (src == reset) {
			clicks = 0;
			clickCounter.setText(" Clicks: 0");
			board.randomizeBoard();
			notifier.clear();
		} else if (src == hint) {
			board.flashHint(false);
		} else if (src == solve) {
			board.flashHint(true);
		}

	}

}
