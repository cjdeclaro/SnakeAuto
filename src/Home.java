import javax.swing.*;

public class Home {
	public static void main(String[] args) {
		JFrame frm = new JFrame("Ahas ni Jalem");
		JButton jalemBtn = new JButton("btn");
		frm.setVisible(true);
		frm.setSize(500,500);
		
		Game g = new Game();
		frm.add(g);
	}
}
