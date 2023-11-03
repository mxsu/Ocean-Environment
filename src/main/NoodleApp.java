package main;

import javax.swing.JFrame;

//main class for starting the app, and window information
public class NoodleApp extends JFrame {
	/*
	 * 
	 */
	private static final long serialVersionUI = 1L;

	public NoodleApp(String title) {
		super(title);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		NoodlePanel bpnl = new NoodlePanel(this);
		this.add(bpnl);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new NoodleApp("Broke College Student Lunch");
	}
}
