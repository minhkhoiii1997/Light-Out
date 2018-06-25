package a9;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class LightsOut extends JPanel {
	private static final long serialVersionUID = 3655706520549726425L;

	public LightsOut() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		NotificationPanel notifications = new NotificationPanel();
		this.add(notifications);

		JPanel subpanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
		BoardPanel board = new BoardPanel(notifications);
		MenuPanel menu = new MenuPanel(board, notifications);
		subpanel.add(board);
		subpanel.add(menu);
		subpanel.setBorder(new EmptyBorder(20, 0, 20, 0));
		this.add(subpanel);
	}

	public static void main(String[] args) {
		JFrame window = new JFrame("Lights Out");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		window.setContentPane(new LightsOut());
		window.pack();
		window.setVisible(true);
		window.setResizable(false);
	}
}
