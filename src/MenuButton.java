package a9;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MenuButton extends JButton {
	private static final long serialVersionUID = -8231898214237386323L;

	public MenuButton(String label, ActionListener listener) {
		super(label);
		this.setFocusable(false);
		this.addActionListener(listener);
		this.setFont(new Font("Helvetica", 0, 16));
		this.setForeground(new Color(238, 238, 238));
		this.setBackground(new Color(77, 175, 124));
	}
}
