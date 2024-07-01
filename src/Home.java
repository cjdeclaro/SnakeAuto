import javax.swing.JFrame;

public class Home {
	public static void main(String[] args) {
		JFrame frm = new JFrame("Snake Auto - Jermaine");
		
		frm.setVisible(true);
		frm.setSize(1000,1000);
		
		Game g = new Game();
		frm.add(g);
	}
}
