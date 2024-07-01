import javax.swing.JFrame;

public class Home {
	public static void main(String[] args) {
		JFrame frm = new JFrame("Snake Auto - Chan with daddy chill");
		
		frm.setVisible(true);
		frm.setSize(500,500);
		
		Game g = new Game();
		frm.add(g);
	}
}
