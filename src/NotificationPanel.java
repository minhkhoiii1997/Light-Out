package a9;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotificationPanel extends JPanel {
	private static final long serialVersionUID = -232289348578527243L;
	private JLabel notification;

	public NotificationPanel() {
		super();
		notification = new JLabel(" ");
		this.add(notification);
		this.setMaximumSize(new Dimension(10_000, 20)); // Limit Y-axis
		this.setForeground(new Color(21, 87, 36));
	}

	public void notify(String text) {
		this.setBackground(new Color(212, 237, 218));
		notification.setText(text);
		this.setVisible(true);
	}

	public void clear() {
		notification.setText("");
		this.setVisible(false);
	}
}
