import javax.swing.JFrame;

public class Home {
	public static void main(String[] args) {
		JFrame frm = new JFrame("Snake Automatic 31 ");
		
		frm.setVisible(true);
		frm.setSize(1000,500);
		
		Game g = new Game();
		frm.add(g);
	}
}
